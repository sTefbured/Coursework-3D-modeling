package coursework;

import coursework.geometry.parts.*;
import coursework.exceptions.WrongCountException;
import coursework.geometry.shapes.*;
import coursework.geometry.shapes.Shape;

import java.awt.Graphics2D;

// TODO: PAY ATTENTION TO THE TETRAHEDRON. MAYBE SHOULD
//  DECLARE SOME POINTS IN OTHER WAY
//  (IF THAT, MUST ALSO REFACTOR 'Tetrahedron' CLASS).

public class Model {
    private final Shape[] shapes;

    public boolean isTransforming;
    public ModelCondition condition;

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
        isTransforming = false;
        condition = ModelCondition.NONE;
    }

    public void draw(Graphics2D graphics2D) {
        for (Shape shape : shapes) {
            shape.draw(graphics2D);
        }
    }

    public void transit(int dx, int dy, int dz) {
        for (Shape shape : shapes) {
            shape.transit(dx, dy, dz);
        }
    }

    public void scale(double a, double b, double c) {
        for (Shape shape : shapes) {
            shape.scale(a, b, c);
        }
    }

    public void rotate(double degX, double degY, double degZ) {
        for (Shape shape : shapes) {
            shape.rotateDeg(degX, degY, degZ);
        }
    }

    public void update() {
        if (isTransforming) {
            switch (condition) {
                case MOVING_UP -> transit(0, 10, 0);
                case MOVING_DOWN -> transit(0, -10, 0);
                case MOVING_LEFT -> transit(-10, 0, 0);
                case MOVING_RIGHT -> transit(10, 0, 0);
                case ROTATING_X_NEG -> rotate(-1, 0, 0);
                case ROTATING_X_POS -> rotate(1, 0, 0);
                case ROTATING_Y_NEG -> rotate(0, -1, 0);
                case ROTATING_Y_POS -> rotate(0, 1, 0);
                case ROTATING_Z_NEG -> rotate(0, 0, -1);
                case ROTATING_Z_POS -> rotate(0, 0, 1);
            }
        }
    }

    public Shape[] getShapes() {
        return shapes;
    }
}
