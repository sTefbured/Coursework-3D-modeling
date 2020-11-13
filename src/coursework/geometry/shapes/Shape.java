package coursework.geometry.shapes;

import coursework.exceptions.WrongCountException;
import coursework.geometry.parts.*;

import java.awt.*;

public abstract class Shape {
    protected final Vertex[] beginValues;
    protected Vertex[] vertices;
    protected Edge[] edges;
    protected Face[] faces;

    public Shape(int verticesCount, Vertex... vertices)
            throws WrongCountException {
        if (vertices.length != verticesCount) {
            throw new WrongCountException("Wrong vertices count. " +
                    "Correct count is " + verticesCount + ".");
        }
        this.vertices = new Vertex[verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            this.vertices[i] = new Vertex(vertices[i]);
        }
        this.beginValues = new Vertex[verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            this.beginValues[i] = new Vertex(vertices[i]);
        }
        initializeEdges();
        initializeFaces();
    }

    protected abstract void initializeEdges();

    protected abstract void initializeFaces();

    public void draw(Graphics2D graphics2D, int projectionMode) {
        for (Face face : faces) {
            face.draw(graphics2D, projectionMode);
        }
    }
}