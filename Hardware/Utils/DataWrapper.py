import json

def jsonDefault(OrderedDict):
    return OrderedDict.__dict__

class DataWrapper:

    def __init__(self, on, data):
        self.on = on
        self.data = data
    
    def getJson(self):
        return json.dumps(self, default=jsonDefault)
