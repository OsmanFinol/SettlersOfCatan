import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Board {

	private Tile[][] gameBoard;
	private Intersection[][] inters;
	private ArrayList<Harbor> harbs;
	private BufferedImage rob;
	Font AlmendraSC;

	public Board(ArrayList<Tile> arrList) {
		harbs = new ArrayList<>();
		try {
			Scanner harb = new Scanner(new File("Harbors.txt"));
			while (harb.hasNextLine()) {
				harbs.add(new Harbor(Integer.parseInt(harb.next()), Integer.parseInt(harb.next()),
						new ResourceCard(harb.next())));
			}

			Collections.shuffle(harbs);

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			AlmendraSC = Font.createFont(Font.TRUETYPE_FONT, new File("AlmendraSC-Regular.ttf")).deriveFont(15f)
					.deriveFont(AlmendraSC.BOLD);
		} catch (FontFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("AlmendraSC-Regular.ttf")));
		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			rob = ImageIO.read(Board.class.getResource("/Images/robber_icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (arrList.get(9).getType().equals("Desert"))
			Collections.shuffle(arrList);
		Iterator<Tile> iter = arrList.iterator();

		gameBoard = new Tile[5][5];
		// tiles
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if ((i == 0 && j == 0) || (i == 0 && j == 4) || (i == 1 && j == 0) || (i == 3 && j == 0)
						|| (i == 4 && j == 0) || (i == 4 && j == 4)) {
					gameBoard[i][j] = null;
					continue;
				}
				Tile t = iter.next();
				gameBoard[i][j] = t;
				t = gameBoard[i][j];
				if (t.getType().equals("Desert"))
					t.addRobber();
				if (i == 0 && j == 1) {
					t.setXCord(585);
					t.setYCord(125);
				} else if (i == 0 && j == 2) {
					t.setXCord(695);
					t.setYCord(125);
				} else if (i == 0 && j == 3) {
					t.setXCord(805);
					t.setYCord(125);
				} else if (i == 1 && j == 1) {
					t.setXCord(530);
					t.setYCord(208);
				} else if (i == 1 && j == 2) {
					t.setXCord(640);
					t.setYCord(208);
				} else if (i == 1 && j == 3) {
					t.setXCord(750);
					t.setYCord(208);
				} else if (i == 1 && j == 4) {
					t.setXCord(860);
					t.setYCord(208);
				}

				else if (i == 2 && j == 0) {
					t.setXCord(475);
					t.setYCord(291);
				} else if (i == 2 && j == 1) {
					t.setXCord(585);
					t.setYCord(291);
				} else if (i == 2 && j == 2) {
					t.setXCord(695);
					t.setYCord(291);
				} else if (i == 2 && j == 3) {
					t.setXCord(805);
					t.setYCord(291);
				} else if (i == 2 && j == 4) {
					t.setXCord(915);
					t.setYCord(291);
				}

				else if (i == 3 && j == 1) {
					t.setXCord(530);
					t.setYCord(374);
				} else if (i == 3 && j == 2) {
					t.setXCord(640);
					t.setYCord(374);
				} else if (i == 3 && j == 3) {
					t.setXCord(750);
					t.setYCord(374);
				} else if (i == 3 && j == 4) {
					t.setXCord(860);
					t.setYCord(374);
				} else if (i == 4 && j == 1) {
					t.setXCord(585);
					t.setYCord(457);
				} else if (i == 4 && j == 2) {
					t.setXCord(695);
					t.setYCord(457);
				} else if (i == 4 && j == 3) {
					t.setXCord(805);
					t.setYCord(457);
				}
			}
		}
		spiral();

		// intersections :|
		inters = new Intersection[6][11];
		// 7, 9, 11, 11, 9, 7
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 11; c++) {
				// row one
				// subtract x by 10, add 20 to y, for starting one; then add 55 to x and
				// subtract 30 or add 30 for subsequents
				if (r == 0) {
					if (c == 0 || c == 1 || c == 9 || c == 10)
						inters[r][c] = null;
					else if (c == 2) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][1]);
						inters[r][c] = new Intersection(575, 145, true, temp);
					} else if (c == 3) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][1]);
						inters[r][c] = new Intersection(630, 115, true, temp);
					} else if (c == 4) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][1]);
						temp.add(gameBoard[0][2]);
						inters[r][c] = new Intersection(685, 145, false, temp);
					} else if (c == 5) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][2]);
						inters[r][c] = new Intersection(740, 115, true, temp);
					} else if (c == 6) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][2]);
						temp.add(gameBoard[0][3]);
						inters[r][c] = new Intersection(795, 145, true, temp);
					} else if (c == 7) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][3]);
						inters[r][c] = new Intersection(850, 115, false, temp);
					} else if (c == 8) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][3]);
						inters[r][c] = new Intersection(905, 145, false, temp);
					}
				} // end of row 1

				// row 2
				else if (r == 1) {
					if (c == 0 || c == 10) {
						inters[r][c] = null;
					} else if (c == 1) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][1]);
						inters[r][c] = new Intersection(520, 228, true, temp);
					} else if (c == 2) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][1]);
						temp.add(gameBoard[1][1]);
						inters[r][c] = new Intersection(575, 198, false, temp);
					} else if (c == 3) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][1]);
						temp.add(gameBoard[1][1]);
						temp.add(gameBoard[1][2]);
						inters[r][c] = new Intersection(630, 228, false, temp);
					} else if (c == 4) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][1]);
						temp.add(gameBoard[0][2]);
						temp.add(gameBoard[1][2]);
						inters[r][c] = new Intersection(685, 198, false, temp);
					} else if (c == 5) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][2]);
						temp.add(gameBoard[1][2]);
						temp.add(gameBoard[1][3]);
						inters[r][c] = new Intersection(740, 228, false, temp);
					} else if (c == 6) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][2]);
						temp.add(gameBoard[0][3]);
						temp.add(gameBoard[1][3]);
						inters[r][c] = new Intersection(795, 198, false, temp);
					} else if (c == 7) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][3]);
						temp.add(gameBoard[1][3]);
						temp.add(gameBoard[1][4]);
						inters[r][c] = new Intersection(850, 228, false, temp);
					} else if (c == 8) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[0][3]);
						temp.add(gameBoard[1][4]);
						inters[r][c] = new Intersection(905, 198, true, temp);
					} else if (c == 9) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][4]);
						inters[r][c] = new Intersection(960, 228, true, temp);
					}
				} // end of row 2

				// row 3
				else if (r == 2) {
					if (c == 0) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[2][0]);
						inters[r][c] = new Intersection(465, 311, false, temp);
					} else if (c == 1) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][1]);
						temp.add(gameBoard[2][0]);
						inters[r][c] = new Intersection(520, 281, true, temp);
					} else if (c == 2) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][1]);
						temp.add(gameBoard[2][0]);
						temp.add(gameBoard[2][1]);
						inters[r][c] = new Intersection(575, 311, false, temp);
					} else if (c == 3) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][1]);
						temp.add(gameBoard[1][2]);
						temp.add(gameBoard[2][1]);
						inters[r][c] = new Intersection(630, 281, false, temp);
					} else if (c == 4) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][2]);
						temp.add(gameBoard[2][1]);
						temp.add(gameBoard[2][2]);
						inters[r][c] = new Intersection(685, 311, false, temp);
					} else if (c == 5) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][2]);
						temp.add(gameBoard[1][3]);
						temp.add(gameBoard[2][2]);
						inters[r][c] = new Intersection(740, 281, false, temp);
					} else if (c == 6) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][3]);
						temp.add(gameBoard[2][2]);
						temp.add(gameBoard[2][3]);
						inters[r][c] = new Intersection(795, 311, false, temp);
					} else if (c == 7) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][3]);
						temp.add(gameBoard[1][4]);
						temp.add(gameBoard[2][3]);
						inters[r][c] = new Intersection(850, 281, false, temp);
					} else if (c == 8) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][4]);
						temp.add(gameBoard[2][3]);
						temp.add(gameBoard[2][4]);
						inters[r][c] = new Intersection(905, 311, false, temp);
					} else if (c == 9) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[1][4]);
						temp.add(gameBoard[2][4]);
						inters[r][c] = new Intersection(960, 281, false, temp);
					} else if (c == 10) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[2][4]);
						inters[r][c] = new Intersection(1015, 311, true, temp);
					}
				} // end of row 3
					// row 4
				else if (r == 3) {
					if (c == 0) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[2][0]);
						inters[r][c] = new Intersection(465, 364, false, temp);
					} else if (c == 1) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[3][1]);
						temp.add(gameBoard[2][0]);
						inters[r][c] = new Intersection(520, 394, true, temp);
					} else if (c == 2) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[3][1]);
						temp.add(gameBoard[2][0]);
						temp.add(gameBoard[2][1]);
						inters[r][c] = new Intersection(575, 364, false, temp);
					} else if (c == 3) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[3][1]);
						temp.add(gameBoard[3][2]);
						temp.add(gameBoard[2][1]);
						inters[r][c] = new Intersection(630, 394, false, temp);
					} else if (c == 4) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[3][2]);
						temp.add(gameBoard[2][1]);
						temp.add(gameBoard[2][2]);
						inters[r][c] = new Intersection(685, 364, false, temp);
					} else if (c == 5) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[3][2]);
						temp.add(gameBoard[3][3]);
						temp.add(gameBoard[2][2]);
						inters[r][c] = new Intersection(740, 394, false, temp);
					} else if (c == 6) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[3][3]);
						temp.add(gameBoard[2][2]);
						temp.add(gameBoard[2][3]);
						inters[r][c] = new Intersection(795, 364, false, temp);
					} else if (c == 7) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[3][3]);
						temp.add(gameBoard[3][4]);
						temp.add(gameBoard[2][3]);
						inters[r][c] = new Intersection(850, 394, false, temp);
					} else if (c == 8) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[3][4]);
						temp.add(gameBoard[2][3]);
						temp.add(gameBoard[2][4]);
						inters[r][c] = new Intersection(905, 364, false, temp);
					} else if (c == 9) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[3][4]);
						temp.add(gameBoard[2][4]);
						inters[r][c] = new Intersection(960, 394, false, temp);
					} else if (c == 10) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[2][4]);
						inters[r][c] = new Intersection(1015, 364, true, temp);
					}
				} // end of row 4
				else if (r == 4) { // row 5
					if (c == 0 || c == 10) {
						inters[r][c] = null;
					} else if (c == 1) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[3][1]);
						inters[r][c] = new Intersection(520, 447, true, temp);
					} else if (c == 2) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[4][1]);
						temp.add(gameBoard[3][1]);
						inters[r][c] = new Intersection(575, 477, false, temp);
					} else if (c == 3) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[4][1]);
						temp.add(gameBoard[3][1]);
						temp.add(gameBoard[3][2]);
						inters[r][c] = new Intersection(630, 447, false, temp);
					} else if (c == 4) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[4][1]);
						temp.add(gameBoard[4][2]);
						temp.add(gameBoard[3][2]);
						inters[r][c] = new Intersection(685, 477, false, temp);
					} else if (c == 5) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[4][2]);
						temp.add(gameBoard[3][2]);
						temp.add(gameBoard[3][3]);
						inters[r][c] = new Intersection(740, 447, false, temp);
					} else if (c == 6) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[4][2]);
						temp.add(gameBoard[4][3]);
						temp.add(gameBoard[3][3]);
						inters[r][c] = new Intersection(795, 477, false, temp);
					} else if (c == 7) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[4][3]);
						temp.add(gameBoard[3][3]);
						temp.add(gameBoard[3][4]);
						inters[r][c] = new Intersection(850, 447, false, temp);
					} else if (c == 8) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[4][3]);
						temp.add(gameBoard[3][4]);
						inters[r][c] = new Intersection(905, 477, true, temp);
					} else if (c == 9) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[3][4]);
						inters[r][c] = new Intersection(960, 447, true, temp);
					}
				} // end of row 5
				else if (r == 5) { // row 6
					if (c == 0 || c == 1 || c == 9 || c == 10)
						inters[r][c] = null;
					else if (c == 2) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[4][1]);
						inters[r][c] = new Intersection(575, 527, true, temp);
					} else if (c == 3) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[4][1]);
						inters[r][c] = new Intersection(630, 557, true, temp);
					} else if (c == 4) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[4][1]);
						temp.add(gameBoard[4][2]);
						inters[r][c] = new Intersection(685, 527, false, temp);
					} else if (c == 5) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[4][2]);
						inters[r][c] = new Intersection(740, 557, true, temp);
					} else if (c == 6) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[4][2]);
						temp.add(gameBoard[4][3]);
						inters[r][c] = new Intersection(795, 527, true, temp);
					} else if (c == 7) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[4][3]);
						inters[r][c] = new Intersection(850, 557, false, temp);
					} else if (c == 8) {
						ArrayList<Tile> temp = new ArrayList<>();
						temp.add(gameBoard[4][3]);
						inters[r][c] = new Intersection(905, 527, false, temp);
					}
				}
			}
		}
	}

	public void paintTiles(Graphics g) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Tile temp = gameBoard[i][j];
				if (temp != null) {
					g.drawImage(temp.getImage(), temp.getXCord(), temp.getYCord(), 110, 110, null);
					if (!temp.isRobber()) {
						g.drawImage(temp.getNumImage(), temp.getXCord() + 40, temp.getYCord() + 35, 30, 30, null);
					} else {
						g.drawImage(rob, temp.getXCord() + 40, temp.getYCord() + 15, 30, 60, null);
					}
				}
			}
		}
	}

	public void paintNotRobberTiles(Graphics g) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Tile temp = gameBoard[i][j];
				if (temp != null) {
					if (!temp.isRobber()) {
						g.setColor(new Color(222, 235, 52, 150));
						g.fillOval(temp.getXCord() + 40, temp.getYCord() + 35, 30, 30);
					}
				}
			}
		}
	}

	public void paintStructures(Graphics g) {
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 11; c++) {
				Intersection temp = inters[r][c];
				if (temp != null && temp.hasStructure() == true /* && temp.getSet().equals("full") */) {
					/*
					 * if (temp.getSet("settlement1") || temp.getSet("settlement2") ||
					 * temp.getSet("settlement3") || temp.getSet("settlement4")) {
					 * g.drawImage(temp.getImageStructure(), temp.getXCord(), temp.getYCord(), 20,
					 * 20, null); //temp.setSet("no"); }
					 */
					g.drawImage(temp.getImageStructure(), temp.getXCord(), temp.getYCord(), 20, 20, null);
					// temp.setSet("no");
					System.out.println(temp.getBorders() + "hi???");
				}
			}
			System.out.println();
		}
	}

	public void paintInters(Graphics g) {
		g.setColor(new Color(222, 235, 52, 175));
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 11; c++) {
				Intersection temp = inters[r][c];
				if (temp != null && !temp.hasStructure()) {
					g.fillOval(temp.getXCord(), temp.getYCord(), 20, 20);
				}
			}
		}
	}

	public void paintHarbors(Graphics g) {
		g.setColor(Color.white);
		g.setFont(AlmendraSC);

		g.drawImage(rotateImageByDegrees(harbs.get(0).getImage(), 340), 568, 80, 80, 80, null);
		g.drawString(harbs.get(0).toString(), 590, 100);

		g.drawImage(rotateImageByDegrees(harbs.get(0).getImage(), 20), 743, 80, 80, 80, null);
		g.drawImage(rotateImageByDegrees(harbs.get(0).getImage(), 18), 908, 162, 80, 80, null);
		g.drawImage(rotateImageByDegrees(harbs.get(0).getImage(), 90), 1025, 310, 30, 70, null);
		g.drawImage(rotateImageByDegrees(harbs.get(0).getImage(), 162), 908, 450, 80, 80, null);
		g.drawImage(rotateImageByDegrees(harbs.get(0).getImage(), 150), 745, 535, 80, 60, null);
		g.drawImage(rotateImageByDegrees(harbs.get(0).getImage(), 210), 568, 530, 80, 70, null);
		g.drawImage(rotateImageByDegrees(harbs.get(0).getImage(), 270), 450, 310, 30, 75, null);
	}

	public void spiral() {
		Scanner app = new Scanner("5 2 6 3 8 10 9 12 11 4 8 10 9 4 5 6 3 11 -1");

		// possible starting positions:
		// (0,1), (0,3), (2,0), (2,4), (4,1), (4,3)

		int top = 0, bottom = gameBoard.length - 1, left = 1, right = gameBoard.length - 1;
		ArrayList<Integer> nums = new ArrayList<>();
		nums.add(1);
		nums.add(2);
		nums.add(3);
		nums.add(4);
		int dir = 3;
		int startVal = dir;
		// 1 = (0,1), 3 = (4,3)

		while (top <= bottom && right >= left) {
			if (dir == 1) { // topright -> topleft
				for (int i = right; i >= left; i--) {
					if (gameBoard[top][i] != null && !gameBoard[top][i].getType().equals("Desert"))
						gameBoard[top][i].setNum(Integer.parseInt(app.next()));
				}
				top++;
				dir = 2;
			} else if (dir == 2) { // topleft -> bottomleft
				for (int i = top; i <= bottom; i++) {
					if (i == 2) {
						if (gameBoard[i][left - 1] != null && !gameBoard[i][left].getType().equals("Desert"))
							gameBoard[i][left - 1].setNum(Integer.parseInt(app.next()));
					} else {
						if (gameBoard[i][left] != null && !gameBoard[i][left].getType().equals("Desert"))
							gameBoard[i][left].setNum(Integer.parseInt(app.next()));
					}
				}
				left++;
				dir = 3;
			} else if (dir == 3) { // bottomleft -> bottomright
				for (int i = left; i <= right; i++) {
					if (gameBoard[bottom][i] != null && !gameBoard[bottom][i].getType().equals("Desert"))
						gameBoard[bottom][i].setNum(Integer.parseInt(app.next()));
				}
				bottom--;
				dir = 4;
			} else if (dir == 4) { // bottomright -> topright
				for (int i = bottom; i >= top; i--) {
					if (gameBoard[i][right] != null && !gameBoard[i][right].getType().equals("Desert"))
						gameBoard[i][right].setNum(Integer.parseInt(app.next()));
				}
				right--;
				dir = 1;
			}
		}
		if (!gameBoard[2][2].getType().equals("Desert")) {
			gameBoard[2][2].setNum(Integer.parseInt(app.next()));
		}
	}

	public Tile[][] getTiles() {
		return gameBoard;
	}

	public Tile robberLocation() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Tile temp = gameBoard[i][j];
				if (temp != null && temp.isRobber()) {
					return temp;
				}
			}
		}
		return null;
	}
	
	public Intersection[][] getInters() {
		return inters;
	}

	private static BufferedImage rotateImageByDegrees(BufferedImage buffImage, double angle) {
		double radian = Math.toRadians(angle);
		double sin = Math.abs(Math.sin(radian));
		double cos = Math.abs(Math.cos(radian));

		int width = buffImage.getWidth();
		int height = buffImage.getHeight();

		int nWidth = (int) Math.floor((double) width * cos + (double) height * sin);
		int nHeight = (int) Math.floor((double) height * cos + (double) width * sin);

		BufferedImage rotatedImage = new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_ARGB);

		Graphics2D graphics = rotatedImage.createGraphics();

		graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

		graphics.translate((nWidth - width) / 2, (nHeight - height) / 2);
		// rotation around the center point
		graphics.rotate(radian, (double) (width / 2), (double) (height / 2));
		graphics.drawImage(buffImage, 0, 0, null);
		graphics.dispose();

		return rotatedImage;
	}

}
