# Use budtmo as base image
FROM budtmo/docker-android:emulator_13.0_v2.1.3-p1

# Enable User root
USER root
RUN apt-get update && apt-get install -y bash passwd
RUN echo "root:x:0:0:root:/root:/bin/bash" >> /etc/passwd

# Install Java + Maven
RUN apt-get update && apt-get install -y \
    openjdk-21-jdk \
    maven \
    wget \
    unzip \
    gnupg \
    python3-pip \
    && rm -rf /var/lib/apt/lists/*

# (Optional) Install Appium Python Client if needed
RUN pip3 install Appium-Python-Client

# Install Chrome browser (optional, if needed)
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    echo "deb [arch=amd64] http://dl.google.com/linux/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list && \
    apt-get update && \
    apt-get install -y google-chrome-stable && \
    apt-get clean

# Install ChromeDriver
RUN CHROME_DRIVER_VERSION=$(curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE) && \
    wget -O /tmp/chromedriver.zip https://chromedriver.storage.googleapis.com/$CHROME_DRIVER_VERSION/chromedriver_linux64.zip && \
    unzip /tmp/chromedriver.zip -d /usr/local/bin/ && \
    rm /tmp/chromedriver.zip    

# Set environment variables
ENV CHROME_DRIVER_PATH=/usr/local/bin/chromedriver

# Install Node.js (if not already)
RUN curl -fsSL https://deb.nodesource.com/setup_18.x | bash - && \
    apt-get install -y nodejs

# Install Appium CLI
RUN npm install -g appium --allow-insecure=adb_shell

# Install UiAutomator2 driver
RUN appium driver install uiautomator2

# Configure Appium to run with --allow-insecure=adb_shell
# RUN echo "[program:appium]" > /etc/supervisor/conf.d/appium.conf && \
#   echo "command=/usr/local/bin/appium --allow-insecure=adb_shell --base-path /wd/hub" >> /etc/supervisor/conf.d/appium.conf && \
#   echo "autostart=true" >> /etc/supervisor/conf.d/appium.conf && \
#   echo "autorestart=true" >> /etc/supervisor/conf.d/appium.conf && \
#   echo "stderr_logfile=/var/log/appium.err.log" >> /etc/supervisor/conf.d/appium.conf && \
#   echo "stdout_logfile=/var/log/appium.out.log" >> /etc/supervisor/conf.d/appium.conf

# Install nano editor
RUN apt-get install nano

# Copy test project into container
COPY . /root/automation-tests

WORKDIR /root/automation-tests

# Pre-download Maven dependencies (optional)
RUN mvn dependency:resolve

CMD ["appium", "--allow-insecure=adb_shell"]