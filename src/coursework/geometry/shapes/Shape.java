package coursework.geometry.shapes;

import coursework.exceptions.WrongCountException;
import coursework.geometry.parts.*;
import coursework.geometry.utils.Transformations;

import java.awt.*;

public abstract class Shape implements Projections {
    protected final Vertex[] beginValues;
    protected Vertex[] vertices;
    protected Face[] faces;
    protected Face comparisonFace;

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
                return this;
            }
        }
        return copyShape;
    }

    public void draw(Graphics2D graphics2D,
                     int projectionMode,
                     boolean deleteInvisible,
                     boolean isForColoring,
                     boolean isForLight,
                     Vertex lightPosition) {
        for (Face face : faces) {
            face.draw(graphics2D, projectionMode, deleteInvisible,
                    isForColoring, isForLight, lightPosition);
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

    public Face getComparisonFace() {
        return comparisonFace;
    }
}
