"use strict"
const Location = require('./../DatabaseModels/Location');
const moment = require('moment');

module.exports = {

    insertOrUpdateLocation: function(location) {

        // Check if a previous location object is present
        Location.find({}, (error, response) => {

            if (!error) {
                console.log(response);

                if (response.length == 0) {
                    
                    // If database has no previous save location object
                    var wheelChairLocation = new Location({
                        l_id: 1,
                        latitude: 14.335,
                        longitude: 15.667,
                        timeStamp: moment.utc().toDate().toUTCString()
                    });

                    wheelChairLocation.save((err, resp) => {
                        if(!err) {
                            console.log("Saved");
                        }
                    });
                } else {
                    
                    // Update the database
                     Location.updateOne({l_id: 1}, {$set: {latitude: location.latitude, longitude: location.longitude}}, (err, response)=> {
                        if (!err) {
                            console.log("Successful");
                        } else {
                            console.log("Unsuccessful");
                        }
                     });
                }
            }
        });
    }
};
