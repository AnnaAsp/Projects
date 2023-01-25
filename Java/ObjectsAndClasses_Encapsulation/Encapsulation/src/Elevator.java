public class Elevator {
    private int currentFloor = 1;
    private int minFloor;
    private int maxFloor;

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void moveDown() {
        if (currentFloor > minFloor) {
            currentFloor = currentFloor - 1;
        }
    }

    public void moveUp() {
        if (currentFloor < maxFloor) {
            currentFloor = currentFloor + 1;
        }
    }

    public void move(int floor) {
        if (floor > maxFloor || floor < minFloor) {
            System.out.println("Что-то пошло не так :(");
        } else if (floor == currentFloor) {
            System.out.println("");
        } else if (floor > currentFloor) {
            System.out.println(getCurrentFloor());
            while (floor >= currentFloor) {
                moveUp();
                System.out.println(getCurrentFloor());
                if (floor == currentFloor) {
                    break;
                }
            }
        } else if (floor < currentFloor) {
            System.out.println(getCurrentFloor());
            while (floor <= currentFloor) {
                moveDown();
                System.out.println(getCurrentFloor());
                if (floor == currentFloor) {
                    break;
                }
            }
        }
    }


}
