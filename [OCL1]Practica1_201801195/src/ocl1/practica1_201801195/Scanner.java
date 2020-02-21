/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocl1.practica1_201801195;

import java.util.ArrayList;

/**
 *
 * @author jose5
 */
public class Scanner {
    private ArrayList<Token> output;
    private ArrayList<Token> errors;
    private int state;
    private int caso;
    private String auxLex;
    private int row;
    private int column;
    
    public Scanner()
    {
        
    }
    
    public void Read(String entrada)
    {
        entrada = entrada+"#";
        this.output = new ArrayList();
        this.auxLex = "";
        this.state = 1;
        this.caso = 0;
        this.row = 1;
        this.column = 0;
        Character c = ' ';
        
        
        for(int i = 0; i < entrada.length(); i++)
        {
            c = entrada.charAt(i);
            column++;
            
            switch(this.state)
            {
                case 1:
                    if(c.compareTo('/')==0)
                    {
                        this.caso = 1;
                        this.state = 2;
                        this.auxLex += c;
                    }
                    else if (c.compareTo('<')==0){
                        this.caso = 2;
                        this.state = 2;
                        this.auxLex += c;
                    }
                    else if (c.compareTo('"')==0){
                        this.caso = 3;
                        this.state = 2;
                    }
                    else if(c.compareTo('-')==0){
                        this.caso = 4;
                        this.state = 2;
                        this.auxLex += c;
                    }
                    else if(Character.isDigit(c)){
                        this.caso =5;
                        this.state = 2;
                        this.auxLex += c;
                    }
                    else if (Character.isLetter(c)){
                        this.caso = 6;
                        this.state = 2;
                        this.auxLex += c;
                    }
                    else if (c.compareTo(':')==0){
                        this.auxLex +=c;
                        addToken(Token.type.DOS_PUNTOS,row, column);
                    }
                    else if (c.compareTo(';')==0){
                        this.auxLex +=c;
                        addToken(Token.type.PUNTO_Y_COMA,row, column);
                    }
                    else if (c.compareTo('|')==0){
                        this.auxLex +=c;
                        addToken(Token.type.PLECA,row, column);
                    }
                    else if (c.compareTo('%')==0){
                        this.auxLex +=c;
                        addToken(Token.type.PORCENTAJE,row, column);
                    }
                    else if (c.compareTo('{')==0){
                        this.auxLex +=c;
                        addToken(Token.type.LLAVE_IZQ,row, column);
                    }
                    else if (c.compareTo('}')==0){
                        this.auxLex +=c;
                        addToken(Token.type.LLAVE_DER,row, column);
                    }
                    else if (c.compareTo('*')==0){
                        this.auxLex +=c;
                        addToken(Token.type.ASTERISCO,row, column);
                    }
                    else if (c.compareTo('+')==0){
                        this.auxLex +=c;
                        addToken(Token.type.CERRADURA_POSITIVA,row, column);
                    }
                    else if (c.compareTo('.')==0){
                        this.auxLex +=c;
                        addToken(Token.type.CONCATENACION,row, column);
                    }
                    else if (c.compareTo('~')==0){
                        this.auxLex +=c;
                        addToken(Token.type.VIRGULILLA,row, column);
                    }
                    else if (c.compareTo(',')==0){
                        this.auxLex +=c;
                        addToken(Token.type.COMA,row, column);
                    }
                    else if (c.compareTo('?')==0){
                        this.auxLex +=c;
                        addToken(Token.type.CERRADURA_BINARIA,row, column);
                    }
                    else if (c.compareTo('\n')==0){
                        this.row ++;
                        this.column = 0;
                    }
                    else if(Character.isWhitespace(c)){
                        this.state = 1;
                        this.auxLex = "";
                    }
                    else if (c.compareTo('#') == 0 && i == entrada.length()-1)
                    {
                        System.out.println("Se realizo el analisis con exito");
                    }
                    else if(((int)c >= 33 && (int)c <= 47) || ((int)c >=  58 && (int)c <= 64) || ((int)c >= 91 && (int)c <=96) || ((int) c>=123 && (int)c <= 131))
                    {
                        this.auxLex += c;
                        addToken(Token.type.SIMBOLO, row, column);
                    }
                    else{
                        this.auxLex += c;
                        addError(row, column);
                    }
                break;
                
                case 2:
                    switch (this.caso) {
                        case 1:
                            if (c.compareTo('/') == 0) {
                                this.caso = 1;
                                this.state = 3;
                                this.auxLex += c;
                            }
                            else
                            {
                                this.auxLex += c;
                                addError(row,column-1);
                                i--;
                            }
                            break;
                            
                        case 2:
                            if (c.compareTo('!') == 0) {
                                this.caso = 2;
                                this.state = 3;
                                this.auxLex += c;
                            }
                            else
                            {
                                this.auxLex += c;
                                addError(row,column-1);
                                i--;
                            }
                            
                            break;

                        case 3:
                            if (c.compareTo('"') == 0) {
                                addToken(Token.type.CADENA, row, column);
                            }
                            else
                            {
                                if (c.compareTo('\n') == 0) {

                                    row++;
                                    column = 0;
                                    this.auxLex += "<br>";

                                } else if (c.compareTo('#') == 0) {
                                    
                                    caso = 0;
                                    state = 1;
                                    addError(row, column - 1);
                                    i--;
                                    
                                } else {
                                    
                                    this.auxLex += c;
                                    
                                }

                            }

                            break;

                        case 4:
                            if (c.compareTo('>') == 0) {
                                this.auxLex += c;
                                addToken(Token.type.ARROW, row, column);
                            }
                            else
                            {
                                this.auxLex += c;
                                addError(row, column-1);
                                i--;
                            }
                        
                            break;

                        case 5:
                            if (Character.isDigit(c)) {
                                this.auxLex += c;
                            }
                            else
                            {
                                addToken(Token.type.NUMERO, row, column);
                                i--;
                            }
                            break;

                        case 6:
                            if (Character.isLetter(c)||Character.isDigit(c)) {
                                this.auxLex += c;
                            }
                            else
                            {
                                addId(row, column);
                                i--;
                            }
                            
                            break;
                    }
                    
                break;
                
                case 3:
                    
                    switch (this.caso) {
                        case 1:
                            if (c.compareTo('\n') == 0) {
                                addToken(Token.type.COMENTARIO, row, column);
                                row++;
                                column= 0;
                            }
                            else if (c.compareTo('#') == 0) {
                                caso = 0;
                                state = 1;
                                addError(row, column-1);
                                i--;
                            }
                            else
                            {
                                this.auxLex += c;
                            }
                                
                            break;
                            
                        case 2:
                            if (c.compareTo('!') == 0) {
                                this.caso = 2;
                                this.state = 4;
                                this.auxLex += c;
                            }
                            else
                            {
                                this.auxLex += c;
                            }
                            
                            break;
                    }
                    
                break;
                
                case 4:
                    
                    switch (this.caso) {
                            
                        case 2:
                            if (c.compareTo('>') == 0) {
                                this.auxLex += c;
                                addToken(Token.type.COMENTARIO, row, column);
                            }
                            else if (c.compareTo('#') == 0) {
                                caso = 0;
                                state = 1;
                                addError(row, column-1);
                                i--;
                            }
                            else
                            {
                                this.auxLex += c;
                                state = 3;
                                caso = 2;
                                i--;
                            }
                            
                            break;
                    }
                    
                break;
                    
                    
            }
        }
        
    }
    
    public void addToken(Token.type type, int row, int column) {
        this.output.add(new Token(type, auxLex, row, column));
        this.auxLex = "";
        this.state = 0;
        this.caso = 0;
    }
    
    public void addError(int row, int column) {
        this.errors.add(new Token(Token.type.ERROR, auxLex, row, column));
        this.auxLex = "";
        this.state = 1;
        this.caso = 0;
    }
    
    public void addId(int row, int column) {
        if(this.auxLex.equals("CONJ"))
        {
            addToken(Token.type.SET,row, column);
        }
        else
        {
            addToken(Token.type.ID, row, column);
        }
    }
    
    public ArrayList getSet(){
        
        ArrayList<Set> groups = new ArrayList();
        
        for(int i = 0; i<this.output.size(); i++)
        {
            if(this.output.get(i).t ==Token.type.ARROW)
            {
                if(this.output.get(i-3).t != Token.type.SET)
                {
                    Set pattern = new Set();
                    pattern.id = this.output.get(i-1).lexeme;
                    while(this.output.get(i).t != Token.type.PUNTO_Y_COMA)
                    {
                        i++;
                        pattern.set.add(this.output.get(i).lexeme);
                    }
                    
                    groups.add(pattern);
                }
            }
            
            if(this.output.get(i).t == Token.type.PORCENTAJE)
            {
                return groups;
            }
        }
        return groups;
    }
    
}
