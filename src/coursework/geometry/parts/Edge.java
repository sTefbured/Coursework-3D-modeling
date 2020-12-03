package coursework.geometry.parts;

import coursework.Main;
import coursework.geometry.utils.Transformations;

import java.awt.*;

public class Edge implements Projections {
    private final Vertex[] vertices;

    public Edge(Vertex start, Vertex end) {
        vertices = new Vertex[] {
                start,
                end
        };
    }

    private Edge(Vertex[] vertices) {
        this.vertices = vertices;
    }

    public void draw(Graphics2D graphics2D, int projectionMode) {
        int[] coordinates = new int[4];
        int xCenter = Main.getWindow().getDrawingPanel().getWidth() / 2;
        int yCenter = Main.getWindow().getDrawingPanel().getHeight() / 2;
        switch (projectionMode) {
            case UP_PROJECTION ->
                    setXZCoordinates(coordinates,
                                     vertices[0].getCoordinates()[0],
                                     vertices[1].getCoordinates()[0]);
            case SIDE_PROJECTION ->
                    setZYCoordinates(coordinates,
                                     vertices[0].getCoordinates()[0],
                                     vertices[1].getCoordinates()[0]);
            default ->
                    setXYCoordinates(coordinates,
                                     vertices[0].getCoordinates()[0],
                                     vertices[1].getCoordinates()[0]);
        }
        graphics2D.drawLine(coordinates[0] + xCenter, -coordinates[1] + yCenter,
                coordinates[2] + xCenter, -coordinates[3] + yCenter);
        graphics2D.fillOval(coordinates[0] + xCenter - 5,
                -coordinates[1] + yCenter - 5, 10, 10);
        graphics2D.fillOval(coordinates[2] + xCenter - 5,
                -coordinates[3] + yCenter - 5, 10, 10);
    }

    private void setXYCoordinates(int[] outCoordinates,
                                  double[] startCoordinates,
                                  double[] endCoordinates) {
        outCoordinates[0] = (int) startCoordinates[0];
        outCoordinates[1] = (int) startCoordinates[1];
        outCoordinates[2] = (int) endCoordinates[0];
        outCoordinates[3] = (int) endCoordinates[1];
    }

    private void setXZCoordinates(int[] outCoordinates,
                                  double[] startCoordinates,
                                  double[] endCoordinates) {
        outCoordinates[0] = (int) startCoordinates[0];
        outCoordinates[1] = (int) startCoordinates[2];
        outCoordinates[2] = (int) endCoordinates[0];
        outCoordinates[3] = (int) endCoordinates[2];
    }

    private void setZYCoordinates(int[] outCoordinates,
                                  double[] startCoordinates,
                                  double[] endCoordinates) {
        outCoordinates[0] = (int) startCoordinates[2];
        outCoordinates[1] = (int) startCoordinates[1];
        outCoordinates[2] = (int) endCoordinates[2];
        outCoordinates[3] = (int) endCoordinates[1];
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Edge\n");
        for (Vertex vertex : vertices) {
            builder.append("\t\t").append(vertex.toString()).append("\n");
        }
        return builder.toString();
    }

//    public Edge getCopy() {
//        Vertex[] copiedVertices = new Vertex[vertices.length];
//        for (int i = 0; i < vertices.length; i++) {
//            copiedVertices[i] = vertices[i].getCopy();
//        }
//        return new Edge(copiedVertices);
//    }
}
