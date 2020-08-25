package Geometry.Parts;

// TODO: maybe should delete set() methods

public class Vertex {
    private double x;
    private double y;
    private double z;
    private double one;

    public Vertex(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.one = 1;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getOne() {
        return one;
    }

    public void setOne(double one) {
        this.one = one;
    }
}
