/**
 * @(#)GamePanel.java
 *
 *
 * @Cameron Beneteau and Tailai Wang
 * ICS 4U
 * @version 1.00 2018/2/5
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import javafx.scene.shape.Ellipse;
import javax.sound.sampled.*;

public class GamePanel extends JPanel {
	Tank redTank, greenTank; //tank objects
	int screenX = 1300;
	int screenY = 700;
	
	int turnNum; //turn variables
	String turn;
	String winner; //used for win screen
	
	Bullet p1shot, p2shot; //shots and bullets
	Ellipse p1bullet = new Ellipse();
	Ellipse p2bullet = new Ellipse();

	ImageIcon gameBack = new ImageIcon("Images/gameBack.jpg"); //images
	Image green, green1, red, red1;
	Image redTurn,greenTurn,redWins,greenWins,winBack;
	
	int greenIndex, redIndex = 0;
	int greenX = 100; //starting X positions for tanks
	int oldGreenX = greenX;
	int redX = 1200;
	int oldRedX = redX;
	int greenY, redY = 50; //starting Y positions for tanks
	int greenAngle = 45; //starting angles
	int redAngle = 135;
	int greenPower = 50; //starting powers
	int redPower = 50;
	Rectangle greenTankRect = new Rectangle(redX, redY, 78, 30);
	Rectangle redTankRect = new Rectangle(greenX, greenY, 78, 30); //tank rectangles	
	
	private boolean[] keys;
	private Image[] greens, reds;
	
	Polygon groundPoly = new Polygon(); //map polygons
	Polygon groundPoly2 = new Polygon();
	Polygon groundPoly3 = new Polygon();
	Polygon groundPoly4 = new Polygon();
	Polygon groundPoly5 = new Polygon();
	
	int xPoints[] = new int[522]; //point lists for polygons
	int yPoints[] = new int[522];
	
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
    	redTurn = new ImageIcon("Images/RedTurn.png").getImage();
    	redTurn = redTurn.getScaledInstance(300, 80, Image.SCALE_SMOOTH);
    	greenTurn = new ImageIcon("Images/GreenTurn.png").getImage();
    	greenTurn = greenTurn.getScaledInstance(300, 80, Image.SCALE_SMOOTH);
    	redWins = new ImageIcon("Images/RedWins.png").getImage();
    	redWins = redWins.getScaledInstance(300,80, Image.SCALE_SMOOTH);
    	greenWins = new ImageIcon("Images/GreenWins.png").getImage();
    	greenWins = greenWins.getScaledInstance(300,80, Image.SCALE_SMOOTH);
    	winBack = new ImageIcon("Images/winBack.jpg").getImage();
    	winBack = winBack.getScaledInstance(1300,700, Image.SCALE_SMOOTH);
    	///////////////////////////////////////////////
    	turnNum = randInt(1, 2);
    	if (turnNum == 1){
    		turn = "p1 select";
    	}
    	else{
    		turn = "p2 select";
    	}
    	 ////////////////////////////////////////////////
    	//new Timer(1000,this).start();
    	////////////////////////////////////////////////
    	makeLand(); //generate the land
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
    
    public void p1select(){
	    if (keys[KeyEvent.VK_D]){
	    	if (greenX <= screenX - 85){ //cannot fall off map
	    		if (greenX < oldGreenX + 100){ //restrict movement
	    			greenX += 2;
	    		}
	    		//greenX += 2;
	    	}
			greenY -= 3; //push the image down
			greenIndex = 0;
			if (!groundPoly.contains(greenX + 40, greenY + 30)){
				greenY += 3; //push the image up
			}
		}
		if (keys[KeyEvent.VK_A] ){
			if (greenX >= 0){ //cannot fall off map
			if (greenX > oldGreenX - 100){ //restrict movement
	    			greenX -= 2;
	    		}
	    		//greenX -= 2;
	    	}
			greenY -= 3;
			greenIndex = 1;
			if (!groundPoly.contains(greenX + 40, greenY + 30)){
				greenY += 3;
			}
		}
		if (keys[KeyEvent.VK_W]){
			if(greenAngle < 360){
				greenAngle += 1;
			}
			else{
				greenAngle = 0;
			}
		}
		if (keys[KeyEvent.VK_S]){
			if(greenAngle > 0){
				greenAngle -= 1;
			}
			else{
				greenAngle = 360;
			}
		}
		if (keys[KeyEvent.VK_E]){
			if (greenPower > -1 && greenPower < 100){
				greenPower += 1;
			}
		}
		if (keys[KeyEvent.VK_Q]){
			if (greenPower > 0 && greenPower < 101){
				greenPower -= 1;
			}
		}
		if (keys[KeyEvent.VK_SPACE]){
			turn = "p1 shoot"; 
			p1shot = new Bullet(greenPower, greenAngle,  greenX + 40 + (int) (20*(Math.cos(Math.toRadians(greenAngle)))), greenY - (int) (20*(Math.sin(Math.toRadians(greenAngle)))));
			p1bullet.setRadiusX(10);
			p1bullet.setRadiusY(10);
			oldRedX = redX;

		}
    }
    
    public void p1shoot(){
    	p1shot.advance();
    	p1bullet.setCenterX(p1shot.getX());
		p1bullet.setCenterY(p1shot.getY());
		if (groundPoly.contains(p1shot.getX(), p1shot.getY())){ //when the shot hits the ground
			turn = "p2 select";
		}
		if (redTankRect.contains(p1shot.getX(), p1shot.getY())){ //check for hit against opponent
			redTank.takeDamage(20);
			turn = "p2 select";
		}
		if (greenTankRect.contains(p1shot.getX(), p1shot.getY())){ //check for hit against itself
			greenTank.takeDamage(20);
			turn = "p2 select";
		}
		if (p1shot.getX() < -50 || p1shot.getX() > 1350){ //shot goes off the side of screen
			turn = "p2 select";
		}
    }
    
    public void p2select(){
    	if(keys[KeyEvent.VK_RIGHT]){
			if (redX <= screenX - 85){ //cannot fall off map
				if (redX < oldRedX + 100){ //restrict movement
	    				redX += 2;
	    			}
	    			//redX +=2;
    		}
			redY -= 3;
			redIndex = 1;
			if (!groundPoly.contains(redX + 40, redY + 30)){
				redY += 3;
			}
		}
		if(keys[KeyEvent.VK_LEFT]){
			if (redX >= 0){ //cannot fall off map
				if (redX > oldRedX - 100){ //restrict movement
	    				redX -= 2;
	    			}
    			//redX -= 2;
    		}
			redY -= 3;
			redIndex = 0;
			if (!groundPoly.contains(redX + 40, redY + 30)){
				redY += 3;
			}
		}
		if (keys[KeyEvent.VK_DOWN]){
			if (redAngle < 360){
				redAngle += 1;
			}
			else{
				redAngle = 0;
			}
		}
		if (keys[KeyEvent.VK_UP]){
			if (redAngle > 0){
				redAngle -= 1;
			}
			else{
				redAngle = 360;
			}
		}
		if (keys[KeyEvent.VK_PERIOD]){
			if (redPower > -1 && redPower < 100){
				redPower += 1;
			}
		}
		if (keys[KeyEvent.VK_SLASH]){
			if (redPower > 0 && redPower < 101){
				redPower -= 1;
			}
		}
		if (keys[KeyEvent.VK_ENTER]){
			turn = "p2 shoot";
			p2shot = new Bullet(redPower, redAngle,  redX + 40 + (int) (20*(Math.cos(Math.toRadians(redAngle)))), redY - (int) (20*(Math.sin(Math.toRadians(redAngle)))));
			p2bullet.setRadiusX(10);
			p2bullet.setRadiusY(10);
			oldGreenX = greenX;
		}			
    }
    
    public void p2shoot(){
    	p2shot.advance();
    	p2bullet.setCenterX(p2shot.getX());
		p2bullet.setCenterY(p2shot.getY());
		if (groundPoly.contains(p2shot.getX(), p2shot.getY())){ //when the shot hits the ground
			turn = "p1 select";
		}
		if (greenTankRect.contains(p2shot.getX(), p2shot.getY())){ //check for hit against opponent
			greenTank.takeDamage(20);
			turn = "p1 select";
		}
		if (redTankRect.contains(p2shot.getX(), p2shot.getY())){ //check for hit against itself
			redTank.takeDamage(20);
			turn = "p1 select";
		}
		if (p2shot.getX() < -50 || p2shot.getX() > 1350){ //shot goes off the side of screen
			turn = "p1 select";
		}
    }

    public void refresh(){
    	greenTankRect.x = greenX;
    	greenTankRect.y = greenY;
    	redTankRect.x = redX;
    	redTankRect.y = redY;
    	if (turn == "p1 select"){
    		p1select();
    	}
    	if (turn == "p1 shoot"){
    		p1shoot();
    	}
    	if (turn == "p2 select"){
    		p2select();
    	}
    	if (turn == "p2 shoot"){
    		p2shoot();	
    	}
		if (!groundPoly.contains(greenX + 40, greenY + 30)){
			greenY += 3;
		}
		if (!groundPoly.contains(redX + 40, redY + 30)){
			redY += 3;
		}
		if (greenTank.getHealth() == 0){
			turn = "nothing";
			winner = "red";
		}
		if (redTank.getHealth() == 0){
			turn = "nothing";
			winner = "green";
		}
    }
    
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		//////////////////////////////////////////////////////
		gameBack.paintIcon(this, g, -200,-400); //background
		if (turn == "p1 select"){
			g.drawImage(greenTurn, (screenX / 2) - (greenTurn.getWidth(null) / 2), 20, this);
		}
		if (turn == "p1 shoot"){
			g.fillOval(p1shot.getX(), p1shot.getY(), 10, 10);
			g.drawImage(greenTurn, (screenX / 2) - (greenTurn.getWidth(null) / 2), 20, this);
		}
		if (turn == "p2 select"){
			g.drawImage(redTurn, (screenX/2) - (redTurn.getWidth(null) / 2), 20, this);
		}
		if (turn == "p2 shoot"){
			g.fillOval(p2shot.getX(), p2shot.getY(), 10, 10);
			g.drawImage(redTurn, (screenX/2) - (redTurn.getWidth(null) / 2), 20, this);
		}
		
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
		//g2.draw(redTankRect); //tank rectangles
		//g2.draw(greenTankRect);
		g.drawImage(greens[greenIndex], greenX - 10, greenY - 35, this); //draw tanks
		g.drawImage(reds[redIndex], redX - 10, redY - 35, this);
        g2.setStroke(new BasicStroke(5)); //green tank barrel colour
        g.setColor(new Color(33, 255, 0));
		g2.drawLine(greenX + 40, greenY, greenX + 40 + (int) (20*(Math.cos(Math.toRadians(greenAngle)))), greenY - (int) (20*(Math.sin(Math.toRadians(greenAngle))))); //barrel from green tank
		g.setColor(new Color(244, 22, 14)); //red tank barrel colour
		g2.drawLine(redX + 40, redY, redX + 40 + (int) (20*(Math.cos(Math.toRadians(redAngle)))), redY - (int) (20*(Math.sin(Math.toRadians(redAngle))))); //barrel from green tank
		///////////////////////////////////////////////////
		g.setFont(new Font("Arial Black", Font.PLAIN, 25));
		g.setColor(Color.GREEN);
		g.drawString("Health: " + greenTank.getHealth(), 5, 25);
		g.drawString("Angle: " + greenAngle, 5, 55);
		g.drawString("Power: " + greenPower, 5, 85);
		g.setColor(Color.RED);
		g.drawString("Health: " + redTank.getHealth(), 1130, 25);
		g.drawString("Angle: " + redAngle, 1130, 55);
		g.drawString("Power: " + redPower, 1130, 85);	
		if (turn == "nothing"){
			g.drawImage(winBack,0,0,this);
			if(winner == "red"){
				g.drawImage(redWins,(screenX/2) - (redWins.getWidth(null)/2),(screenY/2) - (redWins.getHeight(null)/2),this);
			}
			if(winner == "green"){
				g.drawImage(greenWins,(screenX/2) - (greenWins.getWidth(null)/2),(screenY/2) - (greenWins.getHeight(null)/2),this);
			}
		}
	}
}