/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printerrobot;
import static java.lang.Math.abs;
import java.util.*;
import static java.lang.Math.abs;
/**
 *
 * @author Frankenstein Nie
 */
public class Direction {
    double degree;
    Direction(){
    }    
    
    /**
     * get a coordinate array from the list of array and 
     * get the direction of robot's rotation in term of degrees
     * @param n the number of step now
     * @param allCrd the arraylist of all coordinates in the path
     * @return rotation angle in degree
     */
    public double[] getDegree(ArrayList<double[]> allCrd,int n ){
        
        //add the start point to the arraylist of coordinates
        allCrd.add(0, startPnt);
        //create an array of current coordinate
        double[] curCrd;
        
        curCrd=allCrd.get(n);
       
        //create an array of next coordinate
        double[] nxtCrd;
        nxtCrd=allCrd.get(n+1);
        
        //create an array of last coordinate
        double[] lstCrd;
        lstCrd=allCrd.get(n-1);
        
        //initialize current cordinate x and y
        double x=curCrd[0];
        double y=curCrd[1];
        
        //initialize next corndinate x and y
        double xn=nxtCrd[0];
        double yn=nxtCrd[1];
        
        //initialize last cordinate x and y
        double xl=lstCrd[0];
        double yl=lstCrd[1];
          
        Double angle;
        
        //using trgnometry to calculate the angle of the path
       
        double a=Math.sqrt(Math.pow((abs(x-xn)),2)+Math.pow((abs(y-yn)),2));
        
        double b=Math.sqrt(Math.pow((abs(x-xl)),2)+Math.pow((abs(y-yl)),2));
        
        double c=Math.sqrt(Math.pow((abs(xl-xn)),2)+Math.pow((abs(yl-yn)),2));
        
        angle=180-Math.acos((a*a+b*b-c*c)/(2*a*b))*(180/Math.PI);
        
        //calculate the slope and the intercept of the line crossing last point 
        //and the point now
        Double slope=(y-yl)/(x-xl);
        Double intercept=(xl*y-x*yl)/(xl-x);
        
        //define if the next point is above or below the line
        Boolean updown =true;
        if (slope*xn+intercept<yn){
            updown=true;
        }else if (slope*xn+intercept>yn){
            updown=false;
        }else angle=0.;
        
        //define a boolean as it goes clockwise or counter-clockwise
        int rotDirect=3;
        
        //judge if the rotation is clockwise or counter clockwise
        //if rotDirect=0 rotate clockwise if rotDirect=1 counter-clockwise
        if(slope>0){
            if((yn-y)>0&(xn-x)>0){
                if(updown){
                    rotDirect=1;
                }
                if(!updown){
                    rotDirect=0;
                }
            }if((yn-y)<0&(xn-x)<0){
                if(updown){
                    rotDirect=0;
                }
                if(!updown){
                    rotDirect=1;
                }
            }    
        }
        else if(slope<0){       
            if((yn-y)>0&(xn-x)<0){
                if(updown){
                    rotDirect=0;
                }
                if(!updown){
                    rotDirect=1;
                }
            }if((yn-y)<0&(xn-x)>0){
                if(updown){
                    rotDirect=1;
                }
                if(!updown){
                    rotDirect=0;
                }
            }    
        }
        
        if(x-xl==0){
            if(y-yl>0){
                if(xn-x>0){
                    rotDirect=1;
                }else if(xn-x<0){
                    rotDirect=0;
                }
            }
            else if(y-yl<0){
                if(xn-x>0){
                    rotDirect=0;
                }else if(xn-x<0){
                    rotDirect=1;
                }    
            }    
            
        }else if(y-yl==0){
            if(x-xl>0){
                if(yn-y>0){
                    rotDirect=1;
                }else if(xn-x<0){
                    rotDirect=0;
                }
            }
            else if(x-xl<0){
                if(yn-y>0){
                    rotDirect=0;
                }else if(yn-y<0){
                    rotDirect=1;
                }    
            }
        }
        
        //output a double array contaning 3 variables 
        //the angle, rotation direction and the distance the next step
        double[] direction =new double[2];
        direction[0]=angle;
        direction[1]=rotDirect;
        direction[2]=a;
        
        
        return direction;
    }   
    
    /**
     * Adding starting point and the end points and (0,1) into the arraylist 
     * in order to reset the direction
     * @param Al the arraylist of coordinates of dot in the path
     * @return the full list of coordinates
     */
    public ArrayList<double[]> completeList(ArrayList<double[]> al){
        //set a starting point of corrdinate (0,0)
        double[] startPnt=new double[1];
        startPnt[0]=0;
        startPnt[1]=0;
        
        //add the start point to the arraylist of coordinates
        al.add(0, startPnt);
        
        //set the end point of coordinate (3,0)
        double[] endPnt=new double[1];
        endPnt[0]=3;
        endPnt[1]=0;
      
        al.add(endPnt);//add the end point to the array list
        
        //set a point (3,1) for resetting direction
        double[] directionPnt=new double[1];
        directionPnt[0]=3;
        directionPnt[1]=1;
        
        al.add(directionPnt);//add the point to the array list
        
        ArrayList allCrd=al;
        
        return allCrd;
    }
    
    
    
    /**
     * First step going from origin to the first step
     * @param allCrd 
     * @return the direction and the distance of next step
     */
    public double[] firstStepDrt(ArrayList<double[]> allCrd){
 
        //create an array of the coordinate of first step
        double[] nextPnt;
        
        nextPnt=allCrd.get(1);
        
        //define the coordinate of start point
        double x0=0;
        double y0=0;
        
        double xn=nextPnt[0];
        double yn=nextPnt[1];
        
        double angle;
        
        if(yn-y0==0){
            angle=90;
        }
        else{
            angle=Math.atan(yn/xn);
        }
        
        double distance=Math.sqrt(Math.pow((abs(xn)),2)+Math.pow((abs(yn)),2));
        double rotDrt=1;
        double[] firstDirection=new double[2];
        firstDirection[0]=angle;
        firstDirection[1]=rotDrt;
        firstDirection[2]=distance;
        
        return firstDirection;
              
    }    
    
    
        
}
