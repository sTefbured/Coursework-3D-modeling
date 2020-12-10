package coursework.geometry.utils;

import coursework.geometry.parts.Face;
import coursework.geometry.shapes.Shape;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

public class ZBuffer {
    private Color[][] pixelColors;
    private BufferedImage image;
    private double[][] buffer;
    private int projectionMode;

    public ZBuffer(int screenWidth, int screenHeight, int projectionMode) {
        this.projectionMode = projectionMode;
        pixelColors = new Color[screenHeight][screenWidth];
        buffer = new double[screenHeight][screenWidth];

        image = new BufferedImage(screenWidth,
                screenHeight, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < screenHeight; i++) {
            for (int j = 0; j < screenWidth; j++) {
                pixelColors[i][j] = Color.WHITE;
                buffer[i][j] = -100000000000f;
                try {
                    image.setRGB(i, j, Color.WHITE.getRGB());
                } catch (ArrayIndexOutOfBoundsException exception) {

                }
            }
        }
    }

    public void initializeBuffer(Shape[] shapes) {
        ArrayList<Face> faces = getFaces(shapes);
        for (Face face : faces) {
            for (int i = 0; i < buffer.length; i++) {
                for (int j = 0; j < buffer[i].length; j++) {
//                    checkPixel(j, i, face);
                }
            }
        }
    }

    private ArrayList<Face> getFaces(Shape[] shapes) {
        ArrayList<Face> faces = new ArrayList<>();
        for (Shape shape : shapes) {
            Collections.addAll(faces, shape.getFaces());
        }
        faces.sort((ob1, ob2) -> {
            if (ob1.getCenter().getZ() < ob2.getCenter().getZ()) {
                return -1;
            } else if (ob1.getCenter().getZ() > ob2.getCenter().getZ()) {
                return 1;
            }
            return 0;
        });
        return faces;
    }

//    private void checkPixel(int x, int y, Face face) {
//        try {
//            if (face.getPolygon(projectionMode).intersects(x, y, 1, 1)) {
//                if (buffer[x][y] < face.getZ(x, y)) {
//                    buffer[x][y] = face.getZ(x, y);
//                    image.setRGB(x, y, face.getColor());
//                }
//            }
//        } catch (ArrayIndexOutOfBoundsException exception) {
//
//        }
//    }

    public BufferedImage getImage() {
        return image;
    }
}
