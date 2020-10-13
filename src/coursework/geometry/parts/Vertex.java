package coursework.geometry.parts;

public class Vertex {
    private final double[][] coordinates;

    public Vertex(double x, double y, double z, double parameter) {
        coordinates = new double[][] {
                {x},
                {y},
                {z},
                {parameter}
        };
    }

    public Vertex(Vertex vertex) {
        double[][] coordinates = vertex.getCoordinates();
        this.coordinates = new double[][] {
                {coordinates[0][0]},
                {coordinates[1][0]},
                {coordinates[2][0]},
                {coordinates[3][0]}
        };
    }

    public double getX() {
        return coordinates[0][0];
    }

    public void setX(double x) {
        coordinates[0][0] = x;
    }

    public double getY() {
        return coordinates[1][0];
    }

    public void setY(double y) {
        coordinates[1][0] = y;
    }

    public double getZ() {
        return coordinates[2][0];
    }

    public void setZ(double z) {
        coordinates[2][0] = z;
    }

    public double[][] getCoordinates() {
        return coordinates;
    }

    public double getParameter() {
        return coordinates[3][0];
    }

    public void setParameter(double parameter) {
        coordinates[3][0] = parameter;
    }
}
