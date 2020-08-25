import Exceptions.WrongCountException;
import Geometry.Parts.Edge;
import Geometry.Parts.Face;
import Geometry.Parts.Vertex;
import Geometry.Shapes.Shape;

public class Main {

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

    public static void main(String[] args) {

    }
}
