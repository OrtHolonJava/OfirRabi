import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Rival implements ActionListener {
	private int _xPosition = 0;// the x position of the player
	private int _yPosition = 13 * GamePanel._blockSize;// the y position of the player
	private Img _image;// the image of the player
	private int _spritesTicker;// counting every delay of timer to change the sprite of the player

	private LinkedList<PlayerMovedInterface> _listeners;// list of panel listeners (mainly the game panel)
	private Player _player;
	private Timer _playerTimer;// the player timer

	private int _fps = 60;// the players' speed

	public Rival(Player p) {
		_image = new Img("WalkingPlayerForward//", _xPosition, _yPosition, 40, 40);// setting the first image
		_listeners = new LinkedList<PlayerMovedInterface>();// initialize the listeners list
		_player = p;
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
		int spriteNumber = _spritesTicker % 10 + 1;
		Image i = new ImageIcon(this.getClass().getClassLoader().getResource("WalkingRival"
				+ _player.getGravityList().get(_player.getTurn() - wait) + "//Walk (" + spriteNumber + ").png"))
				.getImage();
		_image.setImg(i);
		_spritesTicker++;
	}

	static int wait = 10;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (_player.getTurn() > wait) {
			_xPosition = _player.getXList().get(_player.getTurn() - wait);
			_yPosition = _player.getYList().get(_player.getTurn() - wait);
			walk();
		}
		if (_xPosition == _player.getXPosition() && _yPosition == _player.getYPosition()) {
			_player.getTimer().stop();
			_playerTimer.stop();
		}
	}

}
