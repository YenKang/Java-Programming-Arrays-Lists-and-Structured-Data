
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
         //  initialize records to an empty ArrayList 
         records = new ArrayList<LogEntry>();
        
     } 
     public void readFile(String filename) {
         // complete method
         // create a FileResource for fileName
         // interate over its lines for each one
         //  Use WebLogParser.parseEntry to records
         
         FileResource fr = new FileResource(filename);
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
      public int countUniqueIPs()
      {
        // uniqueIPs starts as an empty list
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        // for each element le in records
        for(LogEntry le:records)
        {
          
           // ipAddr is le's ipAddress
            String ipAddr = le.getIpAddress();
           // if ipAddr is not in uniqueIPs
            if(! uniqueIPs.contains(ipAddr)){
                // add ipAddr to uniqueIPs
                uniqueIPs.add(ipAddr);
            }
            
        }    
        // return the size of uniqueIPs
        return uniqueIPs.size();
     }
      
     public void printAll() {
         //implicitly uese .toString() in LogEntry
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }

     public void printAllHigherThanNum(int num){
         for(LogEntry le:records){
             if(le.getStatusCode() > num){
                 System.out.println(le);
             }
         }
     }
 
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> addresses = new ArrayList<String>();
         for (LogEntry le : records) {
             String d = le.getAccessTime().toString();
             String ip = le.getIpAddress();
             if (d.contains(someday) && !addresses.contains(ip)) {
                 addresses.add(ip);
             }
         }
         return addresses;
        
     }
     
     public int countUniqueIPsInRange(int low, int high){
        HashMap<String,Integer> uniques = new HashMap<String, Integer>();
        //for every record
        for(LogEntry le:records){
            // obtain ip address and status code
            String ip = le.getIpAddress();
            int status = le.getStatusCode();
            // if key does not already exist in map and status code is within range
            if(!uniques.containsKey(ip) && (status >=low) && (status <= high)){
                // add unique IP address to map
                uniques.put(ip,status);
            }
        }
        
        System.out.println("Unique ips in range are" + uniques);

        return uniques.size();
     }
     
   /////////////////////////////////////////////////////////////////////////////////
    // The following section is about counting website visits //  
     
     public HashMap<String,Integer> countVisitsPerIP(){
         // Make an empty HashMap<String,Integer> counts
         HashMap<String,Integer> counts = new HashMap<String, Integer>();
         // For each le's ipAddress
         for(LogEntry le:records){
         // check if ip is in counts
            String ip = le.getIpAddress();
            if(!counts.containsKey(ip)){
               counts.put(ip,1);
            }
            
            else{
                counts.put(ip,counts.get(ip)+1);
            }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
            int max=0;
            // for every ip address
            for(String ip:counts.keySet()){
                // obtain its number of counts
                int curr = counts.get(ip);
                if(curr > max){
                    max = curr;
                }
            }
            return max;
        }
     
     public ArrayList iPsMostVisits(HashMap<String,Integer> counts){
             ArrayList<String> maxIPs = new ArrayList<String>();
             int max = mostNumberVisitsByIP(counts);
             for(String ip:counts.keySet()){
                 if(max == counts.get(ip)){
                     maxIPs.add(ip);
                 }
             }
             
             return maxIPs;
        }
     
     public HashMap<String, ArrayList<String>> iPsForDays(){
            HashMap<String, ArrayList<String>> dateMap = new HashMap<String, ArrayList<String>>();
            for(LogEntry le:records){
                // obtain date and ipAddr
                String date = le.getAccessTime().toString().substring(4,10);
                String ipAddr = le.getIpAddress();
                // If dateMap does not contain date yet
                if(!dateMap.containsKey(date)){
                    // create ArrayList<String> and add IP address
                    ArrayList<String> ipArray = new ArrayList<String>();
                    ipArray.add(ipAddr);
                    // update dateMap
                    dateMap.put(date,ipArray);
                }
                
                else{
                    // obtain ArrayList of IP address
                    ArrayList<String> ipArray = dateMap.get(date);
                    ipArray.add(ipAddr);
                    dateMap.put(date,ipArray);
                }
                
            }
            return dateMap;
        }
        
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dateMap){
            String maxDay = "";
            for (String date : dateMap.keySet()) {
                ArrayList<String> ips = dateMap.get(date);
                if (maxDay.equals("") || ips.size() > dateMap.get(maxDay).size()) {
                    maxDay = date;
                }
            }
            return maxDay;

        }   
     
      public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dateMap, String date) {
         ArrayList<String> ips = dateMap.get(date);
         HashMap<String, Integer> visits = new HashMap<String, Integer>();
         for (String ip : ips) {
             if (visits.containsKey(ip)) {
                 int num = visits.get(ip);
                 visits.put(ip, num + 1);
             } else {
                 visits.put(ip, 1);
             }
         }
         
         return iPsMostVisits(visits);
     }
     
      
     
}
