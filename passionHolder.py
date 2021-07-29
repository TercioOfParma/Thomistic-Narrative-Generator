import pandas as pd
import numpy as np
import sqlite3
'''
This needs a far more sophisticated program than I anticipated:
	- 7 virtues + 5 passions + 1 basic 
	- Virtues and Passions should be split into 3 categories of precons etc, then 3 by person, then under precons 2 as 
	above or below 
	- 3 x 2 [Postconditions] + (3 x 3)[Precons] <-- It's the same as a virtue basically 
	- Each of these will have the story action column just ripped across
	- Then, they will be committed to their respective tables


'''
class passionHolder:

    def __init__(self):
        i = 0
        tables = []
        while i < 15:
            tables.append(pd.DataFrame())
            print(str(i))
            i = i + 1
        print("Done!")
