import socket
from SocketReadUtils import SocketReadUtils
import threading
from CallBackHandler import CallBackHandler
import json
from SendUtils import SendUtils

def call1():
    print("Callback 1")

def call2():
    print("Callback 2")

def main():
    soc = socket.socket()
    soc.connect(('127.0.0.1', 2000))
    x = SocketReadUtils(soc, 'reader', threading.Event())
    x.registerCallback(CallBackHandler('a', call1))
    x.registerCallback(CallBackHandler('b', call2))
    x.start()
    arr = [[1,2,3], [3,4,5]]
    arrJson = json.dumps(arr)
    s = SendUtils(soc, arrJson)
    s.start()
    while True:
        pass

main()

