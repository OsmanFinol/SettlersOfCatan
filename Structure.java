import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Structure {

	private int type;
	private String col;
	private BufferedImage struct;
	private ArrayList<ResourceCard> yield;
	private boolean nearHarbor;
	
	public Structure(int i, String c) {
		type = i;
		col = c;
	}
	
	public void upgrade() {
		if (type == 1) 
			type = 2;
	}
	public boolean getNearHarbor()
	{
		return nearHarbor;
	}
	
	
	
}
