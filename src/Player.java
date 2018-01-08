import images.Img;

public class Player  {

	private Img _image;
	public Player(int blockSize)
	{
		_image = new Img("images\\player.png", 0, 0, blockSize, blockSize);
	}
}
