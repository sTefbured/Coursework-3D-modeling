package coursework.geometry.shapes;

import coursework.exceptions.WrongCountException;
import coursework.geometry.parts.*;

public class Tetrahedron extends Shape {
    public static final int VERTICES_COUNT = 4;

    public Tetrahedron(Vertex... vertices)
            throws WrongCountException {
        super(VERTICES_COUNT, vertices);
    }

    @Override
    protected void initializeEdges() {
        edges = new Edge[] {
                new Edge(vertices[0], vertices[1]),
                new Edge(vertices[1], vertices[2]),
                new Edge(vertices[2], vertices[0]),
                new Edge(vertices[0], vertices[3]),
                new Edge(vertices[1], vertices[3]),
                new Edge(vertices[2], vertices[3])
        };
    }

    @Override
    protected void initializeFaces() {
        faces = new Face[] {
                new Face(edges[0], edges[4], edges[2]),
                new Face(edges[0], edges[4], edges[3]),
                new Face(edges[3], edges[5], edges[2]),
                new Face(edges[4], edges[1], edges[5])
        };
    }
}
