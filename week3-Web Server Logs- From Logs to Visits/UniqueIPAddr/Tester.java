
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;


public class Tester {
    public void testUniqueIP(){
        // create a LogAnalyzer object
        LogAnalyzer analyzer = new LogAnalyzer();
        // read the file from short-test_log
        analyzer.readFile("short-test_log");
        // test the method countUniqueIPs
        int numOfUniqueIPs = analyzer.countUniqueIPs();
        System.out.println("Uniques IPs: "+ numOfUniqueIPs);
        
    }
    
    public void testPrintAllHigherThanNum(){
        // create a LogAnalyzer object
        LogAnalyzer analyzer = new LogAnalyzer();
        // Read the data file
        analyzer.readFile("weblog1_log");
        // test the method printAllHigherThanNum
        int num = 400;
        analyzer.printAllHigherThanNum(num);
    
    }
    
    public void testUniqueIPVistsOnDay(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog1_log");
        String someday1 = "Mar 18";
        String someday2 = "Mar 19";
        String someday3 = "Mar 21";


        ArrayList<String> addresses1 = analyzer.uniqueIPVisitsOnDay(someday1);
        System.out.println("There were " + addresses1.size() + " visits on " + someday1);
        System.out.println(someday1 + " has those Unique ip addresses:" + addresses1);

        ArrayList<String> addresses2 = analyzer.uniqueIPVisitsOnDay(someday2);
        System.out.println("There were " + addresses2.size() + " visits on " + someday2);
        System.out.println(someday2 + " has those Unique ip addresses:" + addresses2);

        ArrayList<String> addresses3 = analyzer.uniqueIPVisitsOnDay(someday3);
        System.out.println("There were " + addresses3.size() + " visits on " + someday3);
        System.out.println(someday3 + " has those Unique ip addresses:" + addresses3);
    }
    
    public void testUniqueIPsInRange(){
    // create a logAnalyzer object
    LogAnalyzer analyzer = new LogAnalyzer();
    // Read the data files 
    analyzer.readFile("short-test_log");

    // Test the method countUniqueIPsInRange
    int low = 200;
    int high = 299;
    
    int num = analyzer.countUniqueIPsInRange(low,high);
    System.out.println("Number of unique IPs in range " + low + "-" + high + ": " + num);
        
    // int low2 = 300;
    // int high2 = 399;
    // int num2 = analyzer.countUniqueIPsInRange(low2, high2);
    // System.out.println("Number of unique IPs in range " + low2 + "-" + high2 + ": " +num2);
    
    }

    /////////////////////////////////////////////////////////////////////////////////
    // The following section is about counting website visits //  
    
    public void testMostNumberVisitsByIP(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog3-short_log");
        
        HashMap<String, Integer> counts = analyzer.countVisitsPerIP();
        int max = analyzer.mostNumberVisitsByIP(counts);
        System.out.println("Maximum number of visits is: "+ max);
    }
    
    public void testIPsMostVisits(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog3-short_log");
        HashMap<String, Integer> counts = analyzer.countVisitsPerIP();
        ArrayList maxIPs = analyzer.iPsMostVisits(counts);
        System.out.println("Max IPs:" + maxIPs);
    }
    
    public void testIPsForDays(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog3-short_log");
        String date1 = "Sep 14";
        HashMap<String, ArrayList<String>> dateMap = analyzer.iPsForDays();
        System.out.println(date1 + " maps to " + dateMap.get(date1).size() + " IP addresses");
        System.out.println(dateMap);
        
        String date2 = "Sep 21"; 
        dateMap = analyzer.iPsForDays();
        System.out.println(date2 + " maps to " + dateMap.get(date2).size() + " IP addresses");
        System.out.println(dateMap);
        
        String date3 = "Sep 30";
        dateMap = analyzer.iPsForDays();
        System.out.println(date3 + " maps to " + dateMap.get(date3).size() + " IP addresses");
    }
    
    public void testDayWithMostIPVisits(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> dateMap = analyzer.iPsForDays();
        String day = analyzer.dayWithMostIPVisits(dateMap);
        System.out.println("The day with the most IP visits: " + day); 
    }
    
     public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog1_log");
        HashMap<String, ArrayList<String>> dateMap = analyzer.iPsForDays();
        String date = "Sep 30";
        ArrayList<String> ips = analyzer.iPsWithMostVisitsOnDay(dateMap, date);
        System.out.println(ips);
    }
}
