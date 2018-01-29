import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameFrame extends JFrame implements PlayerMovedInterface {

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
		setSize(getMaximumSize().width, 16*40);
		setVisible(true);
	}

	@Override
	public void playerMoved() {
		
	}
}
