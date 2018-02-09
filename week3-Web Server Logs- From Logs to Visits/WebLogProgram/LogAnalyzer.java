
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         /** 
          initialize records to an empty ArrayList  
          */
         
         records = new ArrayList<LogEntry>();
         
         
     }
        
     public void readFile(String filename) {
         // complete method
         /** 
          create a FileResource for fileName
          interate over its lines for each one
          Use WebLogParser.parseEntry to records
         */
         FileResource fr = new FileResource();
         String ip;
         Date time;
         int status;
         int bytes;
         
         for(String line:fr.lines()){
             // create a new LogEntry object by parsing the file with WebLogParser
             LogEntry le = WebLogParser.parseEntry(line);
             // store LogEntry object in the records ArrayList
             records.add(le);
         }
        
     }
        
     public void printAll() {
         /**
          * implicitly uese .toString() in LogEntry
         */
         
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
