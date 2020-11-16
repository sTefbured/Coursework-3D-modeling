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

    public void draw(Graphics2D graphics2D, int projectionMode) {
        int[] coordinates = new int[4];
        int xCenter = Main.getWindow().getDrawingPanel().getWidth() / 2;
        int yCenter = Main.getWindow().getDrawingPanel().getHeight() / 2;
        switch (projectionMode) {
            case OBLIQUE_PROJECTION -> {
                double[] startCoordinates = Transformations.getObliqueCoordinates(vertices[0]);
                double[] endCoordinates = Transformations.getObliqueCoordinates(vertices[0]);
                coordinates[0] = (int) startCoordinates[0];
                coordinates[1] = (int) startCoordinates[1];
                coordinates[2] = (int) endCoordinates[0];
                coordinates[3] = (int) endCoordinates[1];
            }
            case UP_PROJECTION -> {
                coordinates[0] = (int) vertices[0].getX();
                coordinates[1] = (int) vertices[0].getZ();
                coordinates[2] = (int) vertices[1].getX();
                coordinates[3] = (int) vertices[1].getZ();
            }
            case SIDE_PROJECTION -> {
                coordinates[0] = (int) vertices[0].getZ();
                coordinates[1] = (int) vertices[0].getY();
                coordinates[2] = (int) vertices[1].getZ();
                coordinates[3] = (int) vertices[1].getY();
            }
            default -> {
                coordinates[0] = (int) vertices[0].getX();
                coordinates[1] = (int) vertices[0].getY();
                coordinates[2] = (int) vertices[1].getX();
                coordinates[3] = (int) vertices[1].getY();
            }
        }
        graphics2D.drawLine(coordinates[0] + xCenter, -coordinates[1] + yCenter,
                coordinates[2] + xCenter, -coordinates[3] + yCenter);
        graphics2D.fillOval(coordinates[0] + xCenter - 5, -coordinates[1] + yCenter - 5, 10, 10);
        graphics2D.fillOval(coordinates[2] + xCenter - 5, -coordinates[3] + yCenter - 5, 10, 10);
    }
}
