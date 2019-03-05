import cv2
import numpy as np
from urllib.request import urlopen
import threading
import time

class WebCamUsingPhone(threading.Thread):
  
  def __init__(self, threadID, name, url, stopper):
    threading.Thread.__init__(self)
    self.threadID = threadID
    self.name = name
    self.url = url
    self.stopper = stopper

  def run(self):
    while not self.stopper.is_set():
      try:
        capturedFrame = urlopen(self.url)
        frameAsArray = np.array(bytearray(capturedFrame.read()), dtype=np.uint8)
        decodedImage = cv2.imdecode(frameAsArray, -1)
        cv2.imshow(self.name, decodedImage)
      except Exception as e:
        print('Exception occured....exiting')
        exit(0)
        
      # Export through socket
      if ord('q') == cv2.waitKey(10):
        exit(0)
        
      exit(0)

stopper = threading.Event()
threadTest = WebCamUsingPhone(1, 'left', "http://10.105.17.33:8080/shot.jpg", stopper)
threadTest.start()

