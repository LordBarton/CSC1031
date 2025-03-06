interface Vehicle {
    void start();
    void stop();
} 

class Car implements Vehicle{

    @Override
    public void start() {
        System.out.println("Car is starting...");
    }
    
    @Override
    public void stop() {
        System.out.println("Car is stopping...");
    }
}

class Bicycle implements Vehicle{

    @Override
    public void start() {
        System.out.println("Bicycle is starting...");
    }
    
    @Override
    public void stop() {
        System.out.println("Bicycle is stopping...");
    }
}
public class Vehicles {
    public static void main(String[] args) {
        Vehicle car = new Car();
        car.start();
        car.stop();

        Vehicle bicycle = new Bicycle();
        bicycle.start();
        bicycle.stop();
    }
}
