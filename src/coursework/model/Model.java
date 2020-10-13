package coursework.model;

import coursework.geometry.parts.*;
import coursework.exceptions.WrongCountException;
import coursework.geometry.shapes.*;
import coursework.geometry.shapes.Shape;

import java.awt.Graphics2D;
import java.util.EnumSet;

// TODO: PAY ATTENTION TO THE TETRAHEDRON. MAYBE SHOULD
//  DECLARE SOME POINTS IN OTHER WAY
//  (IF THAT, MUST ALSO REFACTOR 'Tetrahedron' CLASS).
public class Model {
    private final Shape[] shapes;

    public short conditions;

    public Model(double a, double b, double c, double h, double d)
            throws WrongCountException {
        Shape parallelepiped = new Parallelepiped(
                new Vertex(-a / 2, 0, -c / 2, 1),
                new Vertex(-a / 2, b, -c / 2, 1),
                new Vertex(a / 2, b, -c / 2, 1),
                new Vertex(a / 2, 0, -c / 2, 1),
                new Vertex(a / 2, 0, c / 2, 1),
                new Vertex(a / 2, b, c / 2, 1),
                new Vertex(-a / 2, b, c / 2, 1),
                new Vertex(-a / 2, 0, c / 2, 1)
        );
        Shape pyramid = new Tetrahedron(
                new Vertex(0, -h, 0, 1),
                new Vertex(-d / 2, 0, d * Math.sqrt(3) / 6, 1),
                new Vertex(d / 2, 0, d * Math.sqrt(3) / 6, 1),
                new Vertex(0, 0, -d * Math.sqrt(3) / 3, 1)
        );
        shapes = new Shape[] {
                parallelepiped,
                pyramid
        };
        conditions = 0;
    }

    public void draw(Graphics2D graphics2D) {
        for (Shape shape : shapes) {
            shape.draw(graphics2D);
        }
    }

    public void transit(int dx, int dy, int dz) {
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

    public void projectionX() {
        for (Shape shape : shapes) {
            Transformations.makeProjectionX(shape);
        }
    }

    public void projectionY() {
        for (Shape shape : shapes) {
            Transformations.makeProjectionY(shape);
        }
    }

    public void projectionZ() {
        for (Shape shape : shapes) {
            Transformations.makeProjectionZ(shape);
        }
    }

    public void returnToInitialValues() {
        for (Shape shape : shapes) {
            Transformations.returnToInitialValues(shape);
        }
    }

    //FIXME: fix
    public void makePerspective() {
        for (Shape shape : shapes) {
            Transformations.makePerspective(shape);
        }
    }

    public void update() {
        for (ModelCondition condition : EnumSet.allOf(ModelCondition.class)) {
            if ((conditions & condition.getValue()) == condition.getValue()) {
                condition.performAction(this);
            }
        }
    }

    public void activateCondition(ModelCondition condition) {
        conditions |= condition.getValue();
    }

    public void deactivateCondition(ModelCondition condition) {
        conditions ^= condition.getValue();
    }
}