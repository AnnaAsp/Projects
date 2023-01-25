public class Screen {
    private final String diagonal;
    private final ScreenType type;
    private final double weight;

    public Screen(String diagonal, ScreenType type, double weight) {
        this.diagonal = diagonal;
        this.type = type;
        this.weight = weight;
    }

    public String getDiagonal() {
        return diagonal;
    }

    public ScreenType getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public Screen setNewScreen(String diagonal, ScreenType type, double weight) {
        return new Screen(diagonal, type, weight);
    }

    public String toString() {
        return "Экран:\nдиагональ: " + getDiagonal() + ", тип: " + getType() + ", вес: " + getWeight() + "\n";
    }
}
