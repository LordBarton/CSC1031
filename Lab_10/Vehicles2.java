abstract class Vehicle {
    public String brand;
    public Engine engine;

    public Vehicle(String brand) {
        this.brand = brand;
    }

    abstract public void startEngine();
}

class Engine {
    public int horsePower;
    
    public Engine(int horsePower) {
        this.horsePower = horsePower;
    }
}

class Car extends Vehicle {
    public int numDoors;

    public Car(String brand, int numDoors, Engine engine) {
        super(brand);
        this.numDoors = numDoors;
        this.engine = engine;
    }

    public void startEngine() {
        System.out.println("Starting car with " + this.engine.horsePower + " horsepowers");
    }
}

class Bike extends Vehicle {
    public boolean hasCarrier;

    public Bike(String brand, boolean hasCarrier, Engine engine) {
        super(brand);
        this.hasCarrier = hasCarrier;
        this.engine = engine;
    }

    public void startEngine() {
        System.out.println("Starting bike with " + this.engine.horsePower + " horsepowers");
    }
}

class ElectricCar extends Car {
    public int batteryCapacity;

    public ElectricCar(String brand, int numDoors, int batteryCapacity, Engine engine) {
        super(brand, numDoors, engine);
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public void startEngine() {
        System.out.println("Starting electric car silently with " + this.engine.horsePower + " horsepowers");
    }
}
public class Vehicles2 {
    public static void main(String[] args) {
    }
}
