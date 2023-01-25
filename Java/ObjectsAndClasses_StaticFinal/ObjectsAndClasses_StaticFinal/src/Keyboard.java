public class Keyboard {
    private final KeyboardType type;
    private final Backlight backlight;
    private final double weight;

    public Keyboard(KeyboardType type, Backlight backlight, double weight) {
        this.type = type;
        this.backlight = backlight;
        this.weight = weight;
    }

    public KeyboardType getType() {
        return type;
    }

    public Backlight getBacklight() {
        return backlight;
    }

    public double getWeight() {
        return weight;
    }

    public Keyboard setNewKeyboard() {
        return new Keyboard(type, backlight, weight);
    }

    public String toString() {
        return "Клавиатура:\nтип: " + getType() + ", наличие подвески: " + getBacklight() + ", вес: " + getWeight() + "\n";
    }
}

