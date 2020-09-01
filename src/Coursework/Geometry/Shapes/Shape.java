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

    public void transit(int dx, int dy, int dz) {
        for (Vertex vertex : vertices) {
            vertex.setX(vertex.getX() + dx);
            vertex.setY(vertex.getY() + dy);
            vertex.setZ(vertex.getZ() + dz);
        }
    }

    public void scale(double a, double b, double c) {
        for (Vertex vertex : vertices) {
            vertex.setX(vertex.getX() * a);
            vertex.setY(vertex.getY() * b);
            vertex.setZ(vertex.getZ() * c);
        }
    }

    public void rotateRad(double radX, double radY, double radZ) {
        for (Vertex vertex : vertices) {
            double oldX = vertex.getX();
            double oldY = vertex.getY();
            double oldZ = vertex.getZ();

            // Results of multiplied rotation matrices
            vertex.setX(oldX * Math.cos(radY) * Math.cos(radZ)
                    + oldY * (Math.sin(radX) * Math.sin(radY) * Math.cos(radZ)
                        + Math.cos(radX) * Math.sin(radZ))
                    + oldZ * (-Math.cos(radX) * Math.sin(radY) * Math.cos(radZ)
                        + Math.sin(radX) * Math.sin(radZ)));
            vertex.setY(oldX * (-Math.cos(radY) * Math.sin(radZ))
                    + oldY * (-Math.sin(radX) * Math.sin(radY) * Math.sin(radZ)
                    + Math.cos(radX) * Math.cos(radZ))
                    + oldZ * (Math.cos(radX) * Math.sin(radY) * Math.sin(radZ)
                    + Math.sin(radX) * Math.cos(radZ)));
            vertex.setZ(oldX * Math.sin(radY)
                    + oldY * (-Math.sin(radX) * Math.cos(radY))
                    + oldZ * Math.cos(radX) * Math.cos(radY));
        }
    }

    public void rotateDeg(double degX, double degY, double degZ) {
        rotateRad(degX * Math.PI / 180.0,
                degY * Math.PI / 180.0,
                degZ * Math.PI / 180.0);
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