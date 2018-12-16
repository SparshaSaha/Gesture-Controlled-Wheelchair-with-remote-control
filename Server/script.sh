#!/bin/sh

# Install mongoose
mongoose="npm install --save mongoose"
echo "Installing mongoose"
eval $mongoose
echo "Mongoose installed"

# Install mongodb
mongodb="npm install --save mongodb"
echo "Installing mongodb"
eval $mongodb
echo "Mongodb installed"

# Install momentJS
momentJS="npm install --save moment"
echo "Installing moment"
exec $momentJS
echo "Moment installed"
