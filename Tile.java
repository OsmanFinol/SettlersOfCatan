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
	private String[] numsString;
	private int[] numsInt;
	
	public Tile(String str, String n) {
		tnums = "5 2 6 3 8 10 9 12 11 4 8 10 9 4 5 6 3 11";
		numsString = tnums.split(" "); 
		numsInt = new int[numsString.length];
		numsInt = convert(numsString);
		//System.out.println(Arrays.toString(numsInt));
		
		type = str;
		np = n;
		hasRobber = false;
		if (str.equals("Desert")) {
			hasRobber = true;
		}
		try {
			if (!(type.equals("na")))
					pic = ImageIO.read(SettlersOfCatanPanel.class.getResource("/TileImages/" + str +".png"));
					numPic = ImageIO.read(SettlersOfCatanPanel.class.getResource("/NumImages/" + n +".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < numsInt.length; i++) {
			setNum(numsInt[i]);
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
	
public int[] convert(String[] splitArray) {
		
        int[] array = new int[splitArray.length];
 
        // parsing the String argument as a signed decimal
        // integer object and storing that integer into the
        // array
        for (int i = 0; i < splitArray.length; i++) {
            array[i] = Integer.parseInt(splitArray[i]);
        }
        return array;
	}
	
	
	public void setNum(int dR) {
		diceRoll = dR;
		//set numPic to this later
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
