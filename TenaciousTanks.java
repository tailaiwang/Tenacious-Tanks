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

public class TenaciousTanks extends JFrame implements ActionListener{
	Timer myTimer;
	GamePanel game;

    public TenaciousTanks() {
    	super("Tenacious Tanks");
    	setSize(1200,700);
    	myTimer = new Timer (10,this);
    	myTimer.start();
    	game = new GamePanel();
    	add(game);
    	setResizable(false);
    	setVisible(true);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    }//end TenaciousTanks()
    
    public void actionPerformed(ActionEvent e){
    	if(game != null){
    		//game.refresh();
    		game.repaint();
    	}//end if
    }//end actionPerformed
    
    public static void main(String[]args){
    	new TenaciousTanks();
    }//end main
}//end CLASS

class GamePanel extends JPanel implements MouseListener{
	private int mousex,mousey;
	Image background;
	public GamePanel(){
		/////////////////////////////////////////////////
    	background = new ImageIcon("background.jpg").getImage();
    	background = background.getScaledInstance(1200,700,Image.SCALE_SMOOTH);
	}
	
	public void mousePressed(MouseEvent e){
		mousex = e.getX();
		mousey = e.getY();
	}
	
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	
	public void paintComponent(Graphics g){
		g.drawImage(background,0,0,this);
	}
}//end CLASS