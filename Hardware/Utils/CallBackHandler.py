class CallBackHandler(object):
    
    def __init__(self, arg, function):
        self.__argument = arg
        self.__function = function

    def getArgument(self):
        return self.__argument

    def getFunction(self):
        return self.__function
        
