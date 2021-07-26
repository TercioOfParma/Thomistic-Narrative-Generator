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

	    ActionLoader Test = new ActionLoader("story_actions.json", "List Of All Virtues.txt");
    }
}
