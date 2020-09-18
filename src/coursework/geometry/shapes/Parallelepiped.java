package coursework.geometry.shapes;

import coursework.exceptions.WrongCountException;
import coursework.geometry.parts.*;

public class Parallelepiped extends Shape {
    public static final int VERTICES_COUNT = 8;

    public Parallelepiped(Vertex... vertices)
            throws WrongCountException {
        super(VERTICES_COUNT, vertices);
    }

    @Override
    protected void initializeEdges() {
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
        };
    }

    @Override
    protected void initializeFaces() {
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
        };
    }
}
