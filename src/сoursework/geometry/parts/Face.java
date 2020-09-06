package сoursework.geometry.parts;

import java.awt.*;
import java.util.Arrays;

public class Face {
    private final Edge[] edges;

    public Face(Edge... edges) {
        this.edges = Arrays.copyOf(edges, edges.length);
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
