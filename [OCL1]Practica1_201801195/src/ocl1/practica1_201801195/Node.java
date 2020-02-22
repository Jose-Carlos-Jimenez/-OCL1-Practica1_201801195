/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocl1.practica1_201801195;

/**
 *
 * @author Jose
 */
public class Node {

    public Token value;
    public Node leftChild;
    public Node rightChild;
    public type tipo;
    public int numeroDeHoja;
    public int first, last;
    public boolean anulable;
    public enum type{
        OPERADOR, OPERANDO, OPERADOR_UNARIO;
    }
    
    public Node(Token n)
    {
        this.value = n;
        this.leftChild = this.rightChild = null;
        if(n.t == Token.type.ASTERISCO )
        {
            this.tipo = Node.type.OPERADOR_UNARIO;
        }
        else if(n.t == Token.type.CERRADURA_BINARIA)
        {
            this.tipo = Node.type.OPERADOR_UNARIO;
        }
        else if(n.t == Token.type.CERRADURA_POSITIVA)
        {
            this.tipo = Node.type.OPERADOR_UNARIO;
        }
        else if(n.t == Token.type.PLECA)
        {
            this.tipo = Node.type.OPERADOR;
        }
        else if(n.t == Token.type.CONCATENACION)
        {
            this.tipo = Node.type.OPERADOR;
        }
        else
        {
            this.tipo = Node.type.OPERANDO;
        }   
    }
    
    void setAnulable()
    {
        if(this.value.t == Token.type.ASTERISCO )
        {
            this.anulable = true;
        }
        else if(this.value.t == Token.type.CERRADURA_BINARIA)
        {
            this.anulable = true;
        }
        else if(this.value.t == Token.type.CERRADURA_POSITIVA)
        {
            this.anulable = false;
        }
        else if(this.value.t == Token.type.PLECA)
        {
            this.anulable = this.leftChild.anulable || this.rightChild.anulable;                
        }
        else if(this.value.t == Token.type.CONCATENACION)
        {
            if(this.leftChild.anulable && this.rightChild.anulable)
            {
                this.anulable = true;
            }
            else
            {
                this.anulable = false;
            }
        }
        else
        {
            this.anulable = false;
        }
    }
}
