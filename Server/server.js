"use strict";
const express = require("express");
const mongoose = require("mongoose");
const app = express();
const moment = require("moment");
const port = process.env.PORT || 3000;
const databaseHandler = require('./Modules/databaseHandler.js');

// Create Database Instance and connect to database
var database = mongoose.connection.openUri('mongodb://127.0.0.1:27017/WheelChairDatabaseTest');

// On connection successful
mongoose.connection.once('connected', function() {
    console.log("Connected to Database");
});
var loc = {
    latitude: 1.11,
    longitude: 2.33,
    timeStamp: Date.now()
};
databaseHandler.insertOrUpdateLocation(loc, moment().toString());
app.listen(port);
