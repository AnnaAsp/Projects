import java.util.SortedMap;

public class Main {
    public static void main(String[] args) {

        CPU cpu = new CPU(30,4, "Apple", 800);
        RAM ram = new RAM(RAMtype.DDR2, 8, 100);
        Memory memory = new Memory(MemoryType.SSD, 256, 100);
        Screen screen = new Screen("1288*800", ScreenType.IPS, 200);
        Keyboard keyboard = new Keyboard(KeyboardType.MECHANICAL, Backlight.YES, 200);

        Computer computer = new Computer("Apple", "Mac", cpu, ram, memory, screen, keyboard);

        computer.setCpu(cpu.setNewCPU(45, 2, "Intel", 900));
        computer.setRam(ram.setNewRAM(RAMtype.DDR3, 16, 150));


        Computer computer2 = new Computer("IBM", "laptop",
                new CPU(50, 4, "IBM", 1000),
                new RAM(RAMtype.DDR3, 8, 200),
                new Memory(MemoryType.HDD, 128, 300),
                new Screen("1900*1000", ScreenType.TN, 300),
                new Keyboard(KeyboardType.MAGNETIC, Backlight.NO, 300));

        System.out.println(computer + "\n\n" + computer2);




    }
}
