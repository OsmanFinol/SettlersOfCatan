import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {

	private BufferedImage pic;		//picture of tile
	private int diceRoll;			//the dice roll needed to activate it
	private BufferedImage numPic;	//number icon
	private boolean hasRobber;		//to know whether robber is here or not, will be important in board class
	private String type;			//desert, forest, mines, etc.....
	private ResourceCard resource;		//the resource you get from it
	private int xCord; 		//x coord on panel
	private int yCord;		//y cord on panel
	
	
	public Tile(String str) {
		type = str;
		hasRobber = false;
		if (str.equals("Desert")) {
			hasRobber = true;
		}
		try {
			if (!(type.equals("na")))
					pic = ImageIO.read(SettlersOfCatanPanel.class.getResource("/TileImages/" + str +".png"));
		} catch (IOException e) {
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
	
	public void addRobber() {
		hasRobber = true;
	}
	public void removeRobber() {
		hasRobber = true;
	}
	public boolean isRobber() {
		return hasRobber;
	}
	
	
	public void setNum(int dR) {
		diceRoll = dR;
		//set numPic to this later
	}
	
	public String toString() {
		return type;
	}
	
	
}
