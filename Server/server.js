"use strict";
const express = require("express");
const mongoose = require("mongoose");
const app = express();
const port = 8010;
const databaseHandler = require('./Modules/databaseHandler.js');

// Create Database Instance and connect to database
var database = mongoose.connection.openUri('mongodb://127.0.0.1:27017/WheelChairDatabaseTest');

// On connection successful
mongoose.connection.once('connected', function() {
    console.log("Connected to Database");
});
var loc = {
    latitude: 1.11,
    longitude: 2.33
};
databaseHandler.insertOrUpdateLocation(loc); 

app.listen(port);
