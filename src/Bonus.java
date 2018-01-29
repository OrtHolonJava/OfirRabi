
public class Bonus {

	private int _speedChange;
	private int _row;
	private int _col;
	public Bonus(int speed,int row,int col)
	{
		_speedChange=speed;
		_row=row;
		_col=col;
	}
	public int getSpeedChange() {
		return _speedChange;
	}
	public void setSpeedChange(int speedChange) {
		_speedChange = speedChange;
	}
	public int getRow() {
		return _row;
	}
	public void setRow(int row) {
		_row = row;
	}
	public int getCol() {
		return _col;
	}
	public void setCol(int col) {
		_col = col;
	}
	
}
