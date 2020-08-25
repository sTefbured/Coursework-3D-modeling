package Coursework.Geometry.Shapes;

import Coursework.Exceptions.WrongCountException;
import Coursework.Geometry.Parts.*;

import java.awt.*;
import java.util.Arrays;

public abstract class Shape {
    protected Vertex[] vertices;
    protected Edge[] edges;
    protected Face[] faces;

    public Shape(int verticesCount, Vertex... vertices)
            throws WrongCountException {
        if (vertices.length != verticesCount) {
            throw new WrongCountException("Wrong vertices count. " +
                    "Correct count is " + verticesCount + ".");
        }
        this.vertices = Arrays.copyOf(vertices, vertices.length);
        initializeEdges();
        initializeFaces();
    }

    protected abstract void initializeEdges();

    protected abstract void initializeFaces();

    public void draw(Graphics2D graphics2D) {
        for (Edge edge : edges) {
            edge.draw(graphics2D);
        }
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public Edge[] getEdges() {
        return edges;
    }

    public Face[] getFaces() {
        return faces;
    }
}
