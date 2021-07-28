package com.company;

public class Main {

    public static void main(String[] args)
    {
	    Character C = new Character(new ActualGrace());
	    System.out.println(String.valueOf(C.getLengthOfActions()));

	    BibleLoader bible = new BibleLoader("drb.tsv"); //Operates in the root before src and out
	    Verse scripture = bible.getBibleList();
	    scripture.searchForVerse("Genesis",1,1);

		scripture.searchForVerse("John",1,1);
	    System.out.println(scripture.quotePassage("Genesis", 1,1,3,15));
		String [] nameFiles = {"ArabicNames.txt","BabylonianNames.txt","CopticNames.txt","GermanicNames.txt","GreekNames.txt","HebrewNames.txt", "RomanNames.txt"};
		NameGenerator names = new NameGenerator(nameFiles);
	    ActionLoader Test = new ActionLoader("story_actions.json", "List Of All Virtues.txt", bible, names);


		System.out.println(scripture.quotePassage("1 Corinthians",13,1, 13,13));

    }
}
