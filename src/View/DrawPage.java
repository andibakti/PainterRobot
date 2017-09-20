/*
*/
package View;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Timer;

import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import Controller.Controller;
import Model.Dot;

/**
 * Author: Bakti, Andi-Camille
 * Email: andi-camille.bakti@mail.mcgill.ca
 */
public class DrawPage extends JFrame{
	
	private static final long serialVersionUID = 1L;

	
	
	//error messages
	private JLabel errorMessage;
	//interface
	private String error = null;
	private JButton helpButton;
	
	//Visualization
	private Visualizer visualizer;
	
	private List<Dot> dots;
	private boolean show;
	private boolean inMiniDesign=false;
	private GroupLayout myLayout;

	private static final int WIDTH_TILEO_VISUALIZATION = 600;
	private static final int HEIGHT_TILEO_VISUALIZATION = 600;
	
	 private static final int WIDTH_DESIGN_VISUALIZATION = 800;
	 private static final int HEIGHT_DESIGN_VISUALIZATION = 800;
	
	public DrawPage(String initialGame) {
		initComponents(initialGame);
		refreshData();
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
	
	public void initComponents(String initialGame) {
		
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		
		helpButton = new JButton();
		helpButton.setIcon(UIManager.getIcon("OptionPane.questionIcon"));
		//helpButton.setText("HELP");
		helpButton.setToolTipText("Click here for Help.");
		helpButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				helpButtonActionPerformed(evt);
			}
		});
		
		
	    visualizer = new Visualizer(new ArrayList<Dot>());
		visualizer.setMinimumSize(new Dimension(WIDTH_TILEO_VISUALIZATION, 
				HEIGHT_TILEO_VISUALIZATION));
		
		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("PainterRobot");
		
		
		JSeparator verticalLine = new JSeparator();
		
		//layout
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(errorMessage)
						.addComponent(visualizer))
				.addGroup(layout.createParallelGroup()
						.addComponent(verticalLine))
				.addGroup(layout.createParallelGroup()
						.addComponent(helpButton)));
										

		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] 
				{helpButton});
						
				
		layout.setVerticalGroup(
				layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(visualizer))
				.addGroup(layout.createSequentialGroup()
							.addComponent(verticalLine))
				.addGroup(layout.createSequentialGroup()
						.addComponent(errorMessage)
						.addComponent(helpButton)));
		myLayout=layout;
		
		
		pack();
	}
	
	/**
	 * @param evt
	 */
	protected void helpButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}

	public void refreshData() {
		// error
		if(error != null){
			 errorMessage.setText(error);
			 errorMessage.setVisible(true);
		 }else{
			 errorMessage.setVisible(false);
		 }		Controller cont = new Controller();
		System.out.println("refreshData" + show);
		
		
		  visualizer.setMatrix(new ArrayList<Dot>());
		  
		
		pack();
	}
}
