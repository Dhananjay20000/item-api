# Deployment Guide

## Hosting Options for Java Spring Boot Applications

**Important:** Vercel and Netlify are designed for frontend applications and serverless functions. They do **not** support traditional Java Spring Boot applications that require a JVM runtime.

### Recommended Hosting Platforms:

### 1. Railway (Recommended - Easiest & Free Tier Available)

**Steps:**
1. Push your code to GitHub
2. Go to [railway.app](https://railway.app) and sign up
3. Click "New Project" → "Deploy from GitHub repo"
4. Select your repository (`item-api`)
5. Railway will auto-detect Spring Boot and deploy automatically
6. Your API will be live at: `https://your-app-name.up.railway.app`

**Free Tier:** 500 hours/month, $5 credit

**Environment Variables (if needed):**
- `PORT` - Railway sets this automatically
- `JAVA_OPTS` - Optional JVM options

---

### 2. Render (Free Tier Available)

**Steps:**
1. Push code to GitHub
2. Go to [render.com](https://render.com) and sign up
3. Click "New +" → "Web Service"
4. Connect your GitHub repository
5. Configure:
   - **Name:** item-api
   - **Environment:** Java
   - **Build Command:** `./mvnw clean package -DskipTests`
   - **Start Command:** `java -jar target/item-api-1.0.0.jar`
   - **Port:** 8080 (or use `$PORT` environment variable)
6. Click "Create Web Service"

**Free Tier:** 750 hours/month, spins down after 15 min inactivity

**Note:** Update `application.properties` to use `server.port=${PORT:8080}`

---

### 3. Heroku (Paid, but has alternatives)

**Steps:**
1. Install Heroku CLI: [devcenter.heroku.com/articles/heroku-cli](https://devcenter.heroku.com/articles/heroku-cli)
2. Login: `heroku login`
3. Create app: `heroku create your-app-name`
4. Deploy: `git push heroku main`
5. Open: `heroku open`

**Note:** Heroku removed free tier, but alternatives exist

---

### 4. Google Cloud Run (Pay-as-you-go, Generous Free Tier)

**Steps:**
1. Install Google Cloud SDK
2. Build Docker image: `docker build -t gcr.io/YOUR_PROJECT/item-api .`
3. Push to Container Registry: `docker push gcr.io/YOUR_PROJECT/item-api`
4. Deploy to Cloud Run: `gcloud run deploy item-api --image gcr.io/YOUR_PROJECT/item-api`

**Free Tier:** 2 million requests/month, 360,000 GB-seconds memory

---

### 5. AWS Elastic Beanstalk (Free Tier Available)

**Steps:**
1. Install AWS CLI and EB CLI
2. Initialize: `eb init -p java-17 item-api`
3. Create environment: `eb create item-api-env`
4. Deploy: `eb deploy`

**Free Tier:** 750 hours/month for 12 months

---

## Updating Application for Cloud Deployment

### Update `application.properties` for Dynamic Ports:

```properties
server.port=${PORT:8080}
```

This allows platforms like Railway/Render to set the port dynamically.

### Update `Procfile` (if needed):

```
web: java $JAVA_OPTS -jar target/item-api-1.0.0.jar
```

---

## Testing Your Deployed API

Once deployed, test your API endpoints:

**Base URL:** `https://your-app-name.up.railway.app/api/items` (or your platform URL)

**Add an item:**
```bash
curl -X POST https://your-app-name.up.railway.app/api/items \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test Item",
    "description": "Test Description",
    "price": 99.99
  }'
```

**Get item by ID:**
```bash
curl https://your-app-name.up.railway.app/api/items/1
```

---

## Email Deployment Link

Once deployed, you can share the link:
- **Email:** dsvjavalinux@gmail.com
- **Subject:** Item Management API - Deployment Link
- **Body:** Include the deployed URL and brief description

---

## Troubleshooting

### Port Issues
- Ensure your app uses `server.port=${PORT:8080}` for dynamic port assignment
- Some platforms set `PORT` environment variable automatically

### Build Failures
- Ensure Java 17 is specified in `pom.xml`
- Check build logs for Maven dependency issues
- Verify `Procfile` or start command is correct

### Application Not Starting
- Check logs: `heroku logs --tail` or platform equivalent
- Verify JAR file is built correctly: `mvn clean package`
- Ensure all dependencies are included in the JAR

---

## Quick Start: Railway Deployment

**Fastest way to deploy:**

1. **Create GitHub Repository:**
   ```bash
   cd item-api
   git init
   git add .
   git commit -m "Initial commit"
   git remote add origin https://github.com/YOUR_USERNAME/item-api.git
   git push -u origin main
   ```

2. **Deploy on Railway:**
   - Go to railway.app
   - Click "New Project"
   - Select "Deploy from GitHub repo"
   - Choose your repository
   - Wait for deployment (2-3 minutes)
   - Copy the generated URL

3. **Share the Link:**
   - Email: dsvjavalinux@gmail.com
   - Include: Deployment URL and API documentation link

---

**Note:** The application uses in-memory storage, so data will reset when the application restarts. For production use, consider adding a database (PostgreSQL, MySQL, etc.).



