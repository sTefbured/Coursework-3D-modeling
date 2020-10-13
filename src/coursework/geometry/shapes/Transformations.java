package coursework.geometry.shapes;

import coursework.exceptions.MatricesMismatchException;
import coursework.geometry.parts.Vertex;

public class Transformations {
    public static void returnToInitialValues(Shape shape) {
        for (int i = 0; i < shape.beginValues.length; i++) {
            Vertex vertex = shape.beginValues[i];
            shape.vertices[i].setX(vertex.getX());
            shape.vertices[i].setY(vertex.getY());
            shape.vertices[i].setZ(vertex.getZ());
        }
    }

    private static double[][] multiply(double[][] matrix1, double[][] matrix2)
            throws MatricesMismatchException {
        if (matrix1[0].length != matrix2.length) {
            throw new MatricesMismatchException(
                    "Columns count of the first matrix"
                            + "must be equal to rows"
                            + "count of the second matrix.");
        }
        double[][] outMatrix = new double[matrix1.length][matrix2[0].length];
        for (int i = 0; i < outMatrix.length; i++) {
            for (int j = 0; j < outMatrix[i].length; j++) {
                for (int k = 0; k < outMatrix.length; k++) {
                    outMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return outMatrix;
    }

    private static void multiply(Vertex dest, double[][] matrix) {
        double[][] coordinates = dest.getCoordinates();
        double[][] result;
        try {
            result = multiply(matrix, coordinates);
        } catch (MatricesMismatchException exception) {
            System.out.println(exception.getMessage());
            return;
        }
        dest.setX(result[0][0]);
        dest.setY(result[1][0]);
        dest.setZ(result[2][0]);
        dest.setParameter(result[3][0]);
    }

    public static void transit(Shape target, double dx, double dy, double dz) {
        double[][] transitionMatrix = {
                {1, 0, 0, dx},
                {0, 1, 0, dy},
                {0, 0, 1, dz},
                {0, 0, 0, 1}
        };
        for (Vertex vertex : target.vertices) {
            multiply(vertex, transitionMatrix);
        }
    }

    public static void scale(Shape target, double a, double b, double c) {
        double[][] scaleMatrix = {
                {a, 0, 0, 0},
                {0, b, 0, 0},
                {0, 0, c, 0},
                {0, 0, 0, 1}
        };
        for (Vertex vertex : target.vertices) {
            multiply(vertex, scaleMatrix);
        }
    }

    private static double[][] createRotationMatrix(double radX,
                                                   double radY,
                                                   double radZ)
            throws MatricesMismatchException{
        double[][] matrixX = {
                {1, 0, 0, 0},
                {0, Math.cos(radX), -Math.sin(radX), 0},
                {0, Math.sin(radX), Math.cos(radX), 0},
                {0, 0, 0, 1}
        };
        double[][] matrixY = {
                {Math.cos(radY), 0, Math.sin(radY), 0},
                {0, 1, 0, 0},
                {-Math.sin(radY), 0, Math.cos(radY), 0},
                {0, 0, 0, 1}
        };
        double[][] matrixZ = {
                {Math.cos(radZ), -Math.sin(radZ), 0, 0},
                {Math.sin(radZ), Math.cos(radZ), 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        double[][] buf = multiply(matrixX, matrixY);
        return multiply(buf, matrixZ);
    }

    public static void rotateRad(Shape target,
                                 double radX,
                                 double radY,
                                 double radZ) {
        double[][] rotationMatrix;
        try {
            rotationMatrix = createRotationMatrix(radX, radY, radZ);
        } catch (MatricesMismatchException exception) {
            System.out.println(exception.getMessage());
            return;
        }
        for (Vertex vertex : target.vertices) {
            multiply(vertex, rotationMatrix);
        }
    }

    public static void rotateDeg(Shape target,
                                 double degX,
                                 double degY,
                                 double degZ) {
        rotateRad(target,
                  degX * Math.PI / 180.0,
                  degY * Math.PI / 180.0,
                  degZ * Math.PI / 180.0);
    }

    public static void makeProjectionX(Shape target) {
        double[][] matrix = {
                {0, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        returnToInitialValues(target);
        for (Vertex vertex : target.vertices) {
            multiply(vertex, matrix);
        }
        rotateDeg(target, 0, -90, 0);
    }

    public static void makeProjectionY(Shape target) {
        double[][] matrix = {
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        returnToInitialValues(target);
        for (Vertex vertex : target.vertices) {
            multiply(vertex, matrix);
        }
        rotateDeg(target, -90, 0, 0);
    }

    public static void makeProjectionZ(Shape target) {
        long time = System.nanoTime();
        double[][] matrix = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 1}
        };
        returnToInitialValues(target);
        for (Vertex vertex : target.vertices) {
            multiply(vertex, matrix);
        }
        System.out.println("TIME: " + (System.nanoTime() - time));
    }

    //FIXME: fix
    public static void makePerspective(Shape target) {
        double[][] matrix = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 1.0 / 3000, 0}
        };
        for (Vertex vertex : target.vertices) {
            multiply(vertex, matrix);
        }
    }
}
