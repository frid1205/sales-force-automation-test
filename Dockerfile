# Base image with Ubuntu
FROM ubuntu:20.04

RUN apt-get update
RUN apt-get -y install tzdata
RUN apt-get -y install apache2
RUN echo "Dockerfile Test on Apache2" > /var/www/html/index.html


# Set non-interactive mode for APT
ENV DEBIAN_FRONTEND=noninteractive

# Install necessary packages and dependencies
RUN apt update && \
    apt install -y \
    libgbm-dev \
    libxshmfence1 \
    libxrandr2 \
    libxi6 \
    libxcursor1 \
    libxcomposite1 \
    libasound2

# Install necessary packages and dependencies
RUN apt-get update && \
    apt-get install -y \
        wget \
        git \
        gnupg \
        curl \
        unzip \
        tzdata \
        openjdk-21-jdk \
        && \
    rm -rf /var/lib/apt/lists/*

# Set the timezone to UTC
RUN ln -fs /usr/share/zoneinfo/UTC /etc/localtime && \
    dpkg-reconfigure --frontend noninteractive tzdata

# Set JAVA_HOME environment variable
ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:$PATH"

# Install Maven 3.8.7
RUN wget https://archive.apache.org/dist/maven/maven-3/3.8.7/binaries/apache-maven-3.8.7-bin.zip && \
    unzip apache-maven-3.8.7-bin.zip -d /opt && \
    ln -s /opt/apache-maven-3.8.7 /opt/maven && \
    ln -s /opt/maven/bin/mvn /usr/bin/mvn && \
    rm apache-maven-3.8.7-bin.zip

# Set MAVEN_HOME environment variable
ENV MAVEN_HOME=/opt/maven
ENV PATH="$MAVEN_HOME/bin:$PATH"

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

# Install nano editor
RUN apt-get install nano

# Set up working directory
WORKDIR /app

# Copy the necessary files into the image
COPY . .

# Ensure the entrypoint script has execution permissions
RUN chmod +x /app/run_test.sh

# Execute the run_test.sh script when the container starts
# CMD ["bash", "/app/run_test.sh"]

EXPOSE 80
CMD ["/usr/sbin/apachectl", "-D", "FOREGROUND"]

