/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package directiontestdemo;

import static java.lang.Math.abs;
import java.util.*;
/**
 *
 * @author Frankenstein Nie
 */
public class DirectionTestDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<double[]> allCrd=new ArrayList();
        int n=1;
        double[] A=new double[2];
        double[] B=new double[2];
        double[] C=new double[2];
        A[1]=1;
        A[0]=1;
        B[0]=2;
        B[1]=2;
        C[0]=3;
        C[1]=1;
        
        
        allCrd.add(0, A);
        allCrd.add(1, B);
        allCrd.add(2, C);
        //create an array of current coordinate
        
        double[] curCrd;
        
        curCrd=allCrd.get(1);
        System.out.println(curCrd[0]+ " curcrd"+curCrd[1]);
       
        //create an array of next coordinate
        double[] nxtCrd;
        nxtCrd=allCrd.get(2);
        System.out.println(nxtCrd[0]+ " nxtcrd"+nxtCrd[1]);
        //create an array of last coordinate
        double[] lstCrd;
        lstCrd=allCrd.get(0);
        System.out.println(lstCrd[0]+ " lstcrd"+lstCrd[1]);
        //initialize current cordinate x and y
        double x=curCrd[0];
        double y=curCrd[1];
        
        System.out.println(x+" xy  "+y);
        //initialize next corndinate x and y
        double xn=nxtCrd[0];
        double yn=nxtCrd[1];
                System.out.println(xn+" xnyn  "+yn);

        //initialize last cordinate x and y
        double xl=lstCrd[0];
        double yl=lstCrd[1];
                 System.out.println(xl+" xlyl  "+yl);
 
        Double angle;
        
        //using trgnometry to calculate the angle of the path
       
        double a=Math.sqrt(Math.pow((abs(x-xn)),2)+Math.pow((abs(y-yn)),2));
        
        double b=Math.sqrt(Math.pow((abs(x-xl)),2)+Math.pow((abs(y-yl)),2));
        
        double c=Math.sqrt(Math.pow((abs(xl-xn)),2)+Math.pow((abs(yl-yn)),2));
        
        angle=180-Math.acos((a*a+b*b-c*c)/(2*a*b))*(180/Math.PI);
        
        System.out.println(a+"  "+b+"  "+c);
        
        System.out.println(angle+"angle");
        
        
        //calculate the slope and the intercept of the line crossing last point 
        //and the point now
        Double slope=(y-yl)/(x-xl);
        Double intercept=(xl*y-x*yl)/(xl-x);
        
        System.out.println("slope"+slope);
        System.out.println(intercept+"inter");
        //define if the next point is above or below the line
        Boolean updown=false;
        if (slope*xn+intercept<yn){
            updown=true;
        }else if (slope*xn+intercept>yn){
            updown=false;
        }else angle=0.;
        System.out.println("updowm"+updown);
        //define a boolean as it goes clockwise or counter-clockwise
        int rotDirect=3;
        
        //judge if the rotation is clockwise or counter clockwise
        //if rotDirect=0 rotate clockwise if rotDirect=1 counter-clockwise
        if(slope>0){
            if((y-yl)>0&(x-xl)>0){
                if(updown){
                    rotDirect=1;
                }
                if(!updown){
                    rotDirect=0;
                }
            }if((y-yl)<0&(x-xl)<0){
                if(updown){
                    rotDirect=0;
                }
                if(!updown){
                    rotDirect=1;
                }
            }    
        }
        else if(slope<0){       
            if((y-yl)>0&(x-xl)<0){
                if(updown){
                    rotDirect=0;
                }
                if(!updown){
                    rotDirect=1;
                }
            }if((y-yl)<0&(x-xl)>0){
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
        double[] direction =new double[2];
        direction[0]=angle;
        direction[1]=rotDirect;
        
        System.out.print(direction[0]+"    "+direction[1]); 
    }
    
}
