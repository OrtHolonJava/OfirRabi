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

	public MapPanel() {
		_size = 15;
		_sizeW = 37;
		_blockSize = 60;
		_imgBackgound = new Img("images\\background.png", 0, 0, _sizeW * _blockSize, _size * _blockSize);
		_floorBlock = new Img("images\\floor.png", 0, 0, _blockSize, _blockSize);
		_topBlock = new Img("images\\top.png", 0, 0, _blockSize, _blockSize);
		_iceBlock = new Img("images\\ice.png", 0, 0, _blockSize, _blockSize);
		_stoneBlock = new Img("images\\stone.png", 0, 0, _blockSize, _blockSize);
		_insideBlock = new Img("images\\inside.png", 0, 0, _blockSize, _blockSize);
		_waterButtomBlock = new Img("images\\waterButtom.png", 0, 0, _blockSize, _blockSize);
		_waterTopBlock = new Img("images\\waterTop.png", 0, 0, _blockSize, _blockSize);
		_crystalBlock=new Img("images\\crystal.png", 0, 0, _blockSize, _blockSize);
		_crateBlock=new Img("images\\crate.png", 0, 0, _blockSize, _blockSize);
		_snowMan=new Img("images\\snowMan.png", 0, 0, _blockSize, _blockSize);
		_sign=new Img("images\\sign.png", 0, 0, _blockSize, _blockSize);
		//_map = new Map(_size, _sizeW, "Maps\\file1.xml");
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		_imgBackgound.drawImg(g);
		for (int i = 0; i < _size; i++) {
			for (int j = 0; j < _sizeW; j++) {
				if (_map.get_map()[i][j]
						== 1) {
					_floorBlock.setImgCords((j * _blockSize), (i) * _blockSize);
					_floorBlock.drawImg(g);
				}
				if (_map.get_map()[i][j] == 2) {
					_topBlock.setImgCords((j * _blockSize), (i) * _blockSize);
					_topBlock.drawImg(g);
				}
				if (_map.get_map()[i][j] == 3) {
					_iceBlock.setImgCords((j * _blockSize), (i) * _blockSize);
					_iceBlock.drawImg(g);
				}
				if (_map.get_map()[i][j] == 4) {
					_stoneBlock.setImgCords((j * _blockSize), (i) * _blockSize);
					_stoneBlock.drawImg(g);
				}
				if (_map.get_map()[i][j] == 5) {
					_insideBlock.setImgCords((j * _blockSize), (i) * _blockSize);
					_insideBlock.drawImg(g);
				}
				if (_map.get_map()[i][j] == 6) {
					_waterButtomBlock.setImgCords((j * _blockSize), (i) * _blockSize);
					_waterButtomBlock.drawImg(g);
				}
				if (_map.get_map()[i][j] == 7) {
					_waterTopBlock.setImgCords((j * _blockSize), (i) * _blockSize);
					_waterTopBlock.drawImg(g);
				}
				if (_map.get_map()[i][j] == 8) {
					_crystalBlock.setImgCords((j * _blockSize), (i) * _blockSize);
					_crystalBlock.drawImg(g);
				}
				if (_map.get_map()[i][j] == 9) {
					_crateBlock.setImgCords((j * _blockSize), (i) * _blockSize);
					_crateBlock.drawImg(g);
				}
				if (_map.get_map()[i][j] == 10) {
					_snowMan.setImgCords((j * _blockSize), (i) * _blockSize);
					_snowMan.drawImg(g);
				}
				if (_map.get_map()[i][j] == 11) {
					_sign.setImgCords((j * _blockSize), (i) * _blockSize);
					_sign.drawImg(g);
				}

			}
		}
	}
}
