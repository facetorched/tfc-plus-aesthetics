filename = input("enter obj filename: ") + ".obj"
a_file = open(filename, "r")
part_list = []
list_of_lines = a_file.readlines()
for i in range(len(list_of_lines)):
    if "mtllib" in list_of_lines[i]:
        list_of_lines[i] = ""
        continue
    word_list = list_of_lines[i].split()
    for j in range(len(word_list)):
        if "e-" in word_list[j]:
            word_list[j] = "0"
        if word_list[j] == "usemtl":
            word_list[j] = "g"
            part_list += [word_list[j+1]]

    list_of_lines[i] = " ".join(word_list) + '\n'

# function to get unique values
def unique(list1):
    unique_list = []
    for x in list1:
        if x not in unique_list:
            unique_list.append(x)
    return unique_list

part_list = unique(part_list)
part_dict = {}
for part in part_list:
    value = input("enter a name for part "+part+" :")
    part_dict[part] = value

for i in range(len(list_of_lines)):
    for key in part_dict:
        list_of_lines[i] = list_of_lines[i].replace(key, part_dict[key])

a_file = open(filename, "w")
a_file.writelines(list_of_lines)
a_file.close()