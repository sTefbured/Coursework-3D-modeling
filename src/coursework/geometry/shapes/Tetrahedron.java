package coursework.geometry.shapes;

import coursework.exceptions.WrongCountException;
import coursework.geometry.parts.*;

public class Tetrahedron extends Shape {
    public static final int VERTICES_COUNT = 4;

    public Tetrahedron(Vertex... vertices)
            throws WrongCountException {
        super(VERTICES_COUNT, vertices);
    }

    @Override
    protected void initializeFaces() {
        faces = new Face[]{
                new Face(vertices[0], vertices[1], vertices[2], vertices[0]),
                new Face(vertices[0], vertices[2], vertices[3], vertices[0]),
                new Face(vertices[1], vertices[3], vertices[2], vertices[1]),
                new Face(vertices[0], vertices[3], vertices[1], vertices[0])
        };
    }

    @Override
    public Tetrahedron getCopy() {
        Tetrahedron copy = null;
        try {
            copy = new Tetrahedron(vertices);
        } catch (WrongCountException e) {
            e.printStackTrace();
        }
        return copy;
    }
}
