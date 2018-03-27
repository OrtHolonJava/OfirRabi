import java.awt.Graphics;

import javax.swing.JPanel;

public class HelpPanel extends JPanel {
private Img _background;
public HelpPanel()
{
	_background=new Img("images//helpBack.jpg", 0, 0, 17*40, 17*40);
}
@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		_background.drawImg(g);
	}
}
