import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class HelpFrame extends JFrame {

	public HelpFrame() {
		HelpPanel panel=new HelpPanel();
		add(panel);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-17*40/2, dim.height/2-17*40/2);
		setSize(17 * 40, 17 * 40);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getX() > 290 && e.getX() < 400)
				{
					if (e.getY() > 435 && e.getY() < 535)
					{
						setVisible(false);
						dispose();
						OpeningFrame g = new OpeningFrame();
					}
				}
				
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
	}

}
