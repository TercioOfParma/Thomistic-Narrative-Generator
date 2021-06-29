import tkinter as tk
from tkinter import ttk #Additional themed Widgets

def button_clicked():
    print("Clicked!")

def generation_options():
    generation = tk.Tk()
    tk.Label(generation, text="Generation Options").pack()
    generation.mainloop()

def story_action_options():
    story_action = tk.Tk()
    tk.Label(story_action,text="Add a Story Action").pack()
    ttk.Button(story_action, text="Preconditions").pack()
    ttk.Button(story_action, text="Postconditions Accept").pack()
    ttk.Button(story_action, text="Postconditions Reject").pack()
    story_action.mainloop()

root = tk.Tk() #The main window

message = tk.Label(root, text="Narrative Generation Editor") #Label on that window
message.pack()#Places it in the main window
ttk.Button(root, text="Click Me!", command=button_clicked).pack()

ttk.Button(root, text="Generation Options", command=generation_options).pack()
ttk.Button(root,text="Add a Story Action", command=story_action_options).pack()

root.mainloop()
