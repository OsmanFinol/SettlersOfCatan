import java.util.*;

import javax.imageio.ImageIO;

import static java.lang.System.*;
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
public class Harbor
{
    private int inputNum;
    private int outputNum;
    private ResourceCard input;
    private ResourceCard output;
    private BufferedImage im;

    public Harbor(int iP, int oP, ResourceCard iR)
    {
        input = iR;
        inputNum=iP;
        outputNum = oP;
        try {
  			im = ImageIO.read(Harbor.class.getResource("/Images/harbor.png"));
  		} catch (IOException e) {
  			
  		}
    }

    public boolean canUse(Player P)
    {
        ArrayList<Structure> b = P.getBuilds();//add get builds method
        for(Structure struct:b)
        {
            if(struct.getNearHarbor() == true)//Add getter method to structure
            {
                return true;
            }
        }
        return false;
    }
    
    public BufferedImage getImage() {
    	return im;
    }
    
    public String resource() {
    	if (input.getName().length() > 1)
    		return input.getName().substring(0, 2);
    	else
    		return input.getName().substring(0, 1);
    }
    
    public String toString() {
    	return inputNum + ":" + outputNum;
    }
}
