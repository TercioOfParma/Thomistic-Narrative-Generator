package com.company;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
public class BibleLoader
{
    private String fn;
    private Verse bibleList = null;
    public BibleLoader(String filename)
    {
        fn = filename;
        try {
            String line;
            File bibleFile = new File(fn);
            Scanner scan = new Scanner(bibleFile);
            System.out.println("Loading Bible...");
            while(scan.hasNextLine())
            {
                line = scan.nextLine();
                if(bibleList == null)
                {
                    bibleList = new Verse(line);
                }
                else
                {
                    Verse newVerse = new Verse(line);
                    bibleList.setSubsequentVerse(newVerse);
                    Verse previousVerse = bibleList;
                    bibleList = newVerse;
                    bibleList.setPriorVerse(previousVerse);
                }

            }
            System.out.println("Loaded!");
            bibleList.printVerseInfo();
            bibleList.getPriorVerse().printVerseInfo();
            bibleList.getPriorVerse().getPriorVerse().printVerseInfo();
            bibleList.getPriorVerse().getSubsequentVerse().printVerseInfo();
        }
        catch(FileNotFoundException e)
        {
            System.out.println(fn + " not found!");
        }

    }
    public Verse getBibleList(){
        return bibleList;
    }
    public String getVerse(String searchTerm)
    {
        return "";
    }
}
