@echo off
echo Membuka Emulator...
cd C:\Users\Fendy Ridwan\AppData\Local\Android\Sdk\emulator
start emulator -avd Pixel_9_API_33_Tiramisu
echo Menunggu emulator siap...
adb wait-for-device

echo Menjalankan Appium di window baru...
cd C:\Users\Fendy Ridwan\AppData\Roaming\npm
start cmd /k "appium --use-plugins=execute-driver --relaxed-security"

:: Tunggu beberapa detik agar Appium siap
timeout /t 10

:: Kembali ke direktori proyek sebelum menjalankan Maven
cd /d %~dp0

echo Menjalankan Maven tests...
call mvn test -f pom.xml

REM Cek apakah perintah sebelumnya gagal (error level lebih dari 0)
if %errorlevel% neq 0 (
    echo Maven test failed with error level %errorlevel%.
    goto end
) else (
    echo Maven test completed successfully.
)

echo Menutup Emulator...
"C:\Users\Fendy Ridwan\AppData\Local\Android\Sdk\platform-tools\adb.exe" -s emulator-5554 emu kill
timeout /t 5

echo Mematikan semua emulator...
taskkill /F /IM qemu-system-*

echo Menutup Appium...
taskkill /F /IM node.exe /T
timeout /t 3

echo Menutup Semua Command Prompt...
taskkill /F /IM cmd.exe /T

echo Selesai.
pause
exit