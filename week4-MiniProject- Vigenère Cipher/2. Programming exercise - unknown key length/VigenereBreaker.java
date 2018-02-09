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
    
  
    
    public HashSet<String> readDictionary(FileResource fr){
        // make a new HashSet of Strings
        HashSet<String> dictionaryWords = new HashSet<String>();
        // read each line in fr
        for(String line:fr.lines())
        {
           // convert that line to lowercase
            line = line.toLowerCase();
           // put that line into the HashSet that you created
            dictionaryWords.add(line);
        }
       return dictionaryWords;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int realWordNums = 0;
        //split the message into words
        String[] words = message.split("\\W");
        // iterate over those words
        for(String word:words){
          // see how many of those words are "real words"
          word = word.toLowerCase();
          if(dictionary.contains(word)){
              realWordNums++;
            }
    
        }
        return realWordNums;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int maxRealWords = 0;
        int finalKeyLength = 1;
        String decryptionWithMostRealWords = "";
        
        //try all key lengths from 1 to 100
        for(int keyLength=1 ; keyLength <=100; keyLength++){
           // decrypt message 
           int []key = tryKeyLength(encrypted,keyLength,'e');
           VigenereCipher vc = new VigenereCipher(key);
           String decrypted = vc.decrypt(encrypted);
           //count how many of the words in it are real words in English
           int realWords = countWords(decrypted,dictionary);
           if(realWords > maxRealWords){
               maxRealWords = realWords;
               decryptionWithMostRealWords = decrypted;
               finalKeyLength = keyLength;
            }
        
        }
        
        //System.out.println("Message contains "+ maxRealWords+ " real words");
        //System.out.println("FinalkeyLength is "+ finalKeyLength);
        return decryptionWithMostRealWords;
    }
    
    /** original version
    
    public void breakVigenere(){
        System.out.println("-------------------------");
        FileResource fr = new FileResource();
        String message = fr.asString();
        int []key = tryKeyLength(message,4,'e');
        
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(message);
        System.out.println(decrypted);
    }
    
    */
    
    // new version of breakVigenere
  
    public void breakVigenere(){
       // create a new FileResource
       FileResource fr = new FileResource();
       // Use that FileResource's .asString()
       String message = fr.asString();
       // make a new FileResource to read from the English dictionary
       FileResource dicFile = new FileResource("dictionaries/English");
       // use readDictionary method to read the contents of that dicFile into a HashSet of Strings
       HashSet<String> dictionary = readDictionary(dicFile);
       // use breakForLanguage to decrypt encrypted message
       //String decrypted = breakForLanguage(message, dictionary);
       
       
       // print out decrypted message
       //System.out.println("decrypted:" + decrypted);
    }
    
    public void testCountWords(){
         // create a new FileResource
       FileResource fr = new FileResource();
       // Use that FileResource's .asString()
       String message = fr.asString();
       // make a new FileResource to read from the English dictionary
       FileResource dicFile = new FileResource("dictionaries/English");
       // use readDictionary method to read the contents of that dicFile into a HashSet of Strings
       HashSet<String> dictionary = readDictionary(dicFile);
       int realWordNums = countWords(message,dictionary);
       System.out.println("realWordNums " + realWordNums);
    }
    
    public void testBreakForLanguage(){
       FileResource fr = new FileResource();
       String message = fr.asString();
       FileResource dicFile = new FileResource("dictionaries/English");
       HashSet<String> dictionary = readDictionary(dicFile);
       breakForLanguage(message,dictionary);
       //String decryptionWithMostRealWords = breakForLanguage(message,dictionary);
       //System.out.println("decryptionWithMostRealWords:" + decryptionWithMostRealWords);
    
    
    }
    
    public void testFixedLengthToFindValidWords(){
     
       FileResource fr = new FileResource();
       String message = fr.asString();
       FileResource dicFile = new FileResource("dictionaries/English");
       HashSet<String> dictionary = readDictionary(dicFile);
       int []key = tryKeyLength(message,38,'e');
       
       VigenereCipher vc = new VigenereCipher(key);
       String decrypted = vc.decrypt(message);
       
       int realWordNums = countWords(decrypted,dictionary);
       System.out.println("realWordNums: " + realWordNums);
    }
    
    public void testShowFirstLine(){
       FileResource fr = new FileResource();
       String message = fr.asString();
       FileResource dicFile = new FileResource("dictionaries/English");
       HashSet<String> dictionary = readDictionary(dicFile);
       int []key = tryKeyLength(message,57,'e');
       
       VigenereCipher vc = new VigenereCipher(key);
       String decrypted = vc.decrypt(message);
       System.out.println("decrypted:" + decrypted);
       
       String decrypted2 = breakForLanguage(message, dictionary);
       System.out.println("=================");
       System.out.println(decrypted2);
    }
    
}
