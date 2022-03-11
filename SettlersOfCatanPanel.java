import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SettlersOfCatanPanel extends JPanel {
	private BufferedImage buildingCost;
	private GameState gs;

	public SettlersOfCatanPanel() {
		try {
			buildingCost = ImageIO.read(SettlersOfCatanPanel.class.getResource("/Images/building_costs.png"));
			
		} catch (Exception e) {
			System.out.println("Exception Error");
			return;
		}
		
		gs = new GameState();

	}
	
	
	public void paint(Graphics g) {
		g.setColor(new Color(210, 180, 140, 250));
		g.fillRect(0, 0, 2000, 2000);
		//g.drawImage(buildingCost, 45, 50, 100, 150, null);
		gs.paint(g);
	}
	
}
