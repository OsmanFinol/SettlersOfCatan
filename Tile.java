import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Tile {

	private BufferedImage pic; // picture of tile
	private int number; // the dice roll needed to activate it
	private BufferedImage numPic; // number icon
	private boolean hasRobber; // to know whether robber is here or not, will be important in board class
	private String type; // desert, forest, mines, etc.....
	private String np;
	private ResourceCard resource; // the resource you get from it
	private int xCord; // x coord on panel
	private int yCord; // y cord on panel

	private String tnums;
	// private

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

	public String getType() {
		return type;
	}

	public ArrayList<ResourceCard> getResources(int i) {
		ArrayList<ResourceCard> temp = new ArrayList<>();
		for (int c = 0; c < i; c++) {
			temp.add(resource);
		}
		return temp;
	}

	public void addRobber() {
		hasRobber = true;
	}

	public void removeRobber() {
		hasRobber = false;
	}

	public boolean isRobber() {
		return hasRobber;
	}

	public void setNum(int i) {
		// set numPic to this later
		number = i;
		try {
			numPic = ImageIO.read(SettlersOfCatanPanel.class.getResource("/NumImages/num" + i + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setImage(String str) {
		type = str;
		if (type.equals("Forest"))
			resource = new ResourceCard("Wood");
		else if (type.equals("Mountains"))
			resource = new ResourceCard("Stone");
		else if (type.equals("Fields"))
			resource = new ResourceCard("Grain");
		else if (type.equals("Plains"))
			resource = new ResourceCard("Sheep");
		else if (type.equals("Mines"))
			resource = new ResourceCard("Brick");
		if (str.equals("Desert")) {
			hasRobber = true;
		}
		try {
			pic = ImageIO.read(SettlersOfCatanPanel.class.getResource("/TileImages/" + str + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getNum() {
		return number;
	}

	public BufferedImage getNumImage() {
		return numPic;
	}

	public String toString() {
		return type;
	}

}
