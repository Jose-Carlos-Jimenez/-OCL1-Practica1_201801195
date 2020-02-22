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
public class BinaryTree {
    
    Node root;
    String name;
    RegularExpression n;
    int liftCount;
    
    public BinaryTree(RegularExpression n)
    {
        root = null;
        liftCount = 0;
    }
    
    
    
    /*METODO PARA LLENAR EL ARBOL EN PREORDER*/
    public void fill()
    {
        int i = 0;
        while(i < n.nodes.size())
        {
            Node nodo = n.nodes.get(i);
            if(nodo.tipo == Node.type.OPERADOR_UNARIO)
            {
                if(n.nodes.get(i+1).tipo == Node.type.OPERANDO)
                {
                    nodo.leftChild = n.nodes.get(i+1);
                    nodo.tipo = Node.type.OPERANDO;
                    n.nodes.remove(i+1); 
                    i = -1;                    
                }
            }
            else if(nodo.tipo == Node.type.OPERADOR)
            {
                Node izq = n.nodes.get(i+1); 
                Node der = n.nodes.get(i+2);
                if(izq.tipo == Node.type.OPERANDO && der.tipo == Node.type.OPERANDO)
                {
                    nodo.leftChild = izq;
                    nodo.rightChild = der;
                    nodo.tipo = Node.type.OPERANDO;
                    n.nodes.remove(i +1);
                    n.nodes.remove(i + 2);
                    i = -1;
                }
            }
            else if(n.nodes.size() == 1)
            {
                root = nodo;
            }
            i++;
        }
    }
        
    /*METODO PARA SETEAR ANULABLES*/
     public void preOrden(Node n) {

        if (n == null) {
            return;
        }
        else {
            n.setAnulable();
            preOrden(n.leftChild);
            preOrden(n.rightChild);
        }
    }
    
    /*METODO PARA ENUMERAR LAS HOJAS*/
    public void enumerateLeaf(Node n)
    {
        if(n == null )
        {
            return;
        }
        else
        {
            if(n.leftChild == null)
            {
                n.numeroDeHoja = this.liftCount;
                liftCount++;
            }
            enumerateLeaf(n.leftChild);
            enumerateLeaf(n.rightChild);
        }
    }
    
    /*METODO PARA COLOCAR PRIMEROS Y ULTIMOS*/
    public void setFirstAndLast(Node n)
    {
        if(n == null)
        {
            return;
        }
        else
        {
            if(n.tipo == Node.type.OPERADOR_UNARIO)
            {
                n.first = n.leftChild.first;
                n.last = n.rightChild.last;
            }
            else if(n.tipo == Node.type.OPERADOR)
            {
                String union = Integer.toString(n.leftChild.first) + Integer.toString(n.rightChild.first);
                n.first = Integer.parseInt(union);
            }
            else
            {
                n.first = n.numeroDeHoja;
                n.last = n.numeroDeHoja;
            }
            setFirstAndLast(n.leftChild);
            setFirstAndLast(n.rightChild);
        }
    }
}
