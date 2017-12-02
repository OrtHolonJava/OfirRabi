import javax.swing.JFrame;

public class MapFrame extends JFrame {

	private MapPanel _map;
	public MapFrame()
	{
		_map=new MapPanel();
		add(_map);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(2000, 1000);
	    setVisible(true);

	}
}
