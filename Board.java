import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Board {


	private Tile[][] gameBoard;
	/*private String tnums;
	//private 
	private String[] numsString;
	private int[] numsInt;*/
	public Board(ArrayList<Tile> arrList) {

		Iterator<Tile> iter = arrList.iterator();
		
		/*tnums = "5 2 6 3 8 10 9 12 11 4 8 10 9 4 5 6 3 11";
		numsString = tnums.split(" "); 
		numsInt = new int[numsString.length];
		numsInt = convert(numsString);
		System.out.println(Arrays.toString(numsInt));*/

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
					t.setXCord(585);
					t.setYCord(125);
				}
				else if (i == 0 && j == 2) {
					t.setXCord(695);
					t.setYCord(125);
				}
				else if (i == 0 && j == 3) {
					t.setXCord(805);
					t.setYCord(125);
				}
				else if (i == 1 && j == 1) {
					t.setXCord(530);
					t.setYCord(208);
				}
				else if (i == 1 && j == 2) {
					t.setXCord(640);
					t.setYCord(208);
				}
				else if (i == 1 && j == 3) {
					t.setXCord(750);
					t.setYCord(208);
				}
				else if (i == 1 && j == 4) {
					t.setXCord(860);
					t.setYCord(208);
				}

				else if (i == 2 && j == 0) {
					t.setXCord(475);
					t.setYCord(291);
				}
				else if (i == 2 && j == 1) {
					t.setXCord(585);
					t.setYCord(291);
				}
				else if (i == 2 && j == 2) {
					t.setXCord(695);
					t.setYCord(291);
				}
				else if (i == 2 && j == 3) {
					t.setXCord(805);
					t.setYCord(291);
				}
				else if (i == 2 && j == 4) {
					t.setXCord(915);
					t.setYCord(291);
				}

				else if (i == 3 && j == 1) {
					t.setXCord(530);
					t.setYCord(374);
				}
				else if (i == 3 && j == 2) {
					t.setXCord(640);
					t.setYCord(374);
				}
				else if (i == 3 && j == 3) {
					t.setXCord(750);
					t.setYCord(374);
				}
				else if (i == 3 && j == 4) {
					t.setXCord(860);
					t.setYCord(374);
				}
				else if (i == 4 && j == 1) {
					t.setXCord(585);
					t.setYCord(457);
				}
				else if (i == 4 && j == 2) {
					t.setXCord(695);
					t.setYCord(457);
				}
				else if (i == 4 && j == 3) {
					t.setXCord(805);
					t.setYCord(457);
				}
			}
		}

	}


	public void nums() {
		//String nums = "5 2 6 3 8 10 9 12 11 4 8 10 9 4 5 6 3 11";
	/*	for (int r = 0; r < gameBoard.length; r++) {
			for (int c = 0; c < gameBoard[r].length; c++) {
				Tile temp = gameBoard[r][c];
				temp.setNum(nums.sub)
			}
		}*/
	}


	public void paintTiles(Graphics g) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Tile temp = gameBoard[i][j];
				if (temp != null) {
					g.drawImage(temp.getImage(), temp.getXCord(), temp.getYCord(), 110, 110, null);
					g.drawImage(temp.getNumImage(), temp.getXCord()+40, temp.getYCord()+35, 30, 30, null);



				}
			}
		}
	}



}