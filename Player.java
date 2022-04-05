import java.util.ArrayList;

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
	
	
	
	public void buildStructure(int t, Intersection i) {
		boolean canPay = false;
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
			//i.putStructure(struct);
			if (t==0) {
				inventory.remove(new ResourceCard("Brick"));
				inventory.remove(new ResourceCard("Wood"));
			}
			else if (t==1) {
				inventory.remove(new ResourceCard("Brick"));
				inventory.remove(new ResourceCard("Sheep"));
				inventory.remove(new ResourceCard("Grain"));
				inventory.remove(new ResourceCard("Wood"));
			}
			System.out.println("Built!");
		}
		else {
			//implement something about not being able to pay
			//:)
			System.out.println("Can't Build");
		}
	}
	
	public void addResources(ArrayList<ResourceCard> arrList) {
		inventory.addAll(arrList);
	}

    public ArrayList<Structure> getBuilds(){return builds;}
}
