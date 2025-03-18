#!/bin/bash

echo "Membuka Emulator..."
emulator -avd Pixel_XL

echo "Menunggu emulator siap..."
adb wait-for-device

echo "Menjalankan Appium di terminal baru..."
gnome-terminal -- bash -c "appium --use-plugins=execute-driver --relaxed-security; exec bash" &

# Tunggu beberapa detik agar Appium siap
sleep 10

# Kembali ke direktori proyek sebelum menjalankan Maven
cd "$(dirname "$0")" || exit

echo "Menjalankan Maven tests..."
mvn test -f pom.xml

# Cek apakah perintah sebelumnya gagal
if [ $? -ne 0 ]; then
    echo "Maven test failed with error level $?."
    exit 1
else
    echo "Maven test completed successfully."
fi

echo "Menutup Emulator..."
adb -s emulator-5554 emu kill
sleep 5

echo "Mematikan semua emulator..."
pkill -f qemu-system-

echo "Menutup Appium..."
pkill -f node
sleep 3

echo "Menutup Semua Terminal..."
pkill -f gnome-terminal

echo "Selesai."
exit 0
