#!/bin/bash

# Start Appium in background with --allow-insecure=adb_shell
echo "Starting Appium..."
appium --allow-insecure=adb_shell > /tmp/appium.log 2>&1 &

# Start noVNC in background
echo "Starting noVNC (novnc_proxy)..."
/opt/noVNC/utils/novnc_proxy --vnc localhost:5900 --listen 6080 > /tmp/novnc.log 2>&1 &

# Wait for Appium to be ready
echo "Waiting for Appium to be up..."
for i in {1..120}; do
  if curl -s http://localhost:4723/status | grep ready; then
    echo "Appium is running!"
    exit 0
  fi
  sleep 2
done

