import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class PlayerManager {
	ArrayList<Player> players;
	int cPlayer;
	TreeSet<Player> playerStanding;
	
	public PlayerManager(int num) { //num of players playing
		players = new ArrayList<>();
		cPlayer = 0;
		playerStanding = new TreeSet<Player>(new playerComparer());
		Scanner scan = new Scanner("Red White Orange Blue"); //sets the player colors
		for (int i = 0; i < num; i++) {
			players.add(new Player(scan.next()));
			playerStanding.add(players.get(i));
		}
		scan.close();
	}
	
	//switches to next player
	public void nextPlayer() {
		cPlayer++;
		if (cPlayer == 4) {
			cPlayer = 0;
		}
	}

	public Player getNextPlayer()
	{
		nextPlayer();
		return players.get(cPlayer);
	}
	
	//returns player
	public Player getCPlayer() {
		return players.get(cPlayer);
	}
	
	public void trade(Player p, ArrayList<ResourceCard> getting, ArrayList<ResourceCard> giving) { 
		//getting and giving from the cPlayer's perspective, not p's
		Player cPlay = players.get(cPlayer);
		cPlay.addResources(getting);
		cPlay.removeResources(giving);
		p.addResources(giving);
		p.removeResources(getting);
	}
	
	public void steal(Player p, ResourceCard chosen) {
		Player cPlay = getCPlayer();
		ArrayList<ResourceCard> list = new ArrayList<>();
		list.add(chosen);
		cPlay.addResources(list);
		p.removeResources(list);
	}
	
	//changing this method a bit
	//it'll find all the players that need to discard and return them
	//then in panel or gamestate or smthn we'll force the player to remove their cards if needbe
	//we'll see if it works
	//:\
	public ArrayList<Player> forcedDiscard() {
		ArrayList<Player> temp = new ArrayList<>();
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).numResources() > 7) {
				temp.add(players.get(i));
			}
		}
		return temp;
	}
	
	
	
	public void updateStanding() {
		playerStanding.clear();
		playerStanding.addAll(players);
	}
	
	public Player hasWon() {
		updateStanding();
		Iterator<Player> iter = playerStanding.iterator();
		while (iter.hasNext()) {
			Player temp = iter.next();
			if (temp.victoryPoints >= 10) {
				return temp;
			}
		}
		return null;
	}
	
	//ignore this, it's just so that the treeset works :)
	public class playerComparer implements Comparator<Player>
	{
	    public int compare(Player p1, Player p2)
	    {
	        return p1.victoryPoints - p2.victoryPoints;
	    }
	}
}
