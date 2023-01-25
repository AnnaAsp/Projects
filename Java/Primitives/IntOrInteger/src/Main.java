public class Main {
    public static void main(String[] args) {


        for (char i = 'Ё'; i <= 'ё'; i++) {
            if ( i > 'Ё' && i < 'А' || i > 'я' && i < 'ё') {
                continue;
            } else {
                System.out.println((int) i + " - " + i);
            }
        }

    }
}
