import java.awt.image.BufferedImage;
import java.util.*;
import static java.lang.System.*;
import java.io.*;
import java.awt.*;
public class DevelopmentCard {
    private String name="";
    private boolean isPlayable;
    private int vicPoints;
    private BufferedImage image;
    public DevelopmentCard(String n, boolean u, int vP)
    {
        name=n;
        vicPoints=vP;
        isPlayable=u;

    }

    public int getPoints()
    {
        return vicPoints;
    }

    public boolean canPlay()
    {
        return isPlayable;
    }

    public BufferedImage getImage()
    {
        return image;
    }

}