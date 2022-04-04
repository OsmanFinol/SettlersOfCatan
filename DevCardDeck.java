import java.util.*;
import static java.lang.System.*;
import java.io.*;
import java.awt.*;
public class DevCardDeck {
    private Stack<DevelopmentCard> deck;
    private Scanner app;

    public DevCardDeck()
    {
        app = new Scanner("DevCards.txt");
        while(app.hasNextLine())
        {
            //Put in order
            String name=app.next();
            boolean playable = Boolean.parseBoolean(app.next());
            int pts = Integer.parseInt(app.next());
            DevelopmentCard temp =
                    new DevelopmentCard(name, playable, pts);
            deck.push(temp);
        }

    public DevelopmentCard draw(Player p)
        {
            DevelopmentCard dc = deck.pop();
            p.addVicCard(dc);

        }

        public void shuffle()
        {
        Collections.shuffle(deck);
        }
    }
}
