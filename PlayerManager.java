import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class PlayerManager {
	ArrayList<Player> players;
	int cPlayer;
	ArrayList<Player> playerStanding;
	int numP;
	private Player longestArmy;
	public PlayerManager(int num) { //num of players playing
		players = new ArrayList<>();
		cPlayer = 0;
		playerStanding = new ArrayList<Player>();
		Scanner scan = new Scanner("Red White Orange Blue"); //sets the player colors
		numP = num;
		for (int i = 0; i < num; i++) {
			players.add(new Player(scan.next()));
			playerStanding.add(players.get(i));
		}
		scan.close();
	}
	
	//switches to next player
	public void nextPlayer() {
		cPlayer++;
		if (cPlayer == numP) {
			cPlayer = 0;
		}
	}
	
	//returns player
	public Player getCPlayer() {
		return players.get(cPlayer);
	}
	public Player getNextPlayer() {
		int c = cPlayer+1;
		if (c == numP) {
			c = 0;
		}
		return players.get(c);
	}
	public int getNumPlayers() {
		return numP;
	}
	public ArrayList<Player> getPlayerList() {
		return players;
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
	
	public void setColors(ArrayList<String> colors) {
		Iterator<String> iter = colors.iterator();
		for (int i = 0; i < players.size(); i++) {
			players.get(i).setColor(iter.next());
		}
	}
	
	public void updateStanding() {
		Collections.sort(playerStanding, new playerComparer());
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
	
	public ArrayList<ResourceCard> getPlayersHand(int index){return players.get(index).getInventory();}
	
	public void reOrderBasedOnRolls() {
		Collections.sort(players, new rollReOrder());
		Collections.reverse(players);
	}
	
	//ignore this, it's just so that the treeset works :)
	public class playerComparer implements Comparator<Player>
	{
	    public int compare(Player p1, Player p2)
	    {
	        return p1.victoryPoints - p2.victoryPoints;
	    }
	}
	
	//also ignore this AHA AHA HAA
	public class rollReOrder implements Comparator<Player> {
		public int compare(Player p1, Player p2) {
			return p1.orderRoll - p2.orderRoll;
		}
	}

	public int cPlayerIndex() {
		return cPlayer;
	}

	public Player findLongestArmy()
	{
		Player temp = players.get(0);
		int num=0;
		for(Player P:players)
		{
			if(P.getKnightCards()>num && P.getKnightCards()>3)
			{
				longestArmy=P;
				num=P.getKnightCards();
			}
		}
		longestArmy.addVictoryPoints(2);
		return longestArmy;
	}

	public Player getLongestArmy() {
		return longestArmy;
	}
}
