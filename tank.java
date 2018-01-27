/**
 * @(#)tank.java
 *
 *
 * @author 
 * @version 1.00 2018/1/27
 */


public class tank {
	private String colour;
	private int health;
	

    public tank(String col) {
    	colour = col;
    	health = 100;
    }
    
    public void takeDamage(int num){
    	health -= num;
    	if (health < 0){
    		health = 0;
    	}
    }
    
    
}