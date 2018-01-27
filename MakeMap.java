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
	Polygon groundPoly = new Polygon();
	Polygon groundPoly2 = new Polygon();
	Polygon groundPoly3 = new Polygon();
	Polygon groundPoly4 = new Polygon();
	Polygon groundPoly5 = new Polygon();
	
	int xPoints[] = new int[522];
	int yPoints[] = new int[522];

	public GamePanel() {
		//new Timer(1000,this).start();
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
			int y = randInt(500, 600);
			int dy = 0;
			for (int x = 0; x < 520; x++){
				if(x % 20 == 0){
					dy = randInt(-15, 15);
				}
				xPoints[x] = x * 15;
				y += randInt(-10 + dy, 10 + dy);
				yPoints[x] = y;
				if (y < 150 || y > 650){
					valid = false;
				}
			}
			if (valid){
				break;
			}
		}
		
		for (int i = 0; i < 520; i++){
			groundPoly.addPoint(xPoints[i], yPoints[i]);
			groundPoly2.addPoint(xPoints[i], yPoints[i] + 15);
			groundPoly3.addPoint(xPoints[i], yPoints[i] + 45);
			groundPoly4.addPoint(xPoints[i], yPoints[i] + 65);
			groundPoly5.addPoint(xPoints[i], yPoints[i] + 120);
		}
		groundPoly.addPoint(1500, 700);
		groundPoly.addPoint(0, 700);
		groundPoly2.addPoint(1500, 700);
		groundPoly2.addPoint(0, 700);
		groundPoly3.addPoint(1500, 700);
		groundPoly3.addPoint(0, 700);
		groundPoly4.addPoint(1500, 700);
		groundPoly4.addPoint(0, 700);
		groundPoly5.addPoint(1500, 700);
		groundPoly5.addPoint(0, 700);
	}
	
	public int randInt(int low, int high){
		int r = high - low + 1;
		return (int)(Math.random() * r) + low;
		
	}
	
	public void paintComponent(Graphics g) {
		//////////////////////////////////////////////////////
		gameBack.paintIcon(this, g, -200,-400);
		g.setColor(new Color(62, 216, 47));
		g.fillPolygon(groundPoly);
		g.setColor(new Color(50, 181, 38));
		g.fillPolygon(groundPoly2);
		g.setColor(new Color(23, 122, 14));
		g.fillPolygon(groundPoly3);
		g.setColor(new Color(9, 86, 12));
		g.fillPolygon(groundPoly4);
		g.setColor(new Color(8, 66, 4));
		g.fillPolygon(groundPoly5);
		//////////////////////////////////////////////////////
		
	}
}