package Geometry.Parts;

public class Edge {
    private final Vertex[] vertices;

    public Edge(Vertex start, Vertex end) {
        vertices = new Vertex[] {
                start,
                end
        };
    }

    public Vertex[] getVertices() {
        return vertices;
    }
}
