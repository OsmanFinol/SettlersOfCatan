import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Board {


	private Tile[][] gameBoard;
	private Intersection[][] inters;
	public Board(ArrayList<Tile> arrList) {

		Iterator<Tile> iter = arrList.iterator();

		gameBoard = new Tile[5][5];
		//tiles
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

		//intersections :|
		inters = new Intersection[6][11];
		//7, 9, 11, 11, 9, 7
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 11; c++) {
				//row one
				//subtract x by 10, add 20 to y, for starting one; then add 55 to x and subtract 30 or add 30 for subsequents
				if (r == 0) {
					if (c == 0 || c == 1 || c == 9 || c == 10)
						inters[r][c] = null;
					else if (c==2) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][1]);
						inters[r][c] = new Intersection(575, 145, true, temp);
					}
					else if (c==3) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][1]);
						inters[r][c] = new Intersection(630, 115, true, temp);
					}
					else if (c==4) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][1]);
						temp.add(gameBoard[0][2]);
						inters[r][c] = new Intersection(685, 145, false, temp);
					}
					else if (c==5) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][2]);
						inters[r][c] = new Intersection(740, 115, true, temp);
					}
					else if (c==6) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][2]);
						temp.add(gameBoard[0][3]);
						inters[r][c] = new Intersection(795, 145, true, temp);
					}
					else if (c==7) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][3]);
						inters[r][c] = new Intersection(850, 115, false, temp);
					}
					else if (c==8) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][3]);
						inters[r][c] = new Intersection(905, 145, false, temp);
					}
				} //end of row 1

				//row 2
				else if (r == 1) {
					if (c == 0 || c== 10) {
						inters[r][c] = null;
					}
					else if (c== 1) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][1]);
						inters[r][c] = new Intersection(520, 228, true, temp);
					}
					else if (c==2) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][1]);
						temp.add(gameBoard[1][1]);
						inters[r][c] = new Intersection(575, 198, false, temp);
					}
					else if (c==3) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][1]);
						temp.add(gameBoard[1][1]);
						temp.add(gameBoard[1][2]);
						inters[r][c] = new Intersection(630, 228, false, temp);
					}
					else if (c==4) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][1]);
						temp.add(gameBoard[0][2]);
						temp.add(gameBoard[1][2]);
						inters[r][c] = new Intersection(685, 198, false, temp);
					}
					else if (c==5) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][2]);
						temp.add(gameBoard[1][2]);
						temp.add(gameBoard[1][3]);
						inters[r][c] = new Intersection(740, 228, false, temp);
					}
					else if (c==6) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][2]);
						temp.add(gameBoard[0][3]);
						temp.add(gameBoard[1][3]);
						inters[r][c] = new Intersection(795, 198, false, temp);
					}
					else if (c==7) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][3]);
						temp.add(gameBoard[1][3]);
						temp.add(gameBoard[1][4]);
						inters[r][c] = new Intersection(850, 228, false, temp);
					}
					else if (c==8) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][3]);
						temp.add(gameBoard[1][4]);
						inters[r][c] = new Intersection(905, 198, true, temp);
					}
					else if (c==9) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][4]);
						inters[r][c] = new Intersection(960, 228, true, temp);
					}
				} //end of row 2

				//row 3
				else if (r==2) {
					if (c == 0) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[2][0]);
						inters[r][c] = new Intersection(465, 311, false, temp);
					}
					else if (c== 1) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][1]);
						temp.add(gameBoard[2][0]);
						inters[r][c] = new Intersection(520, 281, true, temp);
					}
					else if (c==2) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][1]);
						temp.add(gameBoard[2][0]);
						temp.add(gameBoard[2][1]);
						inters[r][c] = new Intersection(575, 311, false, temp);
					}
					else if (c==3) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][1]);
						temp.add(gameBoard[1][2]);
						temp.add(gameBoard[2][1]);
						inters[r][c] = new Intersection(630, 281, false, temp);
					}
					else if (c==4) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][2]);
						temp.add(gameBoard[2][1]);
						temp.add(gameBoard[2][2]);
						inters[r][c] = new Intersection(685, 311, false, temp);
					}
					else if (c==5) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][2]);
						temp.add(gameBoard[1][3]);
						temp.add(gameBoard[2][2]);
						inters[r][c] = new Intersection(740, 281, false, temp);
					}
					else if (c==6) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][3]);
						temp.add(gameBoard[2][2]);
						temp.add(gameBoard[2][3]);
						inters[r][c] = new Intersection(795, 311, false, temp);
					}
					else if (c==7) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][3]);
						temp.add(gameBoard[1][4]);
						temp.add(gameBoard[2][3]);
						inters[r][c] = new Intersection(850, 281, false, temp);
					}
					else if (c==8) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][4]);
						temp.add(gameBoard[2][3]);
						temp.add(gameBoard[2][4]);
						inters[r][c] = new Intersection(905, 311, false, temp);
					}
					else if (c==9) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][4]);
						temp.add(gameBoard[2][4]);
						inters[r][c] = new Intersection(960, 281, false, temp);
					}
					else if (c==10) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[2][4]);
						inters[r][c] = new Intersection(1015, 311, true, temp);
					}
				}//end of row 3
				//row 4
				else if (r == 3) {
					if (c == 0) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[2][0]);
						inters[r][c] = new Intersection(465, 364, false, temp);
					}
					else if (c== 1) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[3][1]);
						temp.add(gameBoard[2][0]);
						inters[r][c] = new Intersection(520, 394, true, temp);
					}
					else if (c==2) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[3][1]);
						temp.add(gameBoard[2][0]);
						temp.add(gameBoard[2][1]);
						inters[r][c] = new Intersection(575, 364, false, temp);
					}
					else if (c==3) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[3][1]);
						temp.add(gameBoard[3][2]);
						temp.add(gameBoard[2][1]);
						inters[r][c] = new Intersection(630, 394, false, temp);
					}
					else if (c==4) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[3][2]);
						temp.add(gameBoard[2][1]);
						temp.add(gameBoard[2][2]);
						inters[r][c] = new Intersection(685, 364, false, temp);
					}
					else if (c==5) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[3][2]);
						temp.add(gameBoard[3][3]);
						temp.add(gameBoard[2][2]);
						inters[r][c] = new Intersection(740, 394, false, temp);
					}
					else if (c==6) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[3][3]);
						temp.add(gameBoard[2][2]);
						temp.add(gameBoard[2][3]);
						inters[r][c] = new Intersection(795, 364, false, temp);
					}
					else if (c==7) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[3][3]);
						temp.add(gameBoard[3][4]);
						temp.add(gameBoard[2][3]);
						inters[r][c] = new Intersection(850, 394, false, temp);
					}
					else if (c==8) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[3][4]);
						temp.add(gameBoard[2][3]);
						temp.add(gameBoard[2][4]);
						inters[r][c] = new Intersection(905, 364, false, temp);
					}
					else if (c==9) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[3][4]);
						temp.add(gameBoard[2][4]);
						inters[r][c] = new Intersection(960, 394, false, temp);
					}
					else if (c==10) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[2][4]);
						inters[r][c] = new Intersection(1015, 364, true, temp);
					}
				} //end of row 4

				else if (r == 4) { //row 5
					if (c == 0 || c== 10) {
						inters[r][c] = null;
					}
					else if (c== 1) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][1]);
						inters[r][c] = new Intersection(520, 447, true, temp);
					}
					else if (c==2) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][1]);
						temp.add(gameBoard[1][1]);
						inters[r][c] = new Intersection(575, 477, false, temp);
					}
					else if (c==3) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][1]);
						temp.add(gameBoard[1][1]);
						temp.add(gameBoard[1][2]);
						inters[r][c] = new Intersection(630, 447, false, temp);
					}
					else if (c==4) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][1]);
						temp.add(gameBoard[0][2]);
						temp.add(gameBoard[1][2]);
						inters[r][c] = new Intersection(685, 477, false, temp);
					}
					else if (c==5) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][2]);
						temp.add(gameBoard[1][2]);
						temp.add(gameBoard[1][3]);
						inters[r][c] = new Intersection(740, 447, false, temp);
					}
					else if (c==6) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][2]);
						temp.add(gameBoard[0][3]);
						temp.add(gameBoard[1][3]);
						inters[r][c] = new Intersection(795, 477, false, temp);
					}
					else if (c==7) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][3]);
						temp.add(gameBoard[1][3]);
						temp.add(gameBoard[1][4]);
						inters[r][c] = new Intersection(850, 447, false, temp);
					}
					else if (c==8) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][3]);
						temp.add(gameBoard[1][4]);
						inters[r][c] = new Intersection(905, 477, true, temp);
					}
					else if (c==9) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][4]);
						inters[r][c] = new Intersection(960, 447, true, temp);
					}
				}//end of row 5



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

	public void paintInters(Graphics g) {
		g.setColor(Color.blue);
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 11; c++) {
				Intersection temp = inters[r][c];
				if (temp!=null) {
					g.fillOval(temp.getXCord(), temp.getYCord(), 20, 20);
					System.out.println(temp.getBorders());
				}
			}
			System.out.println();
		}
	}


}