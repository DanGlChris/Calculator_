package Enum;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DanGlChris
 */
public enum Roman_Numeral_Sign {
    I(1, true), V(5, false), X(10, true),
    L(50, false), C(100, true), D(500, false), M(1000, true);
    
    private int value;
    private boolean unity;
    
    Roman_Numeral_Sign(int value, boolean unity){
        this.value = value;
        this.unity = unity;
    }
    public int getvalue(){
        return value;
    }
    public boolean isUnity(){
        return unity;
    }
    /**
     * 
     * @param entry
     * @return 
     */
    public static Roman_Numeral_Sign[] convert_to_numeral(char[] entry){
        Roman_Numeral_Sign[] numerals = new Roman_Numeral_Sign[entry.length];
        for (int j = 0; j < entry.length; j++) {
            for (int i = 0; i < Roman_Numeral_Sign.values().length; i++) {
                if(String.valueOf(entry[j]).toUpperCase().equals(String.valueOf(Roman_Numeral_Sign.values()[i]))){
                    numerals[j] = Roman_Numeral_Sign.values()[i];
                    i = Roman_Numeral_Sign.values().length;
                }else{
                    if (i == Roman_Numeral_Sign.values().length-1 &&
                            !String.valueOf(entry[j]).toUpperCase().equals(String.valueOf(Roman_Numeral_Sign.values()[i]))) return null;
                }
            }
        } return numerals;
    }
    private static int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    private static String[] romanLiterals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    public static String Roman_value_of(int num){
        String roman_value = "";
        for(int i=0; i<values.length; i++) {
            while(num >= values[i]) {
                num -= values[i];
                roman_value += romanLiterals[i];
            }
        }
        return roman_value;
    }
    
}
