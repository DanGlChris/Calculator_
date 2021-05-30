
import Enum.Roman_Numeral_Sign;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DanGlChris
 */
public class Roman_numeral {
    private Roman_Numeral_Sign[] entry;
    private int value;
    
    private int min_unity = 1;
    private int tour = 0, temp_unity = 0;
    
    
    
    /**
     * 
     * @param entry 
     */
    public Roman_numeral(Roman_Numeral_Sign[] entry)throws Exception{
        for(int i = entry.length-1; i >=0 ; i--) {            
            if(entry[i].isUnity()){
                if(i-1>=0 && entry[i].ordinal()>1 && entry[i-1].ordinal() == (entry[i].ordinal()-2)){   //U(n-2)--U(n) (IX:9, XC:90, CM:900)
                    temp_unity = entry[i].getvalue()-entry[i-1].getvalue();
                    value+= temp_unity;
                    if(temp_unity<min_unity){ throw new Exception("Не правилно вход");}
                    else min_unity = entry[i].getvalue();
                    
                    i-=1;
                    continue;
                    
                }else if(i-1>=0 && entry[i-1].ordinal()>1 && entry[i-1].ordinal() == (entry[i].ordinal()-1)){ //U(n-1)--U(n) (VX, LC)
                    throw new Exception("Не правилно вход");
                    
                }else if(i-1>=0 && entry[i-1].ordinal()>0 && entry[i-1].ordinal() > (entry[i].ordinal())){ //U(n+1, 2, 3)--U(n) (MI, DC, CX, DX)
                    temp_unity = entry[i-1].getvalue()+entry[i].getvalue();
                    value+= temp_unity;
                    if(temp_unity<min_unity || temp_unity%min_unity!=0) throw new Exception("Не правилно вход");
                    else min_unity = entry[i-1].getvalue();
                    
                    i-=1;tour =0;
                    
                }else if((i-1)>=0 && entry[i-1].ordinal() == entry[i].ordinal()){ // U(n)--U(n) (MMM, XX, II, CCC)
                    tour =1;
                    while(entry[i-1].ordinal() == entry[i].ordinal()){
                        tour++;
                        if(tour>3){ throw new Exception("Не правилно вход");}// 
                        else if(i==1) {
                            i=i-1; break;
                        }
                        else i=i-1;
                    }
                    temp_unity= entry[i].getvalue()*(tour);
                    value+=temp_unity;
                    if(temp_unity<min_unity) throw new Exception("Не правилно вход");
                    else min_unity = 5*Roman_Numeral_Sign.values()[entry[i+1].ordinal()].getvalue();
                }else if(i==0){
                    temp_unity = entry[i].getvalue();
                    value+= temp_unity;
                    if(temp_unity<min_unity) throw new Exception("Не правилно вход");
                    else min_unity = temp_unity;
                }   
            }else{
                tour = 0;
                if(i-1>=0 && entry[i-1].ordinal() == (entry[i].ordinal()-1)){                    
                    temp_unity=entry[i].getvalue()-entry[i-1].getvalue();
                    value+= temp_unity;
                    if(temp_unity <min_unity) throw new Exception("Не правилно вход");
                    else min_unity = entry[i].getvalue()*2;
                    i--;
                }else if(i-1>=0 && entry[i-1].ordinal() != (entry[i].ordinal()-1)){
                    temp_unity = entry[i].getvalue();
                    value+= temp_unity;
                    if(temp_unity<min_unity) throw new Exception("Не правилно вход");
                    else min_unity =  entry[i].getvalue()*2;;                    
                }else if(i==0){
                    temp_unity = entry[i].getvalue();
                    value+= temp_unity;
                    if(temp_unity<min_unity) throw new Exception("Не правилно вход");
                    else min_unity = temp_unity*2;
                }   
            }
        }
    }

    public Roman_Numeral_Sign[] getEntry() {
        return entry;
    }

    public int getValue() {
        return value;
    }
    public String add_to(Roman_numeral a){
        return Roman_Numeral_Sign.Roman_value_of(this.getValue() + a.getValue());
    }
    public String substract_to(Roman_numeral a){
        int value = this.getValue() - a.getValue();
        if(value == 0) throw new ArithmeticException();
        else if(value > 0){
            return Roman_Numeral_Sign.Roman_value_of(value);
        }else return "-" + Roman_Numeral_Sign.Roman_value_of(value);
    }
    public String multiply_by(Roman_numeral a){
        return Roman_Numeral_Sign.Roman_value_of(this.getValue() * a.getValue());
    }
    public String divide_by(Roman_numeral a){
        if(a.getValue()==0) throw new ArithmeticException();
        else return Roman_Numeral_Sign.Roman_value_of((int)(this.getValue()/a.getValue()));
    }
    
}
