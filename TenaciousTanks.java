/**
 * @(#)TenaciousTanks.java
 * @Cameron Beneteau and Tailai Wang
 * ICS 4U
 * February 19th, 2018
 * @version 1.00 2018/1/17
 */
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import javafx.scene.shape.Ellipse;
import javax.sound.sampled.*;

public class TenaciousTanks extends JFrame implements ActionListener, KeyListener {
	Timer myTimer;
	GamePanel game;
	int screenX = 1300;
	int screenY = 700;

    public TenaciousTanks() {
    	super("Tenacious Tanks");
    	setSize(screenX, screenY);
    	myTimer = new Timer (10, this);
    	myTimer.start();
    	game = new GamePanel(); //creating game class
    	add(game);
    	addKeyListener(this);
    	setResizable(false);
    	setVisible(true);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e) {
    	if(game != null){
    		game.refresh();
    		game.repaint();
    	}
    }
    
    public void keyTyped(KeyEvent e) {}
    
    public void keyPressed(KeyEvent e) {
    	game.setKey(e.getKeyCode(), true);
    }
    
    public void keyReleased(KeyEvent e) {
    	game.setKey(e.getKeyCode(), false);
    }
    
    public static void main(String[]args) {
    	new TenaciousTanks();
    }   
}