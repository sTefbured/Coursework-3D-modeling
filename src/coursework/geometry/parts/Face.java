package coursework.geometry.parts;

import coursework.geometry.utils.Transformations;

import java.awt.*;
import java.util.Arrays;

public class Face implements Projections {
    private final Edge[] edges;
    private final double[] normalVector;

    public Face(Vertex... vertices) {
        edges = new Edge[vertices.length - 1];
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new Edge(vertices[i], vertices[i + 1]);
        }
        normalVector = new double[4];
        setNormalVector();
    }

    public void draw(Graphics2D graphics2D, int projectionMode) {
        setNormalVector();
        if (Transformations
                .getCos(normalVector, new double[]{0, 0, -1, 0}) < 0) {
            return;
        }

        for (Edge edge : edges) {
            edge.draw(graphics2D, projectionMode);
        }
    }

    public void setNormalVector() {
        Vertex vertex1 = edges[0].getVertices()[0];
        Vertex vertex2 = edges[0].getVertices()[1];
        Vertex vertex3 = edges[1].getVertices()[0];
        if (vertex1 == vertex3 || vertex2 == vertex3) {
            vertex3 = edges[1].getVertices()[1];
        }
        double x1 = vertex1.getX();
        double x2 = vertex2.getX();
        double x3 = vertex3.getX();
        double y1 = vertex1.getY();
        double y2 = vertex2.getY();
        double y3 = vertex3.getY();
        double z1 = vertex1.getZ();
        double z2 = vertex2.getZ();
        double z3 = vertex3.getZ();
        double minor1 = (y2 - y1) * (z3 - z1) - (y3 - y1) * (z2 - z1);
        double minor2 = -(x2 - x1) * (z3 - z1) + (x3 - x1) * (z2 - z1);
        double minor3 = (x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1);
        normalVector[0] = minor1;
        normalVector[1] = minor2;
        normalVector[2] = minor3;
        normalVector[3] = 1;//-x1 * minor1 + y1 * minor2 - z1 * minor3;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Face\n");
        for (Edge edge : edges) {
            builder.append("\t").append(edge.toString()).append("\n");
        }
        builder.append("Normal vector\n");
        builder.append(Arrays.toString(normalVector));
        return builder.toString();
    }
}
