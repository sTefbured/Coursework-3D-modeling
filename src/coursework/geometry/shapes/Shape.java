package coursework.geometry.shapes;

import coursework.exceptions.MatricesMismatchException;
import coursework.exceptions.WrongCountException;
import coursework.geometry.parts.*;

import java.awt.*;
import java.util.Arrays;

public abstract class Shape {
    private final double[][] transitionMatrix = new double[][]{
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
    };
    private final double[][] scaleMatrix = new double[][]{
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
    };
    private final double[][] rotationMatrixX = new double[][]{
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
    };
    private final double[][] rotationMatrixY = new double[][]{
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
    };
    private final double[][] rotationMatrixZ = new double[][]{
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
    };

    protected Vertex[] vertices;
    protected Edge[] edges;
    protected Face[] faces;

    public Shape(int verticesCount, Vertex... vertices)
            throws WrongCountException {
        if (vertices.length != verticesCount) {
            throw new WrongCountException("Wrong vertices count. " +
                    "Correct count is " + verticesCount + ".");
        }
        this.vertices = Arrays.copyOf(vertices, vertices.length);
        initializeEdges();
        initializeFaces();
    }

    protected abstract void initializeEdges();

    protected abstract void initializeFaces();

    public void draw(Graphics2D graphics2D) {
        for (Face face : faces) {
            face.draw(graphics2D);
        }
    }

    private void multiply(Vertex dest, double[][] matrix) {
        double[][] vertexArray = new double[][]{
                {dest.getX()},
                {dest.getY()},
                {dest.getZ()},
                {dest.getOne()}
        };
        double[][] result = null;
        try {
            result = multiply(matrix, vertexArray);
        } catch (MatricesMismatchException exception) {
            System.out.println(exception.getMessage());
            return;
        }
        dest.setX(result[0][0]);
        dest.setY(result[1][0]);
        dest.setZ(result[2][0]);
        dest.setOne(result[3][0]);
    }

    private double[][] multiply(double[][] matrix1, double[][] matrix2)
            throws MatricesMismatchException {
        if (matrix1[0].length != matrix2.length) {
            throw new MatricesMismatchException(
                    "Columns count of the first matrix"
                            + "must be equal to rows"
                            + "count of the second matrix.");
        }
        double buffer = 0;
        double[][] outMatrix = new double[matrix1.length][matrix2[0].length];
        for (int i = 0; i < outMatrix.length; i++) {
            for (int j = 0; j < outMatrix[i].length; j++) {
                for (int k = 0; k < outMatrix.length; k++) {
                    buffer += matrix1[i][k] * matrix2[k][j];
                }
                outMatrix[i][j] = buffer;
                buffer = 0;
            }
        }
        return outMatrix;
    }

    public void transit(int dx, int dy, int dz) {
        transitionMatrix[0][3] = dx;
        transitionMatrix[1][3] = dy;
        transitionMatrix[2][3] = dz;
        for (Vertex vertex : vertices) {
            multiply(vertex, transitionMatrix);
        }
    }

    public void scale(double a, double b, double c) {
        scaleMatrix[0][0] = a;
        scaleMatrix[1][1] = b;
        scaleMatrix[2][2] = c;
        for (Vertex vertex : vertices) {
            multiply(vertex, scaleMatrix);
        }
    }

    public void rotateRad(double radX, double radY, double radZ) {
        double[][] rotationMatrix;
        initializeRotationMatrices(radX, radY, radZ);
        try {
            rotationMatrix = multiply(rotationMatrixX, rotationMatrixY);
            rotationMatrix = multiply(rotationMatrix, rotationMatrixZ);
        } catch (MatricesMismatchException exception) {
            System.out.println(exception.getMessage());
            return;
        }
        for (Vertex vertex : vertices) {
            multiply(vertex, rotationMatrix);
        }
    }

    private void initializeRotationMatrices(double radX,
                                            double radY,
                                            double radZ) {
        rotationMatrixX[1][1] = Math.cos(radX);
        rotationMatrixX[1][2] = -Math.sin(radX);
        rotationMatrixX[2][1] = Math.sin(radX);
        rotationMatrixX[2][2] = Math.cos(radX);

        rotationMatrixY[0][0] = Math.cos(radY);
        rotationMatrixY[0][2] = Math.sin(radY);
        rotationMatrixY[2][0] = -Math.sin(radY);
        rotationMatrixY[2][2] = Math.cos(radY);

        rotationMatrixZ[0][0] = Math.cos(radZ);
        rotationMatrixZ[0][1] = -Math.sin(radZ);
        rotationMatrixZ[1][0] = Math.sin(radZ);
        rotationMatrixZ[1][1] = Math.cos(radZ);
    }

    public void rotateDeg(double degX, double degY, double degZ) {
        rotateRad(degX * Math.PI / 180.0,
                degY * Math.PI / 180.0,
                degZ * Math.PI / 180.0);
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public Edge[] getEdges() {
        return edges;
    }

    public Face[] getFaces() {
        return faces;
    }
}