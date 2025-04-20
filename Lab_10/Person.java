interface SpecialFunctionality {
    void fire();
}

public class Person implements SpecialFunctionality{
    String name;
    int age;
    String address;

    public Person(String name, int age, String address) {
        System.out.println("Person constructor");
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public void fire() {
        this.address = "Fired";
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Address: " + address);
    }

    public static void main(String[] args) {

    }
}

class Worker extends Person {
    private String workerID;
    
    public Worker(String name, int age, String address, String workerID) {
        super(name, age, address);
        this.workerID = workerID;
    }

    public void updateWorkerInfo(String address) {
        this.address = address;
    }

    public void updateWorkerInfo(int age) {
        this.age = age;
    }

    public void updateWorkerInfo(String address, int age) {
        this.address = address;
        this.age = age;
    }

    @Override
    public void fire() {
        super.fire();
        System.out.println("Worker " + this.workerID + " has been fired!");
    }


}