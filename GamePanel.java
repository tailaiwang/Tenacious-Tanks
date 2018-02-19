/**
 * @(#)GamePanel.java
 * @Cameron Beneteau and Tailai Wang
 * ICS 4U
 * February 19th, 2018
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

	String screen = "menu";
	String turn;  //turn variables
	int turnNum;
	
	Bullet p1shot, p2shot; //shots and bullets
	Ellipse p1bullet = new Ellipse();
	Ellipse p2bullet = new Ellipse();

	ImageIcon gameBack = new ImageIcon("Images/gameBack.jpg"); //images
	Image green, green1, red, red1, explosionImage;
	Image titleText, enterText, instructionsText, backspaceText, titleBack, redTurn, greenTurn, redWins, greenWins, winBack, backspaceAgainText, escapeQuitText;
	Integer explosionNum;
	double frame = 0;
	int explodeX, explodeY;
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
	private Image[] greens, reds, explosions;
	
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
    	//////////////////////////////////////////////// menu
    	titleBack = new ImageIcon("Images/titleBack.png").getImage();
    	titleText = new ImageIcon("Images/TitleText.png").getImage();
    	titleText = titleText.getScaledInstance(900, 200, Image.SCALE_SMOOTH);
    	enterText = new ImageIcon("Images/enterText.png").getImage();
    	enterText = enterText.getScaledInstance(600, 75, Image.SCALE_SMOOTH);
    	//////////////////////////////////////////////// instructions
    	instructionsText = new ImageIcon("Images/instructionsText.png").getImage();
    	backspaceText = new ImageIcon("Images/backspaceText.png").getImage();
    	backspaceText = backspaceText.getScaledInstance(600, 75, Image.SCALE_SMOOTH);
    	//////////////////////////////////////////////// game
    	redTurn = new ImageIcon("Images/RedTurn.png").getImage();
    	redTurn = redTurn.getScaledInstance(300, 80, Image.SCALE_SMOOTH);
    	greenTurn = new ImageIcon("Images/GreenTurn.png").getImage();
    	greenTurn = greenTurn.getScaledInstance(300, 80, Image.SCALE_SMOOTH);
    	//////////////////////////////////////////////// win
    	redWins = new ImageIcon("Images/RedWins.png").getImage();
    	redWins = redWins.getScaledInstance(500,125, Image.SCALE_SMOOTH);
    	greenWins = new ImageIcon("Images/GreenWins.png").getImage();
    	greenWins = greenWins.getScaledInstance(500,125, Image.SCALE_SMOOTH);
    	winBack = new ImageIcon("Images/winBack.jpg").getImage();
    	winBack = winBack.getScaledInstance(1300,700, Image.SCALE_SMOOTH);
    	backspaceAgainText = new ImageIcon("Images/backspaceAgainText.png").getImage();
    	backspaceAgainText = backspaceAgainText.getScaledInstance(500, 50, Image.SCALE_SMOOTH);
    	escapeQuitText = new ImageIcon("Images/escapeQuitText.png").getImage();
    	escapeQuitText = escapeQuitText.getScaledInstance(400, 50, Image.SCALE_SMOOTH);
    	///////////////////////////////////////////////
    	explosions = new Image[14]; //cut using python file
    	for (int i = 0; i < 14; i++){
    		Integer explosionNum = i;
    		String imageName = "Images/explosion/explosion_"; //saving to file (temp variable)
    		explosionImage = new ImageIcon(imageName + explosionNum.toString() + ".png").getImage();
    		explosions[i] = explosionImage;
    	}//end for
    	
    	///////////////////////////////////////////////
    	makeLand(); //generate the land
    	turnNum = randInt(1, 2); //randomly select turn
    	if (turnNum == 1){
    		turn = "p1 select";
    	}//end if
    	else{
    		turn = "p2 select";
    	}//end else
	}//end Constructor
	   
	public void makeLand(){ //generation of land
		while(true){
			boolean valid = true; //flag
			int y = randInt(500, 600); //holds range of map
			int dy = 0;
			for (int x = 0; x < 520; x++){
				if(x % 20 == 0){
					dy = randInt(-15, 15); //smooths it out
				}//end if
				xPoints[x] = x * 15;
				y += randInt(-10 + dy, 10 + dy);
				yPoints[x] = y;
				if (y < 200 || y > 650){ //invalid map
					valid = false;
				}//end if
			}//end for
			if (valid){
				break; //breaks with flag
			}//end if
		}//end while
		
		for (int i = 0; i < 520; i++){ //add randomized points to polygon lists
			groundPoly.addPoint(xPoints[i], yPoints[i]);
			groundPoly2.addPoint(xPoints[i], yPoints[i] + 15); //same shape just moved down
			groundPoly3.addPoint(xPoints[i], yPoints[i] + 45); //allows for the multicolored floor
			groundPoly4.addPoint(xPoints[i], yPoints[i] + 65);
			groundPoly5.addPoint(xPoints[i], yPoints[i] + 120);
		}//end for
		
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
	}//end makeLand
	
	public int randInt(int low, int high){
		int r = high - low + 1;
		return (int)(Math.random() * r) + low;
		
	}//end randInt
	
	public void setKey(int k, boolean v) {
    	keys[k] = v;
    }//end setKey
    
    public void menu(){ //not a true menu, just a quick screen where they press Enter
    	if (keys[KeyEvent.VK_ENTER]){
    		screen = "instructions";
    	}//end if
    }//end menu
    
    public void instructions(){ //again, not a true panel, just a screen where they move on by pressing a key
    	if (keys[KeyEvent.VK_BACK_SPACE]){
    		screen = "game";
    	}//end if
    }//end instructions
    public void p1select(){
	    if (keys[KeyEvent.VK_D]){
	    	if (greenX <= screenX - 85){ //cannot fall off map
	    		if (greenX < oldGreenX + 100){ //restrict movement
	    			greenX += 2;
	    		}//end if
	    	}//end if
			greenY -= 3; //push the image down
			greenIndex = 0;
			if (!groundPoly.contains(greenX + 40, greenY + 30)){
				greenY += 3; //push the image up
			}//end if
		}//end if
		if (keys[KeyEvent.VK_A] ){
			if (greenX >= 0){ //cannot fall off map
			if (greenX > oldGreenX - 100){ //restrict movement
	    			greenX -= 2;
	    		}//end if
	    	}//end if
			greenY -= 3;
			greenIndex = 1;
			if (!groundPoly.contains(greenX + 40, greenY + 30)){
				greenY += 3;
			}//end if
		}//end if
		if (keys[KeyEvent.VK_W]){ //angle adjustment
			if(greenAngle < 360){
				greenAngle += 1;
			}//end if
			else{
				greenAngle = 0;
			}//end else
		}//end if
		if (keys[KeyEvent.VK_S]){ //angle adjustment
			if(greenAngle > 0){
				greenAngle -= 1;
			}//end if
			else{
				greenAngle = 360;
			}//end else
		}//end if
		if (keys[KeyEvent.VK_E]){ //power adjustment
			if (greenPower > -1 && greenPower < 100){
				greenPower += 1;
			}//end if
		}//end if
		if (keys[KeyEvent.VK_Q]){ //power adjustment
			if (greenPower > 0 && greenPower < 101){
				greenPower -= 1;
			}//end if
		}//end if
		if (keys[KeyEvent.VK_SPACE]){ //shooting
			turn = "p1 shoot";  //creates new bullet object
			p1shot = new Bullet(greenPower, greenAngle,  greenX + 40 + (int) (20*(Math.cos(Math.toRadians(greenAngle)))), greenY - (int) (20*(Math.sin(Math.toRadians(greenAngle)))));
			p1bullet.setRadiusX(10);
			p1bullet.setRadiusY(10);
			oldRedX = redX;
		}//end if
    }//end p1select
    
    public void p1shoot(){ //advances shot until it hits something
    	p1shot.advance();
    	p1bullet.setCenterX(p1shot.getX());
		p1bullet.setCenterY(p1shot.getY());
		explodeX = p1shot.getX();
		explodeY = p1shot.getY();
		if (groundPoly.contains(p1shot.getX(), p1shot.getY())){ //when the shot hits the ground
			turn = "p1 explode";
		}//end if
		if (redTankRect.contains(p1shot.getX(), p1shot.getY())){ //check for hit against opponent
			redTank.takeDamage(20);
			turn = "p1 explode";	
		}//end if
		if (greenTankRect.contains(p1shot.getX(), p1shot.getY())){ //check for hit against itself
			turn = "p1 explode";
			greenTank.takeDamage(20);	
		}//end if
		if (p1shot.getX() < -50 || p1shot.getX() > 1350){ //shot goes off the side of screen
			turn = "p2 select";
		}//end if
    }//end p1shoot
    
    public void p2select(){
    	//p1 and p2 select are pretty much the exact same thing.
    	if (keys[KeyEvent.VK_RIGHT]){
			if (redX <= screenX - 85){ //cannot fall off map
				if (redX < oldRedX + 100){ //restrict movement
	    				redX += 2;
	    			}//end if
    		}//end if
			redY -= 3;
			redIndex = 1;
			if (!groundPoly.contains(redX + 40, redY + 30)){
				redY += 3;
			}//end if
		}//end if
		if (keys[KeyEvent.VK_LEFT]){
			if (redX >= 0){ //cannot fall off map
				if (redX > oldRedX - 100){ //restrict movement
	    				redX -= 2;
	    			}//end if
    		}//end if
			redY -= 3;
			redIndex = 0;
			if (!groundPoly.contains(redX + 40, redY + 30)){
				redY += 3;
			}//end if
		}//end if
		if (keys[KeyEvent.VK_DOWN]){ //angle adjustment		
			if (redAngle < 360){
				redAngle += 1;
			}//end if
			else{
				redAngle = 0;
			}//end else
		}//end if
		if (keys[KeyEvent.VK_UP]){ //angle adjustment
			if (redAngle > 0){
				redAngle -= 1;
			}//end if
			else{
				redAngle = 360;
			}//end else
		}//end if
		if (keys[KeyEvent.VK_PERIOD]){ //power adjustment
			if (redPower > -1 && redPower < 100){
				redPower += 1;
			}//end if
		}//end if
		if (keys[KeyEvent.VK_SLASH]){
			if (redPower > 0 && redPower < 101){  //power adjustment
				redPower -= 1;
			}//end if
		}//end if
		if (keys[KeyEvent.VK_ENTER]){
			turn = "p2 shoot"; //shoot creates new bullet object
			p2shot = new Bullet(redPower, redAngle,  redX + 40 + (int) (20*(Math.cos(Math.toRadians(redAngle)))), redY - (int) (20*(Math.sin(Math.toRadians(redAngle)))));
			p2bullet.setRadiusX(10);
			p2bullet.setRadiusY(10);
			oldGreenX = greenX;
		}//end if			
    }//end p1select
    
    public void p2shoot(){
    	p2shot.advance(); //advances until it hits something
    	p2bullet.setCenterX(p2shot.getX());
		p2bullet.setCenterY(p2shot.getY());
		explodeX = p2shot.getX();
		explodeY = p2shot.getY();
		if (groundPoly.contains(p2shot.getX(), p2shot.getY())){ //when the shot hits the ground
			turn = "p2 explode";
		}//end if
		if (greenTankRect.contains(p2shot.getX(), p2shot.getY())){ //check for hit against opponent
			turn = "p2 explode";
			greenTank.takeDamage(20);
		}//end if
		if (redTankRect.contains(p2shot.getX(), p2shot.getY())){ //check for hit against itself
			turn = "p2 explode";
			redTank.takeDamage(20);
		}//end if
		if (p2shot.getX() < -50 || p2shot.getX() > 1350){ //shot goes off the side of screen
			turn = "p1 select";
		}//end if
    }//end p2shoot
    
    public void p1explode(){
    	frame += 0.15; //add 0.15 to make it smoother
    	if (frame >= 13.0){
			turn = "p2 select";
			frame = 0.0;
			if (greenTank.getHealth() == 0){ //only check for health after explosion anamation
				screen = "win";
				turn = "red win";
			}//end if
			if (redTank.getHealth() == 0){
				screen = "win";
				turn = "green win";
	    	}//end if
    	}//end if
    }//end p1explode
    
    public void p2explode(){
    	frame += 0.15; //add 0.15 to make it smoother
    	if (frame >= 13.0){
			turn = "p1 select";
			frame = 0.0;
			if (greenTank.getHealth() == 0){  //only check for health after explosion anamation
				screen = "win";
				turn = "red win";
			}//end if
			if (redTank.getHealth() == 0){
				screen = "win";
				turn = "green win";
	    	}//end if
    	}//end if
    }//end p2explode
    
    public void win(){
    	if (keys[KeyEvent.VK_BACK_SPACE]){ //play again (reset all game variables)
    		redTank.resetHealth();
    		redX = 1200; 
    		redY = 50;
    		redPower = 50;
    		redAngle = 135;
    		greenTank.resetHealth();
    		greenX = 100;
    		greenY = 50;
    		greenPower = 50;
    		greenAngle = 45;
			turnNum = randInt(1, 2); //randomize turn
	    	if (turnNum == 1){
   	 			turn = "p1 select";
    		}//end if
    		else{
    			turn = "p2 select";
    		}//end else
    		screen = "game"; //go back to game
		}//end if
		if (keys[KeyEvent.VK_ESCAPE]){ //quit game
			System.exit(0); //close window
		}//end if
    }//end win

    public void refresh(){
    	if (screen == "menu"){
    		menu();
    	}//end if
    	if (screen == "instructions"){
    		instructions();
    	}//end if
    	if (screen == "game"){ //turn variable calls seperate methods
	    	greenTankRect.x = greenX;
   		 	greenTankRect.y = greenY;
    		redTankRect.x = redX;
    		redTankRect.y = redY;
    		if (turn == "p1 select"){
    			p1select();
    		}//end if
    		if (turn == "p1 shoot"){
    			p1shoot();
    		}//end if
    		if (turn == "p1 explode"){
    			p1explode();
    		}//end if
    		if (turn == "p2 select"){
    			p2select();
    		}//end if
    		if (turn == "p2 shoot"){
    			p2shoot();	
    		}//end if
    		if (turn == "p2 explode"){
    			p2explode();
    		}//end if
			if (!groundPoly.contains(greenX + 40, greenY + 30)){
				greenY += 3;
			}//end if
			if (!groundPoly.contains(redX + 40, redY + 30)){
				redY += 3;
			}//end if
    	}//end if
	    if (screen == "win"){ 
	    	win();
	    }//end if
    }//end refresh
    
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		if (screen == "menu"){
			gameBack.paintIcon(this, g, -200,-400); //this is just drawing the map to make it look good
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
			g.drawImage(titleText, (screenX / 2) - (titleText.getWidth(null) / 2), 150, this);
			g.drawImage(enterText, (screenX / 2) - (enterText.getWidth(null) / 2), 500, this);
		}//end if
		if (screen == "instructions"){
			gameBack.paintIcon(this, g, -200,-400); //drawing the map to make it look good
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
			g.drawImage(instructionsText, (screenX / 2) - (instructionsText.getWidth(null) / 2), 30, this); //adding in text
			g.drawImage(backspaceText, (screenX / 2) - (backspaceText.getWidth(null) / 2), 500, this);
			g.drawImage(backspaceText, (screenX / 2) - (backspaceText.getWidth(null) / 2), 500, this);
			g.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
			g.setColor(Color.BLUE);
			g.drawString("Welcome to Tenacious Tanks, a 2-Player battle to the Death!",screenX/5 + 20,160);
			g.setColor(Color.GREEN);
			g.drawString("Green",400,200);
			g.drawString("A & D to move",400,240);
			g.drawString("W & S to angle",400, 260);
			g.drawString("Q & E to power",400,280);
			g.setColor(Color.RED);
			g.drawString("Red", 700,200);
			g.drawString("LEFT & RIGHT to move",700,240);
			g.drawString("UP & DOWN to angle", 700, 260);
			g.drawString(". & ? to power", 700, 280); 
			//draw strings are super inefficient, but whatever xD
		}//end if
		
		if (screen == "game"){
			gameBack.paintIcon(this, g, -200,-400); //background
			////////////////////////////////////////////////////// map
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
			////////////////////////////////////////////////////// tanks
			g.drawImage(greens[greenIndex], greenX - 10, greenY - 35, this); //draw tanks
			g.drawImage(reds[redIndex], redX - 10, redY - 35, this);
        	g2.setStroke(new BasicStroke(5)); //green tank barrel colour
    	    g.setColor(new Color(33, 255, 0));
			g2.drawLine(greenX + 40, greenY, greenX + 40 + (int) (20*(Math.cos(Math.toRadians(greenAngle)))), greenY - (int) (20*(Math.sin(Math.toRadians(greenAngle))))); //barrel from green tank
			g.setColor(new Color(244, 22, 14)); //red tank barrel colour
			g2.drawLine(redX + 40, redY, redX + 40 + (int) (20*(Math.cos(Math.toRadians(redAngle)))), redY - (int) (20*(Math.sin(Math.toRadians(redAngle))))); //barrel from green tank
			//g2.draw(redTankRect); //tank rectangles
			//g2.draw(greenTankRect);
			//////////////////////////////////////////////////////
			if (turn == "p1 select"){
				g.drawImage(greenTurn, (screenX / 2) - (greenTurn.getWidth(null) / 2), 20, this);
			}//end if
			if (turn == "p1 shoot"){
				g.setColor(new Color(0, 0, 0));
				g.fillOval(p1shot.getX(), p1shot.getY(), 10, 10); //bullet
				g.drawImage(greenTurn, (screenX / 2) - (greenTurn.getWidth(null) / 2), 20, this);
			}//end if
			if (turn == "p1 explode"){
				g.drawImage(explosions[(int) frame], explodeX - 48, explodeY - 90, this);
				g.drawImage(greenTurn, (screenX / 2) - (greenTurn.getWidth(null) / 2), 20, this);
			}//end if
			if (turn == "p2 select"){
				g.drawImage(redTurn, (screenX/2) - (redTurn.getWidth(null) / 2), 20, this);	
			}//end if
			if (turn == "p2 shoot"){
				g.setColor(new Color(0, 0, 0));
				g.fillOval(p2shot.getX(), p2shot.getY(), 10, 10); //bullet
				g.drawImage(redTurn, (screenX/2) - (redTurn.getWidth(null) / 2), 20, this);
			}//end if
			if (turn == "p2 explode"){
				g.drawImage(explosions[(int) frame], explodeX - 48, explodeY - 90, this);
				g.drawImage(redTurn, (screenX/2) - (redTurn.getWidth(null) / 2), 20, this);
			}//end if
			/////////////////////////////////////////////////// tank information (heath, angle, power)
			g.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
			g.setColor(Color.GREEN);
			g.drawString("Health: " + greenTank.getHealth(), 5, 25);
			g.drawString("Angle: " + greenAngle, 5, 55);
			g.drawString("Power: " + greenPower, 5, 85);
			g.setColor(Color.RED);
			g.drawString("Health: " + redTank.getHealth(), 1150, 25);
			g.drawString("Angle: " + redAngle, 1150, 55);
			g.drawString("Power: " + redPower, 1150, 85);
		}//end game
		if (screen == "win"){
			//adding in formatted stuff
			g.drawImage(winBack, 0, 0, this);
			g.drawImage(backspaceAgainText, (screenX / 2) - (backspaceAgainText.getWidth(null) / 2), 400, this);
			g.drawImage(escapeQuitText, (screenX / 2) - (escapeQuitText.getWidth(null) / 2), 475, this);
			if (turn == "red win"){
				g.drawImage(redWins, (screenX/2) - (redWins.getWidth(null)/2), 200, this);
			}//end if
			if(turn =="green win"){
				g.drawImage(greenWins, (screenX/2) - (greenWins.getWidth(null)/2), 200, this);
			}//end if
		}//end win
	}//end paintComponent
}//end GamePanel