import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceCard {
	
	String type;		//brick, grain, sheep, stone, wood
	BufferedImage image;
	int code = 0;
	
	
	public ResourceCard(String t) {
		type = t;
		if (t.equals("Brick"))
			code = 1;
		else if (t.equals("Grain"))
			code = 2;
		else if (t.equals("Sheep"))
			code = 3;
		else if (t.equals("Stone"))
			code = 4;
		else if (t.equals("Wood"))
			code = 5;
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
	public String toString() {
		return type;
	}
	@Override
	public int hashCode() {
	  return code;
	}
	@Override
	public boolean equals(Object o) {
		return type.equals(o.toString());
	}
	
	
}
