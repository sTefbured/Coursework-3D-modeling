package coursework.model;

import coursework.geometry.parts.*;
import coursework.exceptions.WrongCountException;
import coursework.geometry.shapes.*;
import coursework.geometry.utils.Transformations;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

public class Model implements Projections {
    private int currentProjection;
    private final Shape[] shapes;
    private boolean isInDeleteInvisibleMode;
    private boolean isInColoringMode;
    private boolean isInLightMode;

    public Model(double a, double b, double c, double h, double d) {
        currentProjection = FRONT_PROJECTION;
        shapes = new Shape[2];
        isInColoringMode = false;
        try {
            shapes[0] = createParallelepiped(a, b, c);
            shapes[1] = createTetrahedron(h, d);
        } catch (WrongCountException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    private Parallelepiped createParallelepiped(double a, double b, double c)
            throws WrongCountException {
        return new Parallelepiped(
                new Vertex(-a / 2, 0, -c / 2, 1),
                new Vertex(-a / 2, b, -c / 2, 1),
                new Vertex(a / 2, b, -c / 2, 1),
                new Vertex(a / 2, 0, -c / 2, 1),
                new Vertex(a / 2, 0, c / 2, 1),
                new Vertex(a / 2, b, c / 2, 1),
                new Vertex(-a / 2, b, c / 2, 1),
                new Vertex(-a / 2, 0, c / 2, 1)
        );
    }

    private Tetrahedron createTetrahedron(double h, double d)
            throws WrongCountException {
        return new Tetrahedron(
                new Vertex(0, -h, 0, 1),
                new Vertex(-d / 2, 0, d * Math.sqrt(3) / 6, 1),
                new Vertex(0, 0, -d * Math.sqrt(3) / 3, 1),
                new Vertex(d / 2, 0, d * Math.sqrt(3) / 6, 1)
        );
    }

    public void draw(Graphics2D graphics2D) {
        Shape[] shapesToDraw = new Shape[shapes.length];
        for (int i = 0; i < shapes.length; i++) {
            shapesToDraw[i] = shapes[i].getProjectedShape(currentProjection);
        }

        if (isInDeleteInvisibleMode) {
            for (Shape shape : shapesToDraw) {
                setNormalVectorToAll(shape.getFaces());
            }
        }

        if (isInColoringMode) {
            ArrayList<Face> faces = getFacesToColor(shapesToDraw);
            faces.forEach((face) -> face.draw(graphics2D, currentProjection,
                    isInDeleteInvisibleMode, isInColoringMode,
                    isInLightMode));
            return;
        }

        for (Shape shape : shapesToDraw) {
            shape.draw(graphics2D, currentProjection,
                    isInDeleteInvisibleMode, isInColoringMode, isInLightMode);
        }
    }

    private void setNormalVectorToAll(Face[] faces) {
        for (Face face : faces) {
            face.setNormalVector();
        }
    }

    private ArrayList<Face> getFacesToColor(Shape[] shapesToDraw) {
        ArrayList<Face> faces = new ArrayList<>();
        for (Shape shape : shapesToDraw) {
            Collections.addAll(faces, shape.getFaces());
        }
        sortFaces(faces);
        return faces;
    }

    private void sortFaces(ArrayList<Face> faces) {
        faces.sort((ob1, ob2) -> {
            if (ob1.getCenter().getZ() < ob2.getCenter().getZ()) {
                return 1;
            } else if (ob1.getCenter().getZ() > ob2.getCenter().getZ()) {
                return -1;
            }
            return 0;
        });
    }

    public void transit(double dx, double dy, double dz) {
        for (Shape shape : shapes) {
            Transformations.transit(shape, dx, dy, dz);
        }
    }

    public void scale(double a, double b, double c) {
        for (Shape shape : shapes) {
            Transformations.scale(shape, a, b, c);
        }
    }

    public void rotate(double degX, double degY, double degZ) {
        for (Shape shape : shapes) {
            Transformations.rotateDeg(shape, degX, degY, degZ);
        }
    }

    public void returnToInitialValues() {
        currentProjection = FRONT_PROJECTION;
        for (Shape shape : shapes) {
            Transformations.returnToInitialValues(shape);
        }
    }

    public void setCurrentProjection(int currentProjection) {
        this.currentProjection = currentProjection;
    }

    public void switchDeleteInvisibleMode() {
        isInDeleteInvisibleMode = !isInDeleteInvisibleMode;
    }

    public void switchColoringMode() {
        isInColoringMode = !isInColoringMode;
    }

    public void switchLightMode() {
        isInLightMode = !isInLightMode;
    }
}
