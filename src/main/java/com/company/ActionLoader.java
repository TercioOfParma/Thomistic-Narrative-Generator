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
    private static Connection conn = null;

    public ActionLoader(String jsonFile, String virtuesFile) {
        this.jsonFile = jsonFile;
        this.virtuesFile = virtuesFile;
        Map<String, String> tempMap;
        actionIDs = new LinkedList<String>();
        dbColumnNames = new LinkedList<String>();
        Preconditions = new HashMap<String, Object>();
        Postconditionsaccept = new HashMap<String, Object>();
        Postconditionsreject = new HashMap<String, Object>();
        basicConditions = new HashMap<String, Object>();
        jsonContents = "";
        String line, key;
        virtuesString = new LinkedList<String>();
        int columnLooper, columnlooperRear;
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
        generateRandomStory();
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
                        if (columnKey.contains("SCRIPTURE_BANKVERSES")) {
                            String[] unsavedScripts = results.getString("SCRIPTURE_BANKVERSES").split(",");
                            scriptures = new LinkedList(Arrays.asList(unsavedScripts));
                        } else if (Preconditions.containsKey(columnKey)) {
                            precons.put(columnKey, results.getObject(columnKey));
                        } else if (Postconditionsaccept.containsKey(columnKey)) {
                            postconsaccept.put(columnKey, results.getObject(columnKey));
                        } else if (Postconditionsreject.containsKey(columnKey)) {
                            postconsreject.put(columnKey, results.getObject(columnKey));
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }
                tempAction.setPreConditions(new Conditions(precons));
                tempAction.setPostConditionsAccept(new Conditions(postconsaccept));
                tempAction.setPostConditionsReject(new Conditions(postconsreject));
                tempAction.getPostConditionsAccept().setScriptures(scriptures);
            } catch (Exception e) {
                System.out.println(e);
            }


            if (actionList == null) {
                System.out.println("Empty ActionList");
                actionList = tempAction;
            } else {
                actionList.setSubsequentAction(tempAction);
                actionList.getSubsequentAction().setPreviousAction(actionList);
                OldAction = actionList;
                actionList = tempAction;
                actionList.setPreviousAction(OldAction);
                try {
                    System.out.println("New Action:" + actionList.getId() + " Previous: " + actionList.getPreviousAction().getId() + " Previous Previous: " + actionList.getPreviousAction().getPreviousAction().getId());

                } catch (Exception e) {

                }
            }
        }
        System.out.println("All actions loaded!");
    }

    public void generateRandomStory() {
        Random rand = new Random();
        System.out.println("Testing Random Story");
        int limit = rand.nextInt(50);
        System.out.println("Testing Random Story " + limit);
        String randomId;
        Character test = null;
        Action toPrint;
        for (int i = 0; i < limit; i++) {
            randomId = actionIDs.get(rand.nextInt(actionIDs.size()));
            toPrint = (actionList.searchList(randomId));
            if (toPrint instanceof Temptation) {
                toPrint = toPrint.copyTemptContents(toPrint);
            } else if (toPrint instanceof ActualGrace) {
                toPrint = toPrint.copyAGContents(toPrint);
            }
            //System.out.println(toPrint.getPostConditionsAccept().getOtherEffects().get("POSTCONDITIONS_ACCEPT_OUTPUT"));
            if (test == null) {
                test = new Character(toPrint);
            } else {
                test.addAction(toPrint);
            }
        }
        test.printStory();
    }
}
