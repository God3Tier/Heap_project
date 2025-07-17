# Welcome to Up2u

## What is this?

**Up2u** is our mini project designed to help students near **SMU** quickly find food places based on nearby MRT stations.  
Users can filter restaurants based on:

- **Average price**
- **Cuisine type**
- **User ratings**
- **Location proximity**

---

## 🌐 How to Use on the Cloud

Head over to [our live webpage](https://up2u-alpha.vercel.app 'up2u to find out') and experience it for yourself!
Please do note that we are poor and do not have the ability to buy for a 24/7 service. Wait 1 - 2 mins if you cannot 
get a response immediately when requesting a function tkyu :-) 

---

## 🖥️ How to Use Locally

> ⚠️ **Note:** Local setup is **not recommended** unless you have:
>
> - Your own database setup  
> - Your own Google Maps API key (with both **Map JavaScript API** and **Geocode API** enabled)  
> - The required API credentials and information  

---

## 🔧 Backend Setup

### 1. Ensure Java 21 is Installed

Verify installation with:

```bash
java --version
```

#### macOS

**With Homebrew:**

```bash
brew install openjdk@21
```

Then add to your `.zshrc` (or `.bashrc`):

```bash
export JAVA_HOME=/opt/homebrew/Cellar/openjdk@21/21.0.7/libexec/openjdk.jdk/Contents/Home/
export PATH=$JAVA_HOME/bin:$PATH
```

**Without Homebrew:**  
Follow [this official Oracle guide](https://docs.oracle.com/en/java/javase/24/install/installation-jdk-macos.html#GUID-F575EB4A-70D3-4AB4-A20E-DBE95171AB5F)  
→ Select **Java 21**, not the latest version.

#### Windows

Follow [this official Oracle guide](https://docs.oracle.com/en/java/javase/24/install/installation-jdk-macos.html#GUID-F575EB4A-70D3-4AB4-A20E-DBE95171AB5F), but make sure you download **Java 21** for Windows.

#### Linux

Use your package manager:

```bash
sudo apt install openjdk-21-jdk      # Debian/Ubuntu
sudo dnf install java-21-openjdk     # Fedora/RHEL
```

---

### 2. Backend Setup Instructions

1. Navigate to the backend folder:

```bash
cd backend
```

2. Create a `.env` file:

```env
DATABASE_HOST=your_database_host
DATABASE_PORT=5432
DATABASE_NAME=your_database_name
DATABASE_USER=your_database_user
DATABASE_PASSWORD=your_database_password
DATABASE_URL=your_database_url
SECRET_PASSCODE=your_secret_pass
FRONTEND_URL=http://localhost
FRONTEND_PORT=5173
```

3. Run the backend:

**With Maven installed:**

```bash
mvn clean install
mvn spring-boot:run
```

**Without Maven installed:**

```bash
./mvnw clean install
./mvnw spring-boot:run
```

---

## 🎨 Frontend Setup

### 1. Install Node.js and npm

**With package manager:**

```bash
sudo apt install npm             # Debian/Ubuntu
brew install npm                 # macOS
```

**Without package manager:**  
Install from [https://nodejs.org/en/download/](https://nodejs.org/en/download/) (select the correct OS)

---

### 2. Frontend Setup Instructions

1. Navigate to the frontend folder:

```bash
cd frontend
```

2. Create a `.env` file:

```env
VITE_BACKEND_URL=http://localhost:8080
VITE_MAPS_API=your_google_maps_api_key
```

3. Navigate to the React app directory:

```bash
cd up2u-react
```

4. Install dependencies:

```bash
npm i
```

5. Start the frontend dev server:

```bash
npm run dev
```

---
