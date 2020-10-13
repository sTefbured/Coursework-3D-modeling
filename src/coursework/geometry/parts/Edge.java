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

    // TODO: remake perspective
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawLine((int) vertices[0].getX()
                        + Main.getWindow().getDrawingPanel().getWidth() / 2,
                   (int) -vertices[0].getY()
                           + Main.getWindow().getDrawingPanel().getHeight() / 2,
                   (int) vertices[1].getX()
                           + Main.getWindow().getDrawingPanel().getWidth() / 2,
                   (int) -vertices[1].getY()
                           + Main.getWindow().getDrawingPanel().getHeight() / 2);
    }
}
