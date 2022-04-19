import java.util.ArrayList;
import java.util.Comparator;

public class Player {
	
	int victoryPoints;	//num victorypoints player has
	String color;	//color, self-explanatory
	ArrayList<ResourceCard> inventory;	//inventory of resource cards
	ArrayList<DevelopmentCard> vicCards;	//inventory of development cards
	ArrayList<Structure> builds;	//their builds and structures
	
	public Player(String s) {
		color = s;
		victoryPoints = 0;
		inventory = new ArrayList<>();
		vicCards = new ArrayList<>();
		builds = new ArrayList<>();
	}
	
	public void setColor(String s) {
		color = s;
	}
	
	public void buildStructure(int t, Intersection i) {
		boolean canPay = false;
		//t == 0 = road, t == 1 = settlement
		if (t == 0) {
			//checks if player has necessary resources
			canPay = inventory.contains(new ResourceCard("Brick")) && inventory.contains(new ResourceCard("Wood"));
		}
		else if (t == 1) {
			//checks if player has necessary resources
			canPay = inventory.contains(new ResourceCard("Brick")) && inventory.contains(new ResourceCard("Sheep"))
					&& inventory.contains(new ResourceCard("Grain")) && inventory.contains(new ResourceCard("Wood"));
		}
		
		if (canPay) {
			Structure struct = new Structure(t, color); //makes the structure the player wants
			i.putStructure(struct);
			addVictoryPoints(t);
			if (t==0) {
				//removes the materials
				inventory.remove(new ResourceCard("Brick"));
				inventory.remove(new ResourceCard("Wood"));
			}
			else if (t==1) {
				//removes the materials
				inventory.remove(new ResourceCard("Brick"));
				inventory.remove(new ResourceCard("Sheep"));
				inventory.remove(new ResourceCard("Grain"));
				inventory.remove(new ResourceCard("Wood"));
			}
		}
		else {
			//implement something about not being able to pay
			//:)
		}
	}
	
	public int numResources() {
		return inventory.size();
	}
	
	
	public void addVictoryPoints(int i) { //adds victory points, self-explanatory
		victoryPoints += i;
	}
	
	public void addResources(ArrayList<ResourceCard> arrList) { //adds resource cards
		inventory.addAll(arrList);
	}
	public void removeResources(ArrayList<ResourceCard> arrList) { //adds resource cards
		inventory.removeAll(arrList);
	}
	
	public String toString() {
		return color;
	}
	public void addVicCard(DevelopmentCard d){vicCards.add(d);}
	public ArrayList<Structure> getBuilds(){return builds;}
	
}
