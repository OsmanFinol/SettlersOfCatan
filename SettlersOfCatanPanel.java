import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.datatransfer.SystemFlavorMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class SettlersOfCatanPanel extends JPanel implements MouseListener {
	private GameState gs;
	private ArrayList<String> lines; // this arraylist has all the log stuff, to add something to the log,
										// add it here
	private boolean actualGame = true;
	String[] colors = { "White", "Orange", "Blue", "Red" };
	String c1, c2, c3, c4; // how we'll access the drop-downs, since they have to be locally made
	ArrayList<String> colorsToSet;

	public SettlersOfCatanPanel(LayoutManager lm) {
		super(lm);
		gs = new GameState();
		colorsToSet = new ArrayList<String>();
		addMouseListener(this);
		lines = new ArrayList<String>();
		lines.add("Game has started! Good luck!");
		c1 = "White";
		c2 = "White";
		c3 = "White";
		c4 = "White"; // so they arent null
	}

	public void paintComponent(Graphics g) {
		gs.paintDefaults(g);
		// COLOR SELECTION DROPDOWNS
		JComboBox<String> colorPicker1 = new JComboBox<String>(colors);
		colorPicker1.setBounds(635, 270, 200, 90);
		colorPicker1.setVisible(false);
		colorPicker1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c1 = colorPicker1.getSelectedItem().toString();
			}
		});
		add(colorPicker1);
		JComboBox<String> colorPicker2 = new JComboBox<String>(colors);
		colorPicker2.setBounds(635, 370, 200, 90);
		colorPicker2.setVisible(false);
		colorPicker2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c2 = colorPicker2.getSelectedItem().toString();
			}
		});

		add(colorPicker2);
		JComboBox<String> colorPicker3 = new JComboBox<String>(colors);
		colorPicker3.setBounds(635, 470, 200, 90);
		colorPicker3.setVisible(false);
		colorPicker3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c3 = colorPicker3.getSelectedItem().toString();
			}
		});
		add(colorPicker3);
		JComboBox<String> colorPicker4 = new JComboBox<String>(colors);
		colorPicker4.setBounds(635, 570, 200, 90);
		colorPicker4.setVisible(false);
		colorPicker4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c4 = colorPicker4.getSelectedItem().toString();
			}
		});
		add(colorPicker4);

		if (gs.getSubState().equals("default"))
			gs.paintLog(g, lines);
		else if (gs.getSubState().equals("setcolors")) {
			colorPicker1.setVisible(true);
			colorPicker2.setVisible(true);
			if (gs.getNumPlayers() >= 3)
				colorPicker3.setVisible(true);
			if (gs.getNumPlayers() >= 4)
				colorPicker4.setVisible(true);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		// buttons on title screen
		if (gs.getState().equals("TITLE")) {
			// selecting game
			if (gs.getSubState().equals("title")) {
				if (x >= 610 && y >= 583 && x <= 610 + 180 && y <= 583 + 50) {
					if (actualGame)
						gs.setSubState("findnumplayers4");
					else {
						gs.setState("GAME");
						gs.setSubState("default");
					}
					repaint();
				}
				// going to menu
				else if (x >= 690 && y >= 640 && x <= 690 + 220 && y <= 640 + 50) {
					gs.setState("RULES");
					gs.setSubState("page1");
					repaint();
				} else if (x >= 660 && y >= 720 && x <= 660 + 290 && y <= 720 + 50) {
					// quitting
					System.exit(0);
				}
			} else if (gs.getSubState().equals("findnumplayers4")) {
				if (x >= 740 && y >= 375 && x <= 740 + 50 && y <= 375 + 50) {
					gs.setSubState("findnumplayers3");
					repaint();
				} else if (x >= 480 && y >= 375 && x <= 480 + 50 && y <= 375 + 50) {
					gs.setSubState("findnumplayers2");
					repaint();
				} else if (x >= 560 && y >= 520 && x <= 560 + 340 && y <= 520 + 150) {
					gs.setState("GAME");
					gs.setSubState("setcolors");
					repaint();
				}
			} else if (gs.getSubState().equals("findnumplayers3")) {
				if (x >= 1000 && y >= 375 && x <= 1050 && y <= 375 + 50) {
					gs.setSubState("findnumplayers4");
					repaint();
				} else if (x >= 480 && y >= 375 && x <= 480 + 50 && y <= 375 + 50) {
					gs.setSubState("findnumplayers2");
					repaint();
				} else if (x >= 560 && y >= 520 && x <= 560 + 340 && y <= 520 + 150) {
					gs.setState("GAME");
					gs.setSubState("setcolors");
					repaint();
				}
			} else if (gs.getSubState().equals("findnumplayers2")) {
				if (x >= 1000 && y >= 375 && x <= 1050 && y <= 375 + 50) {
					gs.setSubState("findnumplayers4");
					repaint();
				} else if (x >= 740 && y >= 375 && x <= 740 + 50 && y <= 375 + 50) {
					gs.setSubState("findnumplayers3");
					repaint();
				} else if (x >= 560 && y >= 520 && x <= 560 + 340 && y <= 520 + 150) {
					gs.setState("GAME");
					gs.setSubState("setcolors");
					repaint();
				}
			}

		}
		// rules screen
		else if (gs.getState().equals("RULES")) {
			if (gs.getSubState().equals("page1")) {
				if (x >= (5 * (1500 / 6) - (1500 / 60)) && y >= 28 * (950 / 29) - (950 / 24) - 20
						&& x <= (5 * (1500 / 6) - (1500 / 60)) + 1500 / 15
						&& y <= 28 * (950 / 29) - (950 / 24) - 20 + 2 * (950 / 29)) {
					gs.setSubState("page2");
					repaint();
				}
			} else if (gs.getSubState().equals("page2")) {
				if (x >= (5 * (1500 / 6) - (1500 / 60)) && y >= 28 * (950 / 29) - (950 / 24) - 20
						&& x <= (5 * (1500 / 6) - (1500 / 60)) + 1500 / 15
						&& y <= 28 * (950 / 29) - (950 / 24) - 20 + 2 * (950 / 29)) {
					gs.setSubState("page3");
					repaint();
				} else if (x >= 1500 / 30 && y >= 28 * (950 / 29) - (950 / 24) - 20 && x <= 1500 / 15 + 1500 / 30
						&& y <= 28 * (950 / 29) - (950 / 24) + 2 * (950 / 29)) {
					gs.setSubState("page1");
					repaint();
				}
			} else if (gs.getSubState().equals("page3")) {
				if (x >= (5 * (1500 / 6) - (1500 / 60)) && y >= 28 * (950 / 29) - (950 / 24) - 20
						&& x <= (5 * (1500 / 6) - (1500 / 60)) + 1500 / 15
						&& y <= 28 * (950 / 29) - (950 / 24) - 20 + 2 * (950 / 29)) {
					gs.setState("TITLE");
					gs.setSubState("title");
					repaint();
				} else if (x >= 1500 / 30 && y >= 28 * (950 / 29) - (950 / 24) - 20 && x <= 1500 / 15 + 1500 / 30
						&& y <= 28 * (950 / 29) - (950 / 24) + 2 * (950 / 29)) {
					gs.setSubState("page2");
					repaint();
				}
			}
		}

		// buttons on game screen
		else if (gs.getState().equals("GAME")) {
			// rolling dice
			if (x >= 710 && x <= 710 + 95 && y >= 853 && y <= 853 + 47) {
				if (!(gs.diceRolled())) {
					int[] temp = gs.rollDice();
					lines.add(gs.getCPlayer() + " rolled a " + temp[0] + " and a " + temp[1] + ".");
					repaint();
				}
			}
			// passing the dice/ending ur turn
			else if (x >= 710 && y >= 600 && x <= 710 + 95 && y <= 600 + 47) {
				lines.add(gs.getCPlayer() + " ended their turn.");
				lines.add("It is now " + gs.getNextPlayer() + "'s turn.");
				gs.nextPlayer();
				repaint();
			} 
			if (gs.cPlayerIndex() == 1) {
				if (x >= 1260 && x <= 1336 && y >= 15 && y <= 40) {
					gs.setSubState("buildmenu");
					repaint();
				}
			}
			else if (gs.getSubState().equals("setcolors") || gs.getSubState().equals("redocolor")) {
				if (x >= 890 && y >= 565 && x <= 890 + 170 && y <= 565 + 70) {
					// add selected items to arraylist, then do some fun java stuff to remove the duplicates :)
					// if there are duplicates, then list wont be right size
					// so thats how we know they selected a color more than once
					//i tried to do this with a treeset and i forgot that it also sorted the elements so :|
					ArrayList<String> temp = new ArrayList<String>();
					temp.add(c1);
					temp.add(c2);
					if (gs.getNumPlayers() > 2)
						temp.add(c3);
					if (gs.getNumPlayers() > 3)
						temp.add(c4);
					Set<String> s = new LinkedHashSet<>();
					s.addAll(temp);
					temp.clear();
					temp.addAll(s);
					if (temp.size() != gs.getNumPlayers())
						gs.setSubState("redocolor");
					else {
						gs.setSubState("default");
						gs.setPlayerColors(temp);
						removeAll(); // this'll get rid of the jcomboboxes finally
					}
					repaint();

				}
			}
			int i=-1;
			if(x>=280 && x<=363 && y>=15 && y<=40)
			{i=0;}
			else if(x>=1160 && x<=1283 && y>=15 && y<=40)
			{i=1;}
			else if(x>=435 && x<=518 && y>=720 && y<=745)
			{i=2;}
			else if(x>=1015 && x<=1098 && y>=720 && y<=745)
			{i=3;}
			gs.showCard(getGraphics(), i);
			repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
