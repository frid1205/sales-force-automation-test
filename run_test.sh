#!/bin/bash

echo "Open Emulator..."
emulator -avd Pixel_XL

echo "Wait for emulator ready..."
adb wait-for-device

echo "Runnning appium in new terminal window..."
gnome-terminal -- bash -c "appium --use-plugins=execute-driver --relaxed-security; exec bash" &

# wait for appium ready
sleep 10

# Back to project directory before running maven
cd "$(dirname "$0")" || exit

echo "Running tests..."
mvn test -f pom.xml

if [ $? -ne 0 ]; then
    echo "Maven test failed with error level $?."
    exit 1
else
    echo "Maven test completed successfully."
fi

echo "Close Emulator..."
adb -s emulator-5554 emu kill
sleep 5

echo "close all emulator..."
pkill -f qemu-system-

echo "Close Appium..."
pkill -f node
sleep 3

echo "Close all Terminal..."
pkill -f gnome-terminal

echo "Done."
exit 0
