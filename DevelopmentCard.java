import java.awt.image.BufferedImage;
import java.util.*;

import javax.imageio.ImageIO;

import static java.lang.System.*;
import java.io.*;
import java.awt.*;
public class DevelopmentCard {
    private String name="";
    private boolean isPlayable;
    private int vicPoints;
    private BufferedImage image;
    private int numTurns; //number of turns since being drawn
    public DevelopmentCard(String n, boolean u, int vP)
    {
        name=n;
        vicPoints=vP;
        isPlayable=u;
        numTurns = 0;
        try {
			image = ImageIO.read(GameState.class.getResource("/DevCards/"+name+".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public String getName() {
    	return name;
    }

    public int getPoints()
    {
        return vicPoints;
    }
    public void incrementTurns() {
    	numTurns++;
    }
    public int getTurns() {
    	return numTurns;
    }

    public boolean canPlay()
    {
        return isPlayable;
    }

    public BufferedImage getImage()
    {
        return image;
    }

    public void setImage(BufferedImage a){image=a;}

}
