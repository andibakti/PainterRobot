/*
*/
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Bakti, Andi-Camille
 * Email: andi-camille.bakti@mail.mcgill.ca
 */
public class Dot {

	private boolean isSelected;
	private int x;
	private int y;
	private List<Connection> connections;
	
	public Dot(int x, int y){
		this.x = x;
		this.y = y;
		isSelected = false;
		connections = new ArrayList<Connection>();
		
	}
	public boolean getSelected(){
		return isSelected;
	}
	
	public boolean setX(int aX)
	  {
	    boolean wasSet = false;
	    x = aX;
	    wasSet = true;
	    return wasSet;
	  }

	  public boolean setY(int aY)
	  {
	    boolean wasSet = false;
	    y = aY;
	    wasSet = true;
	    return wasSet;
	  }

	public int getY() {
		return this.y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public boolean setIsSelected(boolean a)
	  {
	    boolean wasSet = false;
	    isSelected = a;
	    wasSet = true;
	    return wasSet;
	  }
	
	public boolean getIsSelected(){
		return isSelected;
	}
	
	public List<Connection> getConnections() {
		return connections;
	}
	
	public String toString(){
		String s = "X:" + this.x + ", y:" + this.y + ", isSelected:" + this.isSelected;
		return s;
	}

}
