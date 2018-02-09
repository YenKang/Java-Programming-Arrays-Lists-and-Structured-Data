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
        
        // Find the most common char in the dictionary
        char mostCommonChar = mostCommonCharIn(dictionary);
        System.out.println("The most common character in the dictionary is "+ mostCommonChar);
        
        //try all key lengths from 1 to 100
        for(int keyLength=1 ; keyLength <=100; keyLength++){
           // decrypt message 
           int []key = tryKeyLength(encrypted,keyLength,mostCommonChar);
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
        
        System.out.println("Message contains "+ maxRealWords+ " real words");
        System.out.println("Final keyLength is "+ finalKeyLength);
        return decryptionWithMostRealWords;
    }
    
    /** original version with known length and languages
    
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
    
    /** new version of breakVigenere with unkown length
  
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
    
    */
    
    public void breakVigenere(){
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        // read each of dictionaries
        String[] dictionaries = new String[8];
        dictionaries[0] = "Danish";
        dictionaries[1] = "Dutch";
        dictionaries[2] = "English";
        dictionaries[3] = "French";
        dictionaries[4] = "German";
        dictionaries[5] = "Italian";
        dictionaries[6] = "Portuguese";
        dictionaries[7] = "Spanish";
        
        // For each dictionary File
        for(int i=0;i<dictionaries.length; i++){
            String languageName = dictionaries[i];
            FileResource dictFile = new FileResource("dictionaries/"+ languageName);
            HashSet<String> dictionary = readDictionary(dictFile);
            languages.put(languageName,dictionary);
            System.out.println("add " + languageName +"to languages");
        }
        
        FileResource fr = new FileResource();
        String message = fr.asString();
        breakForAllLangs(message, languages);
    }
    
    
    /**
     * Return character,of the letters in the English alphabet, 
     * that appears most often in the words in the dictionary
     */
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character,Integer> charFreq = new HashMap<Character, Integer>();
        // For each dictionary word
        for(String word:dictionary){
            for(int i=0;i<word.length();i++){
                char ch = word.charAt(i);
                // If character is not a letter in English alphabet,break
                if(!Character.isLetter(ch)){
                    break;
                }
                // If charFreq HashMap contains char
                if(charFreq.containsKey(ch)){
                    int freq = charFreq.get(ch);
                    charFreq.put(ch,freq+1);
                }
                else{
                    charFreq.put(ch,1);
                }
            }
        }
        
        // Find most common character in dictionary
        char mostCommonChar = ' ';
        int maxCount =0;
        for(char ch:charFreq.keySet()){
            int freq = charFreq.get(ch);
            // initialize tracker variables
            if(Character.isSpaceChar(mostCommonChar)){
                mostCommonChar = ch;
                maxCount = freq;
            }
            else{
                if(freq>maxCount){
                    mostCommonChar = ch;
                    maxCount = freq;
                }
            }
        }
        
        return mostCommonChar;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        // Iterate over the language.keySet()
        for(String currLanguage:languages.keySet()){
            // Get the language's mapped HashSet dictionary of words
            HashSet<String> dictionary = languages.get(currLanguage);
            System.out.println("current text is analyzed by "+ currLanguage);
            // Call breakForLanguage with the message and the dictionary
            String decrypted = breakForLanguage(encrypted,dictionary);
            // print the decrypted message
            System.out.println(decrypted);
            
        }
    
    }
}
