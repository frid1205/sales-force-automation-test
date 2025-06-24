#!/bin/bash

# Install execute-driver plugin
# echo "Install execute-driver plugin"
# npm install -g lerna@6 \
#  && git clone https://github.com/appium/appium-plugins.git \
# && cd appium-plugins/packages/execute-driver \
# && npm install \
# && npx appium plugin install --source=local .

# Check appium plugin list
# echo "check appium plugin list" 
# appium plugin list

# Start Appium in background with --allow-insecure=adb_shell
echo "Starting Appium..."
# appium --allow-insecure=adb_shell --use-plugins=execute-driver  --relaxed-security > /tmp/appium.log 2>&1 &
appium --allow-insecure=adb_shell > /tmp/appium.log 2>&1 &

# Wait for Appium to be ready
echo "Waiting for Appium to be up..."
for i in {1..120}; do
  if curl -s http://localhost:4723/status | grep ready; then
    echo "Appium is running!"
    exit 0
  fi
  sleep 2
done

# echo "Appium failed to start."
# cat /tmp/appium.log
# exit 1