
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Console;
import java.sql.Time;
import java.util.LinkedList;
import java.util.Timer;

import javax.swing.JPanel;
import javax.swing.text.DefaultEditorKit.BeepAction;

import images.Img;

import map.Map;

public class MapPanel extends JPanel implements KeyListener,ActionListener {
	private javax.swing.Timer _timer; 
	private int _refreshRateX = 3, _refreshRateY = 5, _velX = 0, _velY = 0;
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
	private Img _player;
	private Map _map;
	private String _mapFile;
	private Img _blocks[];
	private LinkedList<Rectangle> _stopers;

	public MapPanel() {
		_timer=new javax.swing.Timer(10, this);
		
		_stopers = new LinkedList<Rectangle>();
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
		_player = new Img("images\\player.png", 0, 0, _blockSize, _blockSize);
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
		_timer.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		_stopers.clear();
		g.translate((int) -_player.getX()+_sizeW, 0);
		_imgBackgound.drawImg(g);
		for (int i = 0; i < _size; i++) {
			for (int j = 0; j < _sizeW; j++) {
				if (_map.get_map()[i][j] != 0 && (j*_blockSize)-_player.getX()>-10*_blockSize) {
					_blocks[_map.get_map()[i][j]].setImgCords(j * _blockSize, i * _blockSize);
					//System.out.println("here"+j * _blockSize);
					_blocks[_map.get_map()[i][j]].drawImg(g);
					Rectangle e = new Rectangle(j * _blockSize,i * _blockSize, _blockSize, _blockSize);
					_stopers.add(e);
				}
			}
		}
		_player.setImgCords(_velX, 12 * _blockSize + _velY);
		_player.drawImg(g);

	}

	public boolean inPlayerRadius(double x,double y )
	{
		if((Math.sqrt((Math.pow(x-_player.getX(), 2.0)+Math.pow(y-_player.getY(), 2.0)))/_blockSize<50))
		{
			//System.out.println(y);
			System.out.println(Math.sqrt((Math.pow(x-_player.getX(), 2.0)+Math.pow(y-_player.getY(), 2.0)))/_blockSize);
			//System.out.println("asdf");
			return true;
		}
		return false;
	}
	public void move() {
		
		boolean up=true,down=true,cont=true;
		
		Rectangle e = new Rectangle(_player.getX(), _player.getY(), _blockSize, _blockSize);
		//System.out.println(e.getY());
		for (int i=0;i<_stopers.size();i++) {
			if (!_stopers.get(i).intersection(e).isEmpty()) {
				if(_stopers.get(i).getX()>e.getX()&&Math.abs(_stopers.get(i).getY()-e.getY())<_blockSize-5)
				{
					
					cont=false;
					//_velX-=_refreshRateX;
				}
				if(_stopers.get(i).getY()<e.getY()&&_stopers.get(i).getX()-e.getX()<_blockSize-5)
				{
					if(_refreshRateY<0)
					{
						System.out.println(_stopers.get(i).getX()-e.getX());
						up=false;
					}
				}
				if(_stopers.get(i).getY()>=e.getY()&&_stopers.get(i).getX()-e.getX()<_blockSize-5)
				{
					if(_refreshRateY>0)
					{	
						down=false;
					}
				}
			}
		}
		if(cont)
		{
			_velX += _refreshRateX;
		}
		if(up&&_refreshRateY<0)
		{
			_velY += _refreshRateY;
		}
		if(down&&_refreshRateY>0)
		{
			_velY += _refreshRateY;
		}
		repaint();
	}

	public void rotatePlayer(double angle)
	{
		_player.setImg(_player.flipImageHorizontally(_player.rotate(angle)));
	}
	public void space() {
		_refreshRateY *= -1;
		rotatePlayer(180);
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_SPACE) {
			space();
		}
		if(code==KeyEvent.VK_ESCAPE)
		{
			_timer.start();
		}
		repaint();

	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_SPACE) {
			 
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		move();
		//_timer.stop();
	}

}
