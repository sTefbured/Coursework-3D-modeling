package coursework.geometry.parts;

import coursework.Main;

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
        int coordinate1, coordinate2, coordinate3, coordinate4;
        int xCenter = Main.getWindow().getDrawingPanel().getWidth() / 2;
        int yCenter = Main.getWindow().getDrawingPanel().getHeight() / 2;
        switch (projectionMode) {
            case XOZ_PROJECTION -> {
                coordinate1 = (int) vertices[0].getX();
                coordinate2 = (int) vertices[0].getZ();
                coordinate3 = (int) vertices[1].getX();
                coordinate4 = (int) vertices[1].getZ();
            }
            case ZOY_PROJECTION -> {
                coordinate1 = (int) vertices[0].getZ();
                coordinate2 = (int) vertices[0].getY();
                coordinate3 = (int) vertices[1].getZ();
                coordinate4 = (int) vertices[1].getY();
            }
            default -> {
                coordinate1 = (int) vertices[0].getX();
                coordinate2 = (int) vertices[0].getY();
                coordinate3 = (int) vertices[1].getX();
                coordinate4 = (int) vertices[1].getY();
            }
        }
        graphics2D.drawLine(coordinate1 + xCenter, -coordinate2 + yCenter,
                coordinate3 + xCenter, -coordinate4 + yCenter);
    }
}
