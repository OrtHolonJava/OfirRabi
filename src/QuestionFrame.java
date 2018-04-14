import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class QuestionFrame extends JFrame implements ActionListener {

	private int _num1;
	private int _num2;
	private int _result;
	private int _place;
	private int _fps;
	private int _score;
	//private Timer _timer;
	//private int _timeLeft;
	private QuestionPanel _panel;
	public QuestionFrame(int fps, int score) {
		//_timer=new Timer(100, this);
		//_timeLeft=10;
		_fps=fps;
		_score=score;
		Random r = new Random();
		_num1 = r.nextInt(10) + 1;
		_num2 = r.nextInt(10) + 1;
		_result = _num1 * _num2;
		while (_result == 100) {
			_num1 = r.nextInt(10) + 1;
			_num2 = r.nextInt(10) + 1;
			_result = _num1 * _num2;
		}
		_place = r.nextInt(3);
		_panel = new QuestionPanel();
		add(_panel);
		_panel.ask(_num1, _num2, _result, _place);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - 17 * 40 / 2, dim.height / 2 - 17 * 40 / 2);
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getY() > 345 && e.getY() < 450) {
					if (e.getX() > 120 && e.getX() < 230) {
						if (_place == 0) {
							reset();
						}
						else
						{
							over();
						}

					} else if (e.getX() > 290 && e.getX() < 400) {
						if (_place == 1) {
							reset();
						}
						else
						{
							over();
						}

					} else if (e.getX() > 460 && e.getX() < 565) {
						if (_place == 2) {
							reset();
						}
						else
						{
							over();
						}

					}
				}
			//	_timer.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		setSize(17 * 40, 17 * 40);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	public void reset()
	{
		for(int i=0;i<getFrames().length;i++)
		{
			getFrames()[i].dispose();
		}
		GameFrame f=new GameFrame(_fps+10, _score);
	}
	public void over()
	{
		OverFrame o=new OverFrame(_score);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//_timeLeft--;
		//_panel.tick(_timeLeft);
	}
}
