# Read Write File with Ruby  
> หากยังไม่ได้ติดตั้ง Ruby สามารถติดตั้งได้ที่ [RubyInstaller](https://rubyinstaller.org/)

เนื้อหาในที่นี้เราจะพูดถึงการอ่านและการเขียนไฟล์ผ่าน File class ของ ruby เป็นหลัก
### Read
> output ของ read ทั้งหมดจะเป็น Hello world เนื่องจากใน example.txt มีข้อความ Hello world

ในการทำอะไรกับไฟล์เราจำเป็นต้องกำหนดสิทธิในการเข้าถึงไฟล์นั้นๆก่อนด้วย เช่น **r**, **w**, **a**  
และการอ่านจะสามารถทำได้จาก 2 คลาส คือ  

1. **File**  
2. **IO**  

> IO เป็นคลาสแม่ของ File  

วิธีการอ่านไฟล์มีหลายแบบ เช่น:  
- **อ่านทั้งไฟล์ทีเดียว** → `File.read` หรือ `IO.read` (คืนค่าเป็น String และโหลดเข้า memory ทั้งหมด)  
- **อ่านทีละบรรทัด/ทีละตัว** → `IO.foreach`, `file.each_line`, `file.gets` (ประหยัด memory เพราะไม่โหลดทั้งหมด)  


#### ประเภทของการอ่าน  

| Mode   | Description |
|--------|-------------|
| `"r"`   | อ่านเท่านั้น pointer ชี้จุดเริ่มต้นของไฟล์ |
| `"r+"`  | อ่านและเขียน pointer ชี้จุดเริ่มต้นของไฟล์ |
| `"w"`   | เขียนเท่านั้น หากไม่มีไฟล์จะทำการเขียนขึ้นมาใหม่ หากมีอยู่แล้วจะเขียนทับ |
| `"w+"`  | อ่านและเขียน หากไม่มีไฟล์จะทำการเขียนขึ้นมาใหม่ หากมีอยู่แล้วจะเขียนทับ |
| `"a"`   | เขียนเท่านั้น pointer ชี้ท้ายของไฟล์ หากไม่มีไฟล์จะเขียนมาใหม่ |
| `"a+"`  | อ่านและเขียน pointer ชี้ท้ายของไฟล์ หากไม่มีไฟล์จะเขียนมาใหม่ |
| `"b"`   | ใช้เป็นโหมด binary เช่น `rb`, `wb` |


### File
เป็นคลาสย่อยของ IO และใช้เพื่ออ่านไฟล์โดยเฉพาะ  
ต่างจาก IO ที่ใช้ในระดับ OS  

```ruby
fileobject = File.new("example.txt", "r")

# อ่านทั้งไฟล์ 
puts fileobject.read

# อ่านทีละบรรทัด
fileobject.each { |line| puts line }

fileobject.close
```
### IO 
ไม่ค่อยนิยมใช้ในการอ่านไฟล์ เพราะใช้ยาก ในที่นี้จะใช้ File แทนทั้งหมด
```ruby
fd = IO.sysopen("example.txt", "r")  # เปิดไฟล์แบบ system call
fileByIO = IO.new(fd)                # สร้าง IO object จาก fd

# อ่านทั้งไฟล์
puts fileByIO.read

fileByIO.rewind  # ย้าย pointer กลับไปต้นไฟล์

# อ่านทีละบรรทัด
fileByIO.readlines.each { |line| puts line }

fileByIO.close
```


### Compare to other language
  <details>
    <summary>Ruby</summary>
  
  ```ruby
    begin
      fileobject = File.new("example.txt", "r+")
    
      # Read
      puts fileobject.read
    
      # Write
      puts "What text do you want to replace:"
      fileobject.syswrite("new Text")
    
    rescue Errno::ENOENT
      puts "Error: File not found!"
    
    rescue Errno::EACCES
      puts "Error: Permission denied!"
    
    rescue => e
      puts "Unexpected error: #{e.message}"
    
    ensure
      fileobject.close if fileobject
    end
   ```
  </details>
<details>
  <summary>Java</summary>

```java
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class rw {
    public static void main(String[] args) {
        // Read
        try (FileReader fr = new FileReader("example.txt")) {
            int ch;
            while ((ch = fr.read()) != -1) {
                System.out.print((char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write
        try (FileWriter fw = new FileWriter("example.txt")) {
            fw.write("new text");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
ใน java มีหลายตัวที่สามารถใช้ได้ในการอ่าน File ได้ เช่น Scanner FileReader Byte BufferReader โดยแต่ละตัวก็จะมีสิ่ง่ที่เป็นเอกลักษณ์ของมัน เช่น 

Scanner -> อ่านทั้งไฟล์

FileReader -> อ่านทีละตัวอักษร

BufferReader -> อ่านทีละบรรทัด

ยังมีอีกหลายตัวที่ใช้แทนกันได้มันจะมีความหลากหลายได้การใช้ แต่ Ruby จะมีแค่ File กับ IO ซึ่งมีน้อยกว่าแต่ก็แลกกับการเขียนได้ง่าย
</details> 

  <details>
    <summary>C</summary>
  
  ```c
  #include <stdio.h>
  
  int main()
  {
      FILE *fptr;
      char filename[100];
      char text[100];
      int ch;
  
      fptr = fopen("example.txt", "r+");
      if (fptr == NULL)
      {
          printf("File not found!\n");
          return 1;
      }
      // Read
      while ((ch = fgetc(fptr)) != EOF)
      {
          putchar(ch);
      }
      printf("\n");
  
      // Write
      printf("Enter text: ");
      scanf(" %[^\n]", text);
      fseek(fptr, 0, SEEK_END);
      fputs(text, fptr);
  
      fclose(fptr);
      return 0;
  }
   ```
เนื่องจาก C เป็น low language มันจะค่อนข้างอ่านยาก เนื่องจากเป็นการอ่านไฟล์โดยใช้ pointer ชี้และอ่านค่าจาก pointer แปลงเป็น char และเมื่อใช้เสร็จจำเป็นต้อง rewind pointer กลับเข้าจุดตั้งเดิม จะเห็นได้เลยว่า Ruby นั้นง่ายกว่า
  </details>

  </details> 
  
  <details>
    <summary>Python</summary>
  
  ```python
try:
    with open("example.txt", "r+") as file:
        # Read
        content = file.read()
        print(content)

        # Write at the end
        text = input("What do you want to write: ")
        file.write("\n" + text)

except FileNotFoundError:
    print("Error: File was not found!")

   ```
python เป็นภาษาที่ออกกแบบมาให้ดูง่าย เมื่อใช้ with ทำให้ file close อัตโนมัต ทำให้โค้ดดูสะอาดและเขียนง่าย
  </details>
  
### Example 

[ตัวอย่าง Ruby](rw.rb)

[ตัวอย่าง Java](rw.java)

[ตัวอย่าง C](rw.c)

[ตัวอย่าง Python](rw.py)

