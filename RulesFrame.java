import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
public class RulesFrame extends JFrame{
    private static final int WIDTH=1600;
    private static final int HEIGHT=960;

    public  RulesFrame(String frameName) {
        super(frameName);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new RulesPanel());
        setVisible(true);
    }

}