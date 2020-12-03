package coursework.geometry.parts;

import coursework.Main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Face implements Projections {
    private final Edge[] edges;
    private final double[] normalVector;

    public Face(Edge... edges) {
        this.edges = edges;
        normalVector = new double[4];
    }

    public void draw(Graphics2D graphics2D, int projectionMode) {
//        int[] xPoints = new int[edges.length];
//        int[] yPoints = new int[edges.length];
//
//        int j = 0;
//        xPoints[0] = (int) edges[0].getVertices()[0].getX() + Main.getWindow().getDrawingPanel().getWidth() / 2;
//        yPoints[0] = (int) -edges[0].getVertices()[0].getY() + Main.getWindow().getDrawingPanel().getHeight() / 2;
//        for (int i = 1; i < edges.length; i++) {
//            if ((edges[i].getVertices()[j] == edges[i - 1].getVertices()[j])) {
//                j = (j == 0) ? 1 : 0;
//            }
//            xPoints[i] = (int) edges[i].getVertices()[j].getX() + Main.getWindow().getDrawingPanel().getWidth() / 2;
//            yPoints[i] = (int) -edges[i].getVertices()[j].getY() + Main.getWindow().getDrawingPanel().getHeight() / 2;
//        }
//
//        Polygon polygon = new Polygon(xPoints, yPoints, xPoints.length);
//        setNormalVector();
//        graphics2D.setColor(Color.GREEN);
//        graphics2D.fillPolygon(polygon);
//        graphics2D.setColor(Color.BLACK);
        for (Edge edge : edges) {
            edge.draw(graphics2D, projectionMode);
        }
    }

    private void setNormalVector() {
        Vertex vertex1 = edges[0].getVertices()[0];
        Vertex vertex2 = edges[0].getVertices()[1];
        Vertex vertex3 = edges[1].getVertices()[0];
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
        double minor2 = (x2 - x1) * (z3 - z1) - (x3 - x1) * (z2 - z1);
        double minor3 = (x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1);
        normalVector[0] = minor1;
        normalVector[1] = -minor2;
        normalVector[2] = minor3;
        normalVector[3] = -x1 * minor1 + y1 * minor2 - z1 * minor3;
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
