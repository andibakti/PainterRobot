/*
*/
package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;

import java.awt.BasicStroke;
import Controller.Controller;

import java.awt.geom.Rectangle2D;


import Model.Dot;
import Model.Connection;

/**
 * Author: Bakti, Andi-Camille
 * Email: andi-camille.bakti@mail.mcgill.ca
 */
public class Visualizer extends JPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Rectangle2D, Dot> pattern;
	private ArrayList<Rectangle2D> rectangles = new ArrayList<Rectangle2D>();
	private String show;
	private List<Dot> dots;
	public ArrayList<Dot> chosenDots;
	private List<Rectangle2D> selectedRects;
	
	private static final int RECTWIDTH = 100;
	private static final int RECTHEIGHT = 100;
	private static final int SPACING = 100;

	public Visualizer(ArrayList<Dot> dots){
		super();
		init(dots);
	}
	

	private void init(ArrayList<Dot> dots){
		this.show ="no";
		this.dots = dots;
		pattern = new HashMap<Rectangle2D, Dot>();
		selectedRects = new ArrayList<Rectangle2D>();
		
		addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				int x = e.getX();
				int y = e.getY();
				for (Rectangle2D rectangle : rectangles){
					if (rectangle.contains(x, y)) {
						if (selectedRects.contains(rectangle)) {
							selectedRects.remove(rectangle);
						}
						else {
							selectedRects.add(rectangle);	
							//Taking the coordinates of the rectangle and
							//using them as reference to create Dot objects
							 int RectX = (int) rectangle.getCenterX()/50;
							 int RectY = (int) rectangle.getCenterY()/50;
							 
							 if(RectX == 5 ){
								 RectX = 2;
							 }
							 if(RectX == 9){
								 RectX = 3;
							 }
							 
							 if(RectY == 1){
								 RectY = 3;
							 }else if(RectY == 5){
								 RectY = 2;
							 }else{
								 RectY = 1;
							 }
							 Dot temp = new Dot(RectX, RectY);
							 chosenDots = new ArrayList<Dot>();
							 
							 temp.setIsSelected(true);
							 chosenDots.add(temp);
							System.out.println(chosenDots.toString());

							 
						}
						break;
					}
				}
				repaint();
			}
		});
		
	}
	
	public void setMatrix(ArrayList<Dot> dots){
		this.dots = dots;
		pattern = new HashMap<Rectangle2D, Dot>();
		selectedRects.clear();
		this.show = "yes";
		repaint();
//		if(show.equals("yes")){
//			this.paintComponent(getGraphics());
//		}else{
//			this.repaint();
//		}
	}
	
	private void doDrawing(Graphics g){
		Controller cont = new Controller();
		Graphics2D g2d = (Graphics2D) g;
		g.create();
		BasicStroke thinStroke = new BasicStroke(2);
		BasicStroke thickStroke = new BasicStroke(6);
		g2d.setStroke(thinStroke);
		for(int i = 0 ; i < 3 ; i++) {
			for(int j = 0 ; j < 3 ; j++) {
				
				Rectangle2D rectangle = new Rectangle2D.Float((RECTWIDTH + SPACING) * i, (RECTHEIGHT + SPACING) * j , RECTWIDTH, RECTHEIGHT);
				rectangles.add(rectangle);
				g2d.setColor(Color.GRAY);
				g2d.draw(rectangle);
				
				g2d.setColor(Color.WHITE);
				g2d.fill(rectangle);
				
			}
		}
		for(Rectangle2D rectangle : rectangles) {
			if (selectedRects.contains(rectangle)) {
				g2d.setColor(Color.RED);
				g2d.draw(rectangle);
				dots.add(new Dot((int) rectangle.getX(), (int) rectangle.getY()));
				
				g2d.setColor(Color.WHITE);
				g2d.fill(rectangle);
				g2d.setStroke(thickStroke);
			}
		}
			
		g2d.setStroke(thickStroke);
		
		for(Dot dot : dots) {
			Rectangle2D rectangle = new Rectangle2D.Float((RECTWIDTH + SPACING) * dot.getX(),
					(RECTHEIGHT + SPACING) * dot.getY() , 
					RECTWIDTH, 
					RECTHEIGHT);
			rectangles.add(rectangle);
			pattern.put(rectangle, dot);
			
			if (getSelectedDots().contains(pattern.get(rectangle))) {
				g2d.setColor(Color.RED);
				g2d.draw(rectangle);
				g2d.setColor(Color.DARK_GRAY);
				g2d.fill(rectangle);
			}
			else {
				g2d.setColor(Color.BLACK);
				g2d.draw(rectangle);
			}
				g2d.setColor(Color.DARK_GRAY);
				g2d.fill(rectangle);
			
		
			g2d.setColor(Color.WHITE);
			g2d.fill(rectangle);
		
		
			if(dot.getConnections().size() != 0) {
				ArrayList<Connection> connections = cont.getConnections(dot);
				for(int i = 0 ; i < connections.size() ; i++) { //iterates through connections attached to tiles of most outside for loop
					for(int j = 0 ; j < connections.get(i).getDots().size() ; j++) { //iterates through ith connection to find the other tile
						Dot connectedDot = connections.get(i).getDot(j);
						if(connectedDot != dot) {
								g2d.setColor(Color.BLACK);
								g2d.drawLine(connectedDot.getX(),connectedDot.getY(),
										dot.getX(), dot.getY());
				
						
							}
						}
					}
				}
			}
		}


	public List<Dot> getSelectedDots() {
		List<Dot> selectedDots = new ArrayList<Dot>();
		for(Rectangle2D rectangle : rectangles) {
			if (selectedRects.contains(rectangle)) {
				int x = (int) rectangle.getX();
				int y = (int) rectangle.getY();
				selectedDots.add(new Dot(x,y));
			}
		}
		
		return this.dots;
	}
	
	public int[] getSelectedRectanglePos(int index) {
		int[] out = new int[2];
		out[0] = (int)selectedRects.get(index).getMaxX()/(RECTWIDTH + SPACING);
		out[1] = (int)selectedRects.get(index).getMaxY()/(RECTHEIGHT + SPACING);
		return out;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		doDrawing(g);
	}
	
}
