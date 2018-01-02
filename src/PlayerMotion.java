
public class PlayerMotion extends Thread {

	private MapPanel _panel;
	public PlayerMotion(MapPanel mp)
	{
		_panel=mp;
	}
	public void run()
	{
		for (int i = 0; i < 2000; i++) {
			_panel.move();
			try{
			sleep(20);
			}
			catch(Exception e){};
		}
	}
}
