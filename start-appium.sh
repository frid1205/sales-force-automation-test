#!/bin/bash

# Start Appium in background with --allow-insecure=adb_shell
echo "Starting Appium..."
appium --allow-insecure=adb_shell > /tmp/appium.log 2>&1 &

# Wait for Appium to be ready
echo "Waiting for Appium to be up..."
for i in {1..30}; do
  if curl -s http://localhost:4723/status | grep -q "Appium"; then
    echo "Appium is running!"
    exit 0
  fi
  sleep 2
done

echo "Appium failed to start."
cat /tmp/appium.log
exit 1