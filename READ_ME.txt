EXTRA CLASSES ADDED IN THE PACKAGE org.serene.tester:
--------------------
1)ArgsFormator.java

2)DocumentFreqMap.java

3)IllegalArgumentException.java

4)LuceneRanker.java


HOW TO RUN THE CODE:
-------------------
The format of the Command Line Arguments is as follows:

s: <search_query> <whitespace> d1: <document content> <whitespace> d2: <document content> ......so on and so forth

Examples:
---------

1)
"s: what is wrong" "d1: what what is wrong wrong wrong" "d2: What what not is wrong wrong" "d3: what what is not wrong"

2)
"s: what is not wrong" "d1: what what is wrong wrong wrong" "d2: What what not is wrong wrong" "d3: what what is not wrong"