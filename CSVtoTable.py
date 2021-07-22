import pandas as pd
import sqlite3


JSONData = pd.read_csv('story_actions.csv')
con = sqlite3.connect('ThomisticNarrativeDB.db')
c = con.cursor()

JSONData.to_sql('ImportedCSV', con, if_exists='append', index = False)
