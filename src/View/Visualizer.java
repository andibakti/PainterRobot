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
	private List<Rectangle2D> selectedDots;
	
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
		selectedDots = new ArrayList<Rectangle2D>();
		
		addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				int x = e.getX();
				int y = e.getY();
				for (Rectangle2D rectangle : rectangles){
					if (rectangle.contains(x, y)) {
						if (selectedDots.contains(rectangle)) {
							selectedDots.remove(rectangle);
						}
						else {
							selectedDots.add(rectangle);
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
		selectedDots.clear();
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
			if (selectedDots.contains(rectangle)) {
				g2d.setColor(Color.RED);
				g2d.draw(rectangle);
				
				g2d.setColor(Color.WHITE);
				g2d.fill(rectangle);
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
			
			if (getSelectedTiles().contains(pattern.get(rectangle))) {
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


	public List<Dot> getSelectedTiles() {
		List<Dot> selectedDots = new ArrayList<Dot>();
		for (Rectangle2D rectangle : this.selectedDots) {
			selectedDots.add(pattern.get(rectangle));
		}
		
		return null;
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		doDrawing(g);
	}
	
}
