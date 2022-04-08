import java.awt.Color;
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
import java.util.Scanner;


import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameState {

	
	private String state, subState; //state is always capital, substate is always lowercase
	private ArrayList<Tile> hexTiles;
	private Board gBoard;
	private BufferedImage titleScreen, blueHex, diceHex, backDiceHex, buildingCost, perimSheep, perimStone, perimGrain, perimWood, 
	perimBrick, perimDevBack, perimLongRoad, perimArmyCard, rollDice, passDice, redDice, yellowDice;
	private Dice dice;
	private PlayerManager pManage;
	public GameState() {
		hexTiles = new ArrayList<>();
		dice = new Dice();
		state = "TITLE";	//initializes both for start of the game
		subState = "title"; //initializes both for start of the game
		pManage = new PlayerManager(4);
		try {
			Scanner app = new Scanner(new File("Tiles.txt"));
			Scanner sc = new Scanner(new File("Nums.txt"));
			ArrayList<String> tempList = new ArrayList<String>();
			while (sc.hasNext()) {
				tempList.add(sc.nextLine());
			}
			Collections.shuffle(tempList);
			int nPol = 0;
			tempList.set(tempList.indexOf("NumDesert"), tempList.get(0));
			tempList.set(0, "NumDesert");
			while (app.hasNextLine()) {
				hexTiles.add(new Tile(app.nextLine(), tempList.get(nPol)));
				nPol++;
			}
			
			titleScreen = ImageIO.read(GameState.class.getResource("Images/title screen.PNG"));
			//background hexagons
			blueHex = ImageIO.read(GameState.class.getResource("/Images/blue_hex.png"));
			diceHex = ImageIO.read(GameState.class.getResource("/Images/dice_hex.png"));
			backDiceHex = ImageIO.read(GameState.class.getResource("/Images/back_dice_hex.png"));
		
			//buildingcost card
			buildingCost = ImageIO.read(GameState.class.getResource("/Images/building_costs.png"));
		
			//perimeter resource cards
			perimSheep = ImageIO.read(GameState.class.getResource("/CardImages/Sheep.jpg"));
			perimStone = ImageIO.read(GameState.class.getResource("/CardImages/Stone.jpg"));
			perimGrain = ImageIO.read(GameState.class.getResource("/CardImages/Grain.jpg"));
			perimWood = ImageIO.read(GameState.class.getResource("/CardImages/Wood.jpg"));
			perimBrick = ImageIO.read(GameState.class.getResource("/CardImages/Brick.jpg"));
			
			//perimeter dev. cards
			perimDevBack = ImageIO.read(GameState.class.getResource("/DevCards/dev_back.png"));
			perimLongRoad = ImageIO.read(GameState.class.getResource("/DevCards/road_card.png"));
			perimArmyCard = ImageIO.read(GameState.class.getResource("/DevCards/army_card.png"));
			
			//dice control buttons
			rollDice = ImageIO.read(GameState.class.getResource("/Buttons/roll_button_blue.png"));
			passDice = ImageIO.read(GameState.class.getResource("/Buttons/pass_dice_button.png"));
			redDice = ImageIO.read(GameState.class.getResource("/diceFaces/red_1.png"));
			yellowDice = ImageIO.read(GameState.class.getResource("/diceFaces/yellow_1.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.shuffle(hexTiles);
		gBoard = new Board(hexTiles);
		
	}
	
	public void paintDefaults(Graphics g) {
		//TITLE SCREEN
		if (state.equals("TITLE")) {
			g.drawImage(titleScreen, 0, 0, 1486, 950, null);
		//	g.drawRect(660, 720, 290, 50);
			
		}
		//RULES SCREEN
		else if (state.equals("RULES") ) {
			//once keerthana finishes the rules panel, put it here <3
		}
		
		//GAME PORTION
		else if (state.equals("GAME")) {
		//background hexagons
		g.drawImage(blueHex, 410, 75, 670, 520, null);
		g.drawImage(backDiceHex, 645, 650, 220, 200, null);
		g.drawImage(diceHex, 655, 660, 200, 180, null);	
		
		//dice buttons
		g.drawImage(rollDice, 710, 853, 95, 47, null);
		g.drawImage(passDice, 710, 600, 95, 47, null);
		
		//buildingcost cards
		g.drawImage(buildingCost, 1155, 175, 316, 390, null);
		g.drawImage(rotateImageByDegrees(buildingCost, 180.0), 15, 175, 316, 390, null);
		
		//perimeter resource cards
		g.drawImage(perimSheep, 1085, 290, 55, 80, null);
		g.drawImage(rotateImageByDegrees(perimStone, 130.0), 1045, 360, 71, 104, null);
		g.drawImage(rotateImageByDegrees(perimGrain, 130.0), 1015, 410, 71, 104, null);
		g.drawImage(rotateImageByDegrees(perimWood, 45.0), 1045, 195, 71, 104, null);
		g.drawImage(rotateImageByDegrees(perimBrick, 45.0), 1015, 145, 71, 104, null);
		
		//perimeter dev cards
		g.drawImage(perimDevBack, 350, 290, 55, 80, null);
		g.drawImage(perimLongRoad, 365, 175, 72, 104, null);
		g.drawImage(perimArmyCard, 365, 381, 72, 104, null);
	
		g.drawImage(redDice, 690, 680, 71, 72, null);
		g.drawImage(yellowDice, 745, 750, 71, 72, null);
		
		gBoard.paintTiles(g); 
		
		
		}
	}
	
	public void rollDice() {
		//this method'll be the one to distribute resources probably.............
		//executed by the panel class
		//im gonna implement this later :)
		int[] rolls = dice.roll();
		try {
			redDice =ImageIO.read(GameState.class.getResource("/diceFaces/red_"+ rolls[0]+ ".png"));
			yellowDice =ImageIO.read(GameState.class.getResource("/diceFaces/yellow_"+ rolls[1]+ ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	private static BufferedImage rotateImageByDegrees(BufferedImage buffImage, double angle) {
	    double radian = Math.toRadians(angle);
	    double sin = Math.abs(Math.sin(radian));
	    double cos = Math.abs(Math.cos(radian));

	    int width = buffImage.getWidth();
	    int height = buffImage.getHeight();

	    int nWidth = (int) Math.floor((double) width * cos + (double) height * sin);
	    int nHeight = (int) Math.floor((double) height * cos + (double) width * sin);

	    BufferedImage rotatedImage = new BufferedImage(
	            nWidth, nHeight, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D graphics = rotatedImage.createGraphics();

	    graphics.setRenderingHint(
	            RenderingHints.KEY_INTERPOLATION,
	            RenderingHints.VALUE_INTERPOLATION_BICUBIC);

	    graphics.translate((nWidth - width) / 2, (nHeight - height) / 2);
	    // rotation around the center point
	    graphics.rotate(radian, (double) (width / 2), (double) (height / 2));
	    graphics.drawImage(buffImage, 0, 0, null);
	    graphics.dispose();

	    return rotatedImage;
	}
	
	
}
