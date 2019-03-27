import threading

class SendUtils(threading.Thread):

    def __init__(self, name, socket, stopper, data):
        threading.Thread.__init__(self)
        self.name = name
        self.socket = socket
        self.stopper = stopper
        self.data = data
    
    def run(self):
        pass