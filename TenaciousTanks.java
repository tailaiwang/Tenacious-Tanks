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
import java.awt.image.*; //awt and swing were painful
import javafx.scene.shape.Ellipse; //used for map design and stuff
import javax.sound.sampled.*; //did we even use sound...? oh well who cares

//THIS is the main file, with the public static void main
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
    }//end constructor
    
    public void actionPerformed(ActionEvent e) {
    	if(game != null){
    		game.refresh();
    		game.repaint();
    	}//end if
    }//end actionPerformed
    
    public void keyTyped(KeyEvent e) {}//end keyTyped
    
    public void keyPressed(KeyEvent e) {
    	game.setKey(e.getKeyCode(), true);
    }//end keyPressed
    
    public void keyReleased(KeyEvent e) {
    	game.setKey(e.getKeyCode(), false);
    }//end keyReleased 
    
    public static void main(String[]args) { //what runs the entire program
    	new TenaciousTanks();
    }//end main
}//end TenaciousTanks