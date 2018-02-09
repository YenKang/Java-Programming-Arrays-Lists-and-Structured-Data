
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Tester {
    public void testCaesarCipher(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipher c = new CaesarCipher(4);
        String encrypted = c.encrypt(message);
        System.out.println("encrypted:"+ encrypted);
        System.out.println("decrypted:"+ c.decrypt(encrypted));
    }
    
    public void testCaesarCracker(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCracker c1 = new CaesarCracker();
        String decrypted1 = c1.decrypt(message);
        System.out.println("decrypted1:"+ decrypted1);
        
        FileResource fr2 = new FileResource();
        String message2 = fr2.asString();
        CaesarCracker c2 = new CaesarCracker();
        String decrypted2 = c2.decrypt(message);
        System.out.println("decrypted2:"+ decrypted2);
    }
}
