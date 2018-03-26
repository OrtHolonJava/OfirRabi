import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Player implements ActionListener, KeyListener {

	private int _xPosition = 0;// the x position of the player
	private int _yPosition = 13 * GamePanel._blockSize;// the y position of the player
	private Img _image;// the image of the player
	private int _spritesTicker;// counting every delay of timer to change the sprite of the player
	private String _playerGravity;// determine the gravity state of the player: "Forward" if as usual,"Backward"
	// if opposite
	private LinkedList<PlayerMovedInterface> _listeners;// list of panel listeners (mainly the game panel)
	private Timer _playerTimer;// the player timer
	private int _xTemp;// the tempt x changes
	private int _yTemp;// the tempt y changes
	private boolean _isAbleToChangeGravity = false;// check if the player can to rotate
	private int _fps = 60;// the players' speed
	private LinkedList<Integer> _xPositions;
	private LinkedList<Integer> _yPositions;
	private LinkedList<String> _gravities;
	private int _turn;
	private boolean _addToList = true;
	private int _score;
	public Player() {
		_score =0;
		_image = new Img("WalkingPlayerForward//", _xPosition, _yPosition, 40, 40);// setting the first image
		_playerGravity = "Forward";// setting the usual gravity
		_listeners = new LinkedList<PlayerMovedInterface>();// initialize the listeners list
		_xTemp = 10;
		_yTemp = 10;
		_turn = 0;
		_xPositions = new LinkedList<Integer>();
		_yPositions = new LinkedList<Integer>();
		_gravities = new LinkedList<String>();
		_playerTimer = new Timer(1000 / _fps, this);
		_playerTimer.start();

	}

	public void addListner(PlayerMovedInterface p) {
		_listeners.add(p);
	}

	// tell the panels the next move of the player to draw
	public void iMoved() {
		for (PlayerMovedInterface p : _listeners) {
			p.playerMoved();
		}
	}

	public Timer getTimer() {
		return _playerTimer;
	}

	public int getXPosition() {
		return _xPosition;
	}

	public void setXPosition(int x) {
		_xPosition = x;
	}

	public int getYPosition() {
		return _yPosition;
	}

	public void setYPosition(int y) {
		_yPosition = y;
	}

	public Img getImage() {
		return _image;
	}

	public void initiatTicker() {
		_spritesTicker = 0;
	}

	// player walk animation
	public void walk() {
		int spriteNumber = _spritesTicker % 13 + 1;
		Image i = new ImageIcon(this.getClass().getClassLoader()
				.getResource("WalkingPlayer" + _playerGravity + "//Walk (" + spriteNumber + ").png")).getImage();
		_image.setImg(i);
		_spritesTicker++;
	}

	public void checkIntersectionsAndSetMoves() {
		boolean xMove = true, yMove = true;
		_isAbleToChangeGravity = false;
		Rectangle e = new Rectangle(_xPosition, _yPosition, GamePanel._blockSize, GamePanel._blockSize);
		for (int i = 0; i < GamePanel._obstacles.size(); i++) {
			if (!GamePanel._obstacles.get(i).intersection(e).isEmpty()) {
				Rectangle t = GamePanel._obstacles.get(i);
				if (t.getX() - e.getX() < GamePanel._blockSize
						&& Math.abs(t.getY() - e.getY()) + 10 < GamePanel._blockSize) {
					xMove = false;
				}
				if (_yTemp < 0 && e.getY() - t.getY() < GamePanel._blockSize && e.getY() - t.getY() >= 0) {
					yMove = false;
					_isAbleToChangeGravity = true;
				} else if (_yTemp > 0 && t.getY() - e.getY() < GamePanel._blockSize && t.getY() - e.getY() >= 0) {
					yMove = false;
					_isAbleToChangeGravity = true;
				}

			}
		}
		if (!xMove) {
			_xPosition -= _xTemp;
		}
		if (!yMove) {
			_yPosition -= _yTemp;
		}
		_addToList = true;
		if (!xMove && !yMove) {
			_addToList = false;
		}
	}

	public LinkedList<Integer> getXList() {
		return _xPositions;
	}

	public LinkedList<Integer> getYList() {
		return _yPositions;
	}

	public LinkedList<String> getGravityList() {
		return _gravities;
	}

	public int getTurn() {
		return _turn;
	}
	public int getScore()
	{
		return _score;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		_score++;
		_xPosition += _xTemp;
		_yPosition += _yTemp;
		checkIntersectionsAndSetMoves();
		if (_addToList) {
			_xPositions.add(_xPosition);
			_yPositions.add(_yPosition);
			_gravities.add(_playerGravity);
		}
		if (_turn < _xPositions.size() + Rival.wait - 1) {
			_turn++;
		}
		walk();
		iMoved();
		if (_yPosition > 15 * 40 || _yPosition < 0) {
			System.out.println("done");
			_playerTimer.stop();
			for (PlayerMovedInterface p : _listeners) {
				p.playerLost();
			}
		}
	}

	// change the gravity side
	public void changeGravity() {
		if (_isAbleToChangeGravity) {
			if (_playerGravity.equals("Forward")) {
				_playerGravity = "Backward";
			} else {
				_playerGravity = "Forward";
			}
			_yTemp *= -1;
		}
	}

	public String getGravity() {
		return _playerGravity;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			changeGravity();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
