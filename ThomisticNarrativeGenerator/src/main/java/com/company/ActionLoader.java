package com.company;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.sql.*;
import java.util.*;


public class ActionLoader {
    private LinkedList<Action> actionList, stateList;
    String jsonFile, virtuesFile, jsonContents;
    private LinkedList<String> virtuesString, dbColumnNames, actionIDs, subvirtuesToTable;//Also for passions
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
        subvirtuesToTable = new LinkedList<>();
        stateList = new LinkedList<>();
        actionList = new LinkedList<>();
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
    }

    public LinkedList<Action> getActionList() {
        return actionList;
    }

    public void setActionList(LinkedList<Action> actionList) {
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

    public LinkedList<String> getSubvirtuesToTable() {
        return subvirtuesToTable;
    }

    public void setSubvirtuesToTable(LinkedList<String> subvirtuesToTable) {
        this.subvirtuesToTable = subvirtuesToTable;
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
        boolean isState = false;
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
                            System.err.println("Virtue added : " + column);
                            allVirtues.put(column, 0);
                            subvirtuesToTable.add(table + "_" + column);
                        }
                    }
                    else if(table.contentEquals("PASSIONS") )
                    {
                        for(String column : columns)
                        {
                            System.err.println("Passion added : " + column);
                            allPassions.put(column, 0);
                            subvirtuesToTable.add(table + "_" + column);
                        }
                    }
                    for(String column: columns)
                    {
                        System.out.println(column + " " + table.contentEquals("BasicInfo"));
                        if(table.contentEquals("BasicInfo"))
                        {
                            if(tempAction == null)
                            {
                                isState = false;
                                if(results.getString("TYPE").contentEquals("TEMPTATION"))
                                {
                                    tempAction = new Temptation(this.getActionList());
                                    tempAction.setId(action);
                                    isState = false;
                                }
                                else if(results.getString("TYPE").contentEquals("ACTUAL_GRACE"))
                                {
                                    tempAction = new ActualGrace(this.getActionList());
                                    tempAction.setId(action);
                                    isState = false;
                                }
                                else if(results.getString("TYPE").contentEquals("VIRTUE_STATE"))
                                {
                                    tempAction = new VirtueState(this.getActionList());
                                    tempAction.setId(action);
                                    isState = true;
                                }
                                else if(results.getString("TYPE").contentEquals("RELATIONSHIP_STATE"))
                                {
                                    tempAction = new RelationshipState(this.getActionList());
                                    tempAction.setId(action);
                                    isState = true;
                                }
                                else if(results.getString("TYPE").contentEquals("PASSION_STATE"))
                                {
                                    tempAction = new RelationshipState(this.getActionList());
                                    tempAction.setId(action);
                                    isState = true;
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
                                        System.err.println("Found Accepted Post Condition");
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
                                    else if(results.getBoolean("IS_PRECONDITION") && !results.getBoolean("IS_ABOVE"))
                                    {
                                        suffix = suffix + "_BELOW";
                                    }
                                    if (virtue.contains("SUBVIRTUE") || virtue.contains("SUBVICE")) {
                                        System.out.println("Subvice or Subvirtue " + prefix + virtue + suffix);
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

                                    } else if (results.getBoolean("IS_POSTCONDITION_REJECT")) {
                                        prefix = "POSTCONDITIONS_REJECT_";
                                        postconditionsReject.putAll(currentHash);

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

            System.out.println(tempAction.getId() + " Completed!");
            System.out.println("Virtue effects Preconditions: " + tempAction.getPreConditions().getVirtueEffects());
            if(!tempAction.getId().contentEquals("TEMPLATE") && !isState) {
                this.actionList.add(tempAction);
                System.err.println("Loaded virtues : " + this.actionList.getLast().getId());
            }
            else if(isState)
            {
                this.stateList.add(tempAction);
                System.err.println("State precons :" + tempAction.getPreConditions().getVirtueEffects());
                System.err.println("Loaded States : " + this.stateList.getLast().getId());
            }

            }
        printAllActions();
        //generateRandomStory();
    }

    public LinkedList<Action> getStateList() {
        return stateList;
    }

    public void setStateList(LinkedList<Action> stateList) {
        this.stateList = stateList;
    }

    public void printAllActions()
    {
        Action tempIterate = null;
        System.err.println("All Actions: ");
        Iterator<Action> iterate = this.getActionList().iterator();
        while(iterate.hasNext())
        {
            tempIterate = iterate.next();
            System.err.println(tempIterate.getId());

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
}
