package Coursework;

import Coursework.Exceptions.WrongCountException;
import Coursework.Geometry.Parts.*;
import Coursework.Geometry.Shapes.Shape;

public class Main {

    private static Model model;

    private static Window window;

    private static void printVertex(Vertex vertex) {
        System.out.printf("%f %f %f\n",
                vertex.getX(), vertex.getY(), vertex.getZ());
    }

    private static void print(Model model) {
        for (Shape shape : model.getShapes()) {
            System.out.println("Vertices:");
            for (Vertex vertex : shape.getVertices()) {
                printVertex(vertex);
            }
            System.out.println("Edges:");
            for (Edge edge : shape.getEdges()) {
                for (Vertex vertex : edge.getVertices()) {
                    printVertex(vertex);
                }
            }
            System.out.println("Faces:");
            for (Face face : shape.getFaces()) {
                for (Edge edge : face.getEdges()) {
                    for (Vertex vertex : edge.getVertices()) {
                        printVertex(vertex);
                    }
                }
            }
        }
    }

// TODO: add radius conversion
    public static void main(String[] args) {
        if (args.length < 5) {
            System.out.println("At least 5 arguments expected.");
            return;
        }
        try {
            model = new Model(Double.parseDouble(args[0]),
                              Double.parseDouble(args[1]),
                              Double.parseDouble(args[2]),
                              Double.parseDouble(args[3]),
                              Double.parseDouble(args[4]));
        }
        catch (WrongCountException exception) {
            System.out.println(exception.getMessage());
            return;
        }

        window = new Window("3d shape");
    }

    public static Model getModel() {
        return model;
    }

    public static Window getWindow() {
        return window;
    }
}
