package Geometry;

public class Edge {
    private Vertex[] vertices;

    public Edge(Vertex start, Vertex end) {
        this.vertices = new Vertex[] {
                start,
                end
        };
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    // TODO: maybe should be deleted
    public void setVertices(Vertex[] vertices) {
        this.vertices = vertices;
    }
}
