import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class OpeningPanel extends JPanel {

	private JButton _startBtn;
	private JButton _instructionsBtn;
	private JButton _exitBtn;
	private Img _background;
	private OpeningFrame _mainFrame;
	
	public OpeningPanel()
	{
		setLayout(null);
		_background=new Img("images//gameBackground.png", 0, 0, 17*40, 17*40);
		
		_startBtn=new JButton();
		_startBtn.setText("START");
		_startBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				GameFrame g = new GameFrame();
				closeMainFrame();
			}
		});
		
		
		_instructionsBtn=new JButton();
		_instructionsBtn.setText("INSTRACTIONS");
		_instructionsBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		_exitBtn=new JButton();
		_exitBtn.setText("EXIT");
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
	public void setMainFrame(OpeningFrame e)
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
		_startBtn.setBounds(14*40/2, 17*40/2, 120, 40);
		_instructionsBtn.setBounds(14*40/2, 20*40/2, 120, 40);
		_exitBtn.setBounds(14*40/2, 23*40/2, 120, 40);
		add(_startBtn);
		add(_instructionsBtn);
		add(_exitBtn);
	}
}
