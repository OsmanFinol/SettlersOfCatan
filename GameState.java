import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class GameState {
	// ctrl+shift+f is ur best friend

	private String state, subState; // state is always capital, substate is always lowercase
	private ArrayList<Tile> hexTiles;
	private Board gBoard;
	private BufferedImage titleScreen, blueHex, diceHex, backDiceHex, buildingCost, sheepCard, stoneCard, grainCard,
			woodCard, brickCard, perimDevBack, perimLongRoad, perimArmyCard, rollDice, passDice, redDice, yellowDice,
			actionLog, diceRollingImage, harTrade, bankTrade, tradeMenu, tradeCon, buildEx, playerSelect, build,
			seeHand, trade, backCard;
	private Dice dice;
	private PlayerManager pManage;
	private boolean diceHaveBeenRolled;
	ArrayList<Player> pListTemp;
	BufferedImage redDice1, yellowDice1, redDice2, yellowDice2, redDice3, yellowDice3, redDice4, yellowDice4; // for
																												// deciding
	private HashMap<String, Color> map;																								// order
	int[] roll1, roll2, roll3, roll4; // also for deciding order <3

	public GameState() {
		hexTiles = new ArrayList<>();
		dice = new Dice();
		state = "TITLE"; // initializes both for start of the game
		subState = "title"; // initializes both for start of the game
		pManage = new PlayerManager(4);
		diceHaveBeenRolled = false;
		map  = new HashMap<>();
		try {
			int randNum = (int) (Math.random() * 6) + 1;
			Scanner app = new Scanner(new File("Tiles.txt"));
			Scanner sc = new Scanner(new File("Nums" + String.valueOf(randNum) + ".txt"));
			ArrayList<String> tempList = new ArrayList<String>();
			ArrayList<String> tempStrList = new ArrayList<>();
			while (sc.hasNext()) {
				tempList.add(sc.nextLine());
			}
			while (app.hasNext()) {
				tempStrList.add(app.nextLine());
			}
			Collections.shuffle(tempStrList);
			for (int i = 0; i < 19; i++) {
				hexTiles.add(new Tile());
				hexTiles.get(i).setNum(tempList.get(i));
				hexTiles.get(i).setImage(tempStrList.get(i));
			}
			titleScreen = ImageIO.read(GameState.class.getResource("Images/title screen.PNG"));
			harTrade = ImageIO.read(GameState.class.getResource("/Images/harbor trade system.png"));
			bankTrade = ImageIO.read(GameState.class.getResource("/Images/bank trade system.png"));
			tradeMenu = ImageIO.read(GameState.class.getResource("/Images/trade menu.png"));
			tradeCon = ImageIO.read(GameState.class.getResource("/Images/trade confirm.png"));
			// background hexagons
			blueHex = ImageIO.read(GameState.class.getResource("/Images/blue_hex.png"));
			diceHex = ImageIO.read(GameState.class.getResource("/Images/dice_hex.png"));
			backDiceHex = ImageIO.read(GameState.class.getResource("/Images/back_dice_hex.png"));

			// buildingcost card
			buildingCost = ImageIO.read(GameState.class.getResource("/Images/building_costs.png"));

			// perimeter resource cards
			sheepCard = ImageIO.read(GameState.class.getResource("/CardImages/Sheep.jpg"));
			stoneCard = ImageIO.read(GameState.class.getResource("/CardImages/Stone.jpg"));
			grainCard = ImageIO.read(GameState.class.getResource("/CardImages/Grain.jpg"));
			woodCard = ImageIO.read(GameState.class.getResource("/CardImages/Wood.jpg"));
			brickCard = ImageIO.read(GameState.class.getResource("/CardImages/Brick.jpg"));
			backCard = ImageIO.read(GameState.class.getResource("/CardImages/back of card.png"));

			// perimeter dev. cards
			perimDevBack = ImageIO.read(GameState.class.getResource("/DevCards/dev_back.png"));
			perimLongRoad = ImageIO.read(GameState.class.getResource("/DevCards/road_card.png"));
			perimArmyCard = ImageIO.read(GameState.class.getResource("/DevCards/army_card.png"));

			// dice control buttons
			rollDice = ImageIO.read(GameState.class.getResource("/Buttons/roll_button_blue.png"));
			passDice = ImageIO.read(GameState.class.getResource("/Buttons/pass_dice_button.png"));
			redDice = ImageIO.read(GameState.class.getResource("/diceFaces/red_1.png"));
			yellowDice = ImageIO.read(GameState.class.getResource("/diceFaces/yellow_1.png"));
			diceRollingImage = ImageIO.read(GameState.class.getResource("/Images/dice roll.png"));

			// action log image
			actionLog = ImageIO.read(GameState.class.getResource("/Images/action_log.png"));

			// build button + example
			buildEx = ImageIO.read(GameState.class.getResource("/Images/building_example.png"));

			// player buttons
			build = ImageIO.read(GameState.class.getResource("/Buttons/build_button_blue.png"));
			seeHand = ImageIO.read(GameState.class.getResource("/Buttons/seehand_button_blue.png"));
			trade = ImageIO.read(GameState.class.getResource("/Buttons/trade_button_blue.png"));

			// rollorder dice
			redDice1 = ImageIO.read(GameState.class.getResource("/diceFaces/red_1.png"));
			yellowDice1 = ImageIO.read(GameState.class.getResource("/diceFaces/yellow_1.png"));
			redDice2 = ImageIO.read(GameState.class.getResource("/diceFaces/red_1.png"));
			yellowDice2 = ImageIO.read(GameState.class.getResource("/diceFaces/yellow_1.png"));
			redDice3 = ImageIO.read(GameState.class.getResource("/diceFaces/red_1.png"));
			yellowDice3 = ImageIO.read(GameState.class.getResource("/diceFaces/yellow_1.png"));
			redDice4 = ImageIO.read(GameState.class.getResource("/diceFaces/red_1.png"));
			yellowDice4 = ImageIO.read(GameState.class.getResource("/diceFaces/yellow_1.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gBoard = new Board(hexTiles);
	}

	public void paintDefaults(Graphics g) {

		// TITLE SCREEN
		if (state.equals("TITLE")) {
			if (subState.equals("title"))
				g.drawImage(titleScreen, 0, 0, 1486, 950, null);
			// getting players
			else if (subState.equals("findnumplayers4")) {
				// g.setColor(new Color(0,200,248, 250));
				try {
					playerSelect = ImageIO.read(GameState.class.getResource("/Images/player4_select.PNG"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				g.drawImage(playerSelect, 300, 150, 900, 600, null);
				pManage = new PlayerManager(4);
			} else if (subState.equals("findnumplayers3")) {
				try {
					playerSelect = ImageIO.read(GameState.class.getResource("/Images/player3_select.PNG"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				g.drawImage(playerSelect, 300, 150, 900, 600, null);
				pManage = new PlayerManager(3);
			} else if (subState.equals("findnumplayers2")) {
				try {
					playerSelect = ImageIO.read(GameState.class.getResource("/Images/player2_select.PNG"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				g.drawImage(playerSelect, 300, 150, 900, 600, null);
				pManage = new PlayerManager(2);
			}
			pListTemp = pManage.getPlayerList();
			// g.drawRect(560, 520, 340, 150);
		}
		// RULES SCREEN
		else if (state.equals("RULES")) {
			paintRules(g);
		}
		// GAME PORTION
		else if (state.equals("GAME")) {
			if (subState.equals("setorder")) {
				g.setColor(new Color(210, 180, 140, 255));
				g.fillRect(0, 0, 2000, 2000);
				try {
					g.drawImage(ImageIO.read(GameState.class.getResource("/Images/player_order.png")), 300, 150, 900,
							587, null);
					g.setColor(Color.BLACK);
					g.setFont(new Font("Arial", Font.BOLD, 30));
					// player 1
					g.drawImage(backDiceHex, 340, 280, 198, 180, null);
					g.drawImage(diceHex, 349, 290, 180, 162, null);
					g.drawImage(redDice1, 385, 310, 64, 64, null);
					g.drawImage(yellowDice1, 430, 380, 64, 64, null);
					g.drawString(pManage.getPlayerList().get(0).toString(), 410, 485);
					// player 2
					g.drawImage(backDiceHex, 550, 280, 198, 180, null);
					g.drawImage(diceHex, 559, 290, 180, 162, null);
					g.drawImage(redDice2, 595, 310, 64, 64, null);
					g.drawImage(yellowDice2, 640, 380, 64, 64, null);
					g.drawString(pManage.getPlayerList().get(1).toString(), 620, 485);
					// player 3
					if (pManage.getNumPlayers() >= 3) {
						g.drawImage(backDiceHex, 760, 280, 198, 180, null);
						g.drawImage(diceHex, 769, 290, 180, 162, null);
						g.drawImage(redDice3, 805, 310, 64, 64, null);
						g.drawImage(yellowDice3, 850, 380, 64, 64, null);
						g.drawString(pManage.getPlayerList().get(2).toString(), 830, 485);
					}
					// player 4
					if (pManage.getNumPlayers() >= 4) {
						g.drawImage(backDiceHex, 970, 280, 198, 180, null);
						g.drawImage(diceHex, 979, 290, 180, 162, null);
						g.drawImage(redDice4, 1015, 310, 64, 64, null);
						g.drawImage(yellowDice4, 1060, 380, 64, 64, null);
						g.drawString(pManage.getPlayerList().get(3).toString(), 1040, 485);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				
			} else if (subState.equals("entergame")) {
				g.setColor(new Color(210, 180, 140, 255));
				g.fillRect(0, 0, 2000, 2000);
				try {
					g.drawImage(ImageIO.read(GameState.class.getResource("/Images/player_order.png")), 300, 150, 900,
							587, null);
					g.drawImage(ImageIO.read(GameState.class.getResource("/Buttons/accept_button.png")), 605, 570, 289, 143, null);
					g.setColor(Color.BLACK);

					g.drawString(pManage.getPlayerList().get(0).toString() + " rolled a " + roll1[2], 410, 515);
					g.drawString(pManage.getPlayerList().get(1).toString() + " rolled a " + roll2[2], 620, 515);
					if (pManage.getNumPlayers() >= 3)
						g.drawString(pManage.getPlayerList().get(2).toString() + " rolled a " + roll3[2], 830, 515);
					if (pManage.getNumPlayers() >= 4)
						g.drawString(pManage.getPlayerList().get(3).toString() + " rolled a " + roll4[2], 1040, 515);
					
					g.setFont(new Font("Arial", Font.BOLD, 30));
					// player 1
					g.drawImage(backDiceHex, 340, 280, 198, 180, null);
					g.drawImage(diceHex, 349, 290, 180, 162, null);
					g.drawImage(redDice1, 385, 310, 64, 64, null);
					g.drawImage(yellowDice1, 430, 380, 64, 64, null);
					g.drawString(pManage.getPlayerList().get(0).toString(), 410, 485);
					// player 2
					g.drawImage(backDiceHex, 550, 280, 198, 180, null);
					g.drawImage(diceHex, 559, 290, 180, 162, null);
					g.drawImage(redDice2, 595, 310, 64, 64, null);
					g.drawImage(yellowDice2, 640, 380, 64, 64, null);
					g.drawString(pManage.getPlayerList().get(1).toString(), 620, 485);
					// player 3
					if (pManage.getNumPlayers() >= 3) {
						g.drawImage(backDiceHex, 760, 280, 198, 180, null);
						g.drawImage(diceHex, 769, 290, 180, 162, null);
						g.drawImage(redDice3, 805, 310, 64, 64, null);
						g.drawImage(yellowDice3, 850, 380, 64, 64, null);
						g.drawString(pManage.getPlayerList().get(2).toString(), 830, 485);
					}
					// player 4
					if (pManage.getNumPlayers() >= 4) {
						g.drawImage(backDiceHex, 970, 280, 198, 180, null);
						g.drawImage(diceHex, 979, 290, 180, 162, null);
						g.drawImage(redDice4, 1015, 310, 64, 64, null);
						g.drawImage(yellowDice4, 1060, 380, 64, 64, null);
						g.drawString(pManage.getPlayerList().get(3).toString(), 1040, 485);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				pManage.reOrderBasedOnRolls();
				g.drawString("New Order: " + pManage.getPlayerList(), 480, 560);
			} else if (subState.equals("setcolors")) {
				g.setColor(new Color(210, 180, 140, 255));
				g.fillRect(0, 0, 2000, 2000);
				g.setColor(new Color(0, 200, 248, 255));
				try {
					g.drawImage(ImageIO.read(GameState.class.getResource("/Images/color_select.png")), 300, 150, 900,
							587, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
				g.fillRect(880, 260, 200, 300);
				if (pManage.getNumPlayers() == 2)
					g.fillRect(320, 450, 560, 200);
				else if (pManage.getNumPlayers() == 3)
					g.fillRect(320, 560, 560, 150);

			} else if (subState.equals("redocolor")) {
				g.setColor(new Color(210, 180, 140, 255));
				g.fillRect(0, 0, 2000, 2000);
				g.setColor(new Color(0, 200, 248, 255));
				try {
					g.drawImage(ImageIO.read(GameState.class.getResource("/Images/color_select.png")), 300, 150, 900,
							587, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
				g.fillRect(880, 260, 200, 300);
				if (pManage.getNumPlayers() == 2)
					g.fillRect(320, 450, 560, 200);
				else if (pManage.getNumPlayers() == 3)
					g.fillRect(320, 560, 560, 150);
				g.setColor(Color.black);
				g.setFont(new Font("Arial", Font.BOLD, 30));
				g.drawString("Two players can't have the same color!", 400, 700);
			} else if (subState.equals("default")) {
				ArrayList<ResourceCard> recs = new ArrayList<>();
				recs.add(new ResourceCard("Grain"));
				recs.add(new ResourceCard("Brick"));
				pManage.getCPlayer().addResources(recs);
				g.setColor(new Color(210, 180, 140, 255));
				g.fillRect(0, 0, 2000, 2000);
				// background hexagons
				g.drawImage(blueHex, 410, 75, 670, 520, null);
				g.drawImage(backDiceHex, 645, 650, 220, 200, null);
				g.drawImage(diceHex, 655, 660, 200, 180, null);

				// dice buttons
				g.drawImage(rollDice, 710, 853, 95, 47, null);
				g.drawImage(passDice, 710, 600, 95, 47, null);

				// buildingcost cards
				g.drawImage(buildingCost, 1155, 175, 316, 390, null);
				g.drawImage(rotateImageByDegrees(buildingCost, 180.0), 15, 175, 316, 390, null);

				// perimeter resource cards
				g.drawImage(sheepCard, 1085, 290, 55, 80, null);
				g.drawImage(rotateImageByDegrees(stoneCard, 130.0), 1045, 360, 71, 104, null);
				g.drawImage(rotateImageByDegrees(grainCard, 130.0), 1015, 410, 71, 104, null);
				g.drawImage(rotateImageByDegrees(woodCard, 45.0), 1045, 195, 71, 104, null);
				g.drawImage(rotateImageByDegrees(brickCard, 45.0), 1015, 145, 71, 104, null);

				// perimeter dev cards
				g.drawImage(perimDevBack, 350, 290, 55, 80, null);
				g.drawImage(perimLongRoad, 365, 175, 72, 104, null);
				g.drawImage(perimArmyCard, 365, 381, 72, 104, null);
				g.drawImage(redDice, 690, 680, 71, 72, null);
				g.drawImage(yellowDice, 745, 750, 71, 72, null);

				// using a hashmap to store the color values with the color name
				// that way, player 1 is in upper left corner, player 2 in upper right, etc etc

				map.put("Red", new Color(230, 22, 16, 255));
				map.put("Orange", new Color(255, 168, 52, 255));
				map.put("White", new Color(255, 255, 255, 255));
				map.put("Blue", new Color(61, 138, 247, 255));
				ArrayList<Player> pListTemp = pManage.getPlayerList();
				for (int i = 0; i < pManage.getNumPlayers(); i++) {
					if (i == 0) {
						g.setColor(map.get(pListTemp.get(i).getColor()));
						g.fillRect(135, 12, 350, 120);
						g.drawImage(build, 380, 15, 76, 25, null);
						g.drawImage(seeHand, 280, 15, 83, 25, null);
						g.drawImage(trade, 145, 15, 122, 25, null);
					} else if (i == 1) {
						g.setColor(map.get(pListTemp.get(i).getColor()));
						g.fillRect(1015, 12, 350, 120);
						g.drawImage(build, 1260, 15, 76, 25, null);
						g.drawImage(seeHand, 1160, 15, 83, 25, null);
						g.drawImage(trade, 1025, 15, 122, 25, null);
					} else if (i == 2) {
						g.setColor(map.get(pListTemp.get(i).getColor()));
						g.fillRect(290, 625, 350, 120);
						g.drawImage(build, 535, 720, 76, 25, null);
						g.drawImage(seeHand, 435, 720, 83, 25, null);
						g.drawImage(trade, 300, 720, 122, 25, null);
					} else if (i == 3) {
						g.setColor(map.get(pListTemp.get(i).getColor()));
						g.fillRect(870, 625, 350, 120);
						g.drawImage(build, 1115, 720, 76, 25, null);
						g.drawImage(seeHand, 1015, 720, 83, 25, null);
						g.drawImage(trade, 880, 720, 122, 25, null);
					}
				}
			HideCards(g);
				gBoard.paintTiles(g);
				// gBoard.paintInters(g);
			}
		}
	}
public void HideCards(Graphics g)
{	for (int i = 0; i < pManage.getNumPlayers(); i++) {
	if (i == 0) {
		g.setColor(map.get(pListTemp.get(i).getColor()));
		g.fillRect(135, 12, 350, 120);
		g.drawImage(build, 380, 15, 76, 25, null);
		g.drawImage(seeHand, 280, 15, 83, 25, null);
		g.drawImage(trade, 145, 15, 122, 25, null);

		int s = pManage.getPlayersHand(i).size();//size of players hand
		int startVal = 140;
		if(s<=8) {
			for (int j = 0; j < s; j++) {
				g.drawImage(backCard, startVal, 40, 45, 90, null);
				startVal += 50;

			}
		}

	} else if (i == 1) {
		g.setColor(map.get(pListTemp.get(i).getColor()));
		g.fillRect(1015, 12, 350, 120);
		g.drawImage(build, 1260, 15, 76, 25, null);
		g.drawImage(seeHand, 1160, 15, 83, 25, null);
		g.drawImage(trade, 1025, 15, 122, 25, null);
		int s = pManage.getPlayersHand(i).size();//size of players hand
		int startVal = 1015;
		if(s<=8) {
			for (int j = 0; j < s; j++) {
				g.drawImage(backCard, startVal, 40, 45, 90, null);
				startVal += 50;
			}
		}

	} else if (i == 2) {
		g.setColor(map.get(pListTemp.get(i).getColor()));
		g.fillRect(290, 625, 350, 120);
		g.drawImage(build, 535, 720, 76, 25, null);
		g.drawImage(seeHand, 435, 720, 83, 25, null);
		g.drawImage(trade, 300, 720, 122, 25, null);
		int s = pManage.getPlayersHand(i).size();//size of players hand
		int startVal = 290;
		if(s<=8) {
			for (int j = 0; j < s; j++) {
				g.drawImage(backCard, startVal, 625, 45, 90, null);
				startVal += 50;
			}
		}
	} else if (i == 3) {
		g.setColor(map.get(pListTemp.get(i).getColor()));
		g.fillRect(870, 625, 350, 120);
		g.drawImage(build, 1115, 720, 76, 25, null);
		g.drawImage(seeHand, 1015, 720, 83, 25, null);
		g.drawImage(trade, 880, 720, 122, 25, null);

		int s = pManage.getPlayersHand(i).size();//size of players hand
		int startVal = 870;
		if(s<=8) {
			for (int j = 0; j < s; j++) {
				g.drawImage(backCard, startVal, 625, 45, 90, null);
				startVal += 50;
			}
		}
	}
}}
	public void paintLog(Graphics g, ArrayList<String> lines) {
		if (state.equals("GAME")) {
			g.drawImage(actionLog, 25, 600, 250, 270, null);
			int i = lines.size() - 1;
			int x = 40;
			int y = 840;
			g.setColor(Color.BLACK);
			while (i >= 0 && y >= 630) {
				g.drawString(lines.get(i), x, y);
				i--;
				y -= 10;
			}
		}
	}

	public int[] rollDice() {
		// this method'll be the one to distribute resources probably.............
		// executed by the panel class
		// im gonna implement this later :)
		int[] rolls = dice.roll();
		diceHaveBeenRolled = true;
		try {
			redDice = ImageIO.read(GameState.class.getResource("/diceFaces/red_" + rolls[0] + ".png"));
			yellowDice = ImageIO.read(GameState.class.getResource("/diceFaces/yellow_" + rolls[1] + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rolls;
	}

	public void rollForOrder(int numPlayers) {
		for (int i = 0; i < numPlayers; i++) {
			int[] arr = dice.roll();
			if (i == 0) {
				try {
					roll1 = arr;
					redDice1 = ImageIO.read(GameState.class.getResource("/diceFaces/red_" + arr[0] + ".png"));
					yellowDice1 = ImageIO.read(GameState.class.getResource("/diceFaces/yellow_" + arr[1] + ".png"));
					pManage.getPlayerList().get(i).orderRoll = roll1[2];
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (i == 1) {
				try {
					roll2 = arr;
					redDice2 = ImageIO.read(GameState.class.getResource("/diceFaces/red_" + arr[0] + ".png"));
					yellowDice2 = ImageIO.read(GameState.class.getResource("/diceFaces/yellow_" + arr[1] + ".png"));
					pManage.getPlayerList().get(i).orderRoll = roll2[2];
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (i == 2) {
				try {
					roll3 = arr;
					redDice3 = ImageIO.read(GameState.class.getResource("/diceFaces/red_" + arr[0] + ".png"));
					yellowDice3 = ImageIO.read(GameState.class.getResource("/diceFaces/yellow_" + arr[1] + ".png"));
					pManage.getPlayerList().get(i).orderRoll = roll3[2];
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (i == 3) {
				try {
					roll4 = arr;
					redDice4 = ImageIO.read(GameState.class.getResource("/diceFaces/red_" + arr[0] + ".png"));
					yellowDice4 = ImageIO.read(GameState.class.getResource("/diceFaces/yellow_" + arr[1] + ".png"));
					pManage.getPlayerList().get(i).orderRoll = roll4[2];
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		subState = "entergame";
	}

	public String getState() {
		return state;
	}

	public String getSubState() {
		return subState;
	}

	public void setState(String s) {
		state = s;
	}

	public void setSubState(String ss) {
		subState = ss;
	}

	public Player getCPlayer() {
		return pManage.getCPlayer();
	}

	public Player getNextPlayer() {
		return pManage.getNextPlayer();
	}

	public int getNumPlayers() {
		return pManage.getNumPlayers();
	}

	public void setPlayerColors(ArrayList<String> colors) {
		pManage.setColors(colors);
	}

	public void nextPlayer() {
		pManage.nextPlayer();
		diceHaveBeenRolled = false;
	}
	
	public int cPlayerIndex() {
		return pManage.cPlayerIndex();
	}
	
	public boolean diceRolled() {
		return diceHaveBeenRolled;
	}

	public void paintRules(Graphics g) {
		if (subState.equals("page1")) {
			g.clearRect(0, 0, 1500, 950);
			g.setColor(Color.GREEN);
			g.fillRect(5 * (1500 / 6) - (1500 / 60), 28 * (950 / 29) - (950 / 24) - 20, 1500 / 15, 2 * (950 / 29));
			g.setColor(Color.BLACK);
			g.setFont(new Font("Times New Roman", Font.PLAIN, 1500 / 75));
			g.drawString("NEXT", 5 * (1500 / 6) - (1500 / 60) + 20, 28 * (950 / 29) - 20);
			g.setFont(new Font("Times New Roman", Font.PLAIN, 1500 / 55));

			g.drawString("Welcome to the our Settlers of Catan simulation.", 4 * (1500 / 15), 2 * (950 / 29));
			g.drawString("Your objective is to accrue victory points by building roads, settlements, and cities.",
					4 * (1500 / 15), 3 * (950) / 29);
			g.drawString("In addition, you can also advance in the game by trading for resources.", 4 * (1500 / 15),
					4 * (950 / 29));
			//
			g.drawString("There are five types of resources: brick, grain, sheep, stone, and wood", 1500 / 30,
					7 * (950 / 29));
			g.drawString("Each tile is consigned to a resource.", 1500 / 30, 8 * (950 / 29));
			g.drawString("Each tile is also assigned a random number 1-12, excluding 7.", 1500 / 30, 9 * (950 / 29));
			g.drawString("When the die are rolled, the tile numbers corresponding to the die roll yield resources.",
					1500 / 30, 10 * (950 / 29));
			g.drawString(
					"If a player has a settlement or city adjacent to the aforementioned tiles, they will add the tiles' resources to their inventory.",
					1500 / 30, 11 * (950 / 29));
			g.drawImage(brickCard, 8 * 1500 / 15 + 10, 5 * (950 / 29) - 10, 3 * (1500 / 60), 4 * (950 / 27), null);
			g.drawImage(grainCard, 9 * 1500 / 15 + 10, 5 * (950 / 29) - 10, 3 * (1500 / 60), 4 * (950 / 27), null);
			g.drawImage(stoneCard, 10 * 1500 / 15 + 10, 5 * (950 / 29) - 10, 3 * (1500 / 60), 4 * (950 / 27), null);
			g.drawImage(sheepCard, 11 * 1500 / 15 + 10, 5 * (950 / 29) - 10, 3 * (1500 / 60), 4 * (950 / 27), null);
			g.drawImage(woodCard, 12 * 1500 / 15 + 10, 5 * (950 / 29) - 10, 3 * (1500 / 60), 4 * (950 / 27), null);

			g.drawString("Victory points can be earned by building cities and settlements.", 21 * (1500 / 60),
					14 * (950 / 29));
			g.drawString("They can also be won by buying development cards.", 21 * (1500 / 60), 15 * (950 / 29));
			g.drawString("The first player to get ten victory points wins..", 21 * (1500 / 60), 16 * (950 / 29));
			g.drawImage(buildingCost, 1500 / 10, 12 * (950 / 29), 9 * (1500 / 60), 9 * (950 / 29), null);
			//

			g.drawString("The player order will be determined by die roll.", 1500 / 30, 22 * (950 / 29));
			g.drawString("Each player will be allowed to begin the game with two settlements.", 1500 / 30,
					23 * (950 / 29));
			g.drawImage(diceRollingImage, 8 * 1500 / 15, 17 * (950 / 29), 10 * (1500 / 60), 10 * (950 / 29), null);
		}

		else if (subState.equals("page2")) {
			g.clearRect(0, 0, 1500, 950);

			g.setColor(Color.GREEN);
			g.fillRect(5 * (1500 / 6) - (1500 / 60), 28 * (950 / 29) - (950 / 24) - 20, 1500 / 15, 2 * (950 / 29));
			g.setColor(Color.BLACK);
			g.setFont(new Font("Times New Roman", Font.PLAIN, 1500 / 75));
			g.drawString("NEXT", 5 * (1500 / 6) - (1500 / 60) + 20, 28 * (950 / 29) - 20);

			g.setColor(Color.GREEN);
			g.fillRect(1500 / 30, 28 * (950 / 29) - (950 / 24) - 20, 1500 / 15, 2 * (950 / 29));
			g.setColor(Color.BLACK);

			g.drawString("PREVIOUS", 1500 / 30, 28 * (950 / 29) - 20);
			g.setFont(new Font("Times New Roman", Font.PLAIN, 1500 / 55));

			g.drawString("Each turn has four parts in order.  It will be ended by the \"Pass Dice\" button: ",
					7 * (1500 / 32), 950 / 29);
			g.drawString("The player will roll the dice.  If the result is not equal to 7, the following proceeds.",
					7 * (1500 / 32), 2 * (950 / 29));
			g.drawString("The player will receive and trade resources.", 7 * (1500 / 32), 3 * (950 / 29));
			g.drawString("The player will build structures.", 7 * (1500 / 32), 4 * (950 / 29));
			g.drawString("The player can play a development card at any time.", 7 * (1500 / 32), 5 * (950 / 29));
			g.drawImage(passDice, 2 * (1500 / 32), 950 / 29, 8 * 1500 / 60, 4 * 950 / 29, null);
			//

			g.drawString("If the die roll a 7:", 2 * (1500 / 28), 8 * (950 / 29));
			g.drawString("The Robber pawn will be moved to a tile of the player's choice.", 2 * (1500 / 28),
					9 * (950 / 29));
			g.drawString("The tile will then not produce resources until it is moved.", 2 * (1500 / 28),
					10 * (950 / 29));
			g.drawString(
					"All other players with a hand greater than 7 will discard half of their cards, rounding down.",
					2 * (1500 / 28), 11 * (950 / 29));
			// Get a Robber Pawn Mockup

			g.drawString("There are three structures: roads, settlements, and cities.", 2 * (1500 / 28),
					15 * (950 / 29));
			g.drawString("A road is worth 0 points, a settlement 1 point, and a city 2 points.", 2 * (1500 / 28),
					16 * (950 / 29));
			g.drawString("A player can only build a city by upgrading a previous settlement.", 2 * (1500 / 28),
					17 * (950 / 29));
			g.drawImage(buildingCost, 1155, 175, 280, 390, null);
			//

			g.drawString("A Knight development card allows the player to move the robber.", 1500 / 15, 20 * (950 / 29));
			g.drawString("The first player to play 3 Knight cards gets the \"Largest Army\" card worth 2 points.",
					1500 / 15, 21 * (950 / 29));
			g.drawString("Progress cards have instructions to be followed by the player.", 1500 / 15, 22 * (950 / 29));
			g.drawString(
					"Victory Point cards must be hidden until the player is sure they have received the 10 points needed to win the game.",
					1500 / 15, 23 * (950 / 29));
			g.drawString("A player may only play one development card per turn by selecting it.", 1500 / 15,
					24 * (950 / 29));
			// g.drawImage(DevCards,15*1500/20, 10*(950/29), 12*(1500/60),12*(950/29), null
			// );

		} else if (subState.equals("page3")) {
			g.clearRect(0, 0, 1500, 950);
			g.setColor(Color.GREEN);
			g.fillRect(1500 / 30, 28 * (950 / 29) - (950 / 24) - 20, 1500 / 15, 2 * (950 / 29));
			g.setColor(Color.BLACK);
			g.setFont(new Font("Times New Roman", Font.PLAIN, 1500 / 75));
			g.drawString("PREVIOUS", 1500 / 30, 28 * (950 / 29) - 20);
			g.setFont(new Font("Times New Roman", Font.PLAIN, 1500 / 55));
			g.setColor(Color.GREEN);
			g.fillRect(5 * (1500 / 6) - (1500 / 60), 28 * (950 / 29) - (950 / 24) - 20, 1500 / 15, 2 * (950 / 29));
			g.setColor(Color.BLACK);
			g.setFont(new Font("Times New Roman", Font.PLAIN, 1500 / 75));
			g.drawString("PROCEED", 5 * (1500 / 6) - (1500 / 60) + 3, 28 * (950 / 29) - 20);

			g.drawString("The player can trade with anyone during their turn.", 12 * (1500 / 30), 950 / 29);
			g.drawString("They may send a trade request.  The receiver can choose to accept or deny the trade.",
					12 * (1500 / 30), 2 * (950 / 29));
			g.drawString("Due to the nature of this simulation, each player's hand will be visible.", 12 * (1500 / 30),
					3 * (950 / 29));
			g.drawString("Trade will be ended by the \"Build\" or \"Pass Dice\" button.", 12 * (1500 / 30),
					4 * (950 / 29));
			g.drawImage(tradeMenu, (1500 / 40), 950 / 70, 10 * 1500 / 60, 6 * 950 / 29, null);
			g.drawImage(tradeCon, 9 * (1500 / 40), 950 / 70, 10 * 1500 / 60, 6 * 950 / 29, null);

			g.drawString("If a player has a structure adjacent to a harbor, they can use it to trade.", 1500 / 40,
					9 * (950 / 29));
			g.drawString("Each harbor demands a particular resource for another.", 1500 / 40, 10 * (950 / 29));
			g.drawString("For the harbors denoted ?, the player may exchange that number of any resource.", 1500 / 40,
					11 * (950 / 29));
			g.drawString("Additionally, a player can exchange 4 of one resource card for one of another at the bank.",
					1500 / 40, 12 * (950 / 29));
			g.drawImage(harTrade, 900, 7 * (950 / 29) - 10, 10 * (1500 / 60), 6 * (950 / 27), null);
			g.drawImage(bankTrade, 1150, 7 * (950 / 29) - 10, 10 * (1500 / 60), 6 * (950 / 27), null);
			// Get a harbor mockup

			g.drawString("A player can build a structure by clicking the \"Build\" button.", 4 * (1500 / 15),
					15 * 950 / 29);
			g.drawString("They must select a highlighted area to place their structure.", 4 * (1500 / 15),
					16 * 950 / 29);
			g.drawString(
					"If a player wishes to upgrade a settlement, they must click the star and then click an existing city.",
					4 * (1500 / 15), 17 * 950 / 29);
			g.drawString(
					"If a player does not have the necessary resources to build a structure, they will be barred from building.",
					4 * (1500 / 15), 18 * 950 / 29);
			g.drawString("All cities and settlements, barring the first two, must be connected by roads.",
					4 * (1500 / 15), 19 * 950 / 29);
			g.drawImage(build, 1500 / 15, 26 * (950 / 60) + 20, 181, 59, null);
			g.drawImage(buildEx, 1500 / 30, 32 * (950 / 60), 12 * 1500 / 60, 6 * 950 / 29, null);
			//
			g.setFont(new Font("Castellar", Font.BOLD, 1500 / 15));
			g.setColor(Color.RED);
			g.drawString("GOOD LUCK!", 3 * 1500 / 13, 25 * 950 / 29);
		}
	}
	
	public void showCard(Graphics g, int p)
	{
		HashMap<String, Color> map = new HashMap<>();
		map.put("Red", new Color(230, 22, 16, 255));
		map.put("Orange", new Color(255, 168, 52, 255));
		map.put("White", new Color(255, 255, 255, 255));
		map.put("Blue", new Color(61, 138, 247, 255));

		if(p==0)
		{
			g.setColor(map.get(pListTemp.get(p).getColor()));
			g.fillRect(135, 12, 350, 120);
			g.drawImage(build, 380, 15, 76, 25, null);
			g.drawImage(seeHand, 280, 15, 83, 25, null);
			g.drawImage(trade, 145, 15, 122, 25, null);

			int s = pManage.getPlayersHand(p).size();//size of players hand
			int startVal = 140;
			if(s<=8) {
				for (int j = 0; j < s; j++) {
					ResourceCard temp=pManage.getPlayersHand(p).get(j);
					if(temp.getName().equals("Brick"))
					{
				g.drawImage(brickCard, startVal, 40, 45, 90, null);
					}
					else if(temp.getName().equals("Stone"))
					{
				g.drawImage(stoneCard, startVal, 40, 45, 90, null);
					}
					else if(temp.getName().equals("Sheep"))
					{
				g.drawImage(sheepCard, startVal, 40, 45, 90, null);
					}
					else if(temp.getName().equals("Wood"))
					{
				g.drawImage(woodCard, startVal, 40, 45, 90, null);
					}
					else if(temp.getName().equals("Grain"))
					{
				g.drawImage(grainCard, startVal, 40, 45, 90, null);
					}

					startVal += 50;

				}
			}
		}
		else if(p==1)
		{
			g.setColor(map.get(pListTemp.get(p).getColor()));
			g.fillRect(1015, 12, 350, 120);
			g.drawImage(build, 1260, 15, 76, 25, null);
			g.drawImage(seeHand, 1160, 15, 83, 25, null);
			g.drawImage(trade, 1025, 15, 122, 25, null);
			int s = pManage.getPlayersHand(p).size();//size of players hand
			int startVal = 1015;
			if(s<=8) {
				for (int j = 0; j < s; j++) {
					ResourceCard temp=pManage.getPlayersHand(p).get(j);
					if(temp.getName().equals("Brick"))
					{
						g.drawImage(brickCard, startVal, 40, 45, 90, null);
					}
					else if(temp.getName().equals("Stone"))
					{
						g.drawImage(stoneCard, startVal, 40, 45, 90, null);
					}
					else if(temp.getName().equals("Sheep"))
					{
						g.drawImage(sheepCard, startVal, 40, 45, 90, null);
					}
					else if(temp.getName().equals("Wood"))
					{
						g.drawImage(woodCard, startVal, 40, 45, 90, null);
					}
					else if(temp.getName().equals("Grain"))
					{
						g.drawImage(grainCard, startVal, 40, 45, 90, null);
					}

					startVal += 50;

				}
			}
		}
		else if (p==2)
		{
			g.setColor(map.get(pListTemp.get(p).getColor()));
			g.fillRect(290, 625, 350, 120);
			g.drawImage(build, 535, 720, 76, 25, null);
			g.drawImage(seeHand, 435, 720, 83, 25, null);
			g.drawImage(trade, 300, 720, 122, 25, null);
			int s = pManage.getPlayersHand(p).size();//size of players hand
			int startVal = 290;
			if(s<=8) {
				for (int j = 0; j < s; j++) {
					ResourceCard temp=pManage.getPlayersHand(p).get(j);
					if(temp.getName().equals("Brick"))
					{
						g.drawImage(brickCard, startVal, 625, 45, 90, null);
					}
					else if(temp.getName().equals("Stone"))
					{
						g.drawImage(stoneCard, startVal, 625, 45, 90, null);
					}
					else if(temp.getName().equals("Sheep"))
					{
						g.drawImage(sheepCard, startVal, 625, 45, 90, null);
					}
					else if(temp.getName().equals("Wood"))
					{
						g.drawImage(woodCard, startVal, 625, 45, 90, null);
					}
					else if(temp.getName().equals("Grain"))
					{
						g.drawImage(grainCard, startVal, 625, 45, 90, null);
					}

					startVal += 50;

				}
			}

		}
		else if (p==3)
		{
			g.setColor(map.get(pListTemp.get(p).getColor()));
			g.fillRect(870, 625, 350, 120);
			g.drawImage(build, 1115, 720, 76, 25, null);
			g.drawImage(seeHand, 1015, 720, 83, 25, null);
			g.drawImage(trade, 880, 720, 122, 25, null);

			int s = pManage.getPlayersHand(p).size();//size of players hand
			int startVal = 870;
			if(s<=8) {
				for (int j = 0; j < s; j++) {
					ResourceCard temp=pManage.getPlayersHand(p).get(j);
					if(temp.getName().equals("Brick"))
					{
						g.drawImage(brickCard, startVal, 625, 45, 90, null);
					}
					else if(temp.getName().equals("Stone"))
					{
						g.drawImage(stoneCard, startVal, 625, 45, 90, null);
					}
					else if(temp.getName().equals("Sheep"))
					{
						g.drawImage(sheepCard, startVal, 625, 45, 90, null);
					}
					else if(temp.getName().equals("Wood"))
					{
						g.drawImage(woodCard, startVal, 625, 45, 90, null);
					}
					else if(temp.getName().equals("Grain"))
					{
						g.drawImage(grainCard, startVal, 625, 45, 90, null);
					}

					startVal += 50;

				}
			}
		}

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
