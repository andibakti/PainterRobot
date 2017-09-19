/*
*/
package Model;

import java.util.List;

/**
 * Author: Bakti, Andi-Camille
 * Email: andi-camille.bakti@mail.mcgill.ca
 */
public class Connection {

	private List<Dot> dots;
	
	
	public List<Dot> getDots() {
		List<Dot> newDots = this.dots;
		return newDots;
	}

	public Dot getDot(int index) {
		Dot dot = dots.get(index);
		return dot;
	}

}
