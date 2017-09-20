/*
*/
package Model;

import java.util.ArrayList;

import View.DrawPage;

/**
 * Author: Bakti, Andi-Camille
 * Email: andi-camille.bakti@mail.mcgill.ca
 */
public class PainterRobot {

	private ArrayList<Dot> dots;
	
	
	public PainterRobot(){
		dots = new ArrayList<Dot>();
	}
	 public static void main(String[] args) {
	        //Schedule a job for the event-dispatching thread:
	        //creating and showing this application's GUI.
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	DrawPage dp = new DrawPage("Si senor");
	            	
	            	dp.setVisible(true);
	                dp.initComponents("vamonos");
	            }
	        });
	    }
	
	public ArrayList<Dot> getAllDots() {

		return this.dots;
	}


	/**
	 * 
	 */
	public void addDot(Dot dot) {
		
		// TODO Auto-generated method stub
		dots.add(dot);
		
	}

}
