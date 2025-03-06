abstract class Shape {

    protected String color;

    Shape(String color) {
        this.color = color;
    }

    abstract double getArea();

    void displayColor() {
        System.out.printf("Shape color: %s\n", this.color);
    }
}

class Circle extends Shape {

    private double radius;

    public Circle(String color, double radius){
        super(color);
        this.radius = radius;
    }

    @Override
    double getArea() {
        if (this.radius < 0) {
            return 0;
        }
        return (Math.pow(this.radius, 2) * Math.PI); 
    }
}

class Rectangle extends Shape {

    private double width, height;

    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    double getArea() {
        if (this. width < 0 || height < 0) {
            return 0;
        }
        return (this.width * this.height);
    }
}

public class Shapes {
    public static void main(String args[]) {
        Shape circ = new Circle("Green", 2.5);
        System.out.println(circ.getArea());
        Shape rect = new Rectangle("Red", 2, 4);
        System.out.println(rect.getArea());

        circ.displayColor();
        rect.displayColor();
    }
}