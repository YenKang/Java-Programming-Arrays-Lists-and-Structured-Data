
## Summary

@version:2017-09-28

-

- ArrayList跟Array一樣，是index collection，但是前者可以add內容，是growable，你不需要事先知道space in ArrayList

- Create ArrayList<Integer>與ArrayList<String>

- Common methods for ArrayList
	- .add()—多加一個元素在Arraylist最後面
	- .size()—元素的數目 
	- .get(index)—返回第幾index th的元素
	- .set(index,elt)— assign let to index location
	
ArrayList<String> a = new ArrayList<String>();
// add elements

for(String s:a){
	// process s
}

### Assignment1:

- 1.你要寫一個program去決定一個檔案中最常出現的字，假如有兩個最多次出現的單字，那麼顯示第一個。

- 2.在計算全部單字之前，先把全部的單字小寫化，不用擔心逗號，並且使用**WordFrequencies**當作是你的起點。

- 3.Create a new project in BlueJ and create a new class called WordFrequencies

- 4.兩個private variables，一個是myWords and myFreqs

- 5.寫一個constructor **WordFrquencies**，初始化private的variable


- 6.寫一個void method findUnique，一開始應該先clear myWords and myFreqs，用.clear(）處理一遍，然後再去計算每個單次的出現頻率。

- 7.寫 void **tester**的方法，主要是print 那些uniques的字，並且列出他們各自的頻率。

- 8.寫一個 **findIndexOfMax**的方法，最高頻率的單字出現在哪一個index中，return int index

- 9.增加code在**tester**中，主要是去call findIndexOfMax

### Assignment2:Character Names

- 寫一個program決定在莎士比雅的plays中，the most speaking parts，接著提供**macbethSmall.txt**橋段。

- 不是count=1就印，而是選一個number或是挑 greater than 1，然後列出output，具體來說有以下六項。

#### 具體事項

- 1.創建一個class named **CharatersInPlay**

- 2.創建兩個private ArrayLists variables

- 3.寫一個方法 named **update**，如果角色的名字沒有出現過，那麼就會被add進去。

- 4.寫一個 void 方法 named findAllChracters，逐行逐行讀，輸出speaking part的name，計算這個人的出現次數

- 5.寫一個void 方法 named **tester**，call findAllChraracters，印出主要的character，一個主要角色要比其他people還多，並且測試file **macbethSmall.txt** 還有**macbeth.txt**

- 6.寫一個method，called chractersWithNumParts(num1,num2)，首先num1 <= num2，而且印出數目（name的數目），介於num1 <= num <= num2，並且寫code進入tester來做測試。 


 