import threading

class SendUtils(threading.Thread):

    def __init__(self, socket, data):
        threading.Thread.__init__(self)
        self.socket = socket
        self.data = data
    
    def run(self):
        self.socket.send(self.data.encode('utf-8'))
    