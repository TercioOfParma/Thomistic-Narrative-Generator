package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Main {

    public static void main(String[] args)
    {
		try {
			System.setOut(new PrintStream(new File("output-file.txt")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		LinkedList<Character> characters = new LinkedList<>();
		Action temp;
		Random rand = new Random();
	    BibleLoader bible = new BibleLoader("drb.tsv"); //Operates in the root before src and out
	    Verse scripture = bible.getBibleList();
	    scripture.searchForVerse("Genesis",1,1);
		FileWriter outputtedStores = null;
	    try {
			outputtedStores = new FileWriter("Outputted Stories.txt");
		}
	    catch(IOException e)
		{
			System.err.println(e);
		}
		scripture.searchForVerse("John",1,1);
	    System.out.println(scripture.quotePassage("Genesis", 1,1,3,15));
		String [] nameFiles = {"ArabicNames.txt","BabylonianNames.txt","CopticNames.txt","GermanicNames.txt","GreekNames.txt","HebrewNames.txt", "RomanNames.txt"};
		NameGenerator names = new NameGenerator(nameFiles);
	    ActionLoader Test = new ActionLoader("story_actions.json", "List Of All Virtues.txt", bible, names);
		Iterator<Action> finalActions;

		System.out.println(scripture.quotePassage("1 Corinthians",13,1, 13,13));

		Test.loadAllActionsNew();
		System.err.println("Actionlist to be parsed to the characters: ");
		Test.printAllActions();
		for(int i =0; i < 2000; i++)
		{
			characters.add(new Character(Test.getActionList(), names.getRandomName(), Test.getAllVirtues(), Test.getAllPassions()));
			System.out.println("Character Stats");
			System.out.println(characters.get(i).getVirtuesAndVices());
		}
		Action tempAction;
		for(int i = 0; i < 3000; i++)
		{
			for(Character chara : characters)
			{
				chara.listOfAllActions.getLast().evaluateChoice(chara, characters.get(rand.nextInt(characters.size())),characters.get(rand.nextInt(characters.size())));
			}
		}
		for(Character chara: characters)
		{
			try {
				outputtedStores.write("New Story: "+"\n");
				outputtedStores.write(chara.getStory() + "\n");
			}
			catch(IOException e)
			{
				System.err.println(e);
			}

		}


    }
}
