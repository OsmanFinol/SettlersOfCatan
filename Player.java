import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

public class Player {

	int victoryPoints; // num victorypoints player has
	String color; // color, self-explanatory
	ArrayList<ResourceCard> inventory; // inventory of resource cards
	ArrayList<DevelopmentCard> vicCards; // inventory of development cards
	ArrayList<Structure> builds; // their builds and structures
	int orderRoll;
	int[]cards;//brick, stone, sheep, wood, grain

	public Player(String s) {
		color = s;
		victoryPoints = 0;
		inventory = new ArrayList<>();
		vicCards = new ArrayList<>();
		builds = new ArrayList<>();
		cards=new int[5];
	}

	public void setColor(String s) {
		color = s;
	}

	public String getColor() {
		return color;
	}

	public void buildStructure(int t, Intersection i) {
		boolean canPay = false;
		// t == 0 = road, t == 1 = settlement
		if (t == 0) {
			// checks if player has necessary resources
			canPay = inventory.contains(new ResourceCard("Brick")) && inventory.contains(new ResourceCard("Wood"));
		} else if (t == 1) {
			// checks if player has necessary resources
			canPay = inventory.contains(new ResourceCard("Brick")) && inventory.contains(new ResourceCard("Sheep"))
					&& inventory.contains(new ResourceCard("Grain")) && inventory.contains(new ResourceCard("Wood"));
		}

		if (canPay) {
			Structure struct = new Structure(t, color); // makes the structure the player wants
			i.putStructure(struct);
			addVictoryPoints(t);
			if (t == 0) {
				// removes the materials
				inventory.remove(new ResourceCard("Brick"));
				inventory.remove(new ResourceCard("Wood"));
			} else if (t == 1) {
				// removes the materials
				inventory.remove(new ResourceCard("Brick"));
				inventory.remove(new ResourceCard("Sheep"));
				inventory.remove(new ResourceCard("Grain"));
				inventory.remove(new ResourceCard("Wood"));
			}
		} else {
			// implement something about not being able to pay
			// :)
		}
		reviseCount();
	}

	public int numResources() {
		return inventory.size();
	}

	public void addVictoryPoints(int i) { // adds victory points, self-explanatory
		victoryPoints += i;
	}

	public void addResources(ArrayList<ResourceCard> arrList) { // adds resource cards
		inventory.addAll(arrList);
		reviseCount();
	}

	public void removeResources(ArrayList<ResourceCard> arrList) { // adds resource cards
		inventory.removeAll(arrList);
		reviseCount();
	}

	public String toString() {
		return color;
	}

	public void addVicCard(DevelopmentCard d) {
		vicCards.add(d);
	}

	public ArrayList<Structure> getBuilds() {
		return builds;
	}

	public ArrayList<ResourceCard> getInventory() {
		return inventory;
	}

	public ResourceCard[] getNoDuplicateInventory() {
		HashSet<ResourceCard> temp = new HashSet<>();
		temp.addAll(inventory);
		ArrayList<ResourceCard> temp2 = new ArrayList<>();
		temp2.addAll(temp);
		ResourceCard[] arr = new ResourceCard[temp2.size()];
		for (int j = 0; j < temp2.size(); j++) {
			arr[j] = temp2.get(j);
		}
		return arr;
	}

	public int handSize() {
		return inventory.size();
	}
	
	public int numOfCards(ResourceCard rc) {	//checks how many of the given resource card the player has
		int cnt = 0;
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).equals(rc))
				cnt++;
		}
		return cnt;
	}

	public int[]getCards(){return cards;}

	public void reviseCount()
	{//brick, stone, sheep, wood, grain
		for(ResourceCard c:inventory)
		{
			if(c.getName().equals("Brick"))
			{
				cards[0]++;
			}
			else if(c.getName().equals("Stone"))
			{
				cards[1]++;
			}
			else if(c.getName().equals("Sheep"))
			{
				cards[2]++;
			}
			else if(c.getName().equals("Wood"))
			{
				cards[3]++;
			}
			else if(c.getName().equals("Grain"))
			{
				cards[4]++;
			}
		}
	}
}
