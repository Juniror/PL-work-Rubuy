# Read Write File with Ruby
ในการเขียนไฟล์ใน Ruby จำเป็นต้องกำหนดสิทธิการแก้ไม่เหมือนกับภาษอื่นที่สามารถอ่านเขียนได้โดยอัตโนมัต
## 📌 File Modes in Ruby

| Mode | Description |
|------|-------------|
| `"r"`   | Read only access. Pointer is positioned at start of file. |
| `"r+"`  | Read and write access. Pointer is positioned at start of file. |
| `"w"`   | Write only access. Pointer is positioned at start of file. Creates file if it does not exist, truncates if it does. |
| `"w+"`  | Read and write access. Pointer is positioned at start of file. Creates file if it does not exist, truncates if it does. |
| `"a"`   | Write only access. Pointer is positioned at end of file. Creates file if it does not exist. |
| `"a+"`  | Read and write access. Pointer is positioned at end of file. Creates file if it does not exist. |
| `"b"`   | Binary file mode. Used in conjunction with other modes (e.g., `"rb"`, `"wb"`). Primarily for Windows/DOS. |

### Read
```ruby
  fileobject = File.new(name, "r")
  puts fileobject.read
```

### Write
```ruby
  fileobject = File.new(name, "w")
  fileobject.syswrite("Hello")
  fileobject.close
```
