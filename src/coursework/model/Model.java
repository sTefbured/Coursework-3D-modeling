package coursework.model;

import coursework.frame.menus.tabbedMenu.panels.ProjectionsPanel;
import coursework.geometry.parts.Projections;
import coursework.geometry.parts.*;
import coursework.exceptions.WrongCountException;
import coursework.geometry.shapes.*;
import coursework.geometry.shapes.Shape;
import coursework.geometry.utils.Transformations;

import java.awt.Graphics2D;
import java.util.EnumSet;

// TODO: PAY ATTENTION TO THE TETRAHEDRON. MAYBE SHOULD
//  DECLARE SOME POINTS IN OTHER WAY
//  (IF THAT, MUST ALSO REFACTOR 'Tetrahedron' CLASS).
public class Model implements Projections {
    private int currentProjection;
    private final Shape[] shapes;

    public short conditions;

    //FIXME: maybe shouldn't throw an exception
    public Model(double a, double b, double c, double h, double d)
            throws WrongCountException {
        currentProjection = FRONT_PROJECTION;
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

    //TODO: maybe move axonometric projection checking to somewhere else
    public void draw(Graphics2D graphics2D) {
        if (currentProjection == AXONOMETRIC_PROJECTION) {
            double[] values = ProjectionsPanel.getAxonometricValues();
            returnToInitialValues();
            currentProjection = AXONOMETRIC_PROJECTION;
            rotate(values[0], values[1], 0);
        }
        for (Shape shape : shapes) {
            shape.draw(graphics2D, currentProjection);
        }
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

    //TODO: maybe delete the comment below
//    public void projectionX() {
//        for (Shape shape : shapes) {
//            Transformations.пуеProjectionX(shape);
//        }
//    }
//
//    public void projectionY() {
//        for (Shape shape : shapes) {
//            Transformations.makeProjectionY(shape);
//        }
//    }
//
//    public void projectionZ() {
//        for (Shape shape : shapes) {
//            Transformations.makeProjectionZ(shape);
//        }
//    }

    public void returnToInitialValues() {
        currentProjection = FRONT_PROJECTION;
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

    public int getCurrentProjection() {
        return currentProjection;
    }

    public void setCurrentProjection(int currentProjection) {
        this.currentProjection = currentProjection;
    }
}
