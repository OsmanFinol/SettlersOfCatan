import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Intersection {
	private int xCord, yCord;
	private boolean hasStructure, hasHarbor;
	private ArrayList<Tile> borders;
	private Structure struct;
	private BufferedImage structImage;
	private String color;
	private String st;
	private Harbor har;
	private int typeOfStructure;
	public Intersection(int x, int y, boolean hasHarb, ArrayList<Tile> b) {
		xCord = x;
		yCord = y;
		hasStructure = false;
		hasHarbor = hasHarb;
		borders = b;
		struct = null;
		color = "";
		st = "";
		typeOfStructure = 0;
	}
	public void setColor(String c) {
		color = c;
		
	}
	
	public String getColor() {
		return color;
	}
	public void putStructure(Structure s) {
		struct = s;
	}
	public void setStructure(boolean b) {
		hasStructure = b;
	}
	public void setSet(String s) {
		st = s;
	}
	public String getSet() {
		return st;
	}
	/*public void setImageStructure(BufferedImage b) {
		structImage = b;
	}*/
	public void setImageStructure(int i) {
		typeOfStructure = i;
		if (i == 1) {
			try {
				structImage = ImageIO.read(Intersection.class.getResource("/BuildImages/settlement_" + color.toLowerCase() + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (i == 2) {
			try {
				structImage = ImageIO.read(Intersection.class.getResource("/BuildImages/city_" + color.toLowerCase() + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public BufferedImage getImageStructure() {
		return structImage;
	}
	public boolean hasStructure() {
		return hasStructure;
	}
	public int getXCord() {
		return xCord;
	}
	public int getYCord() {
		return yCord;
	}
	
	public int getTypeOfStructure() {
		return typeOfStructure;
	}
	
	public int getSize(int i) {
		typeOfStructure = i;
		if (i == 1)
			return 20;
		else if (i==2)
			return 46;
		return -1;
	}
	public ArrayList<Tile> getBorders() {
		return borders;
	}
	
	public boolean hasHarbor() {
		return hasHarbor;
	}
	
	public void addHarbor(Harbor h) {
		har = h;
	}
	
}
