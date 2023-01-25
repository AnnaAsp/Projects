public class Memory {
    private final MemoryType type;
    private final int memoryVolume;
    private final double weight;

    public Memory(MemoryType type, int memoryVolume, double weight) {
        this.type = type;
        this.memoryVolume = memoryVolume;
        this.weight = weight;
    }

    public MemoryType getType() {
        return type;
    }

    public int getMemoryVolume() {
        return memoryVolume;
    }

    public double getWeight() {
        return weight;
    }

    public Memory setNewMemory() {
        return new Memory(type, memoryVolume, weight);
    }

    public String toString() {
        return "Накопитель информации:\nтип: " + getType() + ", объем памяти: " + getMemoryVolume() + ", вес: "
                + getWeight() + "\n";
    }
}
