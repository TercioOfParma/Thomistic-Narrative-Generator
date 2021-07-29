import pandas as pd
import numpy as np
import sqlite3
import virtueHolder as vt

#
#   General Algorithm Mark 1:
#       1) Split column names based on the underscores
#       2) Then, classify whether it's preconditions, postconditions,
#           2.1) ids are added by default, as STORY_ACTION_ID
#           2.2) If it's accept or reject, then classify further on that
#           2.3) If neither, go to the basic information set
#       3) Choose table based on VIRTUE and The name after it
#       4) Choose column based on the SUBVIRTUE/SUBVIRTUE and name concatenated
#       5) SECOND_PERSON and THIRD_PERSON define those as True under separate headings
#       6) Same with ABOVE and BELOW


'''
    The above algorithm fails, here is mark 2:
    1. Create 8 virtueHolder Objects
    2. Generate them with the STORY_ACTION_ID column

'''


# What may be wise is to generate each of the columns as a dictionary, and then generate dataframes and then export
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






isPrecondition = False
isPostconditionaccept = False
isPostconditionreject = False
isAbove = False
isBelow = False
isSecondPerson = False
isThirdPerson = False
TABLE_NAME_VIRTUE = ""
COLUMN_NAME_SUBVIRTUE = ""

CSVData = pd.read_csv('story_actions.csv')

VIRTUE_FAITH = pd.DataFrame({"STORY_ACTION_ID" : CSVData["id"]})
VIRTUE_HOPE = pd.DataFrame({"STORY_ACTION_ID" : CSVData["id"]})
VIRTUE_CHARITY = pd.DataFrame({"STORY_ACTION_ID" : CSVData["id"]})
VIRTUE_FORTITUDE = pd.DataFrame({"STORY_ACTION_ID" : CSVData["id"]})
VIRTUE_PRUDENCE = pd.DataFrame({"STORY_ACTION_ID" : CSVData["id"]})
VIRTUE_JUSTICE = pd.DataFrame()
VIRTUE_TEMPERANCE = pd.DataFrame({"STORY_ACTION_ID" : CSVData["id"]})
PASSIONS = pd.DataFrame({"STORY_ACTION_ID" : CSVData["id"]})
BASICDATA = pd.DataFrame({"ID" : CSVData["id"],"TYPE" : CSVData["TYPE"], "QUOTES_SCRIPTURE": CSVData["QUOTES_SCRIPTURE"]})
SCRIPTUREBANK = pd.DataFrame({"STORY_ACTION_ID" : CSVData["id"], "VersesList": CSVData["SCRIPTURE_BANKVERSES"]})
currentTable = {}

#test = vt.VirtueHolder(CSVData["id"])

for col in CSVData:
    print(col)
    isPrecondition = False
    isPostconditionaccept = False
    isPostconditionreject = False
    isAbove = False
    isBelow = False
    isSecondPerson = False
    isThirdPerson = False
    if "PRECONDITIONS" in col:
        isPrecondition = True
    elif "POSTCONDITIONS" in col:
        if "ACCEPT" in col:
            isPostconditionaccept = True
        else:
            isPostconditionreject = True
    if "ABOVE" in col:
        isAbove = True
    if "BELOW" in col:
        isBelow = True
    if "SECOND_PERSON" in col:
        isSecondPerson = True
    if "THIRD_PERSON" in col:
        isThirdPerson = True
    VIRTUE_JUSTICE = VIRTUE_JUSTICE.append({"IS_PRECONDITION": isPrecondition, "IS_POSTCONDITION_REJECT": isPostconditionreject,
                           "IS_POSTCONDITION_ACCEPT": isPostconditionaccept, "ABOVE":
                               isAbove, "BELOW": isBelow, "SECOND_PERSON": isSecondPerson,
                           "THIRD_PERSON": isThirdPerson}, ignore_index= True)
print(VIRTUE_JUSTICE)
VIRTUE_JUSTICE.to_csv("JusticeTest.csv")
quit()
VIRTUE_JUSTICE = pd.DataFrame({"STORY_ACTION_ID" : CSVData["id"]})
for col in CSVData:
    isPrecondition = False
    isPostconditionaccept = False
    isPostconditionreject = False
    isAbove = False
    isBelow = False
    isSecondPerson = False
    isThirdPerson = False
    if "PRECONDITIONS" in col:
        isPrecondition = True
    elif "POSTCONDITIONS" in col:
        if "ACCEPT" in col:
            isPostconditionaccept = True
        else:
            isPostconditionreject = True
    if "ABOVE" in col:
        isAbove = True
    if "BELOW" in col:
        isBelow = True
    if "SECOND_PERSON" in col:
        isSecondPerson = True
    if "THIRD_PERSON" in col:
        isThirdPerson = True
    if "VIRTUE_JUSTICE" in col:
        VIRTUE_JUSTICE.append({"IS_PRECONDITION": isPrecondition, "IS_POSTCONDITION_REJECT": isPostconditionreject,
                               "IS_POSTCONDITION_ACCEPT": isPostconditionaccept, "ABOVE":
                                   isAbove, "BELOW": isBelow, "SECOND_PERSON": isSecondPerson,
                               "THIRD_PERSON": isThirdPerson}, ignore_index=True)
        sortColumn(VIRTUE_JUSTICE, CSVData, col)

    elif "VIRTUE_TEMPERANCE" in col:
        VIRTUE_TEMPERANCE.append({"IS_PRECONDITION" : isPrecondition, "IS_POSTCONDITION_REJECT": isPostconditionreject, "IS_POSTCONDITION_ACCEPT": isPostconditionaccept, "ABOVE" :
                                                isAbove, "BELOW": isBelow, "SECOND_PERSON": isSecondPerson, "THIRD_PERSON": isThirdPerson}, ignore_index=True)
        sortColumn(VIRTUE_TEMPERANCE, CSVData, col)
    elif "VIRTUE_PRUDENCE" in col:
        VIRTUE_PRUDENCE.append({"IS_PRECONDITION" : isPrecondition, "IS_POSTCONDITION_REJECT": isPostconditionreject, "IS_POSTCONDITION_ACCEPT": isPostconditionaccept, "ABOVE" :
                                                isAbove, "BELOW": isBelow, "SECOND_PERSON": isSecondPerson, "THIRD_PERSON": isThirdPerson}, ignore_index=True)
        sortColumn(VIRTUE_PRUDENCE, CSVData, col)
    elif "VIRTUE_FORTITUDE" in col:
        VIRTUE_FORTITUDE.append({"IS_PRECONDITION" : isPrecondition, "IS_POSTCONDITION_REJECT": isPostconditionreject, "IS_POSTCONDITION_ACCEPT": isPostconditionaccept, "ABOVE" :
                                                isAbove, "BELOW": isBelow, "SECOND_PERSON": isSecondPerson, "THIRD_PERSON": isThirdPerson}, ignore_index=True)
        sortColumn(VIRTUE_FORTITUDE, CSVData, col)
    elif "VIRTUE_CHARITY" in col:
        VIRTUE_CHARITY.append({"IS_PRECONDITION" : isPrecondition, "IS_POSTCONDITION_REJECT": isPostconditionreject, "IS_POSTCONDITION_ACCEPT": isPostconditionaccept, "ABOVE" :
                                                isAbove, "BELOW": isBelow, "SECOND_PERSON": isSecondPerson, "THIRD_PERSON": isThirdPerson}, ignore_index=True)
        sortColumn(VIRTUE_CHARITY, CSVData, col)
    elif "VIRTUE_HOPE" in col:
        VIRTUE_HOPE.append({"IS_PRECONDITION" : isPrecondition, "IS_POSTCONDITION_REJECT": isPostconditionreject, "IS_POSTCONDITION_ACCEPT": isPostconditionaccept, "ABOVE" :
                                                isAbove, "BELOW": isBelow, "SECOND_PERSON": isSecondPerson, "THIRD_PERSON": isThirdPerson}, ignore_index=True)
        sortColumn(VIRTUE_HOPE, CSVData, col)
    elif "VIRTUE_FAITH" in col:
        VIRTUE_FAITH.append({"IS_PRECONDITION" : isPrecondition, "IS_POSTCONDITION_REJECT": isPostconditionreject, "IS_POSTCONDITION_ACCEPT": isPostconditionaccept, "ABOVE" :
                                                isAbove, "BELOW": isBelow, "SECOND_PERSON": isSecondPerson, "THIRD_PERSON": isThirdPerson}, ignore_index=True)
        sortColumn(VIRTUE_FAITH, CSVData, col)

print("PRUDENCE DICTIONARY")
print(VIRTUE_PRUDENCE)

con = sqlite3.connect('ThomisticNarrativeDB.db')
c = con.cursor()

toExport = pd.DataFrame.from_dict(VIRTUE_PRUDENCE)
toExport.to_sql('VIRTUE_PRUDENCE', con, if_exists='replace', index = False)
toExport = pd.DataFrame.from_dict(VIRTUE_FAITH)
toExport.to_sql('VIRTUE_FAITH', con, if_exists='replace', index = False)
toExport = pd.DataFrame.from_dict(VIRTUE_HOPE)
toExport.to_sql('VIRTUE_HOPE', con, if_exists='replace', index = False)
toExport = pd.DataFrame.from_dict(VIRTUE_CHARITY)
toExport.to_sql('VIRTUE_CHARITY', con, if_exists='replace', index = False)
toExport = pd.DataFrame.from_dict(VIRTUE_TEMPERANCE)
toExport.to_sql('VIRTUE_TEMPERANCE', con, if_exists='replace', index = False)
toExport = pd.DataFrame.from_dict(VIRTUE_FORTITUDE)
toExport.to_sql('VIRTUE_FORTITUDE', con, if_exists='replace', index = False)
toExport = pd.DataFrame.from_dict(VIRTUE_JUSTICE)
toExport.to_sql('VIRTUE_JUSTICE', con, if_exists='replace', index = False)

