/**
 * @(#)TenaciousTanks.java
 *
 *
 * @author 
 * @version 1.00 2018/1/17
 */
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;

public class TenaciousTanks extends JFrame implements ActionListener, KeyListener{
	Timer myTimer;
	GamePanel game;
	int screenX = 1300;
	int screenY = 700;

    public TenaciousTanks() {
    	super("Tenacious Tanks");
    	setSize(screenX, screenY);
    	myTimer = new Timer (10,this);
    	myTimer.start();
    	game = new GamePanel();
    	add(game);
    	addKeyListener(this);
    	setResizable(false);
    	setVisible(true);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e){
    	if(game != null){
    		game.refresh();
    		game.repaint();
    	}
    }
    
    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
    	game.setKey(e.getKeyCode(),true);
    }

    public void keyReleased(KeyEvent e) {
    	game.setKey(e.getKeyCode(),false);
    }
    
    public static void main(String[]args){
    	new TenaciousTanks();
    }
}