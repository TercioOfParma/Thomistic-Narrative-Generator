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
names = ["PRUDENCE", "JUSTICE", "FORTITUDE", "TEMPERANCE", "FAITH", "HOPE", "CHARITY", "PASSIONS"]
while i < 8:
    #virtues.append(vt.VirtueHolder(CSVData["id"], names[i]))
    #virtues[i].convertToDataFrames(CSVData)
    #virtues[i].exportToCSV("ExportTestPythonAlgor")
    #virtues[i].exportToSQL(con)
    i = i + 1

virtues[0].tables[7].to_csv("Unambiguous Filename.csv")
