
/**
 * Write a description of CharatersIbPlay here.
 * 
 * @author (Yen Kang) 
 * @version (2017-09-28)
 */

import edu.duke.*;
import java.util.ArrayList; 

public class CharatersInPlay {
    private ArrayList<String> myCharacters;
    private ArrayList<Integer>myCharacterFreqs;
    
    public CharatersInPlay()
    {
        myCharacters = new ArrayList<String>();
        myCharacterFreqs = new ArrayList<Integer>();
    }
    
    public void update(String person){
        //FileResource resource = new FileResource();
        
        int index = myCharacters.indexOf(person);
        if(index == -1){
            myCharacters.add(person);
            myCharacterFreqs.add(1);
        }
        
        else{
            int freq = myCharacterFreqs.get(index);
            myCharacterFreqs.set(index,freq+1);
        
        }
        
        
    }
    
    public void findAllCharaters(){
        FileResource resource = new FileResource();
        for(String line:resource.lines()){
            int periodInLine = line.indexOf(".");
            if(periodInLine != -1){
                String possibleName = line.substring(0,periodInLine);
                update(possibleName);
            }
        }
    }
    
    public void tester(){
        findAllCharaters();
        int index = findMax();
        for(int i=0;i<myCharacters.size();i++){
            System.out.println(myCharacters.get(i)+ " "+ myCharacterFreqs.get(i));
            
        }
        System.out.println("-------------");
        System.out.println("Character with most speaking parts: " + myCharacters.get(index)+" "+myCharacterFreqs.get(index) + "\n");
        System.out.println("-------------");
        charactersWithNumParts(60, 102);
    }
    
    public void charactersWithNumParts(int num1,int num2){
        for(int k=0;k< myCharacterFreqs.size();k++){
            if(myCharacterFreqs.get(k) >= num1 && myCharacterFreqs.get(k) <= num2){
                System.out.println(myCharacters.get(k) + " " + myCharacterFreqs.get(k));
            }
        
        }
        
     

    }
    
    public int findMax(){
        int max = myCharacterFreqs.get(0);
        int maxIndex =0;
        for(int k=0; k< myCharacterFreqs.size(); k++){
            if(myCharacterFreqs.get(k) > max){
                max = myCharacterFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
}
