package com.company;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.sql.*;
import java.util.*;


public class ActionLoader {
    private Action actionList;
    String jsonFile, virtuesFile, jsonContents;
    private LinkedList<String> virtuesString, dbColumnNames, actionIDs;
    private HashMap<String, Object> Preconditions, Postconditionsaccept, Postconditionsreject, basicConditions;
    private HashMap<String, Integer> allVirtues, allPassions;
    private static Connection conn = null;
    private NameGenerator names;
    private BibleLoader bible;
    final int MAXIMUM_STATEMENTS = 2147483647;
    public ActionLoader(String jsonFile, String virtuesFile, BibleLoader bible, NameGenerator Gens) {
        this.jsonFile = jsonFile;
        this.virtuesFile = virtuesFile;
        Map<String, String> tempMap;
        names = Gens;
        actionIDs = new LinkedList<String>();
        dbColumnNames = new LinkedList<String>();
        Preconditions = new HashMap<String, Object>();
        Postconditionsaccept = new HashMap<String, Object>();
        Postconditionsreject = new HashMap<String, Object>();
        basicConditions = new HashMap<String, Object>();
        allVirtues = new HashMap<>();
        allPassions = new HashMap<>();
        jsonContents = "";
        String line, key;
        virtuesString = new LinkedList<String>();
        /*int columnLooper, columnlooperRear;
        try {
            File bibleFile = new File(virtuesFile);
            Scanner scan = new Scanner(bibleFile);
            System.out.println("Loading virtues...");
            while (scan.hasNextLine()) {
                virtuesString.add(scan.nextLine());

            }
        } catch (FileNotFoundException e) {
            System.out.println(virtuesFile + " not found!");
        }
        try {
            connect("ThomisticNarrativeDB.db");
            for (columnLooper = 100; columnLooper < 1100; columnLooper = columnLooper + 100) {
                columnlooperRear = columnLooper - 100;
                System.out.println(columnlooperRear);
                PreparedStatement queryColumn = conn.prepareStatement("SELECT * FROM PRAGMA_TABLE_INFO('ImportedCSV') WHERE cid >" + columnlooperRear + " AND cid < " + columnLooper);
                ResultSet columns = queryColumn.executeQuery();
                while (columns.next()) {
                    dbColumnNames.add(columns.getString("name"));
                    if (columns.getString("name").contains("PRECONDITIONS")) {
                        testField(columns, Preconditions);
                    } else if (columns.getString("name").contains("POSTCONDITIONS_ACCEPT")) {
                        testField(columns, Postconditionsaccept);
                    } else if (columns.getString("name").contains("POSTCONDITIONS_REJECT")) {
                        testField(columns, Postconditionsreject);
                    } else {
                        testField(columns, basicConditions);
                    }
                }
            }
            loadActionIntoActionList("PRECONDITIONS_VIRTUE_PRUDENCE_SUBVIRTUE_MEMORY_ABOVE", "TEMPLATE");
            System.out.println("Loading Story Actions...");
            PreparedStatement queryStat = conn.prepareStatement("SELECT id FROM ImportedCSV");
            ResultSet results = queryStat.executeQuery();
            while (results.next()) {
                actionIDs.add(results.getString("id"));
            }
            System.out.println(actionIDs.toString() + "\n ColumnNames: " + dbColumnNames.toString());
            System.out.println(basicConditions.toString() + "\n" + Postconditionsaccept.toString() + "\n" + Postconditionsreject.toString() + "\n" + Preconditions.toString());
        } catch (Exception e) {
            System.err.println(e.getMessage() + " " + e.getStackTrace()[3].toString());
        }
        loadActionList();
        System.out.println("Loaded! Proving it!");
        generateRandomStory();*/
    }

    public Action getActionList() {
        return actionList;
    }

    public void setActionList(Action actionList) {
        this.actionList = actionList;
    }

    public HashMap<String, Integer> getAllVirtues() {
        return allVirtues;
    }

    public void setAllVirtues(HashMap<String, Integer> allVirtues) {
        this.allVirtues = allVirtues;
    }

    public HashMap<String, Integer> getAllPassions() {
        return allPassions;
    }

    public void setAllPassions(HashMap<String, Integer> allPassions) {
        this.allPassions = allPassions;
    }

    public void loadAllActionsNew()
    {
        connect("ThomisticNarrativeDB.db");
        String [] tables = {"BasicInfo","VIRTUE_PRUDENCE","VIRTUE_FORTITUDE","VIRTUE_JUSTICE", "VIRTUE_TEMPERANCE", "VIRTUE_FAITH", "VIRTUE_HOPE", "VIRTUE_CHARITY", "PASSIONS"};
        LinkedList<String> tableList = new LinkedList<>(Arrays.asList(tables));
        LinkedList<String> columns = new LinkedList<>();
        HashMap<String,Object> preconditions = new HashMap<>();
        HashMap<String,Object> postconditionsAccept= new HashMap<>();
        HashMap<String,Object> postconditionsReject = new HashMap<>();
        PreparedStatement queryStat;
        ResultSet results;
        String suffix = "";
        String prefix = "";
        int limit = 0;
        Action tempAction = null;
        Action oldAction = null;
        HashMap<String, Object> currentHash = new HashMap<>();
        try {
            queryStat = conn.prepareStatement("SELECT id FROM BasicInfo");
            results = queryStat.executeQuery();
            while(results.next())
            {
                this.actionIDs.add(results.getString("id"));
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        for(String action : this.actionIDs)
        {
            tempAction = null;
            preconditions = new HashMap<>();
            postconditionsAccept = new HashMap<>();
            postconditionsReject = new HashMap<>();
            for(String table : tableList)
            {
                columns = new LinkedList<>();
                try
                {
                    queryStat = conn.prepareStatement("SELECT * FROM PRAGMA_TABLE_INFO(\'" + table + "\')");
                    results = queryStat.executeQuery();
                    while(results.next())
                    {
                        columns.add(results.getString("name"));
                    }
                    if(table.contentEquals("BasicInfo"))
                    {
                        queryStat = conn.prepareStatement("SELECT * FROM " + table + " WHERE id = \'" + action + "\'");
                    }
                    else
                    {
                        queryStat = conn.prepareStatement("SELECT * FROM " + table + " WHERE STORY_ACTION_ID = \'" + action + "\'");
                    }
                    queryStat.setFetchSize(MAXIMUM_STATEMENTS);
                    queryStat.setFetchDirection(ResultSet.FETCH_UNKNOWN);
                    results = queryStat.executeQuery();
                    if(!(table.contentEquals("PASSIONS") || table.contentEquals("BasicInfo")))
                    {
                        for(String column : columns)
                        {
                            allVirtues.put(column, 0);
                        }
                    }
                    else if(table.contentEquals("PASSIONS") )
                    {
                        for(String column : columns)
                        {
                            allPassions.put(column, 0);
                        }
                    }
                    for(String column: columns)
                    {
                        System.out.println(column + " " + table.contentEquals("BasicInfo"));
                        if(table.contentEquals("BasicInfo"))
                        {
                            if(tempAction == null)
                            {
                                if(results.getString("TYPE").contentEquals("TEMPTATION"))
                                {
                                    tempAction = new Temptation();
                                    tempAction.setId(action);
                                }
                                else
                                {
                                    tempAction = new ActualGrace();
                                    tempAction.setId(action);
                                }
                            }
                            if(results.getString("POSTCONDITIONS_ACCEPT_OUTPUT") != null)
                            {
                                postconditionsAccept.put("POSTCONDITIONS_ACCEPT_OUTPUT",results.getString("POSTCONDITIONS_ACCEPT_OUTPUT"));
                            }
                            if(results.getString("POSTCONDITIONS_REJECT_OUTPUT") != null)
                            {
                                postconditionsAccept.put("POSTCONDITIONS_REJECT_OUTPUT",results.getString("POSTCONDITIONS_REJECT_OUTPUT"));
                            }
                            if(results.getString("POSTCONDITIONS_ACCEPT_CONSEQUENTIAL_ACTIONS") != null)
                            {
                                postconditionsAccept.put("CONSEQUENTIAL_ACTIONS",results.getString("POSTCONDITIONS_ACCEPT_CONSEQUENTIAL_ACTIONS"));
                            }
                            if(results.getString("POSTCONDITIONS_REJECT_CONSEQUENTIAL_ACTIONS") != null)
                            {
                                postconditionsAccept.put("CONSEQUENTIAL_ACTIONS",results.getString("POSTCONDITIONS_REJECT_CONSEQUENTIAL_ACTIONS"));
                            }
                            if(results.getBoolean("QUOTES_SCRIPTURE") )
                            {
                                basicConditions.put("SCRIPTURE_BANK_VERSES", results.getString("SCRIPTURE_BANK_VERSES"));
                            }
                        }
                        else
                        {
                            while(results.next())
                            {
                                try{
                                for(String virtue: columns) {
                                    System.out.println("In results");
                                    suffix = "";
                                    prefix = "";
                                    currentHash = new HashMap<String,Object>();

                                    if (results.getBoolean("IS_PRECONDITION")) {
                                        prefix= "PRECONDITIONS_";
                                    }
                                    else if (results.getBoolean("IS_POSTCONDITION_ACCEPT")) {
                                        prefix = "POSTCONDITIONS_ACCEPT_";
                                    }
                                    else if (results.getBoolean("IS_POSTCONDITION_REJECT")) {
                                        prefix = "POSTCONDITIONS_REJECT_";
                                    }
                                    if(results.getBoolean("SECOND_PERSON"))
                                    {
                                        suffix = "_SECOND_PERSON";
                                    }
                                    else if(results.getBoolean("THIRD_PERSON"))
                                    {
                                        suffix = "_THIRD_PERSON";
                                    }
                                    if(results.getBoolean("IS_ABOVE") && results.getBoolean("IS_PRECONDITION"))
                                    {
                                        suffix = suffix + "_ABOVE";
                                    }
                                    else if(results.getBoolean("IS_PRECONDITION"))
                                    {
                                        suffix = suffix + "_BELOW";
                                    }
                                    if (virtue.contains("SUBVIRTUE") || virtue.contains("SUBVICE")) {
                                        System.out.println("Subvice or Subvirtue");
                                        currentHash.put(prefix + virtue + suffix, results.getInt(virtue));
                                    }
                                    if (virtue.contains("ANGER") || virtue.contains("DARING") || (virtue.contains("HOPE") && !(virtue.contains("SUBVIRTUE") || virtue.contains("SUBVICE"))) || virtue.contains("PLEASURE") || virtue.contains("LOVE")) {
                                        currentHash.put(prefix + virtue + suffix, results.getInt(virtue));
                                    }
                                    System.out.println("Current Hash: " + currentHash);
                                    System.out.println("Prefix " + prefix + " Suffix " + suffix + " Current Column :" + virtue);
                                    if (results.getBoolean("IS_PRECONDITION")) {
                                        prefix = "PRECONDITIONS_";
                                        preconditions.putAll(currentHash);
                                    } else if (results.getBoolean("IS_POSTCONDITION_ACCEPT")) {
                                        prefix = "POSTCONDITIONS_ACCEPT_";
                                        postconditionsAccept.putAll(currentHash);
                                        ;
                                    } else if (results.getBoolean("IS_POSTCONDITION_REJECT")) {
                                        prefix = "POSTCONDITIONS_REJECT_";
                                        postconditionsReject.putAll(currentHash);
                                        ;
                                    }
                                }
                                }
                                catch(Exception e)
                                {
                                    System.out.println(e);
                                }
                            }
                        }

                    }
             }
                catch(Exception e)
                {
                    System.err.println(e);
                    System.err.println(table);
                    System.err.println(action);
                }
            }
            tempAction.getPreConditions().setVariables(preconditions);
            tempAction.getPostConditionsAccept().setVariables(postconditionsAccept);
            tempAction.getPostConditionsReject().setVariables(postconditionsReject);
            if(this.actionList == null)
            {
                this.actionList = tempAction;
            }
            else
            {
                System.out.println(tempAction.getId() + " Completed!");
                System.out.println("Virtue effects Preconditions: " + tempAction.getPreConditions().getVirtueEffects());
                this.actionList.setSubsequentAction(tempAction);
                oldAction = this.actionList;
                this.actionList = tempAction;
                this.actionList.setPreviousAction(oldAction);
                try {
                    System.out.println(this.actionList.getPreviousAction().getPreviousAction().getId() + " OldAction");
                }
                catch(Exception e)
                {

                }
            }
        }

        generateRandomStory();
    }

    public void rewindActionList()
    {
        while(actionList.getPreviousAction() != null)
        {
            actionList = actionList.getPreviousAction();
        }
    }
    public void printAllActions()
    {
        rewindActionList();
        Action tempIterate = actionList;
        System.out.println("All Actions: ");
        while(tempIterate != null)
        {
            System.out.println(tempIterate.getId());
            tempIterate = tempIterate.getSubsequentAction();
        }
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

    public void loadActionIntoActionList(String Object, String id) {
        String query = "SELECT " + Object + " FROM ImportedCSV WHERE id = \"" + id + "\"";
        System.out.println(query);
        try {
            PreparedStatement queryStat = conn.prepareStatement(query);
            ResultSet results = queryStat.executeQuery();

            System.out.println("Results: " + results.getInt(Object));
        } catch (Exception e) {
            System.err.println(e);
        }
        System.out.println(Object);
    }

    public void testField(ResultSet toTest, HashMap<String, Object> toAdd) {
        System.out.println("True!");
        try {
            if (toTest.getString("type").contentEquals("TEXT")) {
                System.out.println("True 2!");
                toAdd.put(toTest.getString("name"), "TEXT");
            }
            if (toTest.getString("type").contentEquals("INTEGER")) {
                System.out.println("True 3!");
                toAdd.put(toTest.getString("name"), 0);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage() + " " + e.getStackTrace()[3].toString());
        }
    }


    public void loadActionList() {
        System.out.println("Storing Story actions...");
        Iterator<String> columns = dbColumnNames.iterator();
        Action tempAction = new Temptation();
        Action OldAction = actionList;
        Iterator<String> actions = actionIDs.iterator();
        HashMap<String, Object> precons = new HashMap<String, Object>();
        HashMap<String, Object> postconsaccept = new HashMap<String, Object>();
        HashMap<String, Object> postconsreject = new HashMap<String, Object>();
        LinkedList<String> scriptures = new LinkedList<String>();
        PreparedStatement toQuery;
        ResultSet results;
        String columnKey, actionKey;
        while (actions.hasNext()) {
            actionKey = actions.next();
            columns = dbColumnNames.iterator();
            precons = new HashMap<String, Object>();
            postconsaccept = new HashMap<String, Object>();
            postconsreject = new HashMap<String, Object>();
            try {
                toQuery = conn.prepareStatement("SELECT *" + " From ImportedCSV WHERE id = \"" + actionKey + "\"");
                results = toQuery.executeQuery();
                while (columns.hasNext()) {
                    columnKey = columns.next();
                    try {
                        if (columnKey.contains("TYPE") && results.getString("TYPE").contentEquals("TEMPTATION")) {
                            tempAction = new Temptation();
                            tempAction.setId(actionKey);
                        } else if (columnKey.contains("TYPE") && results.getString("TYPE").contentEquals("ACTUAL_GRACE")) {
                            tempAction = new ActualGrace();
                            tempAction.setId(actionKey);
                        }
                        System.out.println(columnKey);
                        if (columnKey.contains("SCRIPTURE_BANKVERSES")) {
                            String[] unsavedScripts = results.getString("SCRIPTURE_BANKVERSES").split(",");
                            scriptures = new LinkedList(Arrays.asList(unsavedScripts));
                            System.out.println("Scriptures: " + unsavedScripts);
                        } else if (Preconditions.containsKey(columnKey)) {
                            precons.put(columnKey, results.getObject(columnKey));
                        } else if (Postconditionsaccept.containsKey(columnKey)) {
                            postconsaccept.put(columnKey, results.getObject(columnKey));
                        } else if (Postconditionsreject.containsKey(columnKey)) {
                            postconsreject.put(columnKey, results.getObject(columnKey));
                        }
                    } catch (Exception e) {
                        System.out.println(e + "\n" + e.getStackTrace()[0].getLineNumber());
                    }

                }
                //tempAction.setPreConditions(new Conditions(precons));
                //tempAction.setPostConditionsAccept(new Conditions(postconsaccept));
                //tempAction.setPostConditionsReject(new Conditions(postconsreject));
                tempAction.getPostConditionsAccept().setScriptures(scriptures);
            } catch (Exception e) {
                System.out.println(e + "\n" + e.getStackTrace()[0].getLineNumber());
            }


            if (actionList == null) {
                System.out.println("Empty ActionList");
                actionList = tempAction;
            } else {

                actionList.setSubsequentAction(tempAction);
                actionList.getSubsequentAction().setPreviousAction(actionList);
                actionList = tempAction;
                System.out.println("New One: " + actionList.getId() + " Old one: " + actionList.getPreviousAction().getId());
                try {
                    System.out.println("New Action:" + actionList.getId() + " Previous: " + actionList.getPreviousAction().getId() + " Previous Previous: " + actionList.getPreviousAction().getPreviousAction().getId());

                } catch (Exception e) {

                }
            }
        }
        System.out.println("All actions loaded!");
    }

    public void generateRandomStory() {
        rewindActionList();
        Random rand = new Random();
        System.out.println("Testing Random Story");
        int limit = rand.nextInt(50);
        System.out.println("Testing Random Story " + limit);
        String randomId;
        Character test = null;
        Action toPrint;
        for (int i = 0; i < limit; i++) {
            randomId = actionIDs.get(rand.nextInt(actionIDs.size()));
            System.out.println("Searching!");
            toPrint = (actionList.searchList(randomId));
            if (toPrint instanceof Temptation) {
                toPrint = toPrint.copyTemptContents(toPrint);
            } else if (toPrint instanceof ActualGrace) {
                toPrint = toPrint.copyAGContents(toPrint);
            }
            //System.out.println(toPrint.getPostConditionsAccept().getOtherEffects().get("POSTCONDITIONS_ACCEPT_OUTPUT"));
            if (test == null) {
                System.out.println("Story started!");
                test = new Character(toPrint, "Test", allVirtues, allPassions);
            } else {
                System.out.println("Story action added!");
                test.addAction(toPrint);
            }
        }
        System.out.println("Story Generated");
        test.printStory(bible, names);
    }
}
