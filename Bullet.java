/**
 * @(#)Tank.java
 * @Cameron Beneteau and Tailai Wang
 * ICS 4U
 * February 19th, 2018
 * @version 1.00 2018/2/2
 */

public class Bullet {
	double power;
	int angle;
	double x;
	double y;
	double vx;
	double vy;

    public Bullet(int p, int ang, double xx, double yy) {
    	x = xx;
    	y = yy;
    	power = p;
    	angle = ang;
    	vx = 0.2 * power * (Math.cos(Math.toRadians(angle))); //scaled x and y components down
    	vy = -0.2 * power * (Math.sin(Math.toRadians(angle)));
    }
    
    public void advance(){ //bullet moving each frame
    	x += vx;
    	vy += 0.15; //gravity acting on bullet
    	y += vy;
    }
    
    public int getX(){ //used to check collision
    	return (int) x;
    }
    
    public int getY(){ //used to check collision
    	return (int) y;
    }
}