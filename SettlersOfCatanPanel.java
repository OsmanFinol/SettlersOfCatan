import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class SettlersOfCatanPanel extends JPanel implements MouseListener {
	private GameState gs;
	private ArrayList<String> lines; // this arraylist has all the log stuff, to add something to the log,
										// add it here
	private boolean actualGame = false;
	String[] colors = { "White", "Orange", "Blue", "Red" };
	String c1, c2, c3, c4; // how we'll access the drop-downs, since they have to be locally made
	ArrayList<String> colorsToSet;
	int reqDropDowns, offDropDowns;
	HashMap<Integer, JComboBox<ResourceCard>> reqTrades;
	HashMap<Integer, JComboBox<Integer>> reqTradeAmounts;
	HashMap<Integer, JComboBox<ResourceCard>> offTrades;
	HashMap<Integer, JComboBox<Integer>> offTradeAmounts;
	int total = 0;
	ArrayList<ResourceCard> request = new ArrayList<ResourceCard>();
	ArrayList<ResourceCard> offers = new ArrayList<ResourceCard>();
	boolean leftAccept = false;
	boolean rightAccept = false;
	boolean declined = false;
	DevCardDeck deck;

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
		reqDropDowns = -1;
		offDropDowns = -1;
		reqTrades = new HashMap<>();
		reqTradeAmounts = new HashMap<>();
		offTrades = new HashMap<>();
		offTradeAmounts = new HashMap<>();
		deck = new DevCardDeck();
		deck.shuffle();
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

		if (gs.getSubState().equals("default") || gs.getSubState().equals("trading")
				|| gs.getSubState().equals("redoTradeReq") || gs.getSubState().equals("redoTradeOff")
				|| gs.getSubState().equals("buildmenu") || gs.getSubState().equals("buildSettlement")
				|| gs.getSubState().equals("buildCity") || gs.getSubState().equals("buildRoad")
				|| gs.getSubState().equals("banktrade") || gs.getSubState().equals("redobanktrade")
				|| gs.getSubState().equals("faileddevcard") || gs.getSubState().equals("moverobber")) {
			gs.paintLog(g, lines);
			if (gs.getSubState().equals("trading")) {
				for (int i = 0; i <= reqDropDowns; i++) {
					reqTrades.get(i).setVisible(true);
					reqTradeAmounts.get(i).setVisible(true);
				}

				for (int i = 0; i <= offDropDowns; i++) {
					offTrades.get(i).setVisible(true);
					offTradeAmounts.get(i).setVisible(true);
				}
			} else if (gs.getSubState().equals("banktrade")) {
				for (int i = 0; i < 2; i++) {
					offTrades.get(i).setVisible(true);
				}
			}
		} else if (gs.getSubState().equals("setcolors")) {
			colorPicker1.setVisible(true);
			colorPicker2.setVisible(true);
			if (gs.getNumPlayers() >= 3)
				colorPicker3.setVisible(true);
			if (gs.getNumPlayers() >= 4)
				colorPicker4.setVisible(true);
		} else if (gs.getSubState().equals("robberdiscard")) {
			if (gs.mustDiscard.size() > 0) {
				gs.setSubState("forcingdiscard");
				gs.forcedDiscard.clear();
				gs.forcedDiscardCords.clear();
			} else
				gs.setSubState("moverobber");
			repaint();
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
			if (gs.getSubState().equals("default") || gs.getSubState().equals("faileddevcard")
					|| gs.getSubState().equals("showcards")) {
				if (x >= 710 && x <= 710 + 95 && y >= 853 && y <= 853 + 47) {
					if (!(gs.diceRolled())) {
						int[] temp = gs.rollDice();
						lines.add(gs.getCPlayer() + " rolled a " + temp[0] + " and a " + temp[1] + ".");
						if (temp[2] != 7)
							gs.setSubState("default");
						else {
							lines.add("Oh no! " + gs.getCPlayer() + " has to move the robber!");
							gs.setSubState("robberdiscard");
						}
						repaint();
					}
				}
				// passing the dice/ending ur turn
				else if (x >= 710 && y >= 600 && x <= 710 + 95 && y <= 600 + 47) {
					lines.add(gs.getCPlayer() + " ended their turn.");
					lines.add("It is now " + gs.getNextPlayer() + "'s turn.");
					gs.setSubState("default");
					gs.nextPlayer();
					repaint();
				}

				// trading stuff
				else if (x >= 145 && y >= 15 && x <= 145 + 122 && y <= 40) {
					if (gs.cPlayerIndex() != 0) {
						gs.setSubState("trading");
						gs.setToTradeWith(gs.getPM().getPlayerList().get(0));
						lines.add(gs.getCPlayer() + " requested to trade with " + gs.getPM().getPlayerList().get(0)
								+ ".");
						repaint();
					}
				} else if (x >= 1025 && y >= 15 && x <= 1025 + 122 && y <= 40) {
					if (gs.cPlayerIndex() != 1) {
						gs.setSubState("trading");
						gs.setToTradeWith(gs.getPM().getPlayerList().get(1));
						lines.add(gs.getCPlayer() + " requested to trade with " + gs.getPM().getPlayerList().get(1)
								+ ".");
						repaint();
					}
				} else if (x >= 300 && y >= 720 && x <= 300 + 122 && y <= 720 + 25 && gs.getNumPlayers() > 2) {
					if (gs.cPlayerIndex() != 2) {
						gs.setSubState("trading");
						gs.setToTradeWith(gs.getPM().getPlayerList().get(2));
						lines.add(gs.getCPlayer() + " requested to trade with " + gs.getPM().getPlayerList().get(2)
								+ ".");
						repaint();
					}
				} else if (x >= 880 && y >= 720 && x <= 880 + 122 && y <= 720 + 25 && gs.getNumPlayers() > 3) {
					if (gs.cPlayerIndex() != 3) {
						gs.setSubState("trading");
						gs.setToTradeWith(gs.getPM().getPlayerList().get(3));
						lines.add(gs.getCPlayer() + " requested to trade with " + gs.getPM().getPlayerList().get(3)
								+ ".");
						repaint();
					}
				} else if (x >= 1235 && y >= 568 && x <= 1235 + 193 && y <= 568 + 66) {
					gs.setSubState("banktrade");
					offDropDowns++;
					ResourceCard[] arr = gs.getCPlayer().getNoDuplicateInventory();
					JComboBox<ResourceCard> picker = new JComboBox<ResourceCard>(arr);
					picker.setBounds(390 + (140 * offDropDowns), 305, 325, 140);
					picker.setVisible(false);
					add(picker);
					offTrades.put(offDropDowns, picker);

					offDropDowns++;
					ResourceCard[] rcs = { new ResourceCard("Grain"), new ResourceCard("Wood"),
							new ResourceCard("Brick"), new ResourceCard("Sheep"), new ResourceCard("Stone") };
					JComboBox<ResourceCard> picker2 = new JComboBox<ResourceCard>(rcs);
					picker2.setBounds(785, 305, 325, 140);
					picker2.setVisible(false);
					add(picker2);
					offTrades.put(offDropDowns, picker2);
					lines.add(gs.getCPlayer() + " selected to trade with the bank.");
					repaint();
				} else if (x >= 340 && y >= 500 && x <= 340 + 140 && y <= 570) {
					ArrayList<ResourceCard> temp = new ArrayList<>();
					temp.add(new ResourceCard("Sheep"));
					temp.add(new ResourceCard("Grain"));
					temp.add(new ResourceCard("Stone"));
					if (gs.getCPlayer().hasThese(temp)) {
						gs.setSubState("default");
						gs.getCPlayer().removeResources(temp);
						deck.draw(gs.getCPlayer());
						lines.add(gs.getCPlayer() + " made a Development Card.");
						repaint();
					} else {
						gs.setSubState("faileddevcard");
						repaint();
					}
				} else if (x >= 280 && x <= 363 && y >= 15 && y <= 40 && gs.cPlayerIndex() == 0) {
					if (gs.getSubState().equals("showcards"))
						gs.setSubState("default");
					else
						gs.setSubState("showcards");
					repaint();
				} else if (x >= 1160 && x <= 1283 && y >= 15 && y <= 40 && gs.cPlayerIndex() == 1) {
					if (gs.getSubState().equals("showcards"))
						gs.setSubState("default");
					else
						gs.setSubState("showcards");
					repaint();
				} else if (x >= 435 && x <= 518 && y >= 720 && y <= 745 && gs.cPlayerIndex() == 2) {
					if (gs.getSubState().equals("showcards"))
						gs.setSubState("default");
					else
						gs.setSubState("showcards");
					repaint();
				} else if (x >= 1015 && x <= 1098 && y >= 720 && y <= 745 && gs.cPlayerIndex() == 3) {
					if (gs.getSubState().equals("showcards"))
						gs.setSubState("default");
					else
						gs.setSubState("showcards");
					repaint();
				}

				if (gs.getSubState().equals("showcards")) {
					if (x >= 390 && y >= 40 && x <= 390 + 45 && y <= 40 + 90 && gs.cPlayerIndex() == 0
							&& gs.getCPlayer().hasDevCards()) {
						gs.setSubState("showdevcards");
						repaint();
					} else if (x >= 1265 && y >= 40 && x <= 1265 + 45 && y <= 40 + 90 && gs.cPlayerIndex() == 1
							&& gs.getCPlayer().hasDevCards()) {
						gs.setSubState("showdevcards");
						repaint();
					} else if (x >= 540 && y >= 625 && x <= 540 + 45 && y <= 625 + 90 && gs.cPlayerIndex() == 2
							&& gs.getCPlayer().hasDevCards()) {
						gs.setSubState("showdevcards");
						repaint();
					} else if (x >= 1130 && y >= 625 && x <= 1130 + 45 && y <= 625 + 90 && gs.cPlayerIndex() == 3
							&& gs.getCPlayer().hasDevCards()) {
						gs.setSubState("showdevcards");
						repaint();
					}
				}
			}
			// rolling dice to figure out turn
			else if (gs.getSubState().equals("setorder")) {
				if (x >= 620 && y >= 580 && x <= 620 + 270 && y <= 680) {
					gs.rollForOrder(gs.getNumPlayers());
					repaint();
				}
			}
			// entering game (finally)
			else if (gs.getSubState().equals("entergame")) {
				if (x >= 605 && y >= 570 && x <= 605 + 289 && y <= 570 + 143) {
					gs.setSubState("default");
					lines.add("It's " + gs.getCPlayer() + "'s turn.");
					repaint();
				}
			}

			// selecting colors
			else if (gs.getSubState().equals("setcolors") || gs.getSubState().equals("redocolor")) {
				if (x >= 890 && y >= 565 && x <= 890 + 170 && y <= 565 + 70) {
					// add selected items to arraylist, then do some fun java stuff to remove the
					// duplicates :)
					// if there are duplicates, then list wont be right size
					// so thats how we know they selected a color more than once
					// i tried to do this with a treeset and i forgot that it also sorted the
					// elements so :|
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
						gs.setSubState("setorder");
						gs.setPlayerColors(temp);
						removeAll(); // this'll get rid of the jcomboboxes finally
					}
					repaint();
				}
			}

			else if (gs.getSubState().equals("trading") || gs.getSubState().equals("redoTradeReq")
					|| gs.getSubState().equals("redoTradeOff")) {
				if (x >= 300 && y >= 270 && x <= 1200 && y <= 270 + 72) {
					reqDropDowns++;
					ResourceCard[] arr = gs.getToTradeWith().getNoDuplicateInventory();
					JComboBox<ResourceCard> picker = new JComboBox<ResourceCard>(arr);
					picker.setBounds(545 + (140 * reqDropDowns), 290, 130, 40);
					picker.setVisible(false);
					add(picker);
					reqTrades.put(reqDropDowns, picker);

					// nums
					total = gs.getToTradeWith().numOfCards((ResourceCard) picker.getSelectedItem());
					for (int i = 0; i < picker.getItemCount(); i++) {
						int temp = gs.getToTradeWith().numOfCards((ResourceCard) picker.getItemAt(i));
						if (temp > total)
							total = temp;
					}
					Integer[] numArr = new Integer[total];
					for (int i = 1; i <= total; i++)
						numArr[i - 1] = i;
					JComboBox<Integer> nums = new JComboBox<Integer>(numArr);
					nums.setBounds(560 + (140 * (reqDropDowns)), 340, 80, 20);
					nums.setVisible(false);
					add(nums);
					reqTradeAmounts.put(reqDropDowns, nums);

					repaint();
				} else if (x >= 1050 && y >= 340 && x <= 1050 + 124 && y <= 340 + 72) {
					if (reqDropDowns > -1) {
						reqTrades.get(reqDropDowns).setVisible(false);
						reqTrades.put(reqDropDowns, null);
						reqTradeAmounts.get(reqDropDowns).setVisible(false);
						reqTrades.put(reqDropDowns, null);
						reqDropDowns--;
						repaint();
					}
				} else if (x >= 1050 && y >= 430 && x <= 1050 + 124 && y <= 430 + 72) {
					offDropDowns++;
					ResourceCard[] arr = gs.getCPlayer().getNoDuplicateInventory();
					JComboBox<ResourceCard> picker = new JComboBox<ResourceCard>(arr);
					picker.setBounds(490 + (140 * offDropDowns), 440, 130, 40);
					picker.setVisible(false);
					add(picker);
					offTrades.put(offDropDowns, picker);

					// nums
					total = gs.getToTradeWith().numOfCards((ResourceCard) picker.getSelectedItem());
					for (int i = 0; i < picker.getItemCount(); i++) {
						int temp = gs.getToTradeWith().numOfCards((ResourceCard) picker.getItemAt(i));
						if (temp > total)
							total = temp;
					}
					Integer[] numArr = new Integer[total];
					for (int i = 1; i <= total; i++)
						numArr[i - 1] = i;
					JComboBox<Integer> nums = new JComboBox<Integer>(numArr);
					nums.setBounds(505 + (140 * (offDropDowns)), 490, 80, 20);
					nums.setVisible(false);
					add(nums);
					offTradeAmounts.put(offDropDowns, nums);
					repaint();
				} else if (x >= 1050 && y >= 500 && x <= 1050 + 124 && y <= 500 + 72) {
					if (offDropDowns > -1) {
						offTrades.get(offDropDowns).setVisible(false);
						offTrades.put(offDropDowns, null);
						offTradeAmounts.get(offDropDowns).setVisible(false);
						offTradeAmounts.put(offDropDowns, null);
						offDropDowns--;
						repaint();
					}
				} else if (x >= 1075 && y >= 190 && x <= 1075 + 90 && y <= 190 + 80) {
					removeAll();
					gs.setSubState("default");
					offDropDowns = -1;
					reqDropDowns = -1;
					reqTrades = new HashMap<>();
					offTrades = new HashMap<>();
					reqTradeAmounts = new HashMap<>();
					offTradeAmounts = new HashMap<>();
					lines.add("...but it didn't work out.");
					repaint();
				}

				// doing the trade
				else if (x >= 645 && y >= 605 && x <= 645 + 200 && y <= 705) {
					request = new ArrayList<ResourceCard>();
					for (int i = 0; i < reqTrades.size(); i++) {
						for (int j = 0; j < (Integer) reqTradeAmounts.get(i).getSelectedItem(); j++) {
							if (reqTrades.get(i) != null)
								request.add((ResourceCard) reqTrades.get(i).getSelectedItem());
						}
					}
					if (gs.getToTradeWith().hasThese(request)) {
						offers = new ArrayList<ResourceCard>();
						for (int i = 0; i < offTrades.size(); i++) {
							if (offTradeAmounts.get(i) != null) {
								for (int j = 0; j < (Integer) offTradeAmounts.get(i).getSelectedItem(); j++) {
									if (offTrades.get(i) != null)
										offers.add((ResourceCard) offTrades.get(i).getSelectedItem());
								}
							}

						}
						if (gs.getCPlayer().hasThese(offers)) {
							gs.setSubState("tradeconfirm");
							removeAll();
							gs.setOffers(offers);
							gs.setRequests(request);
						}

						else
							gs.setSubState("redoTradeOff");
					} else
						gs.setSubState("redoTradeReq");

					repaint();
				}
			} else if (gs.getSubState().equals("tradeconfirm")) {
				if (x >= 420 && y >= 420 && x <= 720 && y <= 580) {
					leftAccept = true;
				} else if (x >= 800 && y >= 420 && x <= 1100 && y <= 580) {
					rightAccept = true;
				} else if ((x >= 460 && y >= 600 && x <= 460 + 220 && y <= 700)
						|| (x >= 840 && y >= 600 && x <= 840 + 220 && y <= 700)) {
					declined = true;
				}
				if (leftAccept && rightAccept && (!declined)) {
					gs.getPM().trade(gs.getToTradeWith(), request, offers);
					request.clear();
					offers.clear();
					reqTrades.clear();
					reqTradeAmounts.clear();
					offTrades.clear();
					offTradeAmounts.clear();
					gs.setToTradeWith(null);
					reqDropDowns = -1;
					offDropDowns = -1;
					leftAccept = false;
					rightAccept = false;
					lines.add("And the trade was successful!");
					gs.setSubState("default");
				}
				if (declined || (x >= 1120 && y >= 175 && x <= 1180 && y <= 175 + 60)) {
					request.clear();
					offers.clear();
					reqTrades.clear();
					reqTradeAmounts.clear();
					offTrades.clear();
					offTradeAmounts.clear();
					gs.setToTradeWith(null);
					gs.setSubState("default");
					reqDropDowns = -1;
					offDropDowns = -1;
					leftAccept = false;
					rightAccept = false;
					lines.add("...but it didn't work out.");
				}
				repaint();
			} else if (gs.getSubState().equals("banktrade") || gs.getSubState().equals("redobanktrade")) {
				if (x >= 1110 && y >= 170 && x <= 1110 + 65 & y <= 170 + 65) {
					gs.setSubState("default");
					lines.add("...but it didn't work out.");
					offTrades.clear();
					offDropDowns = -1;
					removeAll();
					repaint();
				} else if (x >= 600 && y >= 550 && x <= 900 && y <= 550 + 145) {
					ArrayList<ResourceCard> temp = new ArrayList<>();
					ResourceCard rc = (ResourceCard) offTrades.get(0).getSelectedItem();
					for (int i = 0; i < 4; i++)
						temp.add(rc);

					if (temp.get(3) != null && gs.getCPlayer().hasThese(temp)) {
						gs.getCPlayer().removeResources(temp);
						temp = new ArrayList<ResourceCard>();
						temp.add((ResourceCard) offTrades.get(1).getSelectedItem());
						gs.getCPlayer().addResources(temp);
						removeAll();
						gs.setSubState("default");
						lines.add(gs.getCPlayer() + " traded with the bank.");
					} else {
					/*	System.out.println(gs.getCPlayer().hasThese(temp));
						System.out.println(temp);
						System.out.println(gs.getCPlayer().getInventory());
						gs.setSubState("redobanktrade");*/
						System.out.println("ERROR");
					}
					repaint();

				}
			} else if (gs.getSubState().equals("showdevcards")) {
				if (x >= 1110 && y >= 170 && x <= 1110 + 65 & y <= 170 + 65) {
					gs.setSubState("default");
					repaint();
				}

				// playing devcards
				int px = 340;
				for (int i = 0; i < gs.getCPlayer().getDevCards().size(); i++) {
					if (x >= px && y >= 500 && x <= px + 158 && y <= 540
							&& gs.getCPlayer().getDevCards().get(i).canPlay()) {
						DevelopmentCard dev = gs.getCPlayer().getDevCards().get(i);
						if (dev.getName().equals("Knight")) {
							gs.setSubState("moverobber");
							lines.add(gs.getCPlayer() + " has played a Knight card!");

							gs.getCPlayer().removeVicCard(dev);
							repaint();
						} else if (dev.getName().equals("Monopoly")) {
							System.out.println("mono");
						} else if (dev.getName().equals("YearOfPlenty")) {
							System.out.println("year");
						} else if (dev.getName().equals("RoadBuilding")) {
							System.out.println("road");
						}
					}
					px += 200;
				}
			} else if (gs.getSubState().equals("forcingdiscard")) {
				int x1 = 330;
				int y1 = 200;
				for (int i = 0; i < gs.mustDiscard.get(0).getInventory().size(); i++) {
					if (x >= x1 && y >= y1 && x <= x1 + 94 && y <= y1 + 140) {
						gs.forcedDiscard.add(gs.mustDiscard.get(0).getInventory().get(i));
						gs.forcedDiscardCords.add(x1);
						gs.forcedDiscardCords.add(y1);
					}
					x1 += 100;
					if (x1 == 1130) {
						x1 = 330;
						y1 += 150;
					}
				}
				repaint();
			} else if (gs.getSubState().equals("moverobber")) {
				Board b = gs.getBoard();
				int l = lines.size();
					for (Tile[] arr : b.getTiles()) {
						for (Tile temp : arr) {
							if (temp != null && !temp.isRobber()) {
								if (x >= temp.getXCord() + 40 && y >= temp.getYCord() + 35 && x <= temp.getXCord() + 70
										&& y <= temp.getYCord() + 65) {
									b.robberLocation().removeRobber();
									temp.addRobber();
									lines.add("Robber has been moved to " + temp.getType() + ".");
									gs.setSubState("default");
								}
							} 
						}
					}
				repaint();
			}

			// stuff for building
			if (gs.cPlayerIndex() == 0) {
				if (x >= 380 && x <= 456 && y >= 15 && y <= 40) {
					if (!(gs.getSubState().equals("buildmenu"))) {
						gs.setSubState("buildmenu");
						lines.add(gs.getCPlayer() + " started building.");
					} else {
						gs.setSubState("default");
						System.out.println("hi");
					}
					repaint();
				}
				if (x >= 1155 && x <= 1190 && y >= 765 && y <= 810) {
					if (!(gs.getSubState().equals("buildCity"))) {
						gs.setSubState("buildCity");
						lines.add(gs.getCPlayer() + " selected to build City.");
					} else {
						gs.setSubState("buildmenu");
						System.out.println("hi");
					}
					repaint();
				}
				if (x >= 1200 && x <= 1220 && y >= 775 && y <= 805) {
					if (!(gs.getSubState().equals("buildSettlement"))) {
						gs.setSubState("buildSettlement");
						lines.add(gs.getCPlayer() + " selected to build Settlement.");
					} else {
						gs.setSubState("buildmenu");
						System.out.println("hi");
					}
					repaint();
				}
				if (x >= 1225 && x <= 1345 && y >= 780 && y <= 796) {
					if (!(gs.getSubState().equals("buildRoad"))) {
						gs.setSubState("buildRoad");
						lines.add(gs.getCPlayer() + " selected to build Road.");
					} else {
						gs.setSubState("buildmenu");
						System.out.println("hi");
					}
					repaint();
				}
			}

			if (gs.cPlayerIndex() == 1) {
				if (x >= 1260 && x <= 1336 && y >= 15 && y <= 40) {
					if (!(gs.getSubState().equals("buildmenu"))) {
						gs.setSubState("buildmenu");
						lines.add(gs.getCPlayer() + " started building.");

					} else {
						gs.setSubState("default");
						System.out.println("hi");
					}
					repaint();
				}
				if (x >= 1155 && x <= 1190 && y >= 765 && y <= 810) {
					if (!(gs.getSubState().equals("buildCity"))) {
						gs.setSubState("buildCity");
						lines.add(gs.getCPlayer() + " selected to build City.");
					} else {
						gs.setSubState("buildmenu");
						System.out.println("hi");
					}
					repaint();
				}
				if (x >= 1200 && x <= 1220 && y >= 775 && y <= 805) {
					if (!(gs.getSubState().equals("buildSettlement"))) {
						gs.setSubState("buildSettlement");
						lines.add(gs.getCPlayer() + " selected to build Settlement.");
					} else {
						gs.setSubState("buildmenu");
						System.out.println("hi");
					}
					repaint();
				}
				if (x >= 1225 && x <= 1345 && y >= 780 && y <= 796) {
					if (!(gs.getSubState().equals("buildRoad"))) {
						gs.setSubState("buildRoad");
						lines.add(gs.getCPlayer() + " selected to build Road.");
					} else {
						gs.setSubState("buildmenu");
						System.out.println("hi");
					}
					repaint();
				}
			}
			if (gs.cPlayerIndex() == 2) {
				if (x >= 535 && x <= 611 && y >= 720 && y <= 745) {
					if (!(gs.getSubState().equals("buildmenu"))) {
						gs.setSubState("buildmenu");
						lines.add(gs.getCPlayer() + " started building.");

					} else {
						gs.setSubState("default");
						System.out.println("hi");
					}
					repaint();
				}
				if (x >= 1155 && x <= 1190 && y >= 765 && y <= 810) {
					if (!(gs.getSubState().equals("buildCity"))) {
						gs.setSubState("buildCity");
						lines.add(gs.getCPlayer() + " selected to build City.");
					} else {
						gs.setSubState("buildmenu");
						System.out.println("hi");
					}
					repaint();
				}
				if (x >= 1200 && x <= 1220 && y >= 775 && y <= 805) {
					if (!(gs.getSubState().equals("buildSettlement"))) {
						gs.setSubState("buildSettlement");
						lines.add(gs.getCPlayer() + " selected to build Settlement.");
					} else {
						gs.setSubState("buildmenu");
						System.out.println("hi");
					}
					repaint();
				}
				if (x >= 1225 && x <= 1345 && y >= 780 && y <= 796) {
					if (!(gs.getSubState().equals("buildRoad"))) {
						gs.setSubState("buildRoad");
						lines.add(gs.getCPlayer() + " selected to build Road.");
					} else {
						gs.setSubState("buildmenu");
						System.out.println("hi");
					}
					repaint();
				}
			}

			if (gs.cPlayerIndex() == 3) {
				if (x >= 1115 && x <= 1191 && y >= 720 && y <= 745) {
					if (!(gs.getSubState().equals("buildmenu"))) {
						gs.setSubState("buildmenu");
						lines.add(gs.getCPlayer() + " started building.");

					} else {
						gs.setSubState("default");
						System.out.println("hi");
					}
					repaint();
				}
				if (x >= 1155 && x <= 1190 && y >= 765 && y <= 810) {
					if (!(gs.getSubState().equals("buildCity"))) {
						gs.setSubState("buildCity");
						lines.add(gs.getCPlayer() + " selected to build City.");
					} else {
						gs.setSubState("buildmenu");
						System.out.println("hi");
					}
					repaint();
				}
				if (x >= 1200 && x <= 1220 && y >= 775 && y <= 805) {
					if (!(gs.getSubState().equals("buildSettlement"))) {
						gs.setSubState("buildSettlement");
						lines.add(gs.getCPlayer() + " selected to build Settlement.");
					} else {
						gs.setSubState("buildmenu");
						System.out.println("hi");
					}
					repaint();
				}
				if (x >= 1225 && x <= 1345 && y >= 780 && y <= 796) {
					if (!(gs.getSubState().equals("buildRoad"))) {
						gs.setSubState("buildRoad");
						lines.add(gs.getCPlayer() + " selected to build Road.");
					} else {
						gs.setSubState("buildmenu");
						System.out.println("hi");
					}
					repaint();
				}
			}
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
