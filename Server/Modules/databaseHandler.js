"use strict"
const Location = require('./../DatabaseModels/Location');
 
module.exports = {

    insertOrUpdateLocation: function(location, utcString) {

        // Check if a previous location object is present, that is, if the database is empty
        Location.find({}, (error, response) => {

            if (!error) {

                if (response.length == 0) {
                    
                    // If database has no previous save location object
                    var wheelChairLocation = new Location({
                        l_id: 1,
                        latitude: null,
                        longitude: null,
                        timeStamp: Date.now()
                    });
                    
                    // Initializiing the Location object
                    wheelChairLocation.save((err, resp) => {
                        if (!err) {
                            console.log("Saved");
                        }
                    });

                } else {
                    
                    // Update the database as location object is already present
                    Location.updateOne({l_id: 1}, {$set: {latitude: location.latitude, longitude: location.longitude, timeStamp: location.timeStamp}}, (err, response)=> {
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
