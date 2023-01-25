import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) {

      Dimensions dimensions = new Dimensions(100, 200, 50.2);
      Cargo cargo = new Cargo(dimensions, 2, "Санкт-Петербург, 5-я линия, д. 5", true,
              "5678А", true);

      Dimensions dimensionsCopy = dimensions.setNewDimensions(100, 100, 100);

      Cargo copy = cargo.setDimensions(dimensionsCopy);
      Cargo copy2 = cargo.setMass(5.2);
      Cargo copy3 = cargo.setDeliveryAddress("Санкт_петербург, ул. Боровая, д. 5");

      System.out.println(cargo + "\n");
      System.out.println(copy + "\n");
      System.out.println(copy2 + "\n");
      System.out.println(copy3);
   }
}
