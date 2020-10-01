package coursework.geometry.parts;

public class Vertex {
    private final double[][] coordinates;

    //TODO: ask about "one", maybe delete it from constructor's params
    public Vertex(double x, double y, double z, double one) {
        coordinates = new double[][] {
                {x},
                {y},
                {z},
                {1}
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

    public double getOne() {
        return coordinates[3][0];
    }

    public void setOne(double one) {
        coordinates[3][0] = one;
    }
}
