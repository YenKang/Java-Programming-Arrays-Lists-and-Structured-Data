
/**
 * Write a description of WordLengths here.
 * 
 * @author (Bryan) 
 * @version (v1 2017-09-22)
 */
import edu.duke.*;
import edu.duke.FileResource;

public class WordLengths {
    public void countWordLength(FileResource resource,int[] counts){
        final int countsLen = counts.length;
        
        for(String word:resource.words()){
            word = word.toLowerCase();
            int currWordLeghth = Math.min(wordLength(word),countsLen);
            counts[currWordLeghth]++;
        }
        
        
    }
    
    private int wordLength(String word){
        int length =0;
        for(char ch:word.toCharArray()){
            if(Character.isLetter(ch)){
                length++;
            }
        }
        return length;
    }
    
    public void testWordLengths(){
        int[] counts = new int[31];
        FileResource resource = new FileResource("manywords.txt");
        countWordLength(resource,counts);
        
         
        for (int i=1; i<counts.length; i++) {
          System.out.printf("%d:\t%d\n", i, counts[i]);
        }
        
        System.out.println("Largest value in array: "+ indexOfMax(counts));
    }
    
    public int indexOfMax(int[] values){
        int intOfMax = 0;
        int maxSoFar = values[0]; // set values[0] is maximum
        
        for(int i=1;i<values.length;i++)
        {
            if(values[i]> maxSoFar)
            {
              maxSoFar = values[i];
              intOfMax = i;
            }
         
        }
        
        return intOfMax;
    }

}
