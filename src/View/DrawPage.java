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
import javax.swing.JOptionPane;
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
	private JButton sendButton;
	
	//Visualization
	private Visualizer visualizer;
	
	private ArrayList<Dot> dots;
	private boolean show;
	private boolean inMiniDesign=false;
	private GroupLayout myLayout;



	private JFrame frame;

	private static final int WIDTH_TILEO_VISUALIZATION = 600;
	private static final int HEIGHT_TILEO_VISUALIZATION = 600;
	
	 private static final int WIDTH_DESIGN_VISUALIZATION = 800;
	 private static final int HEIGHT_DESIGN_VISUALIZATION = 800;
	
	public DrawPage(String initialGame) {
		initComponents(initialGame);
		refreshData();
	}
	
	
	
	public void initComponents(String initialGame) {
		
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		
		sendButton = new JButton();
		sendButton.setText("SEND");
		//helpButton.setText("HELP");
		sendButton.setToolTipText("Click here for Help.");
		sendButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sendButtonActionPerformed(evt);
			}
		});
		
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    		File file = new File("Dots.txt");
		    		file.delete();
		    		System.exit(0);
		        
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
						.addComponent(sendButton)));
										

		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] 
				{sendButton});
						
				
		layout.setVerticalGroup(
				layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(visualizer))
				.addGroup(layout.createSequentialGroup()
							.addComponent(verticalLine))
				.addGroup(layout.createSequentialGroup()
						.addComponent(errorMessage)
						.addComponent(sendButton)));
		myLayout=layout;
		
		pack();
	}
	
	/**
	 * @param evt
	 */
	protected void sendButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub 
		this.dots = visualizer.chosenDots;
		Controller cont = new Controller();
		cont.sendDots(dots);
		refreshData();
		 
	}

	public void refreshData() {
		// error
		if(error != null){
			 errorMessage.setText(error);
			 errorMessage.setVisible(true);
		 }else{
			 errorMessage.setVisible(false);
		 }		
		Controller cont = new Controller();
		System.out.println("refreshData" + show);
		
		//if(visualizer.getSelectedDots() != null){ System.out.println(visualizer.getSelectedDots().get(0).getX());}
		
		  visualizer.setMatrix(new ArrayList<Dot>());
		  
		
		pack();
	}
}
