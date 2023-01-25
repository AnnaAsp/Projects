import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "anjutaasp";
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT course_name, COUNT(course_name) / (MAX(MONTH(subscription_date)) - MIN(MONTH(subscription_date)) + 1) several_purchase " +
                    "FROM PurchaseList GROUP BY course_name");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("course_name") + " - "
                        + resultSet.getDouble("several_purchase"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
