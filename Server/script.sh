#!/bin/sh

# Install express
express="npm install --save express"
echo "Installing express"
eval $express
echo "Express installed"

# Install mongoose
mongoose="npm install --save mongoose"
echo "Installing mongoose"
eval $mongoose
echo "Mongoose installed"

# Install mongoose-double
mongooseDouble="npm install --save mongoose-double"
echo "Installing mongoose-double"
eval $mongooseDouble
echo "Mongoose-double successfully installled"

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
