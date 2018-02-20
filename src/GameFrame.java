
import javax.swing.JFrame;

public class GameFrame extends JFrame implements PlayerMovedInterface,ExitFrameInterface {

	private GamePanel _game;
	private Player _p;
	public GameFrame() {
		_game = new GamePanel();
		_p=new Player();
		_p.addListner(_game);
		_p.addListner(this);
		_game.setPlayer(_p);
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
		this.setVisible(false);
		this.dispose();
	}

	@Override
	public void closeFrame() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.dispose();
		
	}
}
