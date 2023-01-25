public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();

        basket.add("топор", 1000, 1, 500);
        basket.add("гвоздь", 20, 30, 100.2);

        Basket basket2 = new Basket();

        basket2.add("milk", 50, 1, 200);

        System.out.println(Basket.getTotalProductCount());
        System.out.println(Basket.getTotalCost());
        System.out.println(Basket.getAverageCost());
        System.out.println(Basket.getAverageBasketCost());
    }
}
