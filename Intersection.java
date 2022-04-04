import java.util.*;
import static java.lang.System.*;
import java.io.*;
import java.awt.*;
public class Intersection
{
    private int xCord;//Self explanatory
    private int yCord;//Same as above
    private boolean hasStructure;
    private boolean hasHarbor;
    private Structure struct;
    private ArrayList<Tile> borders;

    public Intersection(ArrayList<Tile> list, boolean hasHarb,
                        int x, int y)
    {
        borders = list;
        hasHarbor = hasHarb;
        xCord=x;
        yCord=y;
        hasStructure = false;
        struct = null;
    }

    public void putStructure(Structure s)
    {
        hasStructure=true;
        struct=s;
    }
}
