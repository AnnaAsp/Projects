public class RAM {
    private final RAMtype type;
    private final int volume;
    private final double weight;

    public RAM(RAMtype type, int volume, double weight) {
        this.type = type;
        this.volume = volume;
        this.weight = weight;
    }

    public RAMtype getType() {
        return type;
    }

    public int getVolume() {
        return volume;
    }

    public double getWeight() {
        return weight;
    }

    public RAM setNewRAM(RAMtype type, int volume, double weight) {
        return new RAM(type, volume, weight);
    }

    public String toString() {
        return "Оперативная память:\nтип: " + getType() + ", объем: " + getVolume() + ", вес: " + getWeight() + "\n";
    }
}
