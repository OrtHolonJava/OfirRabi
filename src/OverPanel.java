import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class OverPanel extends JPanel implements ActionListener {
	private Img _image;
	private int _ticker;
	private Timer _timer;
	private int _score;
	public OverPanel(int score)
	{
		_score=score;
		_ticker=0;
		_image = new Img("Dead\\Dead (1).png ", 0, 0, 17*40, 17*40);
		_timer=new Timer(100, this);
		_timer.start();
	}
	public void die()
	{
		int spriteNumber = _ticker % 17 + 1;
		Image i = new ImageIcon(this.getClass().getClassLoader()
				.getResource("Dead"+ "//Dead (" + spriteNumber + ").png")).getImage();
		_image.setImg(i);
		_ticker++;
		if(_ticker%17==0)
		{
			_timer.stop();
			Image a = new ImageIcon(this.getClass().getClassLoader()
					.getResource("images//overBack.png")).getImage();
			_image.setImg(a);
			repaint();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		die();
		repaint();
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if(_timer.isRunning())
		{
			setBackground(Color.BLACK);
			_image.drawImg(g);
		}
		else
		{
			_image.drawImg(g);
			int fontSize = 50;
		    g.setFont((new Font("Aharoni", Font.PLAIN, fontSize)));
			g.setColor(Color.WHITE);
			g.drawString("Your score is: "+_score+"", 75, 375);
		}
	}
}
