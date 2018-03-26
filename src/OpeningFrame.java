import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class OpeningFrame extends JFrame {

	public OpeningFrame() {
		OpeningPanel panel = new OpeningPanel();
		add(panel);
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println(e.getX() + "," + e.getY());
				if (e.getX() > 250 && e.getX() < 440)// button pressed
				{
					if (e.getY() > 350 && e.getY() < 410)// play
					{
						setVisible(false);
						dispose();
						GameFrame g = new GameFrame();
					} else if (e.getY() > 440 && e.getY() < 500)// help
					{

					} else if (e.getY() > 530 && e.getY() < 590)// exit
					{
						setVisible(false);
						dispose();
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
