import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SettlersOfCatanPanel extends JPanel implements MouseListener {
	private GameState gs;
	Dice d;

	public SettlersOfCatanPanel() {
		gs = new GameState();
		addMouseListener(this);
		d = new Dice();

	}
	
	
	public void paint(Graphics g) {
		g.setColor(new Color(210, 180, 140, 250));
		g.fillRect(0, 0, 2000, 2000);
		gs.paintState(g);
	}


	
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		//rolling dice
		if (x >= 710 && x <= 710+95 && y >= 853 && y <= 853+47) {
			gs.rollDice();
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
