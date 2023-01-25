public class CPU {
    private final int frequency;
    private final int numberOfCores;
    private final String producer;
    private final double weight;

    public CPU(int frequency, int numberOfCores, String producer, double weight) {
        this.frequency = frequency;
        this.numberOfCores = numberOfCores;
        this.producer = producer;
        this.weight = weight;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getNumberOfCores() {
        return numberOfCores;
    }

    public String getProducer() {
        return producer;
    }

    public double getWeight() {
        return weight;
    }

    public CPU setNewCPU(int frequency, int numberOfCores, String producer, double weight) {
        return new CPU(frequency, numberOfCores, producer, weight);
    }

    public String toString() {
        return "Процессор:\nчастота: " + getFrequency() + ", количество ядер: " + getNumberOfCores() + ", производитель: "
                + getProducer() + ", вес: " + getWeight() + "\n";
    }

}
