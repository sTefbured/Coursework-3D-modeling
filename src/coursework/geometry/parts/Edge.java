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
    public void draw(Graphics2D g) {
        for (Vertex vertex : vertices) {
            if (vertex.getZ() + 2000 < 0) {
                return;
            }
        }
        g.drawLine((int) (vertices[0].getX() * 1200 / (2000 + vertices[0].getZ())) + Main.getWindow().getWidth() / 2,
                   (int) -(vertices[0].getY() * 1200 / (2000 + vertices[0].getZ())) + Main.getWindow().getHeight() / 2,
                   (int) (vertices[1].getX() * 1200 / (2000 + vertices[1].getZ())) + Main.getWindow().getWidth() / 2,
                   (int) -(vertices[1].getY() * 1200 / (2000 + vertices[1].getZ()))
                           + Main.getWindow().getHeight() / 2);
    }
}
