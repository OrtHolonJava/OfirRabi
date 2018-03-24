
import javax.swing.JFrame;

public class GameFrame extends JFrame implements PlayerMovedInterface {

	private GamePanel _game;
	private Player _p;
	private Rival _r;
	public GameFrame() {
		_game = new GamePanel();
		_p=new Player();
		_p.addListner(_game);
		_p.addListner(this);
		_r=new Rival(_p);
		_r.addListner(_game);
		_r.addListner(this);
		_game.setPlayer(_p);
		_game.setRival(_r);
		this.addKeyListener(_p);
		add(_game);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(17*40, 17*40);
		setVisible(true);
	}

	@Override
	public void playerMoved() {
		
	}

	@Override
	public void playerLost() {
		// TODO Auto-generated method stub
		_r.getTimer().stop();
		this.setVisible(false);
		this.dispose();
	}

}
