import os

input_file = input("Enter the path to the input file: ")
output_file = os.path.splitext(input_file)[0] + "_sorted.txt"

with open(input_file, "r", encoding="utf-8") as file:
    content = file.read()
entries = []
for entry_ind, entry in enumerate(content.split(";")):
    if entry == "":
        continue
    if entry_ind != 0:
        newline_ind = entry.find("aths")
        new_entry = "\n\t\t" + entry[newline_ind:]
    else:
        new_entry = entry
    new_entry = new_entry.replace("”", "\"").replace("“", "\"").replace("’", "'").replace("‘", "'")
    entries.append(new_entry)
sorted_entries = sorted(entries)

with open(output_file, "w") as file:
    file.write(";".join(sorted_entries) + ";")