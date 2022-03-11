import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class Board {

	Tile[][] gameBoard;
	
	public Board(ArrayList<Tile> arrList) {
		Iterator<Tile> iter = arrList.iterator();
		gameBoard = new Tile[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if ((i == 0 && j == 0) || (i== 0 && j ==4) || (i ==1 && j == 0) ||
						(i == 3 && j ==0) || (i == 4 && j == 0) || (i == 4 && j == 4)) {
					gameBoard[i][j] = null;
					continue;
				}
				Tile t = iter.next();
				gameBoard[i][j] = t;
				t = gameBoard[i][j];
				if (i == 0 && j == 1) {
					t.setXCord(600);
					t.setYCord(200);
				}
				else if (i == 0 && j == 2) {
					t.setXCord(700);
					t.setYCord(200);
				}
				else if (i == 0 && j == 3) {
					t.setXCord(800);
					t.setYCord(200);
				}
				else if (i == 1 && j == 1) {
					t.setXCord(550);
					t.setYCord(275);
				}
				else if (i == 1 && j == 2) {
					t.setXCord(650);
					t.setYCord(275);
				}
				else if (i == 1 && j == 3) {
					t.setXCord(750);
					t.setYCord(275);
				}
				else if (i == 1 && j == 4) {
					t.setXCord(850);
					t.setYCord(275);
				}
				else if (i == 2 && j == 0) {
					t.setXCord(500);
					t.setYCord(350);
				}
				else if (i == 2 && j == 1) {
					t.setXCord(600);
					t.setYCord(350);
				}
				else if (i == 2 && j == 2) {
					t.setXCord(700);
					t.setYCord(350);
				}
				else if (i == 2 && j == 3) {
					t.setXCord(800);
					t.setYCord(350);
				}
				else if (i == 2 && j == 4) {
					t.setXCord(900);
					t.setYCord(350);
				}
				else if (i == 3 && j == 1) {
					t.setXCord(550);
					t.setYCord(425);
				}
				else if (i == 3 && j == 2) {
					t.setXCord(650);
					t.setYCord(425);
				}
				else if (i == 3 && j == 3) {
					t.setXCord(750);
					t.setYCord(425);
				}
				else if (i == 3 && j == 4) {
					t.setXCord(850);
					t.setYCord(425);
				}
				else if (i == 4 && j == 1) {
					t.setXCord(600);
					t.setYCord(500);
				}
				else if (i == 4 && j == 2) {
					t.setXCord(700);
					t.setYCord(500);
				}
				else if (i == 4 && j == 3) {
					t.setXCord(800);
					t.setYCord(500);
				}
			}
		}
	}
	
	public void paintTiles(Graphics g) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Tile temp = gameBoard[i][j];
				if (temp != null) {
					g.drawImage(temp.getImage(), temp.getXCord(), temp.getYCord(), 100, 100, null);
				}
			}
		}
	}
	
	
	
}
