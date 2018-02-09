
/**
 * Write a description of testCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class testCaesarCipher {
    private int[] countLetters(String message){
        String alph = "abcdefghijklmnoqprstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] vals){
        int maxDex = 0;
        for (int k = 0; k < vals.length; k++){
            if (vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }
    
  
    public void breakCaesarCipher(String input){
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 - (4 - maxDex);
        }
        
        OOCaesarCipher a = new OOCaesarCipher(dkey);
        System.out.println("Encrypted message:\n" + input);
        System.out.println("\nkey:" + dkey);
        System.out.println("\nDecrypted message:\n" + a.decrypt(input));

    }
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        String message =fr.asString();
        OOCaesarCipher c = new OOCaesarCipher(18);
        String encrypted = c.encrypt(message);
        System.out.println("encrypted:"+ encrypted);
        System.out.println("decrypted:"+ c.decrypt(encrypted));
        

    }
}
