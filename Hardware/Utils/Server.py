import socket                
  
# next create a socket object 
s = socket.socket()
print("Socket created")
  
# reserve a port on your computer in our 
port = 2002              

s.bind(('', port))
print("Bound to port")
  
# put the socket into listening mode 
s.listen(5)
print("Strated listening")
  
# a forever loop until we interrupt it or  
# an error occurs 
  
   # Establish connection with client. 
c, addr = s.accept()
print("Got connection")
   
while True:
   # send a thank you message to the client.
   d = input()
   c.send(d.encode('utf-8'))
   print("Sent")
   
