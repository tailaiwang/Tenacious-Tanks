/**
 * @(#)MakeMap.java
 *
 *
 * @author 
 * @version 1.00 2018/1/25
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;

public class MakeMap extends JFrame {
	
	GamePanel game = new GamePanel();
	
    public MakeMap() {
    	super("Make Map");
    	setSize(1500, 700);
    	setResizable(false);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	add(game);
    	setVisible(true);
    }
    
    public static void main(String[]args) {
    	new MakeMap();
    }
    
}

class GamePanel extends JPanel implements ActionListener{
	
	ImageIcon gameBack = new ImageIcon("Images/gameBack.jpg");
	
	int xPoints[] = new int[522];
	int yPoints[] = new int[522];
	int y2Points[] = new int[522];
	int y3Points[] = new int[522];
	int y4Points[] = new int[522];
	int y5Points[] = new int[522];
	int nPoints = 522;

	public GamePanel() {
		new Timer(1000,this).start();
		makeLand();
	}
		
     public void actionPerformed(ActionEvent evt) {
    	Object source = evt.getSource();
    	makeLand();
    	repaint();
    }
	public void makeLand(){
		while(true){
			boolean valid = true;
			int y = 500;
			int dy = 0;
			for (int x = 0; x < 520; x++){
				if(x % 20 == 0){
					dy = randInt(-15, 15);
				}
				xPoints[x] = x * 15;
				y += randInt(-10 + dy, 10 + dy);
				yPoints[x] = y;
				y2Points[x] = y + 15;
				y3Points[x] = y + 45;
				y4Points[x] = y + 85;
				y5Points[x] = y + 120;
				if (y < 150 || y > 650){
					valid = false;
				}
			}
			if (valid){
				break;
			}
		}
		xPoints[520] = 1500;
		yPoints[520] = 700;
		y2Points[520] = 700;
		y3Points[520] = 700;
		y4Points[520] = 700;
		y5Points[520] = 700;
		xPoints[521] = 0;
		yPoints[521] = 700;
		y2Points[521] = 700;
		y3Points[521] = 700;
		y4Points[521] = 700;
		y5Points[521] = 700;
	}
	
	public int randInt(int low, int high){
		int r = high - low + 1;
		return (int)(Math.random() * r) + low;
		
	}
	
	public void paintComponent(Graphics g) {
		//////////////////////////////////////////////////////
		gameBack.paintIcon(this, g, -200,-400);
		g.setColor(new Color(44,122,255));
		//g.fillRect(0,0,getWidth(),getHeight()); //for blue background
		g.setColor(new Color(62, 216, 47));
		g.fillPolygon(xPoints, yPoints, nPoints);
		g.setColor(new Color(50, 181, 38));
		g.fillPolygon(xPoints, y2Points, nPoints);
		g.setColor(new Color(23, 122, 14));
		g.fillPolygon(xPoints, y3Points, nPoints);
		g.setColor(new Color(9, 86, 12));
		g.fillPolygon(xPoints, y4Points, nPoints);
		g.setColor(new Color(8, 66, 4));
		g.fillPolygon(xPoints, y5Points, nPoints);
		//////////////////////////////////////////////////////
		
	}
}