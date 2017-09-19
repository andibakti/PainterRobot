/*
*/
package Model;

import java.util.ArrayList;

/**
 * Author: Bakti, Andi-Camille
 * Email: andi-camille.bakti@mail.mcgill.ca
 */
public class PainterRobot {

	private ArrayList<Dot> dots;
	
	
	public PainterRobot(){
		dots = new ArrayList<Dot>();
	}
	
	
	public ArrayList<Dot> getAllDots() {

		return this.dots;
	}

}
