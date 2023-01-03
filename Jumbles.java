import java.util.*;
import java.io.*;

public class Jumbles
{
  static TreeSet<String> dictionary = new TreeSet<String>();
  public static void main(String[] args) throws Exception
  {
    TreeMap<String,TreeSet<String>> output = new TreeMap<String,TreeSet<String>>();

    BufferedReader DicFile = new BufferedReader(new FileReader(args[0]));
    while(DicFile.ready()) dictionary.add(DicFile.readLine());

    BufferedReader JumFile = new BufferedReader(new FileReader(args[1]));
    while(JumFile.ready())
    {
      String jumble = JumFile.readLine();
      output.put(jumble, dfsHelp(jumble));
    }
    for (Map.Entry<String, TreeSet<String>> entry : output.entrySet())
      System.out.println(entry.getKey() + toString(entry.getValue()));
  } // END MAIN

  public static String toString(TreeSet<String> set)
  {
    String retString = " ";
    for(String s : set) retString += s+" ";
    return retString;
  }

  private static TreeSet<String> dfsHelp(String jumble)
  {
    TreeSet<String> answers = new TreeSet<String>();
    dfs("", jumble, answers);
    return answers;
  }

  private static void dfs(String prefix, String jumble, TreeSet<String> answers)
  {
      int n = jumble.length();
      if(prefix.length() > 2 && dictionary.contains(prefix)){answers.add(prefix);}//////////
      if(!hasSubstring(prefix)) return;
      for (int i = 0; i < n; i++)
        dfs(prefix + jumble.charAt(i), jumble.substring(0, i) + jumble.substring(i+1, n), answers);
  }

  private static boolean hasSubstring(String substring)
  {
    try
    {
      if(dictionary.ceiling(substring).contains(substring)) return true;
      return false;
    }
    catch(Exception e){
      return false;
    }
  }
}
