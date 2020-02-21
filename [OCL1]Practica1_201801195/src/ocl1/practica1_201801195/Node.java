/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocl1.practica1_201801195;

/**
 *
 * @author jose5
 */
public class Node {

    public Object value;
    public int position;
    public Node leftChild;
    public Node rightChild;
    
public Node(Object value)
{
    this.value = value;
    this.leftChild = this.rightChild = null;
}

}
