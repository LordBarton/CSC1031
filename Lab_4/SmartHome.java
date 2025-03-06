public class SmartHome {
    
    static class Appliance{
        private String brand;
        private double powerConsumption;
        
        Appliance(String brand, double powerConsumption) {
            this.brand = brand;
            this.powerConsumption = powerConsumption;
        }

        public String getBrand() {
            return brand;
        }
        
        public double getPowerConsumption() {
            return powerConsumption;
        }

        public void setBrand(String newBrand) {
            this.brand = newBrand;
        }

        public void setPowerConsumption(double newCon) {
            this.powerConsumption = newCon;
        }

        public void turnOn() {
            System.out.printf("Turning on %s appliance\n", this.brand);
        }

        public void turnOff() {
            System.out.printf("Turning off %s appliance\n", this.brand);
        }
    }

    static class WashingMachine extends Appliance {
        private int drumSize;

        WashingMachine(String brand, double powerConsumption, int drumSize) {
            super(brand, powerConsumption);
            this.drumSize = drumSize;
        }

        public void washClothes() {
            System.out.printf("Washing clothes in a %s washing machine\n", this.getBrand());
        }

        public int getDrumSize() {
            return this.drumSize;
        }
    }

    static class Refrigerator extends Appliance {
        private double temperature;

        Refrigerator(String brand, double powerConsumption, double temperature) {
            super(brand, powerConsumption);
            this.temperature = temperature;
        }

        public void coolItems() {
            System.out.printf("Cooling items in a %s refrigerator at %.1f°C\n", this.getBrand(), this.getTemperature());
        }

        public void setTemperature(double newTemp) {
            this.temperature = newTemp;
        }

        public double getTemperature() {
            return this.temperature;
        }
    }

    static class SmartWashingMachine extends WashingMachine {
        private boolean hasWiFi;

        SmartWashingMachine(String brand, int powerConsumption, int drumSize, boolean hasWiFi) {
            super(brand, powerConsumption, drumSize);
            this.hasWiFi = hasWiFi;
        }

        public void connectToWiFi() {
            if (this.hasWiFi == true) {
                System.out.println("Smart Washing Machine connected to WiFi");
            } else {
                System.out.println("This washing machine does not support WiFi");
            }
        }

        public boolean hasWiFi() {
            return this.hasWiFi;
        }
    }
    public static void main(String[] args)
    {
        WashingMachine wm = new WashingMachine("LG", 500, 7);
        Refrigerator fridge = new Refrigerator("Samsung", 200, 4.5);
        SmartWashingMachine smartWM = new SmartWashingMachine("Bosch", 600, 9, true);

        wm.turnOn();
        wm.washClothes();
        wm.turnOff();

        fridge.turnOn();
        fridge.coolItems();
        fridge.turnOff();

        smartWM.turnOn();
        smartWM.washClothes();
        smartWM.connectToWiFi();
        smartWM.turnOff();
    }
}
