import pandas as pd
import numpy as np
import sqlite3
import virtueHolder as vt


'''
    The other algorithm fails, here is mark 2:
    1. Create 8 virtueHolder Objects
    2. Generate them with the STORY_ACTION_ID column
        0 : Prudence
        1 : Justice
        2 : Fortitude
        3 : Temperance
        4 : Faith
        5 : Hope
        6 : Charity 
'''

def sortColumn(dictionary, data, columnName):
    splitName = columnName.split("_")
    searchStrings = ["SUBVIRTUE", "SUBVICE"]
    newColumnName = ""
    for search in searchStrings:
        print(splitName)
        if search in splitName:
            newColumnName = search
            try:
                if(splitName[splitName.index(search) + 1] not in "SECOND" and splitName[splitName.index(search) + 1] not in "THIRD" and splitName[splitName.index(search) + 1] not in "ABOVE" and splitName[splitName.index(search) + 1] not in "BELOW"):
                    newColumnName = search + "_" + (splitName[splitName.index(search) + 1])
            except IndexError:
                print("Simply the subvirtue or subvice")
            print("New Column Name: " + newColumnName)
            dictionary.update({newColumnName : data[columnName]})



CSVData = pd.read_csv('story_actions.csv')
con = sqlite3.connect('ThomisticNarrativeDB.db')
c = con.cursor()
i = 0
virtues = []
basicInfo = pd.concat([CSVData["id"], CSVData["TYPE"], CSVData["POSTCONDITIONS_ACCEPT_OUTPUT"], CSVData["POSTCONDITIONS_REJECT_OUTPUT"], CSVData["SECOND_PERSON"] ,CSVData["THIRD_PERSON"], CSVData["QUOTES_SCRIPTURE"], CSVData["SCRIPTURE_BANK_VERSES"],
                       CSVData["POSTCONDITIONS_ACCEPT_CONSEQUENTIAL_ACTIONS"], CSVData["POSTCONDITIONS_REJECT_CONSEQUENTIAL_ACTIONS"]],axis=1)
basicInfo = basicInfo.replace(np.NaN, "")
basicInfo.to_sql("BasicInfo", con, if_exists="replace", index=True)
basicInfo.to_csv("BasicInfo.csv")
names = ["PRUDENCE", "JUSTICE", "FORTITUDE", "TEMPERANCE", "FAITH", "HOPE", "CHARITY"]
while i < 7:
    virtues.append(vt.VirtueHolder(CSVData["id"], names[i]))
    virtues[i].convertToDataFrames(CSVData)
    virtues[i].exportToCSV("ExportTestPythonAlgor")
    virtues[i].exportToSQL(con)
    i = i + 1

passions = vt.PassionHolder(CSVData["id"], "PASSIONS")
passions.convertToDataFrames(CSVData)
passions.exportToCSV("ExportTestPythonAlgor")
passions.exportToSQL(con)

#virtues[0].tables[7].to_csv("Unambiguous Filename.csv")
