#!/bin/bash

DEVICE_ID="emulator-5554"

echo "Waiting for $DEVICE_ID to boot..."

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