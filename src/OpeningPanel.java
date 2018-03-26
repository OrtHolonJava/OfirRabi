import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class OpeningPanel extends JPanel {

	private Img _background;
	public OpeningPanel()
	{
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		_background=new Img("images//mainBack.png", 0, 0, 17*40, 17*40);
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		_background.drawImg(g);
	}
}
