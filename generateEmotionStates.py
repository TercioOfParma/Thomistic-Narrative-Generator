import sqlite3 as sql


newRelationshipTypes = ["RELATIONSHIP_STATE", "VIRTUE_STATE", "PASSION_STATE"]
tableNames = ["'BasicInfo'", "'PASSIONS'", "'VIRTUE_CHARITY'","'VIRTUE_FAITH'","'VIRTUE_HOPE'","'VIRTUE_FORTITUDE'", "'VIRTUE_PRUDENCE'", "'VIRTUE_JUSTICE'", "'VIRTUE_TEMPERANCE'"]
degreeNames = ["VERY_HIGH", "HIGH", "LOW", "LOW_VICE", "HIGH_VICE", "VERY_HIGH_VICE"]
degrees = {"VERY_HIGH": 70, "HIGH": 40, "LOW": 15, "LOW_VICE": -15, "HIGH_VICE": -40, "VERY_HIGH_VICE": -70}
degreePrefixVirtues = ["Due to an abundant habit of ", "Due to a habit of ", "Due to some habit of ", "Due to a slight dislike of ", "Due to a dislike of ", "Due to a hatred of "]
degreePrefixEmotions = ["Due to an overpowering feeling of ", "Due to the strength of ", "Due to feeling ", "Due to a lack of ", "Due to feeling the lack of ", "Due to a profound absence of the feeling of "]


db = sql.connect("ThomisticNarrativeDB.db")
cursor = db.cursor()

for tables in tableNames:
    query = "SELECT * FROM PRAGMA_TABLE_INFO(?)"
    query = query.replace("?", tables)
    cursor.execute(query)
    print(query)
    tableInfo = cursor.fetchall()
    for row in tableInfo:
        threshold = 70
        i = 0
        for degree in degreeNames:
            name = row[1]
            virtue = degreePrefixVirtues[i]
            if "VIRTUE" in name or "VICE" in name:
                StoryActionID = degree + "_" + virtue
                basicInsertQuery = "INSERT INTO BasicInfo (TYPE, id, POSTCONDITIONS_ACCEPT_OUTPUT) VALUES ('VIRTUE_STATE', '" + (degree + "_" + name + "',")
                specificQuery = "INSERT INTO " + tables.replace("'","") + " (STORY_ACTION_ID, IS_ABOVE,IS_PRECONDITION, " + name + ") VALUES ("
                virtueFeeling = virtue + name.replace("VIRTUE","").replace("SUB","").replace("VICE","").replace("_","").lower() + ","
                basicInsertQuery = basicInsertQuery + "'" + virtueFeeling + "')"
                specificQuery = specificQuery + "'" + (degree + "_" + name + "',") + "1,1," + str(threshold) + ")"
                print(basicInsertQuery)
                print(specificQuery)
                cursor.execute(basicInsertQuery)
                cursor.execute(specificQuery)
            emotion = degreePrefixVirtues[i]
            if "ANGER" in name or "HOPE" in name or "PLEASURE" in name or "LOVE" in name or "DARING" in name:
                StoryActionID = degree + "_" + emotion
                basicInsertQuery = "INSERT INTO BasicInfo (TYPE, id, POSTCONDITIONS_ACCEPT_OUTPUT) VALUES ('PASSION_STATE', '" + (degree + "_" + name + "',")
                specificQuery = "INSERT INTO " + tables.replace("'","") + " (STORY_ACTION_ID, IS_ABOVE,IS_PRECONDITION," + name + ") VALUES ("
                virtueFeeling = emotion + name.replace("VIRTUE","").replace("SUB","").replace("VICE","").replace("_","").lower() + ","
                basicInsertQuery = basicInsertQuery + "'" + virtueFeeling + "')"
                specificQuery = specificQuery + "'" + (degree + "_" + name + "',") + "1,1," + str(threshold) + ")"
                basicRelationInsertQuery = "INSERT INTO BasicInfo (TYPE, id, POSTCONDITIONS_ACCEPT_OUTPUT) VALUES ('RELATIONSHIP_STATE', '" + (degree + "_" + name + "',")
                specificRelationQuery = "INSERT INTO " + tables.replace("'","") + " (STORY_ACTION_ID,  IS_ABOVE,IS_PRECONDITION, " + name + ") VALUES ("
                virtueRelationFeeling = virtue + name.replace("VIRTUE","").replace("SUB","").replace("VICE","").replace("_","").lower() + ","
                basicRelationInsertQuery = basicRelationInsertQuery + "'" + virtueRelationFeeling + "')"
                specificRelationQuery = specificRelationQuery + "'" + (degree + "_" + name + "',") + "1,1," + str(threshold) + ")"
                print(basicInsertQuery)
                print(specificQuery)
                print(basicRelationInsertQuery)
                print(specificRelationQuery)
                cursor.execute(basicInsertQuery)
                cursor.execute(specificQuery)
                cursor.execute(basicRelationInsertQuery)
                cursor.execute(specificRelationQuery)
            threshold = threshold - 30
            i = i + 1
db.commit()
                                                                                
