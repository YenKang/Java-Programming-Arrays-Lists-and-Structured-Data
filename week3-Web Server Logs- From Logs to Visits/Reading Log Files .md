### 2017-10-04(三）蓋樓[291]

##Reading Log Files

####Module Learning Outcomes / Resources
10 min

- 了解網頁有多少人來訪、然後斷線該怎麼處理

####Introduction
1 min

####Understanding Log Files
6 min

####LogEntry Class with toString
- toString 與 getLogInfo的比較
- System.out.println(le)與 System.out.println(le.getLogInfo())

####Parsing Log Files
3 min

####Summary
- web server logs:info about your site
- Format:Apache documentation
- Class for LogEntry:Learned about toString // 注意S是大寫
- Write code to read log file 
- ready to analyze

####Programming Exercise: Reading Log Files
- class 有四個：LogEntry,LogAnalyzer,WebLogParser,Tester


####Practice Quiz:

Reading Log Files

- Suppose a new integer variable named location will be added as a private variable to the LogEntry class. This variable should be initialized as 0 just once when the object is created.

- Where is the best location in the class to initialize this variable?

>- Initialize location in the constructor with the code:
>- location =0;
