/*
*/
package Controller;

import java.util.ArrayList;

import Model.Connection;
import Model.Dot;
import Model.PainterRobot;

/**
 * Author: Bakti, Andi-Camille
 * Email: andi-camille.bakti@mail.mcgill.ca
 */
public class Controller {
	
	private ArrayList<Dot> dots;
	

	/**
	 * @return
	 */
	public ArrayList<Dot> getAllDots() {
		PainterRobot pr = new PainterRobot();
		this.dots = pr.getAllDots();
		return this.dots;
	}
	
	//Send the coordinates of the dots to the back-end to translate
	//data into rotations and movements that the robot has to perform.
	public boolean sendDots(ArrayList<Dot> dots){
		boolean wasSet = false;
		this.dots = dots;
		
		//Send to back-end
		
		wasSet = true;
		return wasSet;
	}


	/**
	 * @param dot
	 * @return
	 */
	public ArrayList<Connection> getConnections(Dot dot) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * @param aX
	 * @param aY
	 */
	public boolean createDot(int aX, int aY){
		boolean wasSet = false;
		PainterRobot pr = new PainterRobot();
		try{
			for(Dot dot: pr.getAllDots()){
				if(dot.getX() ==aX && dot.getY() == aY){
					throw new RuntimeException("Already a dot on that spot");
				}
			}
			Dot dot = new Dot(aX, aY);
			pr.addDot(dot);
			this.dots.add(dot);
			
		}catch(RuntimeException e){
			
		}
		
		return wasSet = true;
		// TODO Auto-generated method stub
		
	}

}
