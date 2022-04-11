import java.util.ArrayList;

public class Intersection {
	private int xCord, yCord;
	private boolean hasStructure, hasHarbor;
	private ArrayList<Tile> borders;
	private Structure struct;
	
	public Intersection(int x, int y, boolean hasHarb, ArrayList<Tile> b) {
		xCord = x;
		yCord = y;
		hasStructure = false;
		hasHarbor = hasHarb;
		borders = b;
		struct = null;
	}
	
	public void putStructure(Structure s) {
		struct = s;
	}
	
	
	
	
	
}
