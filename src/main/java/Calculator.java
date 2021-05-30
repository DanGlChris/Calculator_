
import Enum.Operations;
import Enum.Roman_Numeral_Sign;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DanGlChris
 */
public class Calculator {

    /**
     * @param args the command line arguments
     */
    private static String[] inputs = new String[3]; // {элемент a, операция, элемент b}
    
    public static void main(String[] args) {     
        char[] entry = "MDCCCXXVI".toCharArray();
        
        Roman_Numeral_Sign[] Roman_value_a = Roman_Numeral_Sign.convert_to_numeral(entry);
        
        Roman_numeral a = new Roman_numeral(Roman_value_a);
        //System.out.println(Roman_value_a[0].isUnity());
        System.out.println(String.copyValueOf(entry) + " = " +a.getValue());
        
        /*Scanner scanner = new Scanner(System.in);
        boolean test = false;
        
        while(!test){
            System.out.println("Input:");
            String entry = scanner.next();
            test = Verify_Entry(entry);
            test = Verify_Element(inputs);            
        }*/
    }
    /**
     * Функция помогает узнать если вход содержает 
     * 3 элемент (a sign b)
     * @param entry
     * @return 
     */
    private static boolean Verify_Entry(String entry){
        entry = entry.replaceAll(" ", "");
        
        char sign = '#';
	for(Operations operation :  Operations.values()) {
	    if (entry.contains(String.valueOf(operation.getSign()))){
                sign = operation.getSign();
                break;
            }
	}
        if(sign!='#'){
            String[] elements = entry.split(String.valueOf("\\" + sign)); // "\\" Чтобы избегать PatternSyntaxException
            if(elements.length==2){
                inputs[0] = elements[0];    // элемент a
                inputs[1] = String.valueOf(sign);   //операция
                inputs[2] = elements[1];    //элемент b
                return true;
            }
        }
        return false;
    }
    
    private static int int_value_a, int_value_b;        
    private static boolean test_roman, roman_value;
    /**
     * Число от 1 до 10 только
     * Не может считат арабскии и римскии одновремено
     * @param entry Массив из 3 числа
     * @return 
     */
    private static boolean Verify_Element(String[] entry){
        test_roman = false;        
        roman_value = false;
        int_value_a = 0;
        int_value_b = 0;
        
        Roman_Numeral_Sign[] Roman_value_a = Roman_Numeral_Sign.convert_to_numeral(entry[0].toCharArray());
        Roman_Numeral_Sign[] Roman_value_b = Roman_Numeral_Sign.convert_to_numeral(entry[2].toCharArray());
        if(Roman_value_a!=null && Roman_value_a!=null){
            try {                
                Roman_numeral a = new Roman_numeral(Roman_value_a);
                Roman_numeral b = new Roman_numeral(Roman_value_b);
                System.out.println("That work! " + a.getValue()+ " " +b.getValue());
            } catch (Exception e) {
                System.err.println("Errrrrrrrrrrrrorrr");
            }
            return true;
        }else{    //Значит что один или два из вдух свойства не соблюдается
            System.out.println("Romans lost the war...");
            try{                
                int_value_a = Integer.parseInt(entry[0]);
                int_value_b = Integer.parseInt(entry[2]);
                if(int_value_a>10 || int_value_a<0){
                    System.out.println("But there are more than 10 persons");                    
                    return false;
                }
                if(int_value_b>10 || int_value_b<0){
                    System.out.println("But there are more than 10 persons");       
                    return false;
                }
                
                return true;
                
            }catch (NumberFormatException ex){
                return false;
            }
            
        }
    }
    
}
