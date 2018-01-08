package images;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Img {
	private Image _image;
	private int x, y, width, height;

	/**
	 * creates new object of img
	 * 
	 * @param path
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Img(String path, int x, int y, int width, int height) {
		_image = new ImageIcon(this.getClass().getClassLoader().getResource(path)).getImage();

		setImgCords(x, y);
		setImgSize(width, height);
	}

	/**
	 * drawing the img
	 * 
	 * @param g
	 */
	public void drawImg(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(_image, x, y, width, height, null);
	}

	/**
	 * set the cords of the img
	 * 
	 * @param x
	 * @param y
	 */
	public void setImgCords(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	/**
	 * set the size of the img
	 * 
	 * @param width
	 * @param height
	 */
	public void setImgSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * set the img
	 * 
	 * @param image
	 */
	public void setImg(Image image) {
		_image = image;
	}

	public Image rotate(double angle) {
		double sin = Math.abs(Math.sin(Math.toRadians(angle))), cos = Math.abs(Math.cos(Math.toRadians(angle)));
		int w = _image.getWidth(null), h = _image.getHeight(null);
		int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h * cos + w * sin);
		BufferedImage bimg = toBufferedImage(getEmptyImage(neww, newh));
		Graphics2D g = bimg.createGraphics();
		g.translate((neww - w) / 2, (newh - h) / 2);
		g.rotate(Math.toRadians(angle), w / 2, h / 2);
		g.drawRenderedImage(toBufferedImage(_image), null);
		g.dispose();
		return toImage(bimg);
	}

	/**
	 * Creates an empty image with transparency
	 * 
	 * @param width
	 *            The width of required image
	 * @param height
	 *            The height of required image
	 * @return The created image
	 */
	public Image getEmptyImage(int width, int height) {
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		return toImage(img);
	}

	/**
	 * Converts a given BufferedImage into an Image
	 * 
	 * @param bimage
	 *            The BufferedImage to be converted
	 * @return The converted Image
	 */
	public Image toImage(BufferedImage bimage) {
		// Casting is enough to convert from BufferedImage to Image
		Image img = (Image) bimage;
		return img;
	}

	/**
	 * Converts a given Image into a BufferedImage
	 * 
	 * @param img
	 *            The Image to be converted
	 * @return The converted BufferedImage
	 */
	public BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}
		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();
		// Return the buffered image
		return bimage;
	}

	/**
	 * Flips an image horizontally. (Mirrors it)
	 * 
	 * @param img
	 *            The source image
	 * @return The image after flip
	 */
	public Image flipImageHorizontally(Image img) {
		int w = img.getWidth(null);
		int h = img.getHeight(null);
		BufferedImage bimg = toBufferedImage(getEmptyImage(w, h));
		Graphics2D g = bimg.createGraphics();
		g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
		g.dispose();
		return toImage(bimg);
	}
}
