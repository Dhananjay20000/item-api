# Quick Start Guide

## Local Development

### Prerequisites
- Java 17 or higher installed
- Maven 3.6+ (or use Maven Wrapper)

### Run the Application

**Option 1: Using Maven Wrapper (if available)**
```bash
./mvnw spring-boot:run
```

**Option 2: Using Maven**
```bash
mvn spring-boot:run
```

**Option 3: Build and Run JAR**
```bash
mvn clean package
java -jar target/item-api-1.0.0.jar
```

The API will be available at: **http://localhost:8080**

## Test the API

### Add an Item
```bash
curl -X POST http://localhost:8080/api/items \
  -H "Content-Type: application/json" \
  -d '{
    "name": "iPhone 15",
    "description": "Latest iPhone model",
    "price": 999.99,
    "category": "Electronics",
    "stock": 50
  }'
```

### Get Item by ID
```bash
curl http://localhost:8080/api/items/1
```

### Get All Items
```bash
curl http://localhost:8080/api/items
```

## Deployment to Railway (Recommended)

1. **Push to GitHub:**
   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   git remote add origin https://github.com/YOUR_USERNAME/item-api.git
   git push -u origin main
   ```

2. **Deploy on Railway:**
   - Visit [railway.app](https://railway.app)
   - Sign up/login
   - Click "New Project" → "Deploy from GitHub repo"
   - Select your repository
   - Railway auto-detects Spring Boot and deploys
   - Copy the generated URL

3. **Share Deployment Link:**
   - Email: dsvjavalinux@gmail.com
   - Subject: Item Management API - Deployment Complete
   - Include: Your Railway deployment URL

## API Endpoints Summary

- `POST /api/items` - Add a new item
- `GET /api/items/{id}` - Get item by ID
- `GET /api/items` - Get all items

See `README.md` for detailed API documentation.



