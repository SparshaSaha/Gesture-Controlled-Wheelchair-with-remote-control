import socket
from SocketReadUtils import SocketReadUtils
import threading
from CallBackHandler import CallBackHandler
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
    while True:
        pass

main()

