import json

def jsonDefault(OrderedDict):
    return OrderedDict.__dict__

class DataWrapper:

    def __init__(self, cmd, payload):
        self.cmd = on
        self.payload = payload
    
    def getJson(self):
        return json.dumps(self, default=jsonDefault)
