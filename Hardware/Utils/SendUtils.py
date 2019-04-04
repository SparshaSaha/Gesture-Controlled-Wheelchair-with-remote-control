import threading

class SendUtils(threading.Thread):

    def __init__(self, socket, nextFrameReady, stopper):
        threading.Thread.__init__(self)
        self.socket = socket
        self.data = None
        self.nextFrameReady = nextFrameReady # Event to trigger send again
        self.stopper = stopper # Event to kill off thread
    
    def setData(self, data):
        self.data = data
    
    def run(self):
        while not self.stopper.isSet():
            self.socket.send(self.data.encode('utf-8'))
            self.nextFrameReady.wait(timeout=None) # Waiting for next frame to be ready
            self.nextFrameReady.clear() # Clearing Event so that it waits again after sending current data
