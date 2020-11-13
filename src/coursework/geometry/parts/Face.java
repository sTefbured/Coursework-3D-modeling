package coursework.geometry.parts;

import java.awt.*;

public class Face implements Projections {
    private final Edge[] edges;

    public Face(Edge... edges) {
        this.edges = edges;
    }

    public void draw(Graphics2D graphics2D, int projectionMode) {
        for (Edge edge : edges) {
            edge.draw(graphics2D, projectionMode);
        }
    }
}
