import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import images.Img;

public class Player {

	private Img _image;
	private int _blockSize;
	private int _jumpCounter;
	private boolean isGravity;

	public Player(int blockSize) {
		_blockSize = blockSize;
		_image = new Img("images\\player.png", 0, 0, blockSize, blockSize);
		_jumpCounter = 1 * 10;
		isGravity = false;
	}

	public Img getImage() {
		return _image;
	}

	public void rotatePlayer(double angle, boolean changeIsGravity) {
		_image.setImg(_image.flipImageHorizontally(_image.rotate(angle)));
		if (changeIsGravity) {
			isGravity = !isGravity;
		}
	}

	public int move(LinkedList<Rectangle> _stopers, int _refreshRateY) {

		boolean up = true, down = true, cont = true;
		int toMove = 0;
		Rectangle e = new Rectangle(_image.getX(), _image.getY(), _blockSize, _blockSize);
		// System.out.println(e.getY());
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
		walk();
		return toMove;
	}

	public void walk() {
		Image i = new ImageIcon(this.getClass().getClassLoader()
				.getResource("images\\playerWalk\\Walk (" + _jumpCounter / 10 + ").png")).getImage();
		// Img i=new Img("images\\playerJump\\("+_jumpCounter+").png", 0, 0,
		// _blockSize, _blockSize);
		_image.setImg(i);
		if (isGravity) {
			rotatePlayer(180, false);
		}
		if (_jumpCounter == 13 * 10) {
			_jumpCounter = 1 * 10;
		} else {
			_jumpCounter++;
		}
	}
}
