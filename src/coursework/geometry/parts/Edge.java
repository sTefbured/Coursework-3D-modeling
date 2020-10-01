package coursework.geometry.parts;

import coursework.Main;

import java.awt.*;

public class Edge {
    private final Vertex[] vertices;

    public Edge(Vertex start, Vertex end) {
        vertices = new Vertex[] {
                start,
                end
        };
    }

    // TODO: try to find the most suitable values instead of 1200 and 2000
    // TODO: remake perspective
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawLine((int) vertices[0].getX()
                        + Main.getWindow().getWidth() / 2,
                   (int) -vertices[0].getY()
                           + Main.getWindow().getHeight() / 2,
                   (int) vertices[1].getX()
                           + Main.getWindow().getWidth() / 2,
                   (int) -vertices[1].getY()
                           + Main.getWindow().getHeight() / 2);
    }
}
