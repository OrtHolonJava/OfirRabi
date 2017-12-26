
import java.awt.Graphics;
import javax.swing.JPanel;

import images.Img;

import map.Map;

public class MapPanel extends JPanel {
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
		_mapFile ="Maps\\map.xml";
		_size = Map.getElementCountByName(_mapFile,"Line");
		_sizeW = Map.getElementCountByName(_mapFile,"Area")/_size;
		_blockSize = 40;
		_blocks=new Img[12];
		_imgBackgound = new Img("images\\background.png", 0, 0, _sizeW * _blockSize, _size * _blockSize);
		_floorBlock = new Img("images\\floor.png", 0, 0, _blockSize, _blockSize);
		_blocks[3]=_floorBlock;
		_topBlock = new Img("images\\top.png", 0, 0, _blockSize, _blockSize);
		_blocks[2]=_topBlock;
		_iceBlock = new Img("images\\ice.png", 0, 0, _blockSize, _blockSize);
		_blocks[5]=_iceBlock;
		_stoneBlock = new Img("images\\stone.png", 0, 0, _blockSize, _blockSize);
		_blocks[4]=_stoneBlock;
		_insideBlock = new Img("images\\inside.png", 0, 0, _blockSize, _blockSize);
		_blocks[1]=_insideBlock;
		_waterButtomBlock = new Img("images\\waterButtom.png", 0, 0, _blockSize, _blockSize);
		_blocks[9]=_waterButtomBlock;
		_waterTopBlock = new Img("images\\waterTop.png", 0, 0, _blockSize, _blockSize);
		_blocks[7]=_waterTopBlock;
		_crystalBlock=new Img("images\\crystal.png", 0, 0, _blockSize, _blockSize);
		_blocks[8]=_crystalBlock;
		_crateBlock=new Img("images\\crate.png", 0, 0, _blockSize, _blockSize);
		_blocks[6]=_crateBlock;
		_snowMan=new Img("images\\snowMan.png", 0, 0, _blockSize, _blockSize);
		_blocks[10]=_snowMan;
		_sign=new Img("images\\sign.png", 0, 0, _blockSize, _blockSize);
		_blocks[11]=_sign;
	//	_map = new Map(_size, _sizeW, _mapFile);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		_imgBackgound.drawImg(g);
		for (int i = 0; i < _size; i++) {
			for (int j = 0; j < _sizeW; j++) {
				if (_map.get_map()[i][j]!= 0) {
					_blocks[_map.get_map()[i][j]].setImgCords((j * _blockSize), (i) * _blockSize);
					_blocks[_map.get_map()[i][j]].drawImg(g);
				}
			}
		}
	}
}
