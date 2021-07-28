import pandas as pd
import numpy as np
import sqlite3

class virtueHolder:

    def __init__(self):
        i = 0
        tables = []
        while i < 15:
            tables.append(pd.DataFrame())
            print(str(i))
            i = i + 1
        print("Done!")
