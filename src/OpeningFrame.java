import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class OpeningFrame extends JFrame {

	public OpeningFrame() {
		OpeningPanel panel = new OpeningPanel();
		add(panel);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-17*40/2, dim.height/2-17*40/2);
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getX() > 250 && e.getX() < 440)// button pressed
				{
					if (e.getY() > 350 && e.getY() < 410)// play
					{
						setVisible(false);
						dispose();
						GameFrame g = new GameFrame(50,0);
					} else if (e.getY() > 440 && e.getY() < 500)// help
					{
						setVisible(false);
						dispose();
						HelpFrame h=new HelpFrame();

					} else if (e.getY() > 530 && e.getY() < 590)// exit
					{
						setVisible(false);
						for(int i=0;i<getFrames().length;i++)
						{
							getFrames()[i].dispose();
						}
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
		setSize(17 * 40, 17 * 40);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
