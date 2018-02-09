import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        String sliced = "";
        for(int i=whichSlice;i< message.length(); i += totalSlices){
            char letter = message.charAt(i);
            sliced = sliced + letter;
        }
        return sliced;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        for(int i=0;i<klength;i++ ){
            String sliced = sliceString(encrypted,i,klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[i] = cc.getKey(sliced);
        }
        return key;
    }


    
    public void testSliceString(){
        System.out.println(sliceString("abcdefghijklm", 0, 3));
        System.out.println(sliceString("abcdefghijklm", 1, 3));
        System.out.println(sliceString("abcdefghijklm", 2, 3));
        System.out.println(sliceString("abcdefghijklm", 0, 4));
        System.out.println(sliceString("abcdefghijklm", 1, 4));
        System.out.println(sliceString("abcdefghijklm", 2, 4));
        System.out.println(sliceString("abcdefghijklm", 3, 4));
        System.out.println(sliceString("abcdefghijklm", 0, 5));
        System.out.println(sliceString("abcdefghijklm", 1, 5));
        System.out.println(sliceString("abcdefghijklm", 2, 5));
        System.out.println(sliceString("abcdefghijklm", 3, 5));
        System.out.println(sliceString("abcdefghijklm", 4, 5));
    }
    
    public void testTryKeyLength(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        //int klength = "flute".length();
        int klength = 4;
        int []key = tryKeyLength(message, klength, 'e');// {5,11,20,19,4}
        
        for(int i=0; i< key.length;i++){
           System.out.println(key[i]);
        }
      
    }
    
    public void breakVigenere(){
        System.out.println("-------------------------");
        FileResource fr = new FileResource();
        String message = fr.asString();
        int []key = tryKeyLength(message,4,'e');
        
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(message);
        System.out.println(decrypted);
    }
}
