import threading

class SocketReadUtils(threading.Thread):
    
    def __init__(self, socket, name, stopper):
        threading.Thread.__init__(self)
        self.name = name
        self.socket = socket
        self.callbacks = {}
        self.stopper = stopper

    def registerCallback(self, registrationObject):
        if registrationObject.getArgument in self.callbacks:
            self.callbacks[registrationObject.getArgument()] = registrationObject.getFunction()
            return True
        return False

    def triggerCallBacks(self, triggerString):
        if triggerString not in self.callbacks:
            return False

        self.callbacks[triggerString]()
        return True
    
    def run(self):
        pass
        
