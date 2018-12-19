"use strict"
const Location = require('./../DatabaseModels/Location');

module.exports = {

    insertOrUpdateLocation: function(location) {

        // Check if a previous location object is present
        Location.find({}, (error, response) => {

            if (!error) {

                if (response.length == 0) {
                    
                    // If database has no previous save location object
                    var wheelChairLocation = new Location({
                        latitude: 14.335,
                        longitude: 15.667,
                        timeStamp: 'RandomTime'
                    });

                    wheelChairLocation.save((err, resp) => {
                        if(!err) {
                            console.log("Saved");
                        }
                    });
                } else {
                    // Update
                }
            }

        });
    }
};
