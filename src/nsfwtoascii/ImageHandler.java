package nsfwtoascii;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

class ImageHandler {
    static BufferedImage loadImageFromFile(String path) throws IOException {
        BufferedImage img = ImageIO.read(new File(path));
        return rescaleImage(img);
    }

    static BufferedImage loadImageFromUrl(String address) throws IOException {
        BufferedImage img = ImageIO.read(new URL(address));
        if (img != null) {
            return rescaleImage(img);
        }
        else {
            throw new IOException("Could not load image from URL.");
        }
    }

    private static BufferedImage rescaleImage(BufferedImage img) throws IOException {
        final int NEW_WIDTH = 175;
        double widthScaleFactor = img.getWidth()/NEW_WIDTH;
        img = resize(img, NEW_WIDTH, (int)(img.getHeight()/(2.5 * widthScaleFactor)));
        return img;
    }

    private static BufferedImage resize(BufferedImage img, int newWidth, int newHeight) throws IOException {
        return Thumbnails.of(img).forceSize(newWidth, newHeight).asBufferedImage();
    }
}
