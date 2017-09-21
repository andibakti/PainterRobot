/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printerrobot;
import java.util.*;
/**
 *
 * @author Frankenstein Nie
 */
public class GetCoord {
    GetCoord(){
        
    }

    /**
     * collect the coordinates of the points and store them in an array list
     * @param cordinate the coordinate of the point
     * @return the array list of the coordinates
     */
    public ArrayList<int[]> storeCrd(int[] coordinate,int index){
        //create an array list of integer array coordinate
        ArrayList<int[]> allCrd= new ArrayList<int[]>();
        
        allCrd.add(index,coordinate);
        
        return allCrd;
    }
}