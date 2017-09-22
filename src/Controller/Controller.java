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
import com.fazecast.jSerialComm.SerialPort;


import Model.Connection;
import Model.Dot;
import Model.PainterRobot;

/**
 * Author: Bakti, Andi-Camille
 * Email: andi-camille.bakti@mail.mcgill.ca
 */
public class Controller {
	
	private ArrayList<Dot> dots;
	static SerialPort chosenPort;
	private String port = "COM3";
	

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
		
		ArrayList<double[]> data;
		try {
			data = readFromFile();
//			for(int i=0; i<data.size(); i++){
//				for(int j = 0; j<2; j++){
//					System.out.print( data.get(i)[j]);
//					System.out.print(",");
//				}
//				System.out.println();
//			}
			String isConnected = "Connect";
			ArrayList<String> portList = new ArrayList<String>();
			SerialPort[] portNames = SerialPort.getCommPorts();
			
			
			if(isConnected.equals("Connect")) {
				// attempt to connect to the serial port
				chosenPort = SerialPort.getCommPort(port);
				chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
				if(chosenPort.openPort()) {
					isConnected = "Disconnect";
					portList.setEnabled(false);
					
					// create a new thread for sending data to the arduino
					Thread thread = new Thread(){
						@Override public void run() {
							// wait after connecting, so the bootloader can finish
							try {Thread.sleep(100); } catch(Exception e) {}

							// enter an infinite loop that sends text to the arduino
							PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
							while(true) {
								output.print(new SimpleDateFormat("hh:mm:ss a     MMMMMMM dd, yyyy").format(new Date()));
								output.flush();
								try {Thread.sleep(100); } catch(Exception e) {}
							}
						}
					};
					thread.start();
				}
			} else {
				// disconnect from the serial port
				chosenPort.closePort();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Send to back-end
		
		wasSet = true;
		return wasSet;
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
