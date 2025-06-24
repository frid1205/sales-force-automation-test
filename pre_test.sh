#!/bin/bash

echo "Get SDK List"
sdkmanager --list

echo "Get Device Available"
avdmanager list avd

echo "Running emulator"
if ! pgrep -f "emulator.*nexus_5_13.0" > /dev/null; then
  echo "Starting emulator..."
  nohup emulator -avd nexus_5_13.0 -no-audio -no-window -gpu swiftshader_indirect > /dev/null 2>&1 &
else
  echo "Emulator already running. Skipping..."
fi

DEVICE_ID="emulator-5554"

echo "Waiting for emulator to appear in adb devices..."
until adb devices | grep -w "$DEVICE_ID" | grep -w "device"; do
  sleep 2
done

echo "Waiting for $DEVICE_ID to boot..."
adb -s $DEVICE_ID wait-for-device

# Wait for sys.boot_completed
while [[ "$(adb -s $DEVICE_ID shell getprop sys.boot_completed 2>/dev/null | tr -d '\r')" != "1" ]]; do
  sleep 2
done
echo "Boot completed."

# Wait for package service to be available
echo "Waiting for 'package' service on $DEVICE_ID..."

while true; do
  output=$(adb -s "$DEVICE_ID" shell service check package 2>&1)
  echo "[$(date +%H:%M:%S)] service check output: $output"

  if echo "$output" | grep -q "found"; then
    echo "Package service is ready."
    break
  fi

  sleep 2
done

# Extra delay for stability
echo "Waiting additional 30 seconds for stability..."
sleep 30

# Install the APK
echo "Installing APK..."
if ! adb -s $DEVICE_ID install -g /root/stp.apk; then
  echo "APK installation failed. Exiting..."
  adb -s $DEVICE_ID shell logcat -d | tail -n 50
  exit 1
fi

echo "APK installed successfully."

echo "Check emulator status"
curl http://localhost:4723/status
