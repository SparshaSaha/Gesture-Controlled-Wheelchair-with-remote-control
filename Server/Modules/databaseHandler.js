"use strict"
const Location = require('./../DatabaseModels/Location');
 
module.exports = {

    insertOrUpdateLocation: function(location, utcString) {

        // Check if a previous location object is present
        Location.find({}, (error, response) => {

            if (!error) {
                console.log(response);

                if (response.length == 0) {
                    
                    // If database has no previous save location object
                    var wheelChairLocation = new Location({
                        l_id: 1,
                        latitude: null,
                        longitude: null,
                        timeStamp: Date.now()
                    });

                    wheelChairLocation.save((err, resp) => {
                        if (!err) {
                            console.log("Saved");
                        }
                    });

                } else {
                    
                    // Update the database
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
