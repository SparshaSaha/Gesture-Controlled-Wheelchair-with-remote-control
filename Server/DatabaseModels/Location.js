var mongoose = require('mongoose');
require('mongoose-double')(mongoose);
var SchemaTypes = mongoose.Schema.Types;

var locationSchema = new mongoose.Schema({
    l_id: {type: Number, required:true},
    latitude: {type: SchemaTypes.Double, required: true},
    longitude: {type: SchemaTypes.Double, required: true},
    accuracy: {type: SchemaTypes.Double},
    timeStamp: {type:String, required: true}
});

module.exports = mongoose.model('Location', locationSchema);
