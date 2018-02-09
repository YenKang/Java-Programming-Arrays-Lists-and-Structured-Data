
/**
 * Write a description of OOCaesarCipher here.
 * 
 * @author (Yen Kang) 
 * @version (2017-09-23)
 */
import edu.duke.*;

public class OOCaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public OOCaesarCipher(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key)+alphabet.substring(0,key);
        mainKey = key;
    }
    
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        for(int i =0;i<encrypted.length();i++)
        {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            
            if(idx != -1){
                if(currChar == Character.toUpperCase(currChar)){
                    char newChar = shiftedAlphabet.charAt(idx);
                    encrypted.setCharAt(i,newChar);
                }
                
                else{
                    char newChar = shiftedAlphabet.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, Character.toLowerCase(newChar)); 
                }
            }
        }
        
        return encrypted.toString();
    }
    
    public String decrypt(String input){
        OOCaesarCipher cc = new OOCaesarCipher(26-mainKey);
        return cc.encrypt(input);
    }
    
}
