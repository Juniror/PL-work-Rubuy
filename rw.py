filename = input("Enter the file name to work with (.txt): ")

while True:
    try:
        with open(filename, "r+") as file:
            command = input("read or write or exit : ")

            if command == "read":
                file.seek(0)
                print(file.read())

            elif command == "write":
                text = input("What do u want write : ")
                file.write(text)

            elif command == "exit":
                break

    except FileNotFoundError:
        print(f"Error: File '{filename}' was not found!")
        break