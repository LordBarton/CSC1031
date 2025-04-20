import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Taxi {
    protected String taxiID;
    DispatchCenter center;
    public boolean avail;
    public boolean respond;

    public Taxi(String taxiID) {
        this.taxiID = taxiID;
        this.avail = false;
    }

    public void setAvailable(boolean available) {
        if (available == true) {
            System.out.println("Taxi " + taxiID + " is now available.");
            this.avail = true;
            center.isavailable();

            center.assigntaxi();

        }
        this.avail = false;
    }

    void respondToRide(boolean answer) {
        this.respond = answer;
        center.sendtaxi();
    }

    public static void main(String[] args) {
        DispatchCenter dispatchCenter = new DispatchCenter();

        Passenger alice = new Passenger("Alice");
        Passenger bob = new Passenger("Bob");

        Taxi taxi1 = new Taxi("Taxi-01");
        Taxi taxi2 = new Taxi("Taxi-02");
        Taxi taxi3 = new Taxi("Taxi-03");

        dispatchCenter.registerTaxi(taxi1);
        dispatchCenter.registerTaxi(taxi2);
        dispatchCenter.registerTaxi(taxi3);

        alice.requestRide("Airport", dispatchCenter);
        bob.requestRide("Downtown", dispatchCenter);

        taxi1.setAvailable(true);
        taxi2.setAvailable(true);
        taxi3.setAvailable(true);
        
        taxi1.respondToRide(true); // Accept the ride
        taxi2.respondToRide(false); // Reject the ride
        taxi3.respondToRide(true); // Accept the rejected ride
    }
}

class Passenger {
    protected String name;
    public String destination;

    public Passenger(String name) {
        this.name = name;
    }

    public void requestRide(String destination, DispatchCenter center) {
        System.out.println("Passenger " + this.name + " requested a ride to " + destination + ".");
        center.riderequest(this);
        this.destination = destination;
    }
}

class DispatchCenter {
    public Queue<Passenger> passengerQueue = new LinkedList<>();
    public Queue<Passenger> passengerQueue2 = new LinkedList<>();
    public Queue<Taxi> taxiQueue = new LinkedList<>();
    public ArrayList<Taxi> taxis = new ArrayList<>();

    void registerTaxi(Taxi taxi) {
        taxis.add(taxi);
        taxi.center = this;
    }

    void riderequest(Passenger pass) {
        passengerQueue.add(pass);
        passengerQueue2.add(pass);
    }

    void isavailable() {
        for (Taxi cab : taxis) {
            if (cab.avail == true) {
                taxiQueue.add(cab);
            }
            else if (cab.avail == false) {
                taxiQueue.remove(cab);
            }
        }
    }

    void assigntaxi() {
        if (!passengerQueue2.isEmpty() && !taxiQueue.isEmpty()){
            Taxi taxi = taxiQueue.poll();
            Passenger pass = passengerQueue2.poll();
            System.out.println("Dispatch assigned Taxi " + taxi.taxiID + " to passenger " + pass.name + ".");
        }
    }

    void sendtaxi() {
        for (Taxi t : taxiQueue) {
            if (t.respond == true) {
                t.avail = false;
                String destin = passengerQueue.poll().destination;
                System.out.println("Taxi " + t.taxiID + " accepted the ride to " + destin + ".");
                taxiQueue.remove(t);
                return;
            }
            else if (t.respond == false) {
                String destin = passengerQueue.peek().destination;
                System.out.println("Taxi " + t.taxiID + " rejected the ride to " + destin + ". Searching for another taxi...");
            }
        }
    }
}