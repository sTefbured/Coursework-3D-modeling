package coursework.geometry.utils;

import coursework.exceptions.MatricesMismatchException;
import coursework.geometry.parts.Vertex;
import coursework.geometry.shapes.Shape;

public class Transformations {
    private static double axonometricFi = 30 * Math.PI / 180.0;
    private static double axonometricPsi = 30 * Math.PI / 180.0;

    private static double obliqueLength = 1;
    private static double obliqueAngle = 45 * Math.PI / 180.0;

    private static double perspectiveDistance = 1000;
    private static double perspectiveRo = 1000;
    private static double perspectiveTeta = 1000;
    private static double perspectiveFi = 1000;


    public static void returnToInitialValues(Shape shape) {
        for (int i = 0; i < shape.getBeginValues().length; i++) {
            Vertex vertex = shape.getBeginValues()[i];
            shape.getVertices()[i].setX(vertex.getX());
            shape.getVertices()[i].setY(vertex.getY());
            shape.getVertices()[i].setZ(vertex.getZ());
        }
    }

    private static double[][] multiply(double[][] matrix1, double[][] matrix2)
            throws MatricesMismatchException {
        if (matrix1[0].length != matrix2.length) {
            throw new MatricesMismatchException(
                    "Columns count of the first matrix "
                            + "must be equal to rows "
                            + "count of the second matrix.");
        }
        double[][] outMatrix = new double[matrix1.length][matrix2[0].length];
        for (int i = 0; i < outMatrix.length; i++) {
            for (int j = 0; j < outMatrix[i].length; j++) {
                for (int k = 0; k < outMatrix[i].length; k++) {
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
            result = multiply(coordinates, matrix);
        } catch (MatricesMismatchException exception) {
            System.out.println(exception.getMessage());
            return;
        }
        dest.setX(result[0][0] / result[0][3]);
        dest.setY(result[0][1] / result[0][3]);
        dest.setZ(result[0][2] / result[0][3]);
        dest.setParameter(result[0][3] / result[0][3]);
    }

    public static void transit(Shape target, double dx, double dy, double dz) {
        double[][] transitionMatrix = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {dx, dy, dz, 1}
        };
        for (Vertex vertex : target.getVertices()) {
            multiply(vertex, transitionMatrix);
        }
    }

    public static void scale(Shape target, double a, double b, double c) {
        a = getCorrectScaleValue(a);
        b = getCorrectScaleValue(b);
        c = getCorrectScaleValue(c);
        double[][] scaleMatrix = {
                {a, 0, 0, 0},
                {0, b, 0, 0},
                {0, 0, c, 0},
                {0, 0, 0, 1}
        };
        for (Vertex vertex : target.getVertices()) {
            multiply(vertex, scaleMatrix);
        }
    }

    private static double getCorrectScaleValue(double scale) {
        if (Math.abs(scale) <= 0.001) {
            return 0.001;
        }
        return scale;
    }

    private static double[][] createRotationMatrix(double radX,
                                                   double radY,
                                                   double radZ) {
        double[][] matrixX = {
                {1, 0, 0, 0},
                {0, Math.cos(radX), Math.sin(radX), 0},
                {0, -Math.sin(radX), Math.cos(radX), 0},
                {0, 0, 0, 1}
        };
        double[][] matrixY = {
                {Math.cos(-radY), 0, -Math.sin(-radY), 0},
                {0, 1, 0, 0},
                {Math.sin(-radY), 0, Math.cos(-radY), 0},
                {0, 0, 0, 1}
        };
        double[][] matrixZ = {
                {Math.cos(radZ), Math.sin(radZ), 0, 0},
                {-Math.sin(radZ), Math.cos(radZ), 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        try {
            double[][] buf = multiply(matrixX, matrixY);
            return multiply(buf, matrixZ);
        } catch (MatricesMismatchException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    public static void rotateRad(Shape target,
                                 double radX,
                                 double radY,
                                 double radZ) {
        double[][] rotationMatrix;
        rotationMatrix = createRotationMatrix(radX, radY, radZ);
        for (Vertex vertex : target.getVertices()) {
            multiply(vertex, rotationMatrix);
        }
    }

    public static void rotateDeg(Shape target,
                                 double degX,
                                 double degY,
                                 double degZ) {
        rotateRad(target,
                degX * Math.PI / 180.0,
                -degY * Math.PI / 180.0,
                degZ * Math.PI / 180.0);
    }

    public static void makeAxonometricProjection(Shape copy) {
        double[][] rotationMatrix = createRotationMatrix(axonometricFi,
                axonometricPsi, 0);
        if (rotationMatrix == null) {
            return;
        }
        for (Vertex vertex : copy.getVertices()) {
            multiply(vertex, rotationMatrix);
        }
    }

    public static void setAxonometricFi(double angleDeg) {
        axonometricFi = angleDeg * Math.PI / 180.0;
    }

    public static void setAxonometricPsi(double angleDeg) {
        axonometricPsi = angleDeg * Math.PI / 180.0;
    }

    public static void makeObliqueProjection(Shape copy) {
        double a = obliqueLength * Math.cos(obliqueAngle);
        double b = obliqueLength * Math.sin(obliqueAngle);
        double[][] matrix = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {a, b, 1, 0},
                {0, 0, 0, 1},
        };
        for (Vertex vertex : copy.getVertices()) {
            multiply(vertex, matrix);
        }
    }

    public static void setObliqueLength(double length) {
        obliqueLength = length;
    }

    public static void setObliqueAngle(double angleDeg) {
        obliqueAngle = angleDeg * Math.PI / 180.0;
    }

    public static void makePerspectiveProjection(Shape copy) {
        double[][] matrix = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 1.0 / perspectiveDistance},
                {0, 0, 0, 0}
        };
        Vertex[] vertices = copy.getVertices();
        for (Vertex vertex : vertices) {
            multiply(vertex, matrix);
        }
    }

    public static void setPerspectiveDistance(double distance) {
        perspectiveDistance = distance;
    }

    public static void makeViewTransformations(Shape copy) {
        double sinFi = Math.sin(perspectiveFi);
        double cosFi = Math.cos(perspectiveFi);
        double sinTeta = Math.sin(perspectiveTeta);
        double cosTeta = Math.cos(perspectiveTeta);
        double[][] matrix = {
                {-sinTeta, -cosFi * cosTeta, -sinFi * cosTeta, 0},
                {cosTeta, -cosFi * sinTeta, -sinFi * sinTeta, 0},
                {0, sinFi, -cosFi, 0},
                {0, 0, perspectiveRo, 1}
        };
        for (Vertex vertex : copy.getVertices()) {
            multiply(vertex, matrix);
        }
    }

    public static void setPerspectiveRo(double ro) {
        perspectiveRo = ro;
    }

    public static void setPerspectiveTeta(double teta) {
        perspectiveTeta = Math.PI * teta / 180.0;
    }

    public static void setPerspectiveFi(double fi) {
        perspectiveFi = Math.PI * fi / 180.0;
    }

    public static double getCos(double[] vector1, double[] vector2) {
        double multiplication = 0;
        double module1 = Math.sqrt(vector1[0] * vector1[0]
                + vector1[1] * vector1[1] + vector1[2] * vector1[2]);
        double module2 = Math.sqrt(vector2[0] * vector2[0]
                + vector2[1] * vector2[1] + vector2[2] * vector2[2]);
        for (int i = 0; i < vector1.length - 1; i++) {
            multiplication += vector1[i] * vector2[i];
        }
        return multiplication / (module1 * module2);
    }
}
