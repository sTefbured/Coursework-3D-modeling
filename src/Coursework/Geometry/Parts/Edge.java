package Coursework.Geometry.Parts;

import Coursework.Main;

import java.awt.*;

public class Edge {
    private final Vertex[] vertices;

    public Edge(Vertex start, Vertex end) {
        vertices = new Vertex[] {
                start,
                end
        };
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawLine((int) vertices[0].getX() + Main.getWindow().getWidth() / 2,
                            (int) vertices[0].getZ() + Main.getWindow().getHeight() / 2,
                            (int) vertices[1].getX() + Main.getWindow().getWidth() / 2,
                            (int) vertices[1].getZ() + Main.getWindow().getHeight() / 2);
    }

    public Vertex[] getVertices() {
        return vertices;
    }
}