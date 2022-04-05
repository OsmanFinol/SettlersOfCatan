import java.util.*;
import static java.lang.System.*;
import java.io.*;
import java.awt.*;
public class Harbor
{
    private int inputNum;
    private int outputNum;
    private ResourceCard input;
    private ResourceCard output;

    public Harbor(ResourceCard iR,
                  ResourceCard oR,
                  int iP, int oP)
    {
        input = iR;
        output = oR;
        inputNum=iP;
        outputNum = oP;
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
}
