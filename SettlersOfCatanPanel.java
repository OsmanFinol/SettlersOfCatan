import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SettlersOfCatanPanel extends JPanel implements MouseListener {
	private GameState gs;
	private ArrayList<String> lines; 		//this arraylist has all the log stuff, to add something to the log,
	//add it here

	public SettlersOfCatanPanel() {
		gs = new GameState();
		addMouseListener(this);
		lines = new ArrayList<String>();
	}


	public void paint(Graphics g) {
		g.setColor(new Color(210, 180, 140, 250));
		g.fillRect(0, 0, 2000, 2000);
		gs.paintDefaults(g);
		gs.paintLog(g, lines);
	}



	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		//buttons on title screen
		if (gs.getState().equals("TITLE")) {
			//selecting game
			if (x >= 610 && y >= 583 && x <= 610+180 && y <= 583+50) {
				gs.setState("GAME");
				gs.setSubState("findorder");
				repaint();
			}
			else if (x >=660 && y >= 720 && x <= 660+ 290 && y<= 720+50) {
				//quitting
				System.exit(0);
			}
		}

		//buttons on game screen
		else if (gs.getState().equals("GAME")) {
			//rolling dice
			if (x >= 710 && x <= 710+95 && y >= 853 && y <= 853+47) {
				if (!(gs.diceRolled())) {
					int[] temp = gs.rollDice();
					lines.add(gs.getCPlayer() + " rolled a " + temp[0] + " and a " + temp[1] + ".");
					repaint();
				}
			}
			//passing the dice/ending ur turn
			else if (x >= 710 && y >= 600 && x <= 710+95 && y <= 600+47) {
				lines.add(gs.getCPlayer() + " ended their turn.");
				lines.add("It is now " + gs.getNextPlayer() + "'s turn.");
				gs.nextPlayer();
				repaint();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}