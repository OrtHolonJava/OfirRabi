import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class LostPanel extends JPanel {
	private JButton _startBtn;
	private JButton _instructionsBtn;
	private JButton _exitBtn;
	private Img _background;
	private GameFrame _mainFrame;
	private Font _font;
	private Color _color;
	public LostPanel()
	{
		setLayout(null);
		_background=new Img("images//gameOver.jpg", 0, 0, 17*40, 17*40);
		_color=new Color(128,0,128);
		_font=new Font("Ariel", Font.BOLD, 12);
		_startBtn=new JButton();
		_startBtn.setText("START");
		_startBtn.setForeground(_color);
		_startBtn.setFont(_font);
		_startBtn.setOpaque(false);
		_startBtn.setContentAreaFilled(false);
		_startBtn.setBorderPainted(false);
		_startBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				GameFrame g = new GameFrame();
				closeMainFrame();
			}
		});
		
		
		_instructionsBtn=new JButton();
		_instructionsBtn.setText("INSTRUCTIONS");
		_instructionsBtn.setForeground(_color);
		_instructionsBtn.setFont(_font);
		_instructionsBtn.setOpaque(false);
		_instructionsBtn.setContentAreaFilled(false);
		_instructionsBtn.setBorderPainted(false);
		_instructionsBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		_exitBtn=new JButton();
		_exitBtn.setText("EXIT");
		_exitBtn.setForeground(_color);
		_exitBtn.setFont(_font);
		_exitBtn.setOpaque(false);
		_exitBtn.setContentAreaFilled(false);
		_exitBtn.setBorderPainted(false);
		_exitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closeMainFrame();
			}
		});
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		_background.setImgCords(0, 0);
		_background.drawImg(g);
	}
	public void setMainFrame(GameFrame e)
	{
		_mainFrame=e;
		setPanel();
	}
	public void closeMainFrame()
	{
		_mainFrame.closeFrame();
	}
	public void setPanel()
	{
		_startBtn.setBounds(14*40/2, 15*40/2, 120, 40);
		_instructionsBtn.setBounds(14*40/2, 18*40/2, 120, 40);
		_exitBtn.setBounds(14*40/2, 21*40/2, 120, 40);
		add(_startBtn);
		add(_instructionsBtn);
		add(_exitBtn);
	}
}
