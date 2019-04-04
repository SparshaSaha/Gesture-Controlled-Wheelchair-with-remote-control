import threading

class SocketReadUtils(threading.Thread):
    
    def __init__(self, socket, name, stopper):
        threading.Thread.__init__(self)
        self.name = name
        self.socket = socket
        self.callbacks = {}
        self.stopper = stopper

    def registerCallback(self, registrationObject):
        if registrationObject.getArgument not in self.callbacks:
            self.callbacks[registrationObject.getArgument()] = registrationObject.getFunction()
            return True
        return False

    def triggerCallBack(self, triggerString):
        if triggerString not in self.callbacks:
            return False

        self.callbacks[triggerString]()
        return True
    
    def run(self):
        while True:
            data = self.socket.recv(1024).decode('utf-8')
            if self.triggerCallBack(data):
                print("Successfully")
      
