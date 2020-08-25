package Coursework.Geometry.Parts;

import java.util.Arrays;

public class Face {
    private final Edge[] edges;

    public Face(Edge... edges) {
        this.edges = Arrays.copyOf(edges, edges.length);
    }

    public Edge[] getEdges() {
        return edges;
    }
}