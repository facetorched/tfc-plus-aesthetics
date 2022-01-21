import math


filename = input("enter obj filename: ") + ".obj"
partname = input("enter part name: ")
old_width = input("old width: ")
new_width = input("new width: ")

stretch_factor = (int(old_width) - .01) / int(new_width)

print("stretch factor = " + str(stretch_factor))

a_file = open(filename, "r")
list_of_lines = a_file.readlines()
a_file.close()

i = len(list_of_lines) - 1

while i >= 0:
    if list_of_lines[i] == "g " + partname + "\n" and i > 0:
        print("found one on line " + str(i))
        i-=1
        while list_of_lines[i][0] != "g":
            if list_of_lines[i][0:2] == "vt":
                word_list = list_of_lines[i].split()
                word_list[1] = str(float(word_list[1]) * stretch_factor)
                #word_list[2] = str(float(word_list[2]) * stretch_factor)
                list_of_lines[i] = " ".join(word_list) + '\n'

            i -= 1
    else:
        i -= 1

a_file = open(filename, "w")
a_file.writelines(list_of_lines)
a_file.close()

