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

# Running the Application in Docker

### Option 1: Using docker-compose (always rebuilds)
```
docker-compose up --build
```

### Option 2: Using only Dockerfile

#### Build with Dockerfile
```
docker build -t <your-image-name> .
```

#### Run the Docker container
```
docker run -p 8080:8080 <your-image-name>
```
