import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import static java.lang.System.*;
public class RulesPanel extends JPanel implements MouseListener
{
    private Image brick, grain, stone, wood, sheep, costs, dice, build, DevCards, passDice, harTrade, bankTrade, tradeMenu, tradeCon, buildButton, buildEx;
    private int stage;

    public RulesPanel()
    {
        stage = 0;

        try
        {
            //ImageIO.read(RulesPanel.class.getResource("/Buttons/
            brick = ImageIO.read(RulesPanel.class.getResource("/CardImages/Brick.jpg"));
            grain = ImageIO.read(RulesPanel.class.getResource("/CardImages/Grain.jpg"));
            sheep = ImageIO.read(RulesPanel.class.getResource("/CardImages/Sheep.jpg"));
            stone = ImageIO.read(RulesPanel.class.getResource("/CardImages/Stone.jpg"));
            wood = ImageIO.read(RulesPanel.class.getResource("/CardImages/Wood.jpg"));
            costs = ImageIO.read(RulesPanel.class.getResource("/Images/building_costs.png"));
            dice = ImageIO.read(RulesPanel.class.getResource("/Images/dice roll.PNG"));
            build = ImageIO.read(RulesPanel.class.getResource("/Images/building select.PNG"));
            DevCards = ImageIO.read(RulesPanel.class.getResource("/Images/Catan Development Card Images.jpg"));
            passDice = ImageIO.read(RulesPanel.class.getResource("/Buttons/pass_dice_button.png"));
            harTrade = ImageIO.read(RulesPanel.class.getResource("/Images/harbor trade system.PNG"));
            bankTrade =  ImageIO.read(RulesPanel.class.getResource("/Images/bank trade system.PNG"));
            tradeMenu = ImageIO.read(RulesPanel.class.getResource("/Images/trade menu.PNG"));
            tradeCon = ImageIO.read(RulesPanel.class.getResource("/Images/trade confirm.PNG"));
            buildButton= ImageIO.read(RulesPanel.class.getResource("/Buttons/build_button_blue.png"));
            buildEx = ImageIO.read(RulesPanel.class.getResource("/Images/building example (1).PNG"));
        }
        catch(Exception e)
        {
            out.println("ERROR");
        }

        addMouseListener(this);
    }

    public void addNotify()
    {
        super.addNotify();
        requestFocus();
    }

    public void paint(Graphics g)
    {

        if(stage ==0)
        {
            g.clearRect(0, 0, getWidth(),getHeight());

            g.clearRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.GREEN);
            g.fillRect(5*(getWidth()/6)-(getWidth()/60), 28*(getHeight()/29)-(getHeight()/24), getWidth()/15, 2*(getHeight()/29));
            g.setColor(Color.BLACK);
            g.setFont(new Font("Times New Roman", Font.PLAIN, getWidth()/75));
            g.drawString("NEXT",5*(getWidth()/6)-(getWidth()/60)+(getWidth()/60), 28*(getHeight()/29));
            g.setFont(new Font("Times New Roman", Font.PLAIN, getWidth()/55));


            g.drawString("Welcome to the our Settlers of Catan simulation.", 4*(getWidth()/15) , 2*(getHeight()/29));
            g.drawString("Your objective is to accrue victory points by building roads, settlements, and cities.", 4*(getWidth()/15), 3*(getHeight()/29));
            g.drawString("In addition, you can also advance in the game by trading for resources.",4*(getWidth()/15), 4*(getHeight()/29));
//


            g.drawString("There are five types of resources: brick, grain, sheep, stone, and wood", getWidth()/30 , 7*(getHeight()/29));
            g.drawString("Each tile is consigned to a resource.", getWidth()/30,8*(getHeight()/29));
            g.drawString("Each tile is also assigned a random number 1-12, excluding 7.",getWidth()/30,9*(getHeight()/29));
            g.drawString("When the die are rolled, the tile numbers corresponding to the die roll yield resources.", getWidth()/30,10*(getHeight()/29));
            g.drawString("If a player has a settlement or city adjacent to the aforementioned tiles, they will add the tiles' resources to their inventory.",getWidth()/30,11*(getHeight()/29));
            g.drawImage(brick,8*getWidth()/15, 5*(getHeight()/29)-10, 3*(getWidth()/60),4*(getHeight()/27), null );
            g.drawImage(grain,9*getWidth()/15, 5*(getHeight()/29)-10, 3*(getWidth()/60),4*(getHeight()/27), null );
            g.drawImage(stone,10*getWidth()/15, 5*(getHeight()/29)-10, 3*(getWidth()/60),4*(getHeight()/27), null );
            g.drawImage(sheep,11*getWidth()/15, 5*(getHeight()/29)-10, 3*(getWidth()/60),4*(getHeight()/27), null );
            g.drawImage(wood,12*getWidth()/15, 5*(getHeight()/29)-10, 3*(getWidth()/60),4*(getHeight()/27), null );

            g.drawString("Victory points can be earned by building cities and settlements.", 21*(getWidth()/60) ,14*(getHeight()/29));
            g.drawString("They can also be won by buying development cards.", 21*(getWidth()/60), 15*(getHeight()/29));
            g.drawString("The first player to get ten victory points wins..",21*(getWidth()/60), 16*(getHeight()/29));
            g.drawImage(costs,getWidth()/10, 12*(getHeight()/29), 9*(getWidth()/60),9*(getHeight()/29), null );
//

            g.drawString("The player order will be determined by die roll.", getWidth()/30 , 22*(getHeight()/29));
            g.drawString("Each player will be allowed to begin the game with two settlements.", getWidth()/30,23*(getHeight()/29));
            g.drawImage(dice,8*getWidth()/15, 17*(getHeight()/29), 10*(getWidth()/60),10*(getHeight()/29), null );
        }

        if (stage==1)
        {
            g.clearRect(0, 0, getWidth(),getHeight());


            g.setColor(Color.GREEN);
            g.fillRect(5*(getWidth()/6)-(getWidth()/60), 28*(getHeight()/29)-(getHeight()/24), getWidth()/15, 2*(getHeight()/29));
            g.setColor(Color.BLACK);
            g.setFont(new Font("Times New Roman", Font.PLAIN, getWidth()/75));
            g.drawString("NEXT",5*(getWidth()/6)-(getWidth()/60)+(getWidth()/60), 28*(getHeight()/29));


            g.setColor(Color.GREEN);
            g.fillRect(getWidth()/30, 28*(getHeight()/29)-(getHeight()/24), getWidth()/15, 2*(getHeight()/29));
            g.setColor(Color.BLACK);

            g.drawString("PREVIOUS", getWidth()/30, 28*(getHeight()/29));
            g.setFont(new Font("Times New Roman", Font.PLAIN, getWidth()/55));


            g.drawString("Each turn has four parts in order.  It will be ended by the \"Pass Dice\" button: ", 7*(getWidth()/32) , getHeight()/29);
            g.drawString("The player will roll the dice.  If the result is not equal to 7, the following proceeds.", 7*(getWidth()/32) , 2*(getHeight()/29));
            g.drawString("The player will receive and trade resources.", 7*(getWidth()/32), 3*(getHeight()/29));
            g.drawString("The player will build structures.",7*(getWidth()/32), 4*(getHeight()/29));
            g.drawString("The player can play a development card at any time.",7*(getWidth()/32), 5*(getHeight()/29));
            g.drawImage(passDice, 2*(getWidth()/32), getHeight()/29, 8*getWidth()/60, 4*getHeight()/29, null);
//


            g.drawString("If the die roll a 7:", 2*(getWidth()/28),8*(getHeight()/29));
            g.drawString("The Robber pawn will be moved to a tile of the player's choice.", 2*(getWidth()/28),9*(getHeight()/29));
            g.drawString("The tile will then not produce resources until it is moved.",2*(getWidth()/28),10*(getHeight()/29));
            g.drawString("All other players with a hand greater than 7 will discard half of their cards, rounding down.", 2*(getWidth()/28) , 11*(getHeight()/29));
//Get a Robber Pawn Mockup

            g.drawString("There are three structures: roads, settlements, and cities.", 5*(getWidth()/20), 15*(getHeight()/29));
            g.drawString("A road is worth 0 points, a settlement 1 point, and a city 2 points.",5*(getWidth()/20), 16*(getHeight()/29));
            g.drawString("A player can only build a city by upgrading a previous settlement.",5*(getWidth()/20), 17*(getHeight()/29));
            g.drawImage(build,10, 13*(getHeight()/29), 14*(getWidth()/60),5*(getHeight()/29), null );
//

            g.drawString("A Knight development card allows the player to move the robber.", getWidth()/15 , 20*(getHeight()/29));
            g.drawString("The first player to play 3 Knight cards gets the \"Largest Army\" card worth 2 points.", getWidth()/15,21*(getHeight()/29));
            g.drawString("Progress cards have instructions to be followed by the player.", getWidth()/15,22*(getHeight()/29));
            g.drawString("Victory Point cards must be hidden until the player is sure they have received the 10 points needed to win the game.",getWidth()/15,23*(getHeight()/29));
            g.drawString("A player may only play one development card per turn by selecting it.",getWidth()/15,24*(getHeight()/29));
            g.drawImage(DevCards,15*getWidth()/20, 10*(getHeight()/29), 12*(getWidth()/60),12*(getHeight()/29), null );

        }
        if (stage==2)
        {
            g.clearRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.GREEN);
            g.fillRect(getWidth()/30, 28*(getHeight()/29)-(getHeight()/24), getWidth()/15, 2*(getHeight()/29));
            g.setColor(Color.BLACK);
            g.setFont(new Font("Times New Roman", Font.PLAIN, getWidth()/75));
            g.drawString("PREVIOUS", getWidth()/30, 28*(getHeight()/29));
            g.setFont(new Font("Times New Roman", Font.PLAIN, getWidth()/55));

            g.drawString("The player can trade with anyone during their turn.", 12*(getWidth()/30) , getHeight()/29);
            g.drawString("They may send a trade request.  The receiver can choose to accept or deny the trade.", 12*(getWidth()/30) , 2*(getHeight()/29));
            g.drawString("Due to the nature of this simulation, each player's hand will be visible.", 12*(getWidth()/30), 3*(getHeight()/29));
            g.drawString("Trade will be ended by the \"Build\" or \"Pass Dice\" button.",12*(getWidth()/30), 4*(getHeight()/29));
            g.drawImage(tradeMenu, (getWidth()/40), getHeight()/70, 10*getWidth()/60, 6*getHeight()/29, null);
            g.drawImage(tradeCon, 9*(getWidth()/40), getHeight()/70, 10*getWidth()/60, 6*getHeight()/29, null);
            
            g.drawString("If a player has a structure adjacent to a harbor, they can use it to trade.", getWidth()/40 , 9*(getHeight()/29));
            g.drawString("Each harbor demands a particular resource for another.", getWidth()/40,10*(getHeight()/29));
            g.drawString("For the harbors denoted ?, the player may exchange that number of any resource.", getWidth()/40,11*(getHeight()/29));
            g.drawString("Additionally, a player can exchange 4 of one resource card for one of another at the bank.",getWidth()/40,12*(getHeight()/29));
            g.drawImage(harTrade,20*getWidth()/30, 7*(getHeight()/29)-10, 10*(getWidth()/60),6*(getHeight()/27), null );
            g.drawImage(bankTrade,25*getWidth()/30, 7*(getHeight()/29)-10, 10*(getWidth()/60),6*(getHeight()/27), null );
            //Get a harbor mockup


            g.drawString("A player can build a structure by clicking the \"Build\" button.", 4*(getWidth()/15) ,15*getHeight()/29);
            g.drawString("They must select a highlighted area to place their structure.", 4*(getWidth()/15) ,16*getHeight()/29);
            g.drawString("If a player wishes to upgrade a settlement, they must click the star and then click an existing city.", 4*(getWidth()/15), 17*getHeight()/29);
            g.drawString("If a player does not have the necessary resources to build a structure, they will be barred from building.",4*(getWidth()/15), 18*getHeight()/29);
            g.drawString("All cities and settlements, barring the first two, must be connected by roads.",4*(getWidth()/15), 19*getHeight()/29);
            g.drawImage(buildButton,getWidth()/15, 26*(getHeight()/60), 6*getWidth()/60, 3*getHeight()/29, null);
            g.drawImage(buildEx,getWidth()/30, 32*(getHeight()/60), 12*getWidth()/60, 6*getHeight()/29, null);
//
            g.setFont(new Font("Castellar", Font.BOLD, getWidth()/15));
            g.setColor(Color.RED);
            g.drawString("GOOD LUCK!",3*getWidth()/13,25*getHeight()/29);
        }



    }




    @Override
    public void mouseClicked(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        if(x>=5*(getWidth()/6)-(getWidth()/60)&& x<=5*(getWidth()/6)-(getWidth()/60)+getWidth()/15
                && y>=28*(getHeight()/29)-(getHeight()/24) && y<=28*(getHeight()/29)-(getHeight()/24)+ 2*(getHeight()/29)&& stage<2)
        {

            stage++;

        }

        if(x>=getWidth()/30&& x<=(getWidth()/30)+(getWidth()/15) &&
                y>=28*(getHeight()/29)-(getHeight()/24) && y<=28*(getHeight()/29)-(getHeight()/24)+2*(getHeight()/29)&& stage>0)
        {

            stage--;

        }
        repaint();
    }

    @Override public void mousePressed(MouseEvent e) {}

    @Override public void mouseReleased(MouseEvent e) {}

    @Override public void mouseEntered(MouseEvent e) {}

    @Override public void mouseExited(MouseEvent e) {}

}
