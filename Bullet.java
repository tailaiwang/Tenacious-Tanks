/**
 * @(#)Bullet.java
 *
 *
 * @author 
 * @version 1.00 2018/1/17
 */


public class Bullet {
	private double xx, yy; //initial calculated values (turned into integers)
	
	private int x, y, dx, dy, power, angle, turn, wind;
		

    public Bullet(int mag, int ang, int player, int wind) {
    	power = mag;
    	turn = player;
    	wind = this.wind;
    	if (turn == 2){
    		angle = 180 - ang;
    	}
    	else {
    		angle = ang;
    	}
    	xx = mag * Math.cos(Math.toRadians(angle)); //x-componemt of bullet
    	yy = mag * Math.sin(Math.toRadians(angle)); //y-component of bullet
    	x = (int) Math.round(xx);
    	y = (int) Math.round(yy);
    }
    
    public void output(){
    	System.out.printf("x-component: %d\n", x);
    	System.out.printf("y-component: %d\n", y);
    	System.out.printf("%d degrees\n", angle);
    	System.out.printf("Player: %d\n", turn);
    	System.out.printf("Wind: %d\n", wind);
    }
    
}