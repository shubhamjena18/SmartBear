A Spring Boot WebFlux application that converts digital time input into **British spoken time format**.  
Example:

- `1:00` → **one o'clock**  
- `2:05` → **five past two**  
- `3:15` → **quarter past three**  
- `4:45` → **quarter to five**  

The service uses **Caffeine Cache & Redis** 

---

## 🚀 Features
- Reactive **Spring WebFlux** API
- **Caffeine** (in-memory cache) + **Redis** (distributed cache)
- Dockerized setup with `Dockerfile` and `docker-compose.yml`
- REST API for converting time into British spoken format

---


---

## 🛠️ Prerequisites

- [Docker Desktop](https://www.docker.com/products/docker-desktop) installed and running  
- Java 17+ (if you want to run without Docker)  
- Maven 3.9+  

---

## ⚡ Running with Docker Compose

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/spoken-time-service.git
   cd spoken-time-service

2. Build and start the services :
   docker-compose up --build
  
3. Verify the containers :
  docker ps

4. Test the API using postman :
     http://localhost:8080/api/time/v1/britishtime?time=12:00
