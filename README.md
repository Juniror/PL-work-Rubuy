# Read Write File with Ruby
ในการเขียนไฟล์ใน Ruby จำเป็นต้องกำหนดสิทธิการแก้ไม่เหมือนกับภาษอื่นที่สามารถอ่านเขียนได้โดยอัตโนมัต
## 📌 File Modes in Ruby

| Mode | Description |
|------|-------------|
| `"r"`   | อ่านเท่านั้น pointer ชี้จุดเริ่มต้นของไฟล์ |
| `"r+"`  | อ่านและเขียน pointer ชี้จุดเริ่มต้นของไฟล์ |
| `"w"`   | เขียนเท่านั้น หากไม่มีไฟล์จะทำการเขียนขึ้นมาใหม่ หากมีอยู่แล้วจะเขียนทับ|
| `"w+"`  | อ่านและเขียน หากไม่มีไฟล์จะทำการเขียนขึ้นมาใหม่ หากมีอยู่แล้วจะเขียนทับ |
| `"a"`   | เขียนเท่านั้น pointer ชี้ท้ายของไฟล์ หากไม่มีไฟล์จะเขียนมาใหม่ |
| `"a+"`  | อ่านและเขียน pointer ชี้ท้ายของไฟล์ หากไม่มีไฟล์จะเขียนมาใหม่ |
| `"b"`   | ใช้เป็นโหมด binary เช่น rb wb |

### Read
File จะคืนค่าเป็น object ส่วน IO จะคืนค่าเป็น String 
ในการอ่านสามารถเลือกใช้ได้ว่าต้องการอ่านแต่ละบรรทัด แต่ละตัว หรืออ่านค่าโดยไม่ต้องเก็บเข้า memory ได้(IO) 
#### File
```ruby
  fileobject = File.new("example.txt", "r")
  # อ่านทั้งไฟล์ 
  puts fileobject.read
  # อ่านทีละบรรทัด
  fileobject.each { |line| puts line }
```

#### IO
```ruby
  fileByIO = 
  # อ่านทั้งไฟล์ 
  puts IO.read("example.txt") 
  # อ่านทีละบรรทัด
  puts IO.readlines("example.txt").each { |line| puts line }
```

### Write
ในการอ่านไฟล์จำเป็นต้อง fileobject.close() เสมอ ไม่งั้นไฟล์จะไม่ถูกเขียน เหมือนภาษา java
```ruby
  fileobject = File.new(name, "w")
  fileobject.syswrite("Hello")
  fileobject.close
```
### Example
[ไปที่โค้ดตัวเต็ม](io.ruby)
