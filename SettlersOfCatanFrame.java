import javax.swing.JFrame;

public class SettlersOfCatanFrame extends JFrame{

	private static final int WIDTH = 1500;
	private static final int HEIGHT = 950;
	
	
	public SettlersOfCatanFrame(String s) {
		super(s);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		add(new SettlersOfCatanPanel(null));
		setVisible(true);
		
		
	}
	
	
	
	
}
