/**
 * @(#)GamePanel.java
 *
 *
 * @author 
 * @version 1.00 2018/2/5
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;

class GamePanel extends JPanel {
	
	Tank redTank, greenTank; //tank objects
	int screenX = 1300;
	int screenY = 700;
	
	ImageIcon gameBack = new ImageIcon("Images/gameBack.jpg"); //images
	Image green, green1, red, red1;
	
	int greenIndex, redIndex = 0;
	int greenX = 100; //starting X positions for tanks
	int redX = 1200;
	int greenY, redY = 50; //starting Y positions for tanks
	private boolean[] keys;
	private Image[] greens, reds;
	
	Polygon groundPoly = new Polygon(); //map polygons
	Polygon groundPoly2 = new Polygon();
	Polygon groundPoly3 = new Polygon();
	Polygon groundPoly4 = new Polygon();
	Polygon groundPoly5 = new Polygon();
	
	int xPoints[] = new int[522]; //point lists for polygons
	int yPoints[] = new int[522];
	
	JTextArea redHealthText = new JTextArea(5, 40);
	
	public GamePanel(){
		keys = new boolean [KeyEvent.KEY_LAST+1];
    	//Green Tank
    	greenTank = new Tank();
    	green = new ImageIcon("Images/GreenTankRight.png").getImage();
    	green = green.getScaledInstance(100, 100,Image.SCALE_SMOOTH);
    	green1 = new ImageIcon("Images/GreenTankLeft.png").getImage();
    	green1 = green1.getScaledInstance(100, 100,Image.SCALE_SMOOTH);
    	greens = new Image[2];
    	greens[0] = green;
    	greens[1] = green1;
    	//Red Tank
    	redTank = new Tank();
    	red1 = new ImageIcon("Images/RedTankLeft.png").getImage();
    	red1 = red1.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    	red = new ImageIcon("Images/RedTankRight.png").getImage();
    	red = red.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    	reds = new Image[2];
    	reds[1] = red;
    	reds[0] = red1;   		
    	////////////////////////////////////////////////
    	//new Timer(1000,this).start();
    	makeLand();
	}
	
	public void actionPerformed(ActionEvent evt) {
    	Object source = evt.getSource();
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
				if (y < 200 || y > 650){
					valid = false;
				}
			}
			if (valid){
				break;
			}
		}
		
		for (int i = 0; i < 520; i++){ //add randomized points to polygon lists
			groundPoly.addPoint(xPoints[i], yPoints[i]);
			groundPoly2.addPoint(xPoints[i], yPoints[i] + 15); //same shape just moved down
			groundPoly3.addPoint(xPoints[i], yPoints[i] + 45);
			groundPoly4.addPoint(xPoints[i], yPoints[i] + 65);
			groundPoly5.addPoint(xPoints[i], yPoints[i] + 120);
		}
		
		groundPoly.addPoint(1500, 700); //end points for polygons
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
	
	public void setKey(int k, boolean v) {
    	keys[k] = v;
    }
    
    public void refresh(){
    	if(keys[KeyEvent.VK_D] ){
    		if (greenX <= screenX - 85){ //cannot fall off map
    			greenX += 2;
    		}
			greenY -= 3; //push the image down
			greenIndex = 0;
			if (!groundPoly.contains(greenX + 40, greenY + 30)){
				greenY += 3; //push the image up
			}
		}
		if(keys[KeyEvent.VK_A] ){
			if (greenX >= 0){ //cannot fall off map
    			greenX -= 2;
    		}
			greenY -= 3;
			greenIndex = 1;
			if (!groundPoly.contains(greenX + 40, greenY + 30)){
				greenY += 3;
			}
		}
		if(keys[KeyEvent.VK_RIGHT]){
			if (redX <= screenX - 85){ //cannot fall off map
    			redX += 2;
    		}
			redY -= 3;
			redIndex = 1;
			if (!groundPoly.contains(redX + 40, redY + 30)){
				redY += 3;
			}
		}
		if(keys[KeyEvent.VK_LEFT]){
			if (redX >= 0){ //cannot fall off map
    			redX -= 2;
    		}
			redY -= 3;
			redIndex = 0;
			if (!groundPoly.contains(redX + 40, redY + 30)){
				redY += 3;
			}
		}
		if (!groundPoly.contains(greenX + 40, greenY + 30)){
			greenY += 3;
		}
		if (!groundPoly.contains(redX + 40, redY + 30)){
			redY += 3;
		}
    }
    
	public void paintComponent(Graphics g){
		//////////////////////////////////////////////////////
		gameBack.paintIcon(this, g, -200,-400); //background
		g.setColor(new Color(62, 216, 47));
		g.fillPolygon(groundPoly); //map polygon
		g.setColor(new Color(50, 181, 38));
		g.fillPolygon(groundPoly2);
		g.setColor(new Color(23, 122, 14));
		g.fillPolygon(groundPoly3);
		g.setColor(new Color(9, 86, 12));
		g.fillPolygon(groundPoly4);
		g.setColor(new Color(8, 66, 4));
		g.fillPolygon(groundPoly5);
		//////////////////////////////////////////////////////
		//g.drawRect(redX, redY, 80, 30); //red tank box outline
		//g.drawRect(greenX, greenY, 80, 30); //green tank box outline
		g.drawImage(greens[greenIndex], greenX - 10, greenY - 35, this); //draw tanks
		g.drawImage(reds[redIndex], redX - 10, redY - 35, this);
		//g.setColor(new Color(52, 237, 42));
		//g.drawLine(greenX + 40, greenY, greenX + 40, greenY - 20);
		//g.setColor(new Color(244, 22, 14));
		//g.drawLine(redX + 40, redY, redX + 40, redY - 20);
	}
}