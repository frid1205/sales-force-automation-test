# Use budtmo as base image
FROM budtmo/docker-android:emulator_13.0_v2.1.3-p1

USER root

# Install Java + Maven
RUN apt-get update && \
    apt-get install -y openjdk-11-jdk maven python3-pip && \
    rm -rf /var/lib/apt/lists/*

# (Optional) Install Appium Python Client if needed
RUN pip3 install Appium-Python-Client

# Copy test project into container
COPY . /root/automation-tests

WORKDIR /root/automation-tests

# Pre-download Maven dependencies (optional)
RUN mvn dependency:resolve

CMD ["supervisord", "-c", "/etc/supervisor/supervisord.conf"]
