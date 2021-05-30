/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enum;

/**
 *
 * @author DanGlChris
 */
public enum Operations {
    Addition('+'), Substraction('-'), Multiplication('*'), division('/');
    
    private char sign;
    
    Operations(char sign){
        this.sign = sign;
    }
    
    public char getSign(){
        return sign;
    }
    
}
