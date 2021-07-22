package com.company;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.sql.*;
import java.util.*;


public class ActionLoader
{
    private Action actionList;
    String jsonFile, virtuesFile, jsonContents;
    private LinkedList<String> virtuesString,dbColumnNames, actionIDs;
    private HashMap<String, Object> Preconditions, Postconditionsaccept, Postconditionsreject, basicConditions;
    private static Connection conn = null;

    public ActionLoader(String jsonFile, String virtuesFile)
    {
        this.jsonFile = jsonFile;
        this.virtuesFile = virtuesFile;
        Map <String,String> tempMap;
        actionIDs = new LinkedList<String>();
        dbColumnNames = new LinkedList<String>();
        Preconditions = new HashMap<String,Object>();
        Postconditionsaccept = new HashMap<String,Object>();
        Postconditionsreject = new HashMap<String,Object>();
        basicConditions = new HashMap<String,Object>();
        jsonContents = "";
        String line, key;
        virtuesString = new LinkedList<String>();
        try
        {
            File bibleFile = new File(virtuesFile);
            Scanner scan = new Scanner(bibleFile);
            System.out.println("Loading virtues...");
            while (scan.hasNextLine())
            {
                virtuesString.add(scan.nextLine());

            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println(virtuesFile + " not found!");
        }
        try
        {
            connect("ThomisticNarrativeDB.db");
            loadActionIntoActionList("PRECONDITIONS_VIRTUE_PRUDENCE_SUBVIRTUE_MEMORY_ABOVE", "TEMPLATE");
            System.out.println("Loading Story Actions...");
            PreparedStatement queryStat = conn.prepareStatement("SELECT id FROM ImportedCSV");
            ResultSet results = queryStat.executeQuery();
            PreparedStatement queryColumn = conn.prepareStatement("SELECT * FROM PRAGMA_TABLE_INFO('ImportedCSV')");
            ResultSet columns = queryColumn.executeQuery();
            while(results.next() && columns.next())
            {
                System.out.println(columns.getString("name").contains("POSTCONDITIONS_REJECT") );
                actionIDs.add(results.getString("id"));
                dbColumnNames.add(columns.getString("name"));
                if(columns.getString("name").contains("PRECONDITIONS"))
                {
                    if(columns.getString("type") == "TEXT") {
                        System.out.println("True 2!");
                        Preconditions.put(columns.getString("name"), "");
                    }
                    if(columns.getString("type") == "INTEGER") {
                        Preconditions.put(columns.getString("name"), "");
                    }
                }
                else if(columns.getString("name").contains("POSTCONDITIONS_ACCEPT"))
                {
                    if(columns.getString("type") == "TEXT") {
                        Postconditionsaccept.put(columns.getString("name"), "");
                    }
                    if(columns.getString("type") == "INTEGER") {
                        Postconditionsaccept.put(columns.getString("name"), "");
                    }
                }
                else if(columns.getString("name").contains("POSTCONDITIONS_REJECT"))
                {

                    if(columns.getString("type") == "TEXT") {
                        Postconditionsreject.put(columns.getString("name"), "");
                    }
                    if(columns.getString("type") == "INTEGER") {
                        Postconditionsreject.put(columns.getString("name"), "");
                    }
                }
                else
                {
                    if(columns.getString("type") == "TEXT") {
                        basicConditions.put(columns.getString("name"), "");
                    }
                    if(columns.getString("type") == "INTEGER") {
                        basicConditions.put(columns.getString("name"), "");
                    }
                }
            }
            System.out.println(actionIDs.toString() + "\n ColumnNames: " + dbColumnNames.toString());
            System.out.println(basicConditions.toString() + "\n" + Postconditionsaccept.toString() + "\n" + Postconditionsreject.toString() + "\n" + Preconditions.toString());
            try
            {

            }
            catch(Exception e)
            {
                System.err.println(e);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        System.out.println("Loaded!");
    }
    //taken from elsewhere with some modifications
    public static void connect(String db) {
        try {
            // db parameters
            String url = "jdbc:sqlite:" + db;
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadActionIntoActionList(String Object, String id)
    {
        String query = "SELECT " + Object + " FROM ImportedCSV WHERE id = \"" + id + "\"";
        System.out.println(query);
        try {
            PreparedStatement queryStat = conn.prepareStatement(query);
            ResultSet results = queryStat.executeQuery();

            System.out.println("Results: " + results.getInt(Object));
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        System.out.println(Object);
    }
}
