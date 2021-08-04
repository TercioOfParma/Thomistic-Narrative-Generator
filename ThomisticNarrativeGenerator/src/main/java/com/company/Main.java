package com.company;

import java.util.LinkedList;
import java.util.Random;

public class Main {

    public static void main(String[] args)
    {

		LinkedList<Character> characters = new LinkedList<>();

		Random rand = new Random();
	    BibleLoader bible = new BibleLoader("drb.tsv"); //Operates in the root before src and out
	    Verse scripture = bible.getBibleList();
	    scripture.searchForVerse("Genesis",1,1);

		scripture.searchForVerse("John",1,1);
	    System.out.println(scripture.quotePassage("Genesis", 1,1,3,15));
		String [] nameFiles = {"ArabicNames.txt","BabylonianNames.txt","CopticNames.txt","GermanicNames.txt","GreekNames.txt","HebrewNames.txt", "RomanNames.txt"};
		NameGenerator names = new NameGenerator(nameFiles);
	    ActionLoader Test = new ActionLoader("story_actions.json", "List Of All Virtues.txt", bible, names);


		System.out.println(scripture.quotePassage("1 Corinthians",13,1, 13,13));

		Test.loadAllActionsNew();
		for(int i =0; i < 20; i++)
		{
			characters.add(new Character(Test.getActionList(), names.getRandomName(), Test.getAllVirtues(), Test.getAllPassions()));
			System.out.println("Character Stats");
			System.out.println(characters.get(i).getVirtuesAndVices());
		}
		for(int i = 0; i < 30; i++)
		{
			for(Character chara : characters)
			{
				chara.listOfAllActions.evaluateChoice(chara, characters.get(rand.nextInt(characters.size())), characters.get(rand.nextInt(characters.size())), Test.getActionList());
			}
		}
		for(Character chara: characters)
		{
			System.out.println(chara.getStory());
		}


    }
}
