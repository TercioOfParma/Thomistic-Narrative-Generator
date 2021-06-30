import tkinter as tk
from tkinter import ttk #Additional themed Widgets

def button_clicked():
    print("Clicked!")

def generation_options():
    generation = tk.Tk()
    tk.Label(generation, text="Generation Options").pack()
    tk.Checkbutton(generation, text="Charity").pack()
    tk.Checkbutton(generation, text="Faith").pack()
    tk.Checkbutton(generation, text="Hope").pack()
    tk.Checkbutton(generation, text="Prudence").pack()
    tk.Checkbutton(generation, text="Temperance").pack()
    tk.Checkbutton(generation, text="Justice").pack()
    tk.Checkbutton(generation, text="Fortitude").pack()
    generation.mainloop()

def story_action_options():
    story_action = tk.Tk()
    tk.Label(story_action,text="Add a Story Action").pack()
    ttk.Button(story_action, text="Preconditions", command=preconditions).pack()
    ttk.Button(story_action, text="Postconditions Accept", command=postconditions_accept).pack()
    ttk.Button(story_action, text="Postconditions Reject", command=postconditions_reject).pack()
    story_action.mainloop()

def preconditions():
    preconditions = tk.Tk()
    tk.Label(preconditions,text="Preconditions").pack()
    preconditions.mainloop()

def postconditions_accept():
    postconditions = tk.Tk()
    tk.Label(postconditions,text="Post conditions on Acceptance of Grace").pack()
    postconditions.mainloop()    
def postconditions_reject():
    postconditions = tk.Tk()
    tk.Label(postconditions,text="Post conditions on Rejection of Grace").pack()
    postconditions.mainloop()    
root = tk.Tk() #The main window

message = tk.Label(root, text="Narrative Generation Editor") #Label on that window
message.pack()#Places it in the main window
ttk.Button(root, text="Click Me!", command=button_clicked).pack()

ttk.Button(root, text="Generation Options", command=generation_options).pack()
ttk.Button(root,text="Add a Story Action", command=story_action_options).pack()

root.mainloop()
