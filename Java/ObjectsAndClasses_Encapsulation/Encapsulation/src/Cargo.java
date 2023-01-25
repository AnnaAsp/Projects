public class Cargo {
    private final Dimensions dimensions;
    private final double mass;
    private final String deliveryAddress;
    private final boolean noTurnOver;
    private final String registrationNumber;
    private final boolean fragile;

    public Cargo(Dimensions dimensions, double mass, String deliveryAddress, boolean noTurnOver,
                 String registrationNumber, boolean fragile) {
        this.dimensions = dimensions;
        this.mass = mass;
        this.deliveryAddress = deliveryAddress;
        this.noTurnOver = noTurnOver;
        this.registrationNumber = registrationNumber;
        this.fragile = fragile;
    }

    public Dimensions getDimensions() {
        return (dimensions);
    }

    public double getMass() {
        return mass;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public boolean isNoTurnOver() {
        return noTurnOver;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public boolean isFragile() {
        return fragile;
    }

    public Cargo setDeliveryAddress(String deliveryAddress) {
        return new Cargo(dimensions, mass, deliveryAddress, noTurnOver, registrationNumber, fragile);
    }

    public Cargo setDimensions(Dimensions dimensions) {
        return new Cargo(dimensions, mass, deliveryAddress, noTurnOver, registrationNumber, fragile);
    }

    public Cargo setMass(double mass) {
        return new Cargo(dimensions, mass, deliveryAddress, noTurnOver, registrationNumber, fragile);
    }

    public String toString() {
        return getDimensions() + "\n" + getMass() + "\n" + getDeliveryAddress() + "\n" + isNoTurnOver()
                + "\n" + getRegistrationNumber() + "\n" + isFragile();
    }

}