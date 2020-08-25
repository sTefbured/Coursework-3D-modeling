package Geometry;

import java.util.Arrays;

public class Face {
    private Edge[] edges;

    public Face(Edge... edges) {
        this.edges = Arrays.copyOf(edges, edges.length);
    }

    public Edge[] getEdges() {
        return edges;
    }

    // TODO: maybe should be deleted
    public void setEdges(Edge[] edges) {
        this.edges = edges;
    }
}
