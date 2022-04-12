import java.util.*;

public class DevCardDeck
{
    private Stack<DevelopmentCard> deck;
    private Scanner app;

    public DevCardDeck()
    {
        app = new Scanner("DevCards.txt");
        while (app.hasNextLine())
        {
            //Put in order
            String name = app.next();
            boolean playable = Boolean.parseBoolean(app.next());
            int pts = Integer.parseInt(app.next());
            DevelopmentCard temp =
                    new DevelopmentCard(name, playable, pts);
            deck.push(temp);
        }
    }
    public DevelopmentCard draw (Player player)
    {
        DevelopmentCard dc = deck.pop();
        player.addVicCard(dc);
        return dc;

    }

    public void shuffle()
    {
        Collections.shuffle(deck);
    }
}