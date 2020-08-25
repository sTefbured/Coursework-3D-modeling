import Exceptions.WrongCountException;
import Geometry.*;

// TODO: PAY ATTENTION TO THE PYRAMID. MAYBE SHOULD
//  DECLARE SOME POINTS IN OTHER WAY

public class Model {
    private final int VERTICES_COUNT = 12;
    private final int EDGES_COUNT = 18;
    private final int FACES_COUNT = 10;

    private Vertex[] vertices;
    private Edge[] edges;
    private Face[] faces;

    public Model(double a, double b, double c, double h, double d)
            throws WrongCountException {
        initializeVertices(a, b, c, h, d);
        initializeEdges(vertices);
        initializeFaces(edges);
    }

    private void initializeVertices(double a,
                                    double b,
                                    double c,
                                    double h,
                                    double d) {
        vertices = new Vertex[]{
                // Parallelepiped vertices
                new Vertex(-a / 2, 0, -c / 2),
                new Vertex(-a / 2, b, -c / 2),
                new Vertex(a / 2, b, -c / 2),
                new Vertex(a / 2, 0, -c / 2),
                new Vertex(a / 2, 0, c / 2),
                new Vertex(a / 2, b, c / 2),
                new Vertex(-a / 2, b, c / 2),
                new Vertex(-a / 2, 0, c / 2),
                // Pyramid vertices
                new Vertex(0, -h, 0),
                new Vertex(-d / 2, 0, d * Math.sqrt(3) / 6),
                new Vertex(d / 2, 0, d * Math.sqrt(3) / 6),
                new Vertex(0, 0, d * Math.sqrt(3) / 3)
        };
    }

    private void initializeEdges(Vertex[] vertices)
            throws WrongCountException {
        if (vertices.length != VERTICES_COUNT) {
            throw new WrongCountException("Wrong vertices count.");
        }
        edges = new Edge[] {
                new Edge(vertices[0], vertices[1]), // Front edges
                new Edge(vertices[1], vertices[2]),
                new Edge(vertices[2], vertices[3]),
                new Edge(vertices[3], vertices[0]),
                new Edge(vertices[4], vertices[5]), // Back edges
                new Edge(vertices[5], vertices[6]),
                new Edge(vertices[6], vertices[7]),
                new Edge(vertices[7], vertices[4]),
                new Edge(vertices[0], vertices[7]), // Middle edges
                new Edge(vertices[1], vertices[6]),
                new Edge(vertices[2], vertices[5]),
                new Edge(vertices[3], vertices[4]),
                new Edge(vertices[8], vertices[9]), // Pyramid edges
                new Edge(vertices[9], vertices[10]),
                new Edge(vertices[10], vertices[8]),
                new Edge(vertices[8], vertices[11]),
                new Edge(vertices[9], vertices[11]),
                new Edge(vertices[10], vertices[11])
        };
    }

    private void initializeFaces(Edge[] edges)
            throws WrongCountException {
        if (edges.length != EDGES_COUNT) {
            throw new WrongCountException("Wrong edges count.");
        }
        faces = new Face[] {
                // Front
                new Face(edges[0], edges[1], edges[2], edges[3]),
                // Back
                new Face(edges[4], edges[5], edges[6], edges[7]),
                // Left
                new Face(edges[6], edges[9], edges[0], edges[8]),
                // Up
                new Face(edges[9], edges[5], edges[10], edges[1]),
                // Right
                new Face(edges[2], edges[10], edges[4], edges[11]),
                // Down
                new Face(edges[8], edges[3], edges[11], edges[7]),

                new Face(edges[12], edges[13], edges[14]),
                new Face(edges[12], edges[16], edges[15]),
                new Face(edges[15], edges[17], edges[14]),
                new Face(edges[16], edges[13], edges[17])
        };
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public void setVertices(Vertex[] vertices) {
        this.vertices = vertices;
    }

    public Edge[] getEdges() {
        return edges;
    }

    public void setEdges(Edge[] edges) {
        this.edges = edges;
    }

    public Face[] getFaces() {
        return faces;
    }

    public void setFaces(Face[] faces) {
        this.faces = faces;
    }
}
