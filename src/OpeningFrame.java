import javax.swing.JFrame;

public class OpeningFrame extends JFrame implements ExitFrameInterface {

	public OpeningFrame()
	{
		
	}

	@Override
	public void closeFrame() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.dispose();
	}
	public void startFrame(OpeningPanel op)
	{
		add(op);
		setSize(17*40,17*40);
		setVisible(true);
	}
}
