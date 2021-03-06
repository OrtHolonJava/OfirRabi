
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameFrame extends JFrame implements PlayerMovedInterface {

	private GamePanel _game;
	private Player _p;
	private Rival _r;
	public GameFrame(int fps,int score) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-17*40/2, dim.height/2-17*40/2);
		_game = new GamePanel();
		_p=new Player(fps,score);
		_p.addListner(_game);
		_p.addListner(this);
		_r=new Rival(_p,fps);
		_r.addListner(_game);
		_r.addListner(this);
		_game.setPlayer(_p);
		_game.setRival(_r);
		this.addKeyListener(_p);
		this.addKeyListener(_r);
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
		_p.getTimer().stop();
		this.setVisible(false);
		this.dispose();
		OverFrame f=new OverFrame(_p.getScore());
	}

	@Override
	public void playerAnsweredCurrect() {
		// TODO Auto-generated method stub
	}

	@Override
	public void playerAnsweredMistake() {
		// TODO Auto-generated method stub
		
	}

}
