package coursework.geometry.shapes;

import coursework.exceptions.MatricesMismatchException;
import coursework.exceptions.WrongCountException;
import coursework.geometry.parts.*;

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
        for (Face face : faces) {
            face.draw(graphics2D);
        }
    }
}