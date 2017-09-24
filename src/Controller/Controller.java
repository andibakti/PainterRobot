/*
*/
package Controller;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import com.fazecast.jSerialComm.SerialPort;


import Model.Connection;
import Model.Dot;
import Model.PainterRobot;
import Controller.Direction;

/**	
 * Author: Bakti, Andi-Camille
 * Email: andi-camille.bakti@mail.mcgill.ca
 */
public class Controller {
	
	private ArrayList<Dot> dots;
	static SerialPort chosenPort;
	private String port = "COM3";
	private Direction direction = new Direction();
	private final double DISTANCE_TO_TIME =  3;
	

	/**
	 * @return
	 */
	public ArrayList<Dot> getAllDots() {
		return this.dots;
	}
	
	//Send the coordinates of the dots to the back-end to translate
	//data into rotations and movements that the robot has to perform.
	public boolean sendDots(ArrayList<Dot> dots){
		boolean wasSet = false;
		this.dots = dots;
		
		
		
		ArrayList<double[]> path;
		double[][] data;
		String[][] commands;
		
		try {
			//Get path
			path = readFromFile();
			
			data = new double[path.size()][2];
			commands = new String[path.size()][2];
			data = processPath(path);
			
			for(int i = 0; i<data.length; i++){
				for(int j = 0; j<2; j++){
					//convert distance into times
					if(j == 2){
						commands[i][j] = String.valueOf(data[i][j] * DISTANCE_TO_TIME );
					}else{
						System.out.println("j:" + j + ", i :" + i);
						System.out.println(data[i][j]);
						commands[i][j] = String.valueOf(data[i][j]);
					}
					
				}
			}
		
			String state = "Connect";		
			if(state.equals("Connect")) {
				// attempt to connect to the serial port
				chosenPort = SerialPort.getCommPort(port);
				chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
				if(chosenPort.openPort()) {
					state = "Disconnect";
					// wait after connecting, so the bootloader can finish
					try {Thread.sleep(100); } catch(Exception e) {}
					PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
					
					for(int j = 0; j<commands.length; j++){
						for(int i = 0; i<2; i++){
							if(i== 0 ){
								output.print(commands[j][i] + ",");
							}else if(i == 1){
								output.print(commands[j][i] + ":");
							}else{
								output.print(commands[j][i] + ";");
							}
						
						}
					}
					output.flush();
				}
			} else {
				// disconnect from the serial port
				chosenPort.closePort();
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wasSet = true;
		return wasSet;
	}
	
	//This function processes the path into distance and degree needed
	public double[][] processPath(ArrayList<double[]> path){
		//Initialization
		
		path = direction.completeList(path);
		System.out.println("pathsize" + path.size());
		path.remove(path.size()-1);
		path.remove(path.size()-1);
		path.remove(path.size()-1);
		for(int i = 0 ; i<path.size(); i++){
			for(int j = 0 ; j<2; j++){
				System.out.print(path.get(i)[j]);
			}
			System.out.println("");
		}
		ArrayList<double[]> newPath = path;
		
		double[][] data = new double[newPath.size()][2];
		
		data[0] = direction.firstStepDrt(newPath);
		
		for(int i = 1; i<newPath.size(); i++){
			data[i] = direction.getDegree(newPath, i);
		}
		
		return data;		
	}

	//Read the "Dots.txt" file, puts the information in an array
	//and then once finished it deletes the file
	//Input: none
	//Output: ArrayList of double of the path
	public ArrayList<double[]> readFromFile() throws Exception{
		
		ArrayList<double[]> dots = new ArrayList<double[]>();
 		BufferedReader br = new BufferedReader(new FileReader("Dots.txt"));
		try {
		    String line = br.readLine();
		    while (line != null) {
		        String[] temp = line.split(",");
		        double[] doubleTemp = new double[2];
		        doubleTemp[0] = Double.parseDouble(temp[0]);
		        doubleTemp[1] = Double.parseDouble(temp[1]);
		        dots.add(doubleTemp);
		        line = br.readLine();
		    }
		} catch(IOException e) {
		   
		}finally{
			//Deleting the file 
			
			 br.close();
			 File file = new File("Dots.txt");
			 file.delete();
		}
		return dots;
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
