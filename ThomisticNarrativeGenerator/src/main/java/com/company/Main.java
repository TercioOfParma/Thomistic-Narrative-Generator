package com.company;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Main {

	public static final int NUMBER_OF_CHARACTERS = 3000;
	public static final int LENGTH_OF_STORY = 300;
	public static final int NUMBER_OF_GENERATIONS = 150;
	public static final int DIVISION = 100;
	public static final int NUMBER_OF_POINTS = 10;
	public static final int GRAPH_INTERVAL = LENGTH_OF_STORY / NUMBER_OF_POINTS;
    public static void main(String[] args)
    {
		try {
			System.setOut(new PrintStream(new File("output-file.txt")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		LinkedList<Character> characters = new LinkedList<>();
		LinkedList<Character> tempChars = new LinkedList<>();
		LinkedList<Character> selectedChars = new LinkedList<>();
		LinkedList<Action> lifeToExecute = new LinkedList<>();
		LinkedList<graphNode> toAddToFile = new LinkedList<>();
		Character oldChar, temporaryChar;
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

		System.out.println(scripture.quotePassage("1 Corinthians",13,1, 13,13));

		Test.loadAllActionsNew();
		System.err.println("Actionlist to be parsed to the characters: ");
		Test.printAllActions();
		System.err.println("All Virtues :" + Test.getAllVirtues());
		int char1, char2;

		for(int i =0; i < NUMBER_OF_CHARACTERS; i++)
		{
			characters.add(new Character(Test.getActionList(), Test.getStateList(), names.getRandomName(), Test.getAllVirtues(), Test.getAllPassions(), Test.getSubvirtueToVirtue(), GRAPH_INTERVAL));
			System.out.println("Character Stats");
			System.out.println(characters.get(i).getVirtuesAndVices());
		}
		for(int i =0; i < NUMBER_OF_CHARACTERS; i++)
		{
			characters.get(i).generateRelationships(characters);
		}
		//Initial
		for(int i = 0; i < LENGTH_OF_STORY; i++)
		{
			char2 = rand.nextInt(characters.size());
			for(Character chara : characters)
			{
				rand.setSeed(rand.nextLong());
				char1 = rand.nextInt(characters.size());
				while(characters.get(char1).getName() == chara.getName())
				{
					char1 = rand.nextInt(characters.size());
				}
				char2 = rand.nextInt(characters.size());
				while(characters.get(char2).getName() == chara.getName())
				{
					char2 = rand.nextInt(characters.size());
				}
				chara.listOfAllActions.getLast().evaluateChoice(chara, characters.get(char1),characters.get(char2));
				rand.setSeed(rand.nextLong());

			}
		}
		for(Character chara: characters)
		{
			chara.setCrucialPoints(chara.getCrucialPoints().get(1).normalise(chara.getCrucialPoints()));
		}


		//Generations

		for(int i = 0; i < NUMBER_OF_GENERATIONS; i++)
		{
			System.out.println("Generation: " + (i + 1));
			Collections.sort(characters);//Sorts based on comparable, I hope
			//Collections.reverse(characters);//Inverse order
			selectedChars = new LinkedList<>();
			/*for(int j = 0; j < NUMBER_OF_CHARACTERS / DIVISION; j++)
			{
				System.out.println("FITNESS: " + characters.get(j).returnFitness());
				selectedChars.add(characters.get(j));
			}*/
			//Tournament Selection
			for(int j = 0; j < DIVISION; j++)
			{
				System.out.println("FITNESS: " + characters.get(j).returnFitness());
				selectedChars.add(characters.get(j));
			}
			tempChars = new LinkedList<>();
			// Crossover
			for(int j = 0; j < NUMBER_OF_CHARACTERS; j++) {
				temporaryChar = new Character(Test.getActionList(), Test.getStateList(), names.getRandomName(), Test.getAllVirtues(), Test.getAllPassions(), Test.getSubvirtueToVirtue(), GRAPH_INTERVAL);
				Collections.shuffle(selectedChars);
				temporaryChar.setListOfAllActions(temporaryChar.fullCrossover(selectedChars, LENGTH_OF_STORY));
				tempChars.add(temporaryChar);
			}

			characters = tempChars;
			//Make up their relationships
			for(int j = 0; j < NUMBER_OF_CHARACTERS; j++)
			{
				characters.get(j).generateRelationships(characters);
			}
			//Run the new lives
			for(Character chara : characters)
			{
				lifeToExecute = chara.getListOfAllActions();
				System.out.println("NUMBER OF ACTIONS: " + lifeToExecute.size());
				for(Action toExecute : lifeToExecute)
				{
					rand.setSeed(rand.nextLong());
					char1 = rand.nextInt(characters.size());
					while(characters.get(char1).getName() == chara.getName())
					{
						char1 = rand.nextInt(characters.size());
					}
					char2 = rand.nextInt(characters.size());
					while(characters.get(char2).getName() == chara.getName())
					{
						char2 = rand.nextInt(characters.size());
					}
					toExecute.executeActionInSubsequentGenerations(chara, characters.get(char1),characters.get(char2));
					rand.setSeed(rand.nextLong());
				}
			}
			for(Character chara: characters)
			{
				chara.setCrucialPoints(chara.getCrucialPoints().get(1).normalise(chara.getCrucialPoints()));
			}

		}
		for(Character chara: characters)
		{
			System.err.println("PRINTING FILE");
			try {
				oldChar = chara;
				System.err.println("Current Char : " + chara.getName());
				outputtedStores.write("New Story: "+"\n");
				outputtedStores.write(chara.getStory() + "\n");
				outputtedStores.write(chara.getVirtuesAndVices() + "\n");
				outputtedStores.write(chara.getPassions() + "\n");
				toAddToFile = chara.getCrucialPoints();
				for(graphNode test : toAddToFile)
				{
					outputtedStores.write(test.toString() + "\n");
				}
			}
			catch(IOException e)
			{
				System.err.println(e);
			}

		}



    }
}
