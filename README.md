# Welcome to Up2u 
### What is this 
This is our mini project that allows students near smu to quickly find food places
based on the MRTs near smu. We allow students to filter by average price, cuisine type, 
rating from other users and location. 

## How to use on the cloud 
Go to [our webpage](https://up2u-alpha.vercel.app 'up2u to find out') and experience it for yourself now

## How to use locally

*** NOTE: This is highly not recommended as you would need your own database, your own ***
***       google api login with both map javascript api and geocode api and your own information ***

### Backend 
  1. Ensure Java 21 is Installed (verifyable by typing java --version)
  ####    MacOs 
  With Package Manager (brew. If you used Nix, you should know how to urself)
    1. Type the command ** brew install openjdk@21 **
    2. Type ** vim .zshrc ** (or whatever terminal config you use)
    3. Add the following 
        export JAVA_HOME=/opt/homebrew/Cellar/openjdk@21/21.0.7/libexec/openjdk.jdk/Contents/Home/
        export PATH=$JAVA_HOME/bin:$PATH
  Without Package Manager
    1. Follow the instruction from 
          https://docs.oracle.com/en/java/javase/24/install/installation-jdk-macos.html#GUID-F575EB4A-70D3-4AB4-A20E-DBE95171AB5F
      ** BUT ** use the Java 21 and not the latest version 
  ####    Windows 
    1. Follow the instruction from 
          https://docs.oracle.com/en/java/javase/24/install/installation-jdk-macos.html#GUID-F575EB4A-70D3-4AB4-A20E-DBE95171AB5F
      ** BUT ** use the Java 21 and not the latest version 
  ####    Linux 
    1. Use your package manager to install openjdk 21
  2. Enter the backend folder with ** cd backend **
  3. Create a .env file with the following configurations 
        DATABASE_HOST=DATABASEHOST
        DATABASE_PORT=5432
        DATABASE_NAME=DATABASENAME
        DATABASE_USER=DATABASEUSER
        DATABASE_PASSWORD=YOURDATABASEPASS
        DATABASE_URL=YOURDATABASEPASSWORD
        SECRET_PASSCODE=YOURSECRETPASSWORD
        FRONTEND_URL=http://localhost:
        FRONTEND_PORT=5173
  4. Run the project: 
  #### With maven installed
    mvn clean install
    mvn spring-boot:run   
  #### Without maven installed
    ./mvnw clean install 
    ./mvnw spring-boot:run 
#### Frontend
  1. Ensure npm is installed 
  #### With package manager 
    1. Use your package manager to install npm (e.g. ** debian based distros ** sudo apt install npm or ** macos ** brew install npm)
  #### Without package manager
    2. Follow the instructions on https://nodejs.org/en/download/ with proper os selected
  2. Enter the frontend folder ** cd frontend ** 
  3. Create a .env file with the following 
        VITE_BACKEND_URL=http://localhost:8080
        VITE_MAPS_API=YOUR_GOOGLE_MAPS_API
  4. Enter the frontend folder ** cd frontend/up2u-react ** 
  5. Run the command ** npm i ** to install all required dependencies 
  6. Run ** npm run dev ** to start the frontend 
