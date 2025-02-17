# Spring AI Test Project

This is a small project to test the Spring AI library.

## Developer

- **Name:** Cassio Noronha
- **Role:** Software Developer
- **Certifications:**
  - AWS Architect
  - AWS DevOps
  - Scrum Master
  - Sun Certified Java Programmer 6

## Web Application
The web interface is available at:
[htttp://localhost:3000](http://localhost:3000)]

## Swagger Documentation

Once the application is running, access the API documentation at:  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Environment Setup

Before running the application, export your OpenAI token:
```
export OPENAI_TOKEN=your_openai_api_key
```

# Running the Application in Docker

### Option 1: Using docker-compose (always rebuilds)
```
docker-compose up --build
```

### Option 2: Using only Dockerfile

#### Backend

Go to the backend directory:
```
cd backend
```

##### Build with Dockerfile
```
docker build -t <your-image-name> .
```

##### Run the Docker container
```
docker run -p 8080:8080 -e OPENAI_TOKEN <your-image-name>
```

#### Frontend

Go to the frontend directory:
```
cd frontend
```

##### Build with Dockerfile
```
docker build -t <your-image-name> .
```

##### Run the Docker container
```
docker run -p 3000:3000 -e REACT_APP_BACKEND_URL=http://localhost:8080 <your-image-name>
```
