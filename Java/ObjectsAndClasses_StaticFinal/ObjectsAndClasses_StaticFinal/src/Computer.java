public class Computer {
    private CPU cpu;
    private RAM ram;
    private Memory memory;
    private Screen screen;
    private Keyboard keyboard;
    private final String vendor;
    private final String name;

    public Computer(String vendor, String name, CPU cpu, RAM ram, Memory memory, Screen screen, Keyboard keyboard) {
        this.cpu = cpu;
        this.ram = ram;
        this.memory = memory;
        this.screen = screen;
        this.keyboard = keyboard;
        this.vendor = vendor;
        this.name = name;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public CPU getCpu() {
        return cpu;
    }

    public RAM getRam() {
        return ram;
    }

    public Memory getMemory() {
        return memory;
    }

    public Screen getScreen() {
        return screen;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public String getVendor() {
        return vendor;
    }

    public String getName() {
        return name;
    }

    public double computerWeight() {
        return getCpu().getWeight() + getRam().getWeight() + getMemory().getWeight() + getScreen().getWeight()
                + getKeyboard().getWeight();
    }

    public String toString() {
        return vendor + ", " + name + "\n" + cpu + ram + memory + screen + keyboard + "\nмасса компьютера: " + computerWeight();
    }


}
