import java.awt.Image;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import images.Img;

public class Player {

	private Img _image;
	private int _blockSize;
	private int _walkCounter;
	private String _toRotate;

	public Player(int blockSize) {
		_blockSize = blockSize;
		_image = new Img("images\\player.png", 0, 0, blockSize, blockSize);
		_walkCounter = 0;
		_toRotate = "A";
	}

	public Img getImage() {
		return _image;
	}

	public void rotatePlayer() {

		if (_toRotate.equals("A")) {
			_toRotate = "B";
		} else {
			_toRotate = "A";
		}
	}

	public int move(LinkedList<Rectangle> _stopers, int _refreshRateY) {

		boolean up = true, down = true, cont = true;
		int toMove = 0;
		Rectangle e = new Rectangle(_image.getX(), _image.getY(), _blockSize, _blockSize);
		for (int i = 0; i < _stopers.size(); i++) {
			if (!_stopers.get(i).intersection(e).isEmpty()) {
				if (_stopers.get(i).getX() > e.getX()
						&& Math.abs(_stopers.get(i).getY() - e.getY()) < _blockSize - 0.2 * _blockSize) {

					cont = false;
				}
				if (_stopers.get(i).getY() < e.getY()
						&& _stopers.get(i).getX() - e.getX() < _blockSize - 0.2 * _blockSize) {
					if (_refreshRateY < 0) {
						System.out.println(_stopers.get(i).getX() - e.getX());
						up = false;
					}
				}
				if (_stopers.get(i).getY() >= e.getY()
						&& _stopers.get(i).getX() - e.getX() < _blockSize - 0.2 * _blockSize) {
					if (_refreshRateY > 0) {
						down = false;
					}
				}
			}
		}
		if (cont) {
			toMove += 1;
		}
		if (up && _refreshRateY < 0) {
			toMove += 2;
		}
		if (down && _refreshRateY > 0) {
			toMove += 4;
		}
		if (up && down) {
			// jump();
		} else {
			walk();
		}
		if (_image.getY() > 602) {
			// dive and die
		}
		return toMove;
	}

	public void walk() {
		System.out.println(_walkCounter % 13 + 1);
		System.out.println("images\\playerWalk" + _toRotate + "\\Walk (" + (_walkCounter % 13 + 1) + ").png");
		Image i = new ImageIcon(this.getClass().getClassLoader()
				.getResource("images\\playerWalk" + _toRotate + "\\Walk (" + (_walkCounter % 13 + 1) + ").png"))
						.getImage();
		_image.setImg(i);
		_walkCounter++;
	}

	public void jump() {
		Image i = new ImageIcon(
				this.getClass().getClassLoader().getResource("images\\playerJump" + _toRotate + "\\Jump.png"))
						.getImage();
		_image.setImg(i);

	}
}
