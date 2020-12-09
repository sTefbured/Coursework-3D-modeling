package coursework.geometry.shapes;

import coursework.exceptions.WrongCountException;
import coursework.geometry.parts.*;
import coursework.geometry.utils.Transformations;

import java.awt.*;

public abstract class Shape implements Projections {
    protected final Vertex[] beginValues;
    protected Vertex[] vertices;
    protected Vertex center;
    protected Face[] faces;

    public Shape(int verticesCount, Vertex... vertices)
            throws WrongCountException {
        if (vertices.length != verticesCount) {
            throw new WrongCountException("Wrong vertices count. " +
                    "Correct count is " + verticesCount + ".");
        }
        this.vertices = new Vertex[verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            this.vertices[i] = vertices[i].getCopy();
        }
        this.beginValues = new Vertex[verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            this.beginValues[i] = vertices[i].getCopy();
        }
        center = new Vertex();
        setCenter();
        initializeFaces();
    }

    public Shape getProjectedShape(int projectionMode) {
        Shape copyShape = getCopy();
        switch (projectionMode) {
            case AXONOMETRIC_PROJECTION ->
                    Transformations.makeAxonometricProjection(copyShape);
            case OBLIQUE_PROJECTION ->
                    Transformations.makeObliqueProjection(copyShape);
            case PERSPECTIVE_PROJECTION -> {
                Transformations.makeViewTransformations(copyShape);
                Transformations.makePerspectiveProjection(copyShape);
            }
            default -> {
                setCenter();
                return this;
            }
        }
        return copyShape;
    }

    //NOTE: if something goes wrong it could probably be the center's issue
    public void draw(Graphics2D graphics2D, int projectionMode) {
        for (Face face : faces) {
            face.draw(graphics2D, projectionMode);
        }
    }

    protected abstract void initializeFaces();

    public abstract Shape getCopy();

    public Vertex[] getVertices() {
        return vertices;
    }

    public Face[] getFaces() {
        return faces;
    }

    public Vertex[] getBeginValues() {
        return beginValues;
    }

    public Vertex getCenter() {
        return center;
    }

    public void setCenter() {
        double xSum = 0;
        double ySum = 0;
        double zSum = 0;
        for (Vertex vertex : vertices) {
            xSum += vertex.getX();
            ySum += vertex.getY();
            zSum += vertex.getZ();
        }
        center.setX(xSum);
        center.setY(ySum);
        center.setZ(zSum);
    }
}
