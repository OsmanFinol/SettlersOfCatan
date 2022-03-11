import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameState {

	
	private ArrayList<Tile> hexTiles;
	private Board gBoard;
	
	public GameState() {
		hexTiles = new ArrayList<>();
		try {
			Scanner app = new Scanner(new File("Tiles.txt"));
			while (app.hasNextLine()) {
				hexTiles.add(new Tile(app.nextLine()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		gBoard = new Board(hexTiles);
		
	}
	
	public void paint(Graphics g) {
		gBoard.paintTiles(g);
	}
	
	
	
	
}
