/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocl1.practica1_201801195;

import java.util.ArrayList;

/**
 *
 * @author Jose
 */
public class RegularExpression {
    public ArrayList<Node> nodes;
    public String name;
    
    public RegularExpression(String name){
        nodes = new ArrayList();
        this.name = name;
    }
}