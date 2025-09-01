=begin
https://www.geeksforgeeks.org/ruby/file-handling-in-ruby/ 
https://www.techotopia.com/index.php/Working_with_Files_in_Ruby
=end

def rwCheck()
  puts File.file?("io.txt")
  puts File.readable?("io.txt")
  puts File.writable?("io.txt")  
end 

def rwFile
  print "What name of file to work with (.txt) : "
  name = gets.chomp
  while true 
    puts 'What your Command
1.read
2.write
3.append
4.delete
5.change name
6.exit'
    print "Type number or keyword : "
    command = gets.chomp 
    case command
      # read
      when "read" , "1"
        if !File.file?(name) 
          puts "Create the file first"
        end
        fileobject = File.new(name, "r")
        puts fileobject.read
        fileobject.close

      # write start
      when "write", "2"
        fileobject = File.new(name, "w")
        puts "What text do u want to replace"
        newText = gets.chomp
        fileobject.syswrite(newText)
        fileobject.close

      # write end
      when "append", "3"
        fileobject = File.new(name, "a")
        puts "What text do u want to add"
        added = gets.chomp
        fileobject.syswrite(added)
        fileobject.close
      
      when "delete", "4"
        puts File.delete(name)
      
      when "change name", "5"
        puts "Name want to be change"
        newName = gets.chomp
        File.rename(name,newName)
        puts "Success rename"

      when "exit", "6"
        break
      else
        puts "Unknown command!"
      end
      puts ""
    end
end

rwFile
