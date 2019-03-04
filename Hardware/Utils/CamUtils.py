import cv2
import numpy as np
from urllib.request import urlopen
import threading
import time

class WebCamUsingPhone(threading.Thread):
  
  def __init__(self, threadID, name, url):
    threading.Thread.__init__(self)
    self.threadID = threadID
    self.name = name
    self.url = url

  def run(self):
    while True:
      capturedFrame = urlopen(self.url)
      frameAsArray = np.array(bytearray(capturedFrame.read()), dtype=np.uint8)
      decodedImage = cv2.imdecode(frameAsArray, -1)
      cv2.imshow(self.name, decodedImage)
      # Export through socket
      if ord('q') == cv2.waitKey(10):
        exit(0)


threadTest = WebCamUsingPhone(1, 'front', "http://10.105.16.217:8080/shot.jpg")
threadTest.start()
