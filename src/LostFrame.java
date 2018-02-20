import javax.swing.JFrame;

public class LostFrame extends JFrame implements ExitFrameInterface {
	
	public LostFrame()
	{
		
	}

	@Override
	public void closeFrame() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.dispose();
	}
	public void startFrame(LostPanel op)
	{
		add(op);
		setSize(17*40,17*40);
		setVisible(true);
	}

}
