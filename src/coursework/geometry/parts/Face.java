package coursework.geometry.parts;

import java.awt.*;

public class Face {
    private final Edge[] edges;

    public Face(Edge... edges) {
        this.edges = edges;
    }

    public void draw(Graphics2D graphics2D) {
        for (Edge edge : edges) {
            edge.draw(graphics2D);
        }
    }

    public Edge[] getEdges() {
        return edges;
    }
}
