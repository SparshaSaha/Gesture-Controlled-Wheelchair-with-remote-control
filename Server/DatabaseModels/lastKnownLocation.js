var mongoose = require('mongoose');
var SchemaTypes = mongoose.Schema.Types;

var locationSchema = new mongoose.Schema({
    latitude: {type: SchemaTypes.Double, required: true},
    longitude: {type: SchemaTypes.Double, required: true},
    accuracy: {type: SchemaTypes.Double},
    timeStamp: {type:String, required: true}
});

module.exports = mongoose.model('location', locationSchema);
