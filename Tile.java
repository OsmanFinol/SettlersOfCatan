import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {

	private BufferedImage pic;
	private int diceRoll;
	private BufferedImage numPic;
	private boolean hasRobber;
	private String type;
	private ResourceCard resource;
	private int xCord;
	private int yCord;
	
	
	public Tile(String str) {
		type = str;
		try {
			if (!(type.equals("na")))
					pic = ImageIO.read(SettlersOfCatanPanel.class.getResource("/TileImages/" + str +".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedImage getImage() {
		return pic;
	}
	
	public void setXCord(int x) {
		xCord = x;
	}
	
	public void setYCord(int y) {
		yCord = y;
	}
	public int getXCord() {
		return xCord;
	}
	public int getYCord() {
		return yCord;
	}
	
	public String toString() {
		return type;
	}
	
	
}
