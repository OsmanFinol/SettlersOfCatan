import javax.swing.JFrame;

public class SettlersOfCatanFrame extends JFrame{

	private static final int WIDTH = 1400;
	private static final int HEIGHT = 900;
	
	
	public SettlersOfCatanFrame(String s) {
		super(s);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		add(new SettlersOfCatanPanel());
		setVisible(true);
		
		
		
	}
	
	
	
	
}
