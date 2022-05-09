import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Side {
    private int xCord, yCord;
    //private boolean hasStructure, hasHarbor;
    private boolean hasRoad;
    private String direction;
    //private ArrayList<Tile> borders;
    boolean rightStruct = false, leftStruct = false;
    private Structure struct;
    private BufferedImage structImage;
    private String color;
    private String st;
    //private Harbor har;
    //private int typeOfStructure;
    public Side (int x, int y, String d) {
        xCord = x;
        yCord = y;
        hasRoad = false;
        direction = d;
        //hasHarbor = hasHarb;
        //borders = b;
        struct = null;
        color = "";
        st = "";

        //typeOfStructure = 0;
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
    public void setRoad(boolean b) {
        hasRoad = b;
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
    public void setImageRoad(int i) {
        if (i == 0) {
            try {
                structImage = ImageIO.read(Intersection.class.getResource("/BuildImages/road_" + color.toLowerCase() + ".png"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
    public String getDirection() {
        return direction;
    }
    public BufferedImage getImageRoad() {
        return structImage;
    }
    public boolean hasRoad() {
        return hasRoad;
    }
    public boolean hasRightStruct() {
        return rightStruct;
    }
    public boolean hasLeftStruct() {
        return leftStruct;
    }
    public int getXCord() {
        return xCord;
    }
    public int getYCord() {
        return yCord;
    }

/*	public int getTypeOfStructure() {
		return typeOfStructure;
	}*/

	/*public int getSize(int i) {
		typeOfStructure = i;
		if (i == 1)
			return 20;
		else if (i==2)
			return 46;
		return -1;
	}*/
	/*public ArrayList<Tile> getBorders() {
		return borders;
	}

	public boolean hasHarbor() {
		return hasHarbor;
	}

	public void addHarbor(Harbor h) {
		har = h;
	}*/

}
