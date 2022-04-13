import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Tile {

	private BufferedImage pic;		//picture of tile
	private int diceRoll;			//the dice roll needed to activate it
	private BufferedImage numPic;	//number icon
	private boolean hasRobber;		//to know whether robber is here or not, will be important in board class
	private String type;			//desert, forest, mines, etc.....
	private String np;
	private ResourceCard resource;		//the resource you get from it
	private int xCord; 		//x coord on panel
	private int yCord;		//y cord on panel
	
	private String tnums;
	//private
	
	public Tile() {
		type = "";
		np = "";
		hasRobber = false;
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
	public void setNum(String str) {
		//set numPic to this later
		try {
			numPic = ImageIO.read(SettlersOfCatanPanel.class.getResource("/NumImages/" + str +".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setImage(String str) {
		type = str;
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
	public String getNum() {
		return Integer.toString(diceRoll);
	}
	public BufferedImage getNumImage() {
		return numPic;
	}
	
	public String toString() {
		return type;
	}
	
	
}
