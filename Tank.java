/**
 * @(#)Tank.java
 *
 *
 * @Cameron Beneteau and Tailai Wang
 * ICS 4U
 * @version 1.00 2018/2/2
 */


public class Tank {
	private int health;
	private boolean move = true;
	
    public Tank() {
    	health = 100;
    }
    
    public void takeDamage(int num){
    	health -= num;
    	if (health < 0){
    		health = 0;
    	}
    }
    
    public int getHealth(){
    	return health;
    }
   	
   	public void resetHealth(){
   		health = 100;
   	}
   	
   	public boolean getMove(){
   		return move;
   	}
   	
    public void moveFalse(){
    	move = false;
    }
}