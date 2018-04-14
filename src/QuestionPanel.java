import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class QuestionPanel extends JPanel {

	private int _num1;
	private int _num2;
	private int _result;
	private int _place;
	private Img _background;
	//private int _timeLeft;
	public QuestionPanel() {
		_background = new Img("images//questionBack.jpg", 0, 0, 17 * 40, 17 * 40);
		_num1=0;
		_num2=0;
		_result=0;
		//_timeLeft=0;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		_background.drawImg(g);
		if (_num1 != 0) {
			Random r=new Random();
			int fontSize = 100;
			g.setFont((new Font("Aharoni", Font.PLAIN, fontSize)));
			g.setColor(Color.WHITE);
			g.drawString(_num1 + " x " + _num2 + " = ?", 135, 150);
			
			g.drawString(_result+"",120+170*_place , 390);
			switch(_place)
			{
			case 0:g.drawString(number()+"",120+170*1 , 390);
			   g.drawString(number()+"",120+170*2 , 390);
			   break;
			case 1:g.drawString(number()+"",120+170*0 , 390);
			   g.drawString(number()+"",120+170*2 , 390);
			   break;
			case 2:g.drawString(number()+"",120+170*0, 390);
			   g.drawString(number()+"",120+170*1 , 390);
			   break;
			}
		}
	}

	public void ask(int num1, int num2, int result,int place) {

		_num1=num1;
		_num2=num2;
		_result=_num1*_num2;
		_place=place;
	}
	public int number()
	{
		Random r = new Random();
		int low = _result-5;
		int high = _result+6;
		int res = r.nextInt(high-low) + low;
		while(res<=0||res>=100||res==_result)
		{
			res = r.nextInt(high-low) + low;
		}
		return res;
	}
	/*public void tick(int timeLeft)
	{
		_timeLeft=timeLeft;
		repaint();
	}*/
}
