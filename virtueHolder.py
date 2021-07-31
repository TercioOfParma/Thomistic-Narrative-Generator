import pandas as pd
import numpy as np
import sqlite3

'''
This needs a far more sophisticated program than I anticipated:
	- 7 virtues + 5 passions + 1 basic 
	- Virtues and Passions should be split into 3 categories of precons etc, then 3 by person, then under precons 2 as 
	above or below 
	- 3 x 2 [Postconditions] + (3 x 3)[Precons]
	- Each of these will have the story action column just ripped across
	- Then, they will be committed to their respective tables

    - In practice, use this for passions as well, for the passions, too, for the division is identical
    
    0 : Precons Above
    1 : Precons Below
    2 : Precons Second Above
    3 : Precons Second Below 
    4 : Precons Third Above
    5 : Precons Third Below 
    6 : Postcons Accept
    7 : Postcons Second Accept
    8 : Postcons Third Accept
    9 : Postcons Reject
    10 : Postcons Second Reject
    11 : Postcons Third Reject
    
'''

'''
Redesigning the getting of columns:
    - Rather than go by column, go by rows
    - Extract the column headings, and see if they fit the dataframe
    - If they, concat them onto the side 
    
    That's a lot simpler 



'''


class VirtueHolder:

    def __init__(self, storyIDColumn, virtueName):
        i = 0
        self.tables = []
        self.name = virtueName
        print(self.name)
        while i < 12:
            self.tables.append(pd.DataFrame({"STORY_ACTION_ID": storyIDColumn}))
            i = i + 1

    def convertToDataFrames(self, data):
        i = 0
        for table in self.tables:
            for col in data.columns:
                columnDictionary = {}
                needed = True
                necessarynames = []
                if self.name in col:
                    print("Table " + str(i) + " " + self.name)
                    print(col)
                    if i == 0:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 1, "IS_POSTCONDITION_ACCEPT": 0,
                             "IS_POSTCONDITION_REJECT": 0, "SECOND_PERSON": 0, "THIRD_PERSON": 0,
                             "IS_ABOVE": 1})
                        necessarynames.append("PRECONDITION")
                        necessarynames.append("ABOVE")
                    elif i == 1:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 1, "IS_POSTCONDITION_ACCEPT": 0,
                             "IS_POSTCONDITION_REJECT": 0, "SECOND_PERSON": 0, "THIRD_PERSON": 0,
                             "IS_ABOVE": 0})
                        necessarynames.append("PRECONDITION")
                        necessarynames.append("BELOW")
                    elif i == 2:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 1, "IS_POSTCONDITION_ACCEPT": 0,
                             "IS_POSTCONDITION_REJECT": 0, "SECOND_PERSON": 1, "THIRD_PERSON": 0,
                             "IS_ABOVE": 1})
                        necessarynames.append("PRECONDITION")
                        necessarynames.append("ABOVE")
                        necessarynames.append("SECOND_PERSON")
                    elif i == 4:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 1, "IS_POSTCONDITION_ACCEPT": 0,
                             "IS_POSTCONDITION_REJECT": 0, "SECOND_PERSON": 0, "THIRD_PERSON": 1,
                             "IS_ABOVE": 1})
                        necessarynames.append("PRECONDITION")
                        necessarynames.append("ABOVE")
                        necessarynames.append("THIRD_PERSON")

                    elif i == 3:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 1, "IS_POSTCONDITION_ACCEPT": 0,
                             "IS_POSTCONDITION_REJECT": 0, "SECOND_PERSON": 1, "THIRD_PERSON": 0,
                             "IS_ABOVE": 0})
                        necessarynames.append("PRECONDITION")
                        necessarynames.append("BELOW")
                        necessarynames.append("SECOND_PERSON")
                    elif i == 5:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 1, "IS_POSTCONDITION_ACCEPT": 0,
                             "IS_POSTCONDITION_REJECT": 0, "SECOND_PERSON": 0, "THIRD_PERSON": 1,
                             "IS_ABOVE": 0})
                        necessarynames.append("PRECONDITION")
                        necessarynames.append("BELOW")
                        necessarynames.append("THIRD_PERSON")
                    elif i == 6:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 0, "IS_POSTCONDITION_ACCEPT": 1,
                             "IS_POSTCONDITION_REJECT": 0, "SECOND_PERSON": 0, "THIRD_PERSON": 0,
                             "IS_ABOVE": 0})
                        necessarynames.append("POSTCONDITION")
                        necessarynames.append("ACCEPT")
                    elif i == 7:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 0, "IS_POSTCONDITION_ACCEPT": 1,
                             "IS_POSTCONDITION_REJECT": 0, "SECOND_PERSON": 1, "THIRD_PERSON": 0,
                             "IS_ABOVE": 0})
                        necessarynames.append("POSTCONDITION")
                        necessarynames.append("ACCEPT")
                        necessarynames.append("SECOND_PERSON")
                    elif i == 8:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 0, "IS_POSTCONDITION_ACCEPT": 1,
                             "IS_POSTCONDITION_REJECT": 0, "SECOND_PERSON": 0, "THIRD_PERSON": 1,
                             "IS_ABOVE": 0})
                        necessarynames.append("POSTCONDITION")
                        necessarynames.append("ACCEPT")
                        necessarynames.append("THIRD_PERSON")
                    elif i == 9:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 0, "IS_POSTCONDITION_ACCEPT": 0,
                             "IS_POSTCONDITION_REJECT": 1, "SECOND_PERSON": 0, "THIRD_PERSON": 0,
                             "IS_ABOVE": 0})
                        necessarynames.append("POSTCONDITION")
                        necessarynames.append("REJECT")
                    elif i == 11:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 0, "IS_POSTCONDITION_ACCEPT": 0,
                             "IS_POSTCONDITION_REJECT": 1, "SECOND_PERSON": 0, "THIRD_PERSON": 0,
                             "IS_ABOVE": 0})
                        necessarynames.append("POSTCONDITION")
                        necessarynames.append("REJECT")
                        necessarynames.append("THIRD_PERSON")
                    elif i == 10:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 0, "IS_POSTCONDITION_ACCEPT": 0,
                             "IS_POSTCONDITION_REJECT": 1, "SECOND_PERSON": 0, "THIRD_PERSON": 0,
                             "IS_ABOVE": 0})
                        necessarynames.append("POSTCONDITION")
                        necessarynames.append("REJECT")
                        necessarynames.append("SECOND_PERSON")

                    for necessary in necessarynames:
                        if necessary not in col:
                            if i == 8:
                                print("Triggered on 8 Necessary: " + necessary + " col : " + col)
                            needed = False
                            break
                        if not needed and i == 8:
                            print("8 is fully false")
                    if needed:
                        splitName = col.split("_")
                        newName = ""
                        try:
                            search = ""
                            searchStrings = ["SUBVICE", "SUBVIRTUE"]
                            for searchThing in searchStrings:
                                if searchThing in splitName:
                                    search = searchThing
                            if search != "" and (splitName[splitName.index(search) + 1] not in "SECOND" and splitName[
                                splitName.index(search) + 1] not in "THIRD" and splitName[
                                             splitName.index(search) + 1] not in "ABOVE" and splitName[
                                             splitName.index(search) + 1] not in "BELOW"):
                                newName = search + "_" + (splitName[splitName.index(search) + 1])

                            if newName == "" and search == "SUBVICE":
                                newName = "VIRTUE_" + self.name + "_SUBVICE"
                            if newName == "" and search == "SUBVIRTUE":
                                newName = "VIRTUE_" + self.name + "_SUBVIRTUE"
                            elif newName == "":
                                newName = "VIRTUE_" + self.name

                        except IndexError:
                            print("Simply the subvirtue or subvice")
                        temptable = pd.DataFrame({newName: data[col]})
                        temptable = temptable.replace(np.NaN, 0)
                        temporaryDataFrame = pd.DataFrame()
                        j = 0
                        while j < len(self.tables[i]):
                            repeatedDataframe = pd.DataFrame(columnDictionary, index=[j])
                            temporaryDataFrame = pd.concat([temporaryDataFrame, repeatedDataframe])
                            j = j + 1
                        self.tables[i] = pd.concat([self.tables[i], temptable], axis=1)
                        self.tables[i] = pd.concat([self.tables[i], temporaryDataFrame], axis=1)
                        self.tables[i] = self.tables[i].loc[:, ~self.tables[i].columns.duplicated()]  # Thanks stack overflow
                        print(self.tables[i])
            i = i + 1


    def exportToCSV(self, address):
        i = 0
        for table in self.tables:
            print("Table " + str(i))
            print(table)
            filename = address + "/" + self.name + str(i) + ".csv"
            print(filename)
            table.to_csv(filename)
            i = i + 1


    def exportToSQL(self, con):
        i = 0
        toExportTable = pd.DataFrame()
        for table in self.tables:
            toExportTable = pd.concat([toExportTable, table], ignore_index=True)
            i = i + 1
        print("VIRTUE_" + self.name)
        if(self.name == "HOPE"):
            toExportTable = toExportTable.drop(toExportTable.columns[len(toExportTable.columns) - 1], axis=1)
            print(toExportTable)
            toExportTable.to_csv("BuggyFile.csv")
        toExportTable.to_sql("VIRTUE_" + self.name, con, if_exists="replace", index=True)


import pandas as pd
import numpy as np
import sqlite3

'''
This needs a far more sophisticated program than I anticipated:
	- 7 virtues + 5 passions + 1 basic 
	- Virtues and Passions should be split into 3 categories of precons etc, then 3 by person, then under precons 2 as 
	above or below 
	- 3 x 2 [Postconditions] + (3 x 3)[Precons]
	- Each of these will have the story action column just ripped across
	- Then, they will be committed to their respective tables

    - In practice, use this for passions as well, for the passions, too, for the division is identical

    0 : Precons Above
    1 : Precons Below
    2 : Precons Second Above
    3 : Precons Second Below 
    4 : Precons Third Above
    5 : Precons Third Below 
    6 : Postcons Accept
    7 : Postcons Second Accept
    8 : Postcons Third Accept
    9 : Postcons Reject
    10 : Postcons Second Reject
    11 : Postcons Third Reject

'''

'''
Redesigning the getting of columns:
    - Rather than go by column, go by rows
    - Extract the column headings, and see if they fit the dataframe
    - If they, concat them onto the side 

    That's a lot simpler 



'''


class PassionHolder:

    def __init__(self, storyIDColumn, virtueName):
        i = 0
        self.tables = []
        self.passions = ["ANGER", "DARING", "PLEASURE", "LOVE", "HOPE"]
        self.name = virtueName
        print(self.name)
        while i < 12:
            self.tables.append(pd.DataFrame({"STORY_ACTION_ID": storyIDColumn}))
            i = i + 1

    def convertToDataFrames(self, data):
        i = 0
        for table in self.tables:
            for col in data.columns:
                columnDictionary = {}
                needed = False
                necessarynames = []
                for passion in self.passions:
                    if passion in col and "VIRTUE" not in col:
                        print("Found! " + col)
                        needed = True
                        break
                if needed:
                    print("Col is here!")
                    print("Table " + str(i) + " " + self.name)
                    print(col)
                    if i == 0:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 1, "IS_POSTCONDITION_ACCEPT": 0,
                             "IS_POSTCONDITION_REJECT": 0, "SECOND_PERSON": 0, "THIRD_PERSON": 0,
                             "IS_ABOVE": 1})
                        necessarynames.append("PRECONDITION")
                        necessarynames.append("ABOVE")
                    elif i == 1:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 1, "IS_POSTCONDITION_ACCEPT": 0,
                             "IS_POSTCONDITION_REJECT": 0, "SECOND_PERSON": 0, "THIRD_PERSON": 0,
                             "IS_ABOVE": 0})
                        necessarynames.append("PRECONDITION")
                        necessarynames.append("BELOW")
                    elif i == 2:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 1, "IS_POSTCONDITION_ACCEPT": 0,
                             "IS_POSTCONDITION_REJECT": 0, "SECOND_PERSON": 1, "THIRD_PERSON": 0,
                             "IS_ABOVE": 1})
                        necessarynames.append("PRECONDITION")
                        necessarynames.append("ABOVE")
                        necessarynames.append("SECOND_PERSON")
                    elif i == 4:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 1, "IS_POSTCONDITION_ACCEPT": 0,
                             "IS_POSTCONDITION_REJECT": 0, "SECOND_PERSON": 0, "THIRD_PERSON": 1,
                             "IS_ABOVE": 1})
                        necessarynames.append("PRECONDITION")
                        necessarynames.append("ABOVE")
                        necessarynames.append("THIRD_PERSON")

                    elif i == 3:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 1, "IS_POSTCONDITION_ACCEPT": 0,
                             "IS_POSTCONDITION_REJECT": 0, "SECOND_PERSON": 1, "THIRD_PERSON": 0,
                             "IS_ABOVE": 0})
                        necessarynames.append("PRECONDITION")
                        necessarynames.append("BELOW")
                        necessarynames.append("SECOND_PERSON")
                    elif i == 5:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 1, "IS_POSTCONDITION_ACCEPT": 0,
                             "IS_POSTCONDITION_REJECT": 0, "SECOND_PERSON": 0, "THIRD_PERSON": 1,
                             "IS_ABOVE": 0})
                        necessarynames.append("PRECONDITION")
                        necessarynames.append("BELOW")
                        necessarynames.append("THIRD_PERSON")
                    elif i == 6:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 0, "IS_POSTCONDITION_ACCEPT": 1,
                             "IS_POSTCONDITION_REJECT": 0, "SECOND_PERSON": 0, "THIRD_PERSON": 0,
                             "IS_ABOVE": 0})
                        necessarynames.append("POSTCONDITION")
                        necessarynames.append("ACCEPT")
                    elif i == 7:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 0, "IS_POSTCONDITION_ACCEPT": 1,
                             "IS_POSTCONDITION_REJECT": 0, "SECOND_PERSON": 1, "THIRD_PERSON": 0,
                             "IS_ABOVE": 0})
                        necessarynames.append("POSTCONDITION")
                        necessarynames.append("ACCEPT")
                        necessarynames.append("SECOND_PERSON")
                    elif i == 8:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 0, "IS_POSTCONDITION_ACCEPT": 1,
                             "IS_POSTCONDITION_REJECT": 0, "SECOND_PERSON": 0, "THIRD_PERSON": 1,
                             "IS_ABOVE": 0})
                        necessarynames.append("POSTCONDITION")
                        necessarynames.append("ACCEPT")
                        necessarynames.append("THIRD_PERSON")
                    elif i == 9:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 0, "IS_POSTCONDITION_ACCEPT": 0,
                             "IS_POSTCONDITION_REJECT": 1, "SECOND_PERSON": 0, "THIRD_PERSON": 0,
                             "IS_ABOVE": 0})
                        necessarynames.append("POSTCONDITION")
                        necessarynames.append("REJECT")
                    elif i == 11:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 0, "IS_POSTCONDITION_ACCEPT": 0,
                             "IS_POSTCONDITION_REJECT": 1, "SECOND_PERSON": 0, "THIRD_PERSON": 0,
                             "IS_ABOVE": 0})
                        necessarynames.append("POSTCONDITION")
                        necessarynames.append("REJECT")
                        necessarynames.append("THIRD_PERSON")
                    elif i == 10:
                        columnDictionary.update(
                            {"IS_PRECONDITION": 0, "IS_POSTCONDITION_ACCEPT": 0,
                             "IS_POSTCONDITION_REJECT": 1, "SECOND_PERSON": 0, "THIRD_PERSON": 0,
                             "IS_ABOVE": 0})
                        necessarynames.append("POSTCONDITION")
                        necessarynames.append("REJECT")
                        necessarynames.append("SECOND_PERSON")
                    print(necessarynames)
                    for necessary in necessarynames:
                        print(necessary)
                        if necessary not in col:
                            if i == 8:
                                print("Triggered on 8 Necessary: " + necessary + " col : " + col)
                            needed = False
                            break
                        if not needed and i == 8:
                            print("8 is fully false")
                    if needed:
                        splitName = col.split("_")
                        print(splitName)
                        newName = ""
                        isPassion = False
                        try:
                           for check in splitName:
                               if check == "ANGER" or check == "DARING" or check == "PLEASURE" or check == "LOVE" or check == "HOPE":
                                   newName = check
                                   print("Found a passion " + newName)
                                   isPassion = True
                                   break
                        except IndexError:
                            print("Simply the subvirtue or subvice")
                        if not isPassion: #I.E It's not a passion
                            break
                        temptable = pd.DataFrame({newName: data[col]})
                        temptable = temptable.replace(np.NaN, 0)
                        temporaryDataFrame = pd.DataFrame()
                        j = 0
                        while j < len(self.tables[i]):
                            repeatedDataframe = pd.DataFrame(columnDictionary, index=[j])
                            temporaryDataFrame = pd.concat([temporaryDataFrame, repeatedDataframe])
                            j = j + 1
                        self.tables[i] = pd.concat([self.tables[i], temptable], axis=1)
                        self.tables[i] = pd.concat([self.tables[i], temporaryDataFrame], axis=1)
                        self.tables[i] = self.tables[i].loc[:, ~self.tables[i].columns.duplicated()]  # Thanks stack overflow
                        print(self.tables[i])
            i = i + 1

    def exportToCSV(self, address):
        i = 0
        for table in self.tables:
            print("Table " + str(i))
            print(table)
            filename = address + "/" + self.name + str(i) + ".csv"
            print(filename)
            table.to_csv(filename)
            i = i + 1

    def exportToSQL(self, con):
        i = 0
        toExportTable = pd.DataFrame()
        for table in self.tables:
            toExportTable = pd.concat([toExportTable, table], ignore_index=True)
            i = i + 1
        print(self.name)
        if (self.name == "HOPE"):
            toExportTable = toExportTable.drop(toExportTable.columns[len(toExportTable.columns) - 1], axis=1)
            print(toExportTable)
            toExportTable.to_csv("BuggyFile.csv")
        toExportTable.to_sql(self.name, con, if_exists="replace", index=True)


