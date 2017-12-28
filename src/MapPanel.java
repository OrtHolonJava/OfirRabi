
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import images.Img;

import map.Map;

public class MapPanel extends JPanel implements KeyListener {
	private int refreshRate = 1, velX = 0, velY = 0;
	private int _size;
	private int _sizeW;
	private int _blockSize;
	private Img _imgBackgound;
	private Img _floorBlock;
	private Img _topBlock;
	private Img _iceBlock;
	private Img _stoneBlock;
	private Img _insideBlock;
	private Img _waterButtomBlock;
	private Img _waterTopBlock;
	private Img _crystalBlock;
	private Img _crateBlock;
	private Img _snowMan;
	private Img _sign;
	private Map _map;
	private String _mapFile;
	private Img _blocks[];

	public MapPanel() {

		_mapFile = "Maps\\map.xml";
		_size = Map.getElementCountByName(_mapFile, "Line");
		_sizeW = Map.getElementCountByName(_mapFile, "Area") / _size;
		_blockSize = 40;
		_blocks = new Img[12];
		_imgBackgound = new Img("images\\background.png", 0, 0, _sizeW * _blockSize, _size * _blockSize);
		_floorBlock = new Img("images\\floor.png", 0, 0, _blockSize, _blockSize);
		_blocks[3] = _floorBlock;// down floor with snow
		_topBlock = new Img("images\\top.png", 0, 0, _blockSize, _blockSize);
		_blocks[2] = _topBlock;// up block
		_iceBlock = new Img("images\\ice.png", 0, 0, _blockSize, _blockSize);
		_blocks[5] = _iceBlock;// ice cubes
		_stoneBlock = new Img("images\\stone.png", 0, 0, _blockSize, _blockSize);
		_blocks[4] = _stoneBlock;// stone
		_insideBlock = new Img("images\\inside.png", 0, 0, _blockSize, _blockSize);
		_blocks[1] = _insideBlock;// the base of the floor
		_waterButtomBlock = new Img("images\\waterButtom.png", 0, 0, _blockSize, _blockSize);
		_blocks[9] = _waterButtomBlock;// the base of the water
		_waterTopBlock = new Img("images\\waterTop.png", 0, 0, _blockSize, _blockSize);
		_blocks[7] = _waterTopBlock;// the waves of the water
		_crystalBlock = new Img("images\\crystal.png", 0, 0, _blockSize, _blockSize);
		_blocks[8] = _crystalBlock;// crystal
		_crateBlock = new Img("images\\crate.png", 0, 0, _blockSize, _blockSize);
		_blocks[6] = _crateBlock;// box
		_snowMan = new Img("images\\snowMan.png", 0, 0, _blockSize, _blockSize);
		_blocks[10] = _snowMan;// snow man
		_sign = new Img("images\\sign.png", 0, 0, _blockSize, _blockSize);
		_blocks[11] = _sign;// sign to continue
		_map = new Map(_size, _sizeW, _mapFile);
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				repaint();
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				repaint();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
			g.translate((int)-MouseInfo.getPointerInfo().getLocation().getX(), 0);
			_imgBackgound.drawImg(g);
			for (int i = 0; i < _size; i++) {
				for (int j = 0; j < _sizeW; j++) {
					if (_map.get_map()[i][j] != 0) {
						_blocks[_map.get_map()[i][j]].setImgCords(j * _blockSize, i * _blockSize);
						_blocks[_map.get_map()[i][j]].drawImg(g);
					}
				}
			}
	}

	public void up() {
		velY += -refreshRate;
		velX = 0;
	}

	public void down() {
		velY += refreshRate;
		velX = 0;
	}

	public void left() {
		velX += refreshRate;
		velY = 0;
	}

	public void right() {
		velX += -refreshRate;
		velY = 0;
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_UP) {
			up();
		}

		if (code == KeyEvent.VK_DOWN) {
			down();
		}

		if (code == KeyEvent.VK_LEFT && velX <= 0) {
			left();
		}

		if (code == KeyEvent.VK_RIGHT && velX > -(_sizeW * _blockSize - 5)) {
			right();
		}
		repaint();

	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_UP) {
			velY += 0;
		}
		if (code == KeyEvent.VK_DOWN) {
			velY += 0;
		}
		if (code == KeyEvent.VK_LEFT) {
			velX += 0;
		}
		if (code == KeyEvent.VK_RIGHT) {
			velX += 0;
		}
	}

	
}
