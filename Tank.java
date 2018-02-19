/**
 * @(#)Tank.java
 * @Cameron Beneteau and Tailai Wang
 * ICS 4U
 * February 19th, 2018
 * @version 1.00 2018/2/2
 */

public class Tank {
	private int health;
	private boolean move = true;
	
    public Tank() {
    	health = 100;
    }//end Constructor
    //takeDamage is really the only usefult thing here
    //OOP design is amazing! 
    public void takeDamage(int num){
    	health -= num;
    	if (health < 0){
    		health = 0;
    	}//end if
    }//end takeDamage
    //getters and setters
    public int getHealth(){
    	return health;
    }//end getHealth
   	
   	public void resetHealth(){
   		health = 100;
   	}//end resetHealth
   	
   	public boolean getMove(){
   		return move;
   	}//end getMove
   	
    public void moveFalse(){
    	move = false;
    }//end moveFalse
}//end Tank