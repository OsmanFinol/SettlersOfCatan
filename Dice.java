import java.util.Random;

public class Dice {
	
	int sides;
	Random rand;
	
	public Dice() {
		sides = 6;
		rand = new Random();
	}
	
	public int roll() {
		return (1+rand.nextInt(sides)) + (1+rand.nextInt(sides));
	}
	
	
}
