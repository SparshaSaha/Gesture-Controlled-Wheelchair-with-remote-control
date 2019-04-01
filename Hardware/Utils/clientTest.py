import socket
from SocketReadUtils import SocketReadUtils
import threading
from CallBackHandler import CallBackHandler
import json
from SendUtils import SendUtils
import config

def call1():
    print("Callback 1")

def call2():
    print("Callback 2")

def main():
    soc = socket.socket()
    soc.connect((config.networkConfig['address'], config.networkConfig['port']))
    x = SocketReadUtils(soc, 'reader', threading.Event())
    x.registerCallback(CallBackHandler('a', call1))
    x.registerCallback(CallBackHandler('b', call2))
    x.start()
    arr = [[1,2,3], [3,4,5]]
    ev1 = threading.Event()
    arrJson = json.dumps(arr)
    s = SendUtils(soc, ev1, threading.Event())
    s.setData(arrJson)
    s.start()
    s.setData(arrJson)
    ev1.set()
    while True:
        pass

main()

