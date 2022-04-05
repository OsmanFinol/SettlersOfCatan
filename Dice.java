import java.util.Random;

public class Dice {
	
	int sides;
	Random rand;
	
	public Dice() {
		sides = 6;
		rand = new Random();
	}
	
	public int[] roll() {
		int[] nums = new int[3];
		nums[0] = (1+rand.nextInt(sides));
		nums[1] = (1+rand.nextInt(sides));
		nums[2] = nums[0]+nums[1];
		return nums;
	}
	
	
}
