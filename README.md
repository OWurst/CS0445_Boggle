# omw3_CS0445_Boggle
These are two projects I did for CS0445, Algorithms and Data Structures 1 at UPitt. I like to use them to cheat in imessage games.
###Boggle:
Boggle.java must be ran using the following syntax: "java Boggle [dictionary filename] [boggle grid]". The dictionary I use is listed in this repository and an example of the required grid can be found in input.txt. Boggle.java stores all dictionary values in a TreeSet and uses a dfs to create all possible Strings of characters from the board. If the Strings are found in the dictionary, they are printed out immediately. To prune the search tree, a simple heuristic is used to give up on a given branch whenever the next largest word in the dictionary does not contain the current substring. 
###Jumbles:
Jumbles.java must be ran using the following syntax: "java Jumbles [dictionary filename] [jumble]". The dictionary I use is the same file used by the Boggle program and a jumble example can be found in Jumble.txt. Jumbles.java builds new words using the input jumble, and outputs the jumble next to all words found in the dictionary greater than length 2.
