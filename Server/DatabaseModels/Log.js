var mongoose = require('mongoose');

var Logs = mongoose.Schema({
    id: 1,
    logStatus: []
});
module.exports('Log', Logs);
