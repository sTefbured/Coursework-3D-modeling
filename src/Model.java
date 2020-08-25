import Geometry.Parts.*;
import Geometry.Shapes.*;
import Geometry.Shapes.Shape;
import Exceptions.WrongCountException;

// TODO: PAY ATTENTION TO THE TETRAHEDRON. MAYBE SHOULD
//  DECLARE SOME POINTS IN OTHER WAY
//  (IF THAT, MUST ALSO REFACTOR 'Tetrahedron' CLASS).

public class Model {
    private final int VERTICES_COUNT = 12;
    private final int EDGES_COUNT = 18;
    private final int FACES_COUNT = 10;

    private final Shape[] shapes;

    public Model(double a, double b, double c, double h, double d)
            throws WrongCountException {
        Shape parallelepiped = new Parallelepiped(
                new Vertex(-a / 2, 0, -c / 2),
                new Vertex(-a / 2, b, -c / 2),
                new Vertex(a / 2, b, -c / 2),
                new Vertex(a / 2, 0, -c / 2),
                new Vertex(a / 2, 0, c / 2),
                new Vertex(a / 2, b, c / 2),
                new Vertex(-a / 2, b, c / 2),
                new Vertex(-a / 2, 0, c / 2)
        );
        Shape pyramid = new Tetrahedron(
                new Vertex(0, -h, 0),
                new Vertex(-d / 2, 0, d * Math.sqrt(3) / 6),
                new Vertex(d / 2, 0, d * Math.sqrt(3) / 6),
                new Vertex(0, 0, d * Math.sqrt(3) / 3)
        );
        shapes = new Shape[] {
                parallelepiped,
                pyramid
        };
    }

    public Shape[] getShapes() {
        return shapes;
    }
}
