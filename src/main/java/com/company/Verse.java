package com.company;

public class Verse
{
    private Verse priorVerse;
    private Verse subsequentVerse;
    private String bookName, bookCode;
    private String verseString;
    private int bookNumber,chapterNumber, verseNumber;
    public Verse(String verseContent)
    {
        String [] contents = verseContent.split("\t");

        try {
            bookName = contents[0];
            bookCode = contents[1];
            verseString = contents[5];
            bookNumber = Integer.parseInt(contents[2]);
            chapterNumber = Integer.parseInt(contents[3]);
            verseNumber = Integer.parseInt(contents[4]);
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Missing Info " + contents[0] + " " + contents[3]);
        }

    }
    public int getBookNumber()
    {
        return bookNumber;
    }
    public int getChapterNumber()
    {
        return chapterNumber;
    }
    public int getVerseNumber()
    {
        return verseNumber;
    }
    public String getBookName()
    {
        return bookName;
    }
    public Verse searchForVerse(String bookNameArg,int chapter, int verse)
    {
        Verse searchPlace = this;
        while(searchPlace.getPriorVerse() != null)
        {
            searchPlace = searchPlace.getPriorVerse();
        }
        while(searchPlace.getSubsequentVerse() != null)
        {
            if(searchPlace.getBookName().contentEquals(bookNameArg) && searchPlace.getChapterNumber() == chapter && searchPlace.getVerseNumber() == verse)
            {
                searchPlace.printVerseInfo();
                return searchPlace;

            }
            searchPlace = searchPlace.getSubsequentVerse();
        }
        System.out.println("Verse not found!");
        return null;
    }
    public String quotePassage(String bookNameArg, int initialChapter,int initialVerse, int finalChapter, int finalVerse)
    {
        Verse searchPlace = this;
        String toHold = "";
        if(initialChapter > finalChapter || (initialChapter == finalChapter && initialVerse > finalVerse))
        {
            System.err.println("Quoting Backwards, cancelling!");
            return null;
        }
        while(searchPlace.getPriorVerse() != null)
        {
            searchPlace = searchPlace.getPriorVerse();
        }
        while(searchPlace.getSubsequentVerse() != null)
        {
            if(searchPlace.getBookName().contentEquals(bookNameArg) && searchPlace.getChapterNumber() == initialChapter && searchPlace.getVerseNumber() == initialVerse)
            {
                while(searchPlace.getChapterNumber() != finalChapter || searchPlace.getVerseNumber() != finalVerse + 1 )
                {
                    toHold = toHold + " " + searchPlace.getVerseString();
                    System.out.println(bookNameArg + " " + searchPlace.getChapterNumber() + ":" + searchPlace.getVerseNumber() + " : " +  searchPlace.getVerseString());
                    searchPlace = searchPlace.getSubsequentVerse();
                    //This is to handle terminating at the end of a chapter, which is a rare case
                    if(searchPlace.getChapterNumber() == finalChapter + 1 && searchPlace.getVerseNumber() == 1)
                    {
                        break;
                    }
                }
                System.out.println("Finished Passage!");
            }
            searchPlace = searchPlace.getSubsequentVerse();
        }
        if(toHold.contentEquals(""))
        {
            System.err.println("Verse not Found!");
        }
        return toHold;
    }
    public void printVerseInfo()
    {
        System.err.println("Verse info:");
        System.err.println("Book Name: " + bookName + " Chapter: " + Integer.toString(chapterNumber) + " Verse: " + Integer.toString(verseNumber));
        System.err.println(verseString);
    }
    public Verse getPriorVerse()
    {
        return priorVerse;
    }
    public Verse getSubsequentVerse()
    {
        return subsequentVerse;
    }

    public void setPriorVerse(Verse prior)
    {
        priorVerse = prior;

    }
    public void setSubsequentVerse(Verse subsequent)
    {
        subsequentVerse = subsequent;
    }
    public String getVerseString()
    {
        return verseString;
    }
}
