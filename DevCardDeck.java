import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DevCardDeck
{
    private Stack<DevelopmentCard> deck;
    private Scanner app;

    public DevCardDeck()
    {
    	deck = new Stack<>();
        try {
			app = new Scanner(new File("DevCards.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        while (app.hasNextLine())
        {
            //Put in order
            Scanner t = new Scanner(app.nextLine());
            String name = t.next();
            boolean playable = Boolean.parseBoolean(t.next());
            int pts = Integer.parseInt(t.next());
            DevelopmentCard temp =
                    new DevelopmentCard(name, playable, pts);
            deck.push(temp);
        }
    }
    public DevelopmentCard draw (Player player)
    {
        DevelopmentCard dc = deck.pop();
        if(dc.getName().equals("Knight"))
        {
            player.setKnightCards(1);
        }
        player.addVicCard(dc);
        player.addVictoryPoints(dc.getPoints());
        return dc;

    }

    public void shuffle()
    {
        Collections.shuffle(deck);
    }


}
