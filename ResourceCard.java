import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceCard {
	
	String type;		//brick, grain, sheep, stone, wood
	BufferedImage image;
	
	
	public ResourceCard(String t) {
		type = t;
		try {
			image = ImageIO.read(ResourceCard.class.getResource("/CardImages/" + t + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return type;
	}
	public BufferedImage getImage() {
		return image;
	}
	
}
