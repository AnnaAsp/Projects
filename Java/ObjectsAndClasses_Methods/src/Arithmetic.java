public class Arithmetic {
    private int a = 0;
    private int b = 0;

    public Arithmetic(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int sum() {
        return a + b;
    }

    public int multipl() {
        return a * b;
    }

    public int max() {
        return (a >= b) ? a : b;
    }

    public int min() {
        return (a <= b) ? a : b;
    }
}
