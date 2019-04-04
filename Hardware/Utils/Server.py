import socket
import json
import config            
import socket                

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
   data = c.recv(1024).decode('utf-8')
   lis = json.loads(data)
   print(lis[0])
