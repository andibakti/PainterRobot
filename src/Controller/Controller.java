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


	/**
	 * @param dot
	 * @return
	 */
	public ArrayList<Connection> getConnections(Dot dot) {
		// TODO Auto-generated method stub
		return null;
	}

}
