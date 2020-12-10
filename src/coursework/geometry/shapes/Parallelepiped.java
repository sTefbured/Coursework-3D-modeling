package coursework.geometry.shapes;

import coursework.exceptions.WrongCountException;
import coursework.geometry.parts.*;

public class Parallelepiped extends Shape {
    public static final int VERTICES_COUNT = 8;

    public Parallelepiped(Vertex... vertices)
            throws WrongCountException {
        super(VERTICES_COUNT, vertices);
    }

    @Override
    protected void initializeFaces() {
        faces = new Face[] {
                // Front
                new Face(vertices[0], vertices[1],
                        vertices[2], vertices[3], vertices[0]),
                // Back
                new Face(vertices[4], vertices[5],
                        vertices[6], vertices[7], vertices[4]),
                // Left
                new Face(vertices[7], vertices[6],
                        vertices[1], vertices[0], vertices[7]),
                // Up
                new Face(vertices[1], vertices[6],
                        vertices[5], vertices[2], vertices[1]),
                // Right
                new Face(vertices[3], vertices[2],
                        vertices[5], vertices[4], vertices[3]),
                // Down
                new Face(vertices[7], vertices[0],
                        vertices[3], vertices[4], vertices[7]),
        };
        comparisonFace = faces[5];
    }

    @Override
    public Parallelepiped getCopy() {
        Parallelepiped copy = null;
        try {
            copy = new Parallelepiped(vertices);
        } catch (WrongCountException e) {
            e.printStackTrace();
        }
        return copy;
    }
}
