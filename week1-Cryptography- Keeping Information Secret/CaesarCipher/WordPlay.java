
/**
 * Write a description of WordPlay here.
 * 
 * @author (Yen Kang) 
 * @version (verson1,2017-09-19)
 */
public class WordPlay {
    
   public boolean isVowel(char ch){
        String vowels = "aeiou";
        char lower = Character.toLowerCase(ch);
        if (vowels.indexOf(ch) != -1){
            return true;
        }
        else{
            return false;
        }
    }
    
    public String replaceVowels(String phrase, char ch){
        String lower = phrase.toLowerCase();
        StringBuilder replace = new StringBuilder(lower);
        for(int i=0; i<replace.length(); i++){
            
            if(isVowel(replace.charAt(i))== true){
                replace.setCharAt(i,ch);
            }
        }
        
        return replace.toString();
    
    }
    
    public String emphasize(String phrase,char ch){
        StringBuilder emphasize = new StringBuilder(phrase);
        for(int i=0;i<emphasize.length(); i++){
            if(emphasize.charAt(i)==ch || emphasize.charAt(i)==Character.toUpperCase(ch))
            {
                if(i%2==0){
                    emphasize.setCharAt(i,'*');
                }
                
                else{
                    emphasize.setCharAt(i,'+');
                }
            } 
        }
        return emphasize.toString();
    
    }
    
    public void testVowel(){
        System.out.println(replaceVowels("Hello World",'*'));
        System.out.println(emphasize("dna ctgaaactga",'a'));
    }
    
}
