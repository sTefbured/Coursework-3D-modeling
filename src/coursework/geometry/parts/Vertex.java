package coursework.geometry.parts;

public class Vertex {
    private final double[][] coordinates;

    public Vertex(double x, double y, double z, double parameter) {
        coordinates = new double[][] {
                {x, y, z, parameter}
        };
    }

    public Vertex(Vertex vertex) {
        double[][] coordinates = vertex.getCoordinates();
        this.coordinates = new double[][] {
                {coordinates[0][0], coordinates[0][1], coordinates[0][2], coordinates[0][3]}
        };
    }

    public double getX() {
        return coordinates[0][0];
    }

    public void setX(double x) {
        coordinates[0][0] = x;
    }

    public double getY() {
        return coordinates[0][1];
    }

    public void setY(double y) {
        coordinates[0][1] = y;
    }

    public double getZ() {
        return coordinates[0][2];
    }

    public void setZ(double z) {
        coordinates[0][2] = z;
    }

    public double[][] getCoordinates() {
        return coordinates;
    }

    public double getParameter() {
        return coordinates[0][3];
    }

    public void setParameter(double parameter) {
        coordinates[0][3] = parameter;
    }
}
