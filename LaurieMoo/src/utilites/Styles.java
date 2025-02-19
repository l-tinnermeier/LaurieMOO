package utilites;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/*
 * A utility class to allow for stylistic actions in Java Swing
 * @author Luke Tinnermeier
 * @date 17/2/2025
 * @version 1.0
 * @file Styles.java
 */
public class Styles {
	
	/*
	 * Fades out a specified JPanel on a given JFrame
	 * @param panel: A JPanel variable, used as the object to fade out
	 * @param frame: A JFrame variable, used as the object's parent
	 */
    public static void fadeOutJPanel(JPanel panel, JFrame frame) {
        Timer timer = new Timer(100, new ActionListener() {
            int alpha = 255;

            @Override
            public void actionPerformed(ActionEvent e) {
                alpha -= 5;
                panel.setBackground(new Color(0, 0, 0, alpha));
                System.out.println(alpha);
                frame.revalidate();
                frame.repaint();
                if (alpha == 0) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }
    
    /*
	 * Fades out a specified JPanel on a given JFrame over a specified time by a specified amount
	 * @param panel: A JPanel variable, used as the object to fade out
	 * @param frame: A JFrame variable, used as the object's parent
	 * @param amount: An integer variable, used to calculate the color differences between each loop of the timer
	 * @param delay: An integer variable, used to set the speed of which the panel is faded
	 */
    public static void fadeOutJPanel(JPanel panel, JFrame frame, int amount, int delay) {
        Timer timer = new Timer(delay, new ActionListener() {
            int alpha = 255;

            @Override
            public void actionPerformed(ActionEvent e) {
                alpha -= amount;
                panel.setBackground(new Color(0, 0, 0, alpha));
                System.out.println(alpha);
                frame.revalidate();
                frame.repaint();
                if (alpha == 0) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }
    
    /*
	 * Fades in a specified JPanel on a given JFrame over a specified time by a specified amount
	 * @param panel: A JPanel variable, used as the object to fade in
	 * @param frame: A JFrame variable, used as the object's parent
	 */
    public static void fadeInJPanel(JPanel panel, JFrame frame) {
        Timer timer = new Timer(100, new ActionListener() {
            int alpha = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                alpha += 5;
                panel.setBackground(new Color(0, 0, 0, alpha));
                System.out.println(alpha);
                frame.revalidate();
                frame.repaint();
                if (alpha >= 255) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }
    
    /*
	 * Fades in a specified JPanel on a given JFrame over a specified time by a specified amount
	 * @param panel: A JPanel variable, used as the object to fade in
	 * @param frame: A JFrame variable, used as the object's parent
	 * @param amount: An integer variable, used to calculate the color differences between each loop of the timer
	 * @param delay: An integer variable, used to set the speed of which the panel is faded in
	 */
    public static void fadeInJPanel(JPanel panel, JFrame frame, int amount, int delay) {
        Timer timer = new Timer(delay, new ActionListener() {
            int alpha = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                alpha += amount;
                panel.setBackground(new Color(0, 0, 0, alpha));
                System.out.println(alpha);
                frame.revalidate();
                frame.repaint();
                
                if (alpha >= 255) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }
    
    
}
