package coursework.geometry.parts;

import coursework.Main;
import coursework.geometry.utils.Transformations;

import java.awt.*;
import java.util.Arrays;

public class Face implements Projections {
    private final Edge[] edges;
    private final double[] normalVector;
    private final Vertex center;
    private Color color;

    public Face(Vertex... vertices) {
        edges = new Edge[vertices.length - 1];
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new Edge(vertices[i], vertices[i + 1]);
        }
        center = new Vertex();
        normalVector = new double[4];
        setNormalVector();
    }

    public void draw(Graphics2D graphics2D,
                     int projectionMode,
                     boolean deleteInvisible,
                     boolean isForColoring,
                     boolean isForLight,
                     Vertex lightPoint) {
        if (deleteInvisible && (getVectorsCos(projectionMode) < 0)) {
            return;
        }

        if (isForColoring) {
            color = (isForLight)
                    ? getLightColor(lightPoint, projectionMode)
                    : Color.GRAY;
            Polygon polygon = createPolygon(projectionMode);
            graphics2D.setColor(color);
            graphics2D.fillPolygon(polygon);
            graphics2D.setColor(Color.BLACK);
        }

        if (isForLight) {
            return;
        }

        for (Edge edge : edges) {
            edge.draw(graphics2D, projectionMode);
        }
    }

    public Color getLightColor(Vertex lightPoint, int projection) {
        setNormalVector();
        double[] lightVector = Arrays.copyOf(lightPoint.getCoordinates()[0], 3);
        if (projection == PERSPECTIVE_PROJECTION) {
            for (int i = 0; i < lightVector.length; i++) {
                lightVector[i] *= -1;
            }
        }
        Vertex center = getCenter();
        for (int i = 0; i < lightVector.length; i++) {
            lightVector[i] -= center.getCoordinates()[0][i];
        }
        double i = (127 + 127 * Transformations.cosBetweenVectors(lightVector, normalVector));

        if (i > 254) {
            i = 254;
        }
        if (i < 0) {
            i = 0;
        }
        return new Color((int) i, (int) 0, (int) 0);
    }

    public double getVectorsCos(int projectionMode) {
        setNormalVector();
        double vectorsCos;
        switch (projectionMode) {
            case UP_PROJECTION -> vectorsCos = Transformations
                    .cosBetweenVectors(normalVector, new double[]{0, 1, 0, 0});
            case SIDE_PROJECTION -> vectorsCos = Transformations
                    .cosBetweenVectors(normalVector, new double[]{1, 0, 0, 0});
            case PERSPECTIVE_PROJECTION -> {
                double ro = Transformations.getPerspectiveRo();
                if (ro > 0) {
                    vectorsCos = Transformations
                            .cosBetweenVectors(normalVector, new double[]{0, 0, 1, 0});
                } else {
                    vectorsCos = Transformations
                            .cosBetweenVectors(normalVector, new double[]{0, 0, -1, 0});
                }
            }
            default -> vectorsCos = Transformations
                    .cosBetweenVectors(normalVector, new double[]{0, 0, -1, 0});
        }
        return vectorsCos;
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
        normalVector[3] = 1;
    }

    private Polygon createPolygon(int projectionMode) {
        int[] xPoints = new int[edges.length];
        int[] yPoints = new int[edges.length];
        int centerX = Main.getWindow().getDrawingPanel().getWidth() / 2;
        int centerY = Main.getWindow().getDrawingPanel().getHeight() / 2;
        if (projectionMode == Projections.UP_PROJECTION) {
            for (int i = 0; i < edges.length; i++) {
                xPoints[i] = (int) edges[i].getVertices()[0].getX() + centerX;
                yPoints[i] = (int) -edges[i].getVertices()[0].getZ() + centerY;
            }
        } else if (projectionMode == Projections.SIDE_PROJECTION) {
            for (int i = 0; i < edges.length; i++) {
                xPoints[i] = (int) edges[i].getVertices()[0].getZ() + centerX;
                yPoints[i] = (int) -edges[i].getVertices()[0].getY() + centerY;
            }
        } else if (projectionMode == Projections.PERSPECTIVE_PROJECTION) {
            for (int i = 0; i < edges.length; i++) {
                xPoints[i] = (int) edges[i].getVertices()[0].getX() + centerX;
                yPoints[i] = (int) -edges[i].getVertices()[0].getY() + centerY;
            }
        } else {
            for (int i = 0; i < edges.length; i++) {
                xPoints[i] = (int) edges[i].getVertices()[0].getX() + centerX;
                yPoints[i] = (int) -edges[i].getVertices()[0].getY() + centerY;
            }
        }
        return new Polygon(xPoints, yPoints, edges.length);
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

    public Vertex getCenter() {
        int x = 0;
        int y = 0;
        int z = 0;
        for (Edge edge : edges) {
            x += edge.getVertices()[0].getX();
            y += edge.getVertices()[0].getY();
            z += edge.getVertices()[0].getZ();
        }
        x /= edges.length;
        y /= edges.length;
        z /= edges.length;
        center.setX(x);
        center.setY(y);
        center.setZ(z);
        return center;
    }
}
