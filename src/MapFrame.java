
import javax.swing.JFrame;

public class MapFrame extends JFrame {

	private MapPanel _map;
	private PlayerMotion _motion;
	public MapFrame()
	{
		_map=new MapPanel();
		add(_map);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(2000, 1000);
	    setVisible(true);
	    this.addKeyListener(_map);
	    _map.move();
	 //   _motion=new PlayerMotion(_map);
	    //_motion.start();

	}
}
