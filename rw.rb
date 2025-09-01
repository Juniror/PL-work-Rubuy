def rwCheck()
  puts File.file?("example.txt")
  puts File.readable?("example.txt")
  puts File.writable?("example.txt")  
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
      when "read" , "1"
        begin
          if !File.file?(name) 
            puts "Create the file first"
          else
            fileobject = File.new(name, "r")
            puts fileobject.read
            fileobject.close
          end
        rescue => e
          puts "Error: #{e.message}"
        end

      when "write", "2"
        begin
          fileobject = File.new(name, "w")
          puts "What text do u want to replace"
          newText = gets.chomp
          fileobject.syswrite(newText)
          fileobject.close
        rescue => e
          puts "Error: #{e.message}"
        end

      when "append", "3"
        begin
          fileobject = File.new(name, "a")
          puts "What text do u want to add"
          added = gets.chomp
          fileobject.syswrite(added)
          fileobject.close
        rescue => e
          puts "Error: #{e.message}"
        end

      when "delete", "4"
        begin
          puts File.delete(name)
        rescue => e
          puts "Error: #{e.message}"
        end
      
      when "change name", "5"
        begin
          puts "Name want to be change"
          newName = gets.chomp
          File.rename(name,newName)
          name = newName
          puts "Success rename"
        rescue => e
          puts "Error: #{e.message}"
        end

      when "exit", "6"
        break
      else
        puts "Unknown command!"
      end
      puts ""
    end
end

rwFile
