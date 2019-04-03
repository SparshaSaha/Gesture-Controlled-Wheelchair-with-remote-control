import socket                
import config

s = socket.socket()
print("Socketcreated")
port = config.networkConfig['port']              

s.bind(('', port))
print("Bound to port")
  
s.listen(5)
print("Strated listening")
  
c, addr = s.accept()
print("Got connection")
   
while True:
   d = input()
   c.send(d.encode('utf-8'))
   print("Sent")
   
