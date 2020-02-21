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
public class Token {
    
    public String lexeme;
    public type t ;
    public int row, column;
    
    public enum type{
        DOS_PUNTOS,PUNTO_Y_COMA,PLECA,PORCENTAJE,LLAVE_IZQ, LLAVE_DER, ASTERISCO,CERRADURA_POSITIVA,CONCATENACION,VIRGULILLA,
        COMA,CERRADURA_BINARIA,FIN_CADENA,DIAGONAL, SIMBOLO, ARROW, ERROR, SET, ID, CADENA, NUMERO, COMENTARIO;
    }
    
    public Token(Token.type type ,String lexeme, int row, int column)
    {
        this.t = type;
        this.lexeme = lexeme;
        this.row = row;
        this. column = column;
    }
    
}
