# Item Management REST API

A simple Java Spring Boot RESTful API for managing a collection of items (e.g., ecommerce products like Flipkart, movies like Netflix, etc.).

## Features

- ✅ RESTful API endpoints for managing items
- ✅ In-memory data storage using ArrayList
- ✅ Input validation for required fields
- ✅ Comprehensive error handling
- ✅ CORS enabled for frontend integration

## Prerequisites

- Java 17 or higher
- Maven 3.6+ (or use Maven Wrapper included)

## Project Structure

```
item-api/
├── src/
│   ├── main/
│   │   ├── java/com/example/itemapi/
│   │   │   ├── ItemApiApplication.java    # Main application class
│   │   │   ├── model/
│   │   │   │   └── Item.java              # Item model class
│   │   │   ├── service/
│   │   │   │   └── ItemService.java       # Business logic & in-memory storage
│   │   │   └── controller/
│   │   │       └── ItemController.java    # REST API endpoints
│   │   └── resources/
│   │       └── application.properties     # Application configuration
│   └── test/                              # Test files (optional)
├── pom.xml                                # Maven dependencies
└── README.md                              # This file
```

## How to Run the Application

### Option 1: Using Maven Wrapper (Recommended)

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

### Option 2: Using Maven (if installed)

```bash
mvn spring-boot:run
```

### Option 3: Build and Run JAR

```bash
# Build the JAR file
mvn clean package

# Run the JAR file
java -jar target/item-api-1.0.0.jar
```

The application will start on **http://localhost:8080**

## API Endpoints

### Base URL
```
http://localhost:8080/api/items
```

### 1. Add a New Item

**Endpoint:** `POST /api/items`

**Request Body:**
```json
{
  "name": "iPhone 15 Pro",
  "description": "Latest iPhone with A17 Pro chip",
  "price": 999.99,
  "category": "Electronics",
  "stock": 50
}
```

**Required Fields:**
- `name` (String, 1-200 characters)
- `description` (String, 1-1000 characters)
- `price` (Double, must be positive)

**Optional Fields:**
- `category` (String)
- `stock` (Integer)

**Response:** `201 Created`
```json
{
  "id": 1,
  "name": "iPhone 15 Pro",
  "description": "Latest iPhone with A17 Pro chip",
  "price": 999.99,
  "category": "Electronics",
  "stock": 50,
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

**Error Response:** `400 Bad Request` (if validation fails)
```json
{
  "name": "Name is required",
  "price": "Price must be positive"
}
```

### 2. Get Item by ID

**Endpoint:** `GET /api/items/{id}`

**Example:** `GET /api/items/1`

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "iPhone 15 Pro",
  "description": "Latest iPhone with A17 Pro chip",
  "price": 999.99,
  "category": "Electronics",
  "stock": 50,
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

**Error Response:** `404 Not Found` (if item doesn't exist)

### 3. Get All Items

**Endpoint:** `GET /api/items`

**Response:** `200 OK`
```json
{
  "items": [
    {
      "id": 1,
      "name": "iPhone 15 Pro",
      "description": "Latest iPhone with A17 Pro chip",
      "price": 999.99,
      "category": "Electronics",
      "stock": 50,
      "createdAt": "2024-01-15T10:30:00",
      "updatedAt": "2024-01-15T10:30:00"
    }
  ],
  "total": 1
}
```

## Testing the API

### Using cURL

**Add an item:**
```bash
curl -X POST http://localhost:8080/api/items \
  -H "Content-Type: application/json" \
  -d '{
    "name": "The Matrix",
    "description": "A computer hacker learns about the true nature of reality",
    "price": 9.99,
    "category": "Movies",
    "stock": 100
  }'
```

**Get item by ID:**
```bash
curl http://localhost:8080/api/items/1
```

**Get all items:**
```bash
curl http://localhost:8080/api/items
```

### Using Postman

1. Import the collection or create requests manually
2. Set the base URL to `http://localhost:8080/api/items`
3. Use POST for adding items, GET for retrieving items

## Implementation Details

### Data Storage
- Uses `ArrayList<Item>` for in-memory storage
- Items are stored in the `ItemService` class
- IDs are auto-generated using `AtomicLong` counter
- Data persists only during application runtime (resets on restart)

### Validation
- Uses Jakarta Bean Validation (`@Valid`, `@NotBlank`, `@NotNull`, `@Positive`, `@Size`)
- Validates required fields: name, description, price
- Returns detailed error messages for validation failures

### Error Handling
- Custom exception handler for validation errors
- Returns appropriate HTTP status codes (201, 200, 400, 404)
- Provides clear error messages in JSON format

## Deployment

### Important Note on Hosting

**Vercel and Netlify are primarily for frontend/static sites and serverless functions.** They don't support traditional Java Spring Boot applications directly.

### Recommended Hosting Options:

1. **Railway** (Recommended - Easy & Free tier available)
   - Connect your GitHub repository
   - Railway auto-detects Spring Boot apps
   - Provides free tier with 500 hours/month

2. **Render** (Free tier available)
   - Connect GitHub repository
   - Select "Web Service"
   - Build command: `./mvnw clean package`
   - Start command: `java -jar target/item-api-1.0.0.jar`

3. **Heroku** (Paid, but has free alternatives)
   - Use Heroku CLI or GitHub integration
   - Requires `Procfile` (included below)

4. **Google Cloud Run** (Pay-as-you-go)
   - Containerize the app with Docker
   - Deploy to Cloud Run

5. **AWS Elastic Beanstalk** (Free tier available)
   - Upload JAR file or use Git integration

### Deployment Files Included

- `Procfile` - For Heroku/Railway deployment
- `Dockerfile` - For containerized deployment (optional)

## Example Deployment to Railway

1. Push code to GitHub
2. Go to [Railway.app](https://railway.app)
3. Click "New Project" → "Deploy from GitHub"
4. Select your repository
5. Railway will auto-detect Spring Boot and deploy
6. Your API will be available at `https://your-app-name.up.railway.app`

## Contact

For questions or issues, please contact: dsvjavalinux@gmail.com

## License

This project is open source and available for educational purposes.



