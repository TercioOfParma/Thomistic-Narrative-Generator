package com.company;


import com.ppolivka.namegenerator.Generator;
import com.ppolivka.namegenerator.impl.KatzBackoffGenerator;
import org.apache.maven.shared.utils.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class NameGenerator
{
    private LinkedList<Generator> generators = new LinkedList<Generator>();
    Random rand = new Random();
    public NameGenerator(String [] filenames)
    {
        LinkedList<String> names;
        for(String filename : filenames)
        {
            names = new LinkedList<String>();
            try {
                File bibleFile = new File(filename);
                Scanner scan = new Scanner(bibleFile);
                while(scan.hasNextLine())
                {
                    names.add(scan.nextLine());
                }
                generators.add(new KatzBackoffGenerator(new HashSet<>(names), 3, 0.001f));
            }
            catch(FileNotFoundException e)
            {
                System.out.println(filename + " not found!");
            }
        }

    }

    public String getRandomName()
    {
        String toReturn = generators.get(rand.nextInt(generators.size())).generate();
        toReturn = toReturn.replace(" ", "");
        toReturn = toReturn.toLowerCase();
        toReturn = StringUtils.capitalise(toReturn);
        return toReturn;
    }
}
