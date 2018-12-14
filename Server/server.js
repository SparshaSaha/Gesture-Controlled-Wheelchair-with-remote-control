"use strict";
const express = require("express");
const mongoose = require("mongoose");
const app = express();
const port = 3000;

// Create Database Instance and connect to database
var database = mongoose.connection.openUri('mongodb://127.0.0.1:27017/WheelChairDatabase');

// On connection successful
mongoose.connection.once('connected', function() {
    console.log("Connected to Database");
});

app.listen(port);
