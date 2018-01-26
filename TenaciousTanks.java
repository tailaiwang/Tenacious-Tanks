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

public class TenaciousTanks extends JFrame implements ActionListener,KeyListener{
	Timer myTimer;
	GamePanel game;

    public TenaciousTanks() {
    	super("Tenacious Tanks");
    	setSize(1200,700);
    	myTimer = new Timer (10,this);
    	myTimer.start();
    	game = new GamePanel();
    	add(game);
    	addKeyListener(this);
    	setResizable(false);
    	setVisible(true);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    }//end TenaciousTanks()
    
    public void actionPerformed(ActionEvent e){
    	if(game != null){
    		game.refresh();
    		game.repaint();
    	}//end if
    }//end actionPerformed
    
    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
    	game.setKey(e.getKeyCode(),true);
    }

    public void keyReleased(KeyEvent e) {
    	game.setKey(e.getKeyCode(),false);
    }
    
    public static void main(String[]args){
    	new TenaciousTanks();
    }//end main
}//end CLASS

class GamePanel extends JPanel{
	Image background,green,green1,red,red1;
	int greenX,greenIndex,redX,redIndex = 0;
	private boolean[] keys;
	private Image[]greens,reds;
	public GamePanel(){
		keys = new boolean [KeyEvent.KEY_LAST+1];
		/////////////////////////////////////////////////
    	background = new ImageIcon("background.jpg").getImage();
    	background = background.getScaledInstance(1200,700,Image.SCALE_SMOOTH);
    	/////////////////////////////////////////////////
    	green = new ImageIcon("GreenTank.png").getImage();
    	green = green.getScaledInstance(175,175,Image.SCALE_SMOOTH);
    	green1 = new ImageIcon("GreenTank1.png").getImage();
    	green1 = green1.getScaledInstance(175,174,Image.SCALE_SMOOTH);
    	greens = new Image[2];
    	greens[0] = green;
    	greens[1] = green1;
    	////////////////////////////////////////////////
    	red = new ImageIcon("RedTank.png").getImage();
    	red = red.getScaledInstance(175,175,Image.SCALE_SMOOTH);
    	red1 = new ImageIcon("RedTank1.png").getImage();
    	red1 = red1.getScaledInstance(175,175,Image.SCALE_SMOOTH);
    	reds = new Image[2];
    	reds[0] = red;
    	reds[1] = red1;
	}
	public void setKey(int k, boolean v) {
    	keys[k] = v;
    }
    
    public void refresh(){
    	if(keys[KeyEvent.VK_RIGHT] ){
			greenX += 5;
			greenIndex = 0;
		}
		if(keys[KeyEvent.VK_LEFT] ){
			greenX -= 5;
			greenIndex = 1;
		}
		if(keys[KeyEvent.VK_D]){
			redX += 5;
			redIndex = 0;
		}
		if(keys[KeyEvent.VK_A]){
			redX -= 5;
			redIndex = 1;
		}
    }
    
	public void paintComponent(Graphics g){
		g.drawImage(background,0,0,this);
		g.drawImage(greens[greenIndex],greenX,200,this);
		g.drawImage(reds[redIndex],redX,400,this);
	}
}//end CLASS