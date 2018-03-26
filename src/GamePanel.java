
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements PlayerMovedInterface {

	private Img _objects[]; // Array of all game stand objects
	public static int _blockSize; // size of object
	private Map _maps[]; // map file
	private LinkedList<Map> _mapsToShow;// list of maps to show on panel
	private LinkedList<Integer> _startMap;// list of maps where to start show on
											// panel
	private Player _myPlayer;// the player of the user
	private Rival _rival;
	private int _stepsToEnd;
	private int _finishPosiiton;
	private int _finishTrack;
	public static LinkedList<Rectangle> _obstacles;// ;list of possible
													// obstacles for any player
	private Img _BG;
	public GamePanel() {
		super();
		_blockSize = 40;
		initObjects();
		initMaps();
		
	}

	/**
	 * initializing the objects array
	 */
	public void initObjects() {
		_objects = new Img[12];
		_objects[1] = new Img("images//Crystal.png", 0, 0, _blockSize, _blockSize);
		_objects[2] = new Img("images//IceBox.png", 0, 0, _blockSize, _blockSize);
		_objects[3] = new Img("images//Sign.png", 0, 0, _blockSize, _blockSize);
		_objects[4] = new Img("images//Crate.png", 0, 0, _blockSize, _blockSize);
		_objects[5] = new Img("images//LeftTile.png", 0, 0, _blockSize, _blockSize);
		_objects[6] = new Img("images//InsideTile.png", 0, 0, _blockSize, _blockSize);
		_objects[7] = new Img("images//RightTile.png", 0, 0, _blockSize, _blockSize);
		_objects[8] = new Img("images//ButtomTile.png", 0, 0, _blockSize, _blockSize);
		_objects[9] = new Img("images//Stone.png", 0, 0, _blockSize, _blockSize);
		_objects[10] = new Img("images//Tree.png", 0, 0, _blockSize, _blockSize);
		_objects[11] = new Img("images//SnowButtom.png", 0, 0, _blockSize, _blockSize);
		_BG=new Img("images//BG.png", 0, 0, _blockSize*17, _blockSize*17);
		
	}

	public void setPlayer(Player p) {
		_myPlayer = p;
		initObstacles();
		
	}
	public void setRival(Rival r)
	{
		_rival=r;
	}
	/**
	 * initializing the maps array
	 */
	public void initMaps() {
		String path;// path of map
		int rows, columns;// rows and columns numbers of map
		_maps = new Map[3];
		// creates the maps
		for (int i = 0; i < _maps.length; i++) {
			path = "Maps//map" + i + ".xml";
			rows = Map.getElementCountByName(path, "Line");
			columns = Map.getElementCountByName(path, "Area") / rows;
			_maps[i] = new Map(rows, columns, path);
		}
		// create the random maps list
		_mapsToShow = new LinkedList<Map>();
		_startMap = new LinkedList<Integer>();
		int num=10;
		for (int i = 0; i < num; i++) {
			_mapsToShow.add(_maps[mapToShowNext()]);
		}
		_startMap.add(0);
		for (int i = 1; i < num; i++) {
			_startMap.add(_mapsToShow.get(i - 1).getColumns() + _startMap.get(i - 1));
		}
		_finishPosiiton = _startMap.getLast() + _mapsToShow.getLast().getColumns();
	}

	/**
	 * initialize the obstacles list
	 */
	public void initObstacles() {
		_obstacles = new LinkedList<Rectangle>();
		for (int k = 0; k < _mapsToShow.size(); k++) {
			Map m = _mapsToShow.get(k);
			for (int i = 0; i < m.getRows(); i++) {
				for (int j = 0; j < m.getColumns(); j++) {
					if (m.get_map()[i][j] != 0) {
						Rectangle e = new Rectangle(_startMap.get(k) * _blockSize + j * _blockSize, i * _blockSize,
								_blockSize, _blockSize);
						_obstacles.add(e);
					}
				}
			}
		}
	}

	/**
	 * random over all maps
	 * 
	 * @return map id
	 */
	public int mapToShowNext() {
		Random r = new Random();
		int mapNumber;
		mapNumber = r.nextInt(_maps.length);
		return mapNumber;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		_BG.drawImg(g);
		if (_stepsToEnd >= 14 * 40) {
			g.translate(-_myPlayer.getXPosition() + 3 * _blockSize, 0);// tracking
																		// the
			_finishTrack=-_myPlayer.getXPosition()+ 3 * _blockSize;														// player
		}
		else
		{
			g.translate(_finishTrack,0);
		}
		// fill the panel with random maps
		for (int k = 0; k < _mapsToShow.size(); k++) {
			Map m = _mapsToShow.get(k);
			for (int i = 0; i < m.getRows(); i++) {
				for (int j = 0; j < m.getColumns(); j++) {
					if (m.get_map()[i][j] != 0) {
						_objects[m.get_map()[i][j]].setImgCords(_startMap.get(k) * _blockSize + j * _blockSize,
								i * _blockSize);
						_objects[m.get_map()[i][j]].drawImg(g);
					}
				}
			}
		}
		_myPlayer.getImage().setImgCords(_myPlayer.getXPosition(), _myPlayer.getYPosition());
		_myPlayer.getImage().drawImg(g);
		_rival.getImage().setImgCords(_rival.getXPosition(), _rival.getYPosition());
		_rival.getImage().drawImg(g);
	}

	/**
	 * check if whole map is done
	 * 
	 * @return
	 */
	public boolean isFinishedLevel() {
		_stepsToEnd = _finishPosiiton * _blockSize - _myPlayer.getXPosition();
		if ((_startMap.getLast() * _blockSize - _myPlayer.getXPosition())
				* -1 >= _mapsToShow.getLast().getColumns() * _blockSize - 1000) {
			return true;
		}
		return false;
	}

	@Override
	public void playerMoved() {
		// TODO Auto-generated method stub
		isFinishedLevel();
		if (_stepsToEnd <= 0) {
			_myPlayer.getTimer().stop();
		}
		repaint();
	}

	@Override
	public void playerLost() {
		// TODO Auto-generated method stub

	}
}
