import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.Files.walk;

public class Main {
    public static void main(String[] args) {
        File folder = new File("output");
        if (!folder.exists()) {
            folder.mkdir();
        }
        try {
            FileWriter file = new FileWriter("output/map.json");
            file.write(getMapJSON().toJSONString());
            file.flush();
            file.close();
            FileWriter file2 = new FileWriter("output/stations.json");
            file2.write(getStationsJSON().toJSONString());
            file2.flush();
            file2.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();
        JSONObject jsonData = new JSONObject();
        try {
            List<String> lines = Files.readAllLines(Paths.get("output/map.json"));
            lines.forEach(line -> builder.append(line + "\n"));
            JSONParser parser = new JSONParser();
            jsonData = (JSONObject) parser.parse(builder.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JSONObject stationsObject = (JSONObject) jsonData.get("stations");
        stationsObject.keySet().forEach(lineNumberObject ->
        {
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
            System.out.println("Количество станций на линии " + lineNumberObject + ": " + stationsArray.size());
        });



    }


    public static Document readHTML() {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://skillbox-java.github.io/").get();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return doc;
    }

    public static List<String> searchFiles(String path) {
        List<String> fileList = new ArrayList<>();
        try {
            Files.walk(Paths.get(path))
                    .filter(file -> file.toString().endsWith("json") || file.toString().endsWith("csv"))
                    .forEach(file -> fileList.add(file.toString()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return fileList;
    }

    public static JSONArray readJSON(String path) {
        StringBuilder builder = new StringBuilder();
        JSONArray jsonData = new JSONArray();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> builder.append(line + "\n"));
            JSONParser parser = new JSONParser();
            jsonData = (JSONArray) parser.parse(builder.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jsonData;
    }

    public static List<String[]> readCSV(String path) {
        List<String> lines = new ArrayList<>();
        List<String[]> allLines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        lines.forEach(line ->{
            String[] strings = line.split(",", 2);
            allLines.add(strings);
        });
        return allLines;
    }

    public static JSONObject getMapJSON() {
        Elements allStrings = readHTML().getAllElements();
        JSONObject line = new JSONObject();
        JSONArray lines = new JSONArray();
        for (int i = 0; i < allStrings.size(); i++) {
            Pattern pattern = Pattern.compile("data-line=");
            Matcher matcher = pattern.matcher(allStrings.get(i).toString());
            Pattern pattern2 = Pattern.compile("single-station");
            Matcher matcher2 = pattern2.matcher(allStrings.get(i).toString());
            String lineNumber = "";
            JSONArray stations = new JSONArray();
            if (matcher.find()) {
                lineNumber = allStrings.get(i).attr("data-line");
                String name = allStrings.get(i).select("span[data-line]").text();
                JSONObject lineInfo = new JSONObject();
                if (!lineNumber.isBlank() && !name.isBlank()) {
                    lineInfo.put("number", lineNumber);
                    lineInfo.put("name", name);
                    lines.add(lineInfo);
                }
            }
            if (matcher2.find()) {
                Elements strings = allStrings.get(i).children();
                strings.forEach(string -> stations.add(string.select("p.single-station").text()
                        .replaceAll("\\d+\\.\\s", "")));
            }
            if (!lineNumber.isBlank() && !stations.isEmpty()) {
                line.put(lineNumber, stations);
            }
        }
        JSONObject mapJSON = new JSONObject();
        mapJSON.put("stations" , line);
        mapJSON.put("lines", lines);
        return mapJSON;
    }

    public static JSONObject getStationsJSON() {
        List<String> fileList = searchFiles("data");
        JSONArray stationsInfo = new JSONArray();
        JSONObject map = getMapJSON();
        JSONObject stationsObject = (JSONObject) map.get("stations");
        stationsObject.keySet().forEach(lineNumberObject ->
        {
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
            stationsArray.forEach(stationObject ->
            {
                JSONObject station = new JSONObject();
                stationsInfo.add(station);
                station.put("name", stationObject);
                JSONArray linesArray = (JSONArray) map.get("lines");
                linesArray.forEach(lineObject -> {
                    JSONObject lineJsonObject = (JSONObject) lineObject;
                    if (lineJsonObject.get("number").equals(lineNumberObject)) {
                        station.put("line", lineJsonObject.get("name"));
                    }
                });
                Elements stations = readHTML().select("p.single-station");
                for (int i = 0; i < stations.size(); i++) {
                    if (stations.get(i).text().replaceAll("\\d+\\.\\s", "").equals(stationObject)
                            && stations.get(i).select("span[title]").attr("title").matches("переход.+")) {
                        station.put("hasConnection", true);
                    } else if (stations.get(i).text().replaceAll("\\d+\\.\\s", "").equals(stationObject)
                            && !stations.get(i).select("span[title]").attr("title").matches("переход.+")) {
                        station.put("hasConnection", false);
                    }
                }
                for (String file : fileList) {
                    if (file.matches(".+dates.+json")) {
                        JSONArray dates = readJSON(file);
                        dates.forEach(date -> {
                            JSONObject dateObject = (JSONObject) date;
                            if (dateObject.get("name").equals(stationObject)) {
                                station.put("date", dateObject.get("date"));
                            }
                        });
                    }
                    if (file.matches(".+depths.+json")) {
                        JSONArray depths = readJSON(file);
                        depths.forEach(depth -> {
                            JSONObject depthObject = (JSONObject) depth;
                            if (depthObject.containsKey("name")) {
                                if (depthObject.get("name").equals(stationObject)) {
                                    if (!depthObject.get("depth").equals("?")) {
                                        String depthMeters = depthObject.get("depth").toString()
                                                .replaceAll("−", "-")
                                                .replaceAll(",", ".");
                                        int meters = (int) Math.round(Double.parseDouble(depthMeters));
                                        station.put("depth", meters);
                                    }
                                }
                            }
                            if (depthObject.containsKey("station_name")) {
                                if (depthObject.get("station_name").equals(stationObject)) {
                                    if (!depthObject.get("depth_meters").equals("?")) {
                                        String depthMeters = depthObject.get("depth_meters").toString()
                                                .replaceAll("−", "-")
                                                .replaceAll(",", ".");
                                        int meters = (int) Math.round(Double.parseDouble(depthMeters));
                                        station.put("depth", meters);
                                    }
                                }
                            }
                        });
                    }
                    if (file.matches(".+csv")) {
                        List<String[]> stationInfoList = readCSV(file);
                        stationInfoList.stream().filter(stationInfo -> stationInfo.length == 2)
                                .filter(stationInfo -> stationInfo[0].equals(stationObject))
                                .forEach(stationInfo -> {
                                    if (stationInfo[1].matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
                                        station.put("date", stationInfo[1]);
                                    } else if (!stationInfo[1].matches("\\d{2}\\.\\d{2}\\.\\d{4}")
                                            && !stationInfo[1].equals("?")) {
                                        String depthMeters = stationInfo[1].replaceAll("−", "-")
                                                .replaceAll(",", ".").replaceAll("\"", "");
                                        station.put("depth", (int) Math.round(Double.parseDouble(depthMeters)));
                                    }
                                });
                    }
                }


            });
        });
        JSONObject stations = new JSONObject();
        stations.put("stations", stationsInfo);
        return stations;
    }



}



