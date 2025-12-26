# Distributed Cache System with Intelligent Eviction Policies 

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Core-green)
![FastAPI](https://img.shields.io/badge/FastAPI-Gateway-blue)
![Cache](https://img.shields.io/badge/In--Memory-Cache-orange)
![Eviction](https://img.shields.io/badge/Eviction-Policies-red)
![License](https://img.shields.io/badge/License-MIT-purple)

---

## Overview  

The Distributed Cache System with Intelligent Eviction Policies is a backend systems project that simulates how modern in-memory caches manage data efficiently under limited memory constraints.

The system is built around a **Java-based core cache engine** that handles storage, eviction logic, and metrics, while a **FastAPI gateway** provides a lightweight API and UI for interaction and testing.

Instead of storing data indefinitely, the cache actively applies intelligent eviction strategies such as **LRU (Least Recently Used)**, **LFU (Least Frequently Used)**, **Adaptive eviction**, and **TTL (Time-To-Live)** expiration. Frequently accessed data is retained, while stale or low-utility entries are automatically removed to maintain optimal memory usage.

The system exposes REST APIs for storing and retrieving key-value pairs, tracks real-time cache metrics (hits, misses, evictions), and is validated using stress tests to closely model real-world caching systems such as Redis or Memcached.



---

## System Architecture  
### Flow
```bash
Client / API Requests
        ↓
FastAPI Cache Gateway
        ↓
In-Memory Cache Store
        ↓
Eviction Engine (LRU / LFU / Adaptive / TTL)
```


---

## Tech Stack  

### Cache Core (Eviction & Storage Engine)  
- Java 17  
- Spring Boot  
- Custom In-Memory Data Structures  

### API Gateway & UI  
- Python  
- FastAPI  

### Eviction & Cache Management  
- LRU (Least Recently Used)  
- LFU (Least Frequently Used)  
- Adaptive Eviction Strategy  
- TTL (Time-To-Live) Expiration  

### Testing & Validation  
- Python  
- Requests  
- Stress / Load Testing  


---

## How the Project Works  

### 1️⃣ Cache Storage Layer  
- An in-memory cache stores key–value pairs for fast access  
- Each entry maintains metadata such as last access time and expiry time (TTL)  
- Data is stored entirely in memory to minimize access latency  

---

### 2️⃣ Cache Entry Modeling  
Each cached item is represented internally with:
- Key and value  
- Last accessed timestamp (for LRU tracking)  
- Expiry timestamp (for TTL-based expiration)  

---

### 3️⃣ Intelligent Eviction Logic  
- The cache enforces a fixed capacity limit  
- When capacity is reached:
  - The selected eviction strategy (LRU / LFU / Adaptive) determines which entry is removed  
- Entries with expired **TTL** are automatically removed  
- Eviction decisions ensure frequently accessed and valid data stays in memory  

---

### 4️⃣ Cache Operations & Validation  
- REST APIs allow clients to store and retrieve cached data  
- Cache performance is continuously tracked using metrics such as:
  - Cache hits and misses  
  - Number of evictions  
  - TTL-based expirations  
- Stress tests simulate repeated and concurrent access patterns to validate
  eviction correctness and cache efficiency  

---

## Features  

- **In-Memory Cache with Fast Access**  
  Implements a low-latency in-memory key–value store optimized for frequent reads and writes.

- **Intelligent Eviction Policies (LRU & TTL)**  
  Automatically removes stale or least-used entries when memory constraints or expiry conditions are met.

- **Real-Time Cache Performance Metrics**  
  Tracks hits, misses, evictions, and TTL expirations to analyze cache behavior under different workloads.

- **Configurable TTL Support**  
  Allows per-key expiration times, closely simulating production-grade caching systems.

- **RESTful API Interface**  
  Clean and simple APIs for storing, retrieving, and monitoring cached data.

- **Stress-Tested for Correctness and Stability**  
  Validated using repeated and concurrent requests to ensure eviction logic behaves correctly under load.

- **Clean and Modular Project Design**  
  Clear separation between cache storage, eviction logic, API layer, and metrics collection.


---

## Getting Started  

### Prerequisites  
- Java 17+  
- Maven  
- Python 3.9+  

---

### Clone the Repository  
```bash
git clone https://github.com/parvgupta09/Distributed-Cache-System-with-Intelligent-Eviction-Policies.git
cd Distributed-Cache-System-with-Intelligent-Eviction-Policies
```

### Run the Core Cache

```bash
cd java-cache
mvn spring-boot:run
```

#### The Core Cache runs at :
```bash
http://localhost:8080
```

### Run FastAPI Gateway (API + UI)
```bash
cd fastapi-gateway
pip install -r requirements.txt
uvicorn main:app --reload
```

#### The FastAPI Gateway runs at :
```bash
http://localhost:8000
```

### Run Stress Test
```bash
cd stress-test
python stress_test.py
```


## Project Structure
```bash
distributed-cache-system/
│
├── java-cache/                     ← CORE CACHE (MAIN VALUE)
│   │
│   ├── pom.xml                     ← Maven config (Spring Boot)
│   │
│   └── src/
│       └── main/
│           └── java/               ← ONLY this is Sources Root (blue)
│               └── com/
│                   └── cache/
│                       ├── CacheApplication.java
│                       │
│                       ├── api/
│                       │   └── CacheController.java
│                       │
│                       ├── core/
│                       │   ├── CacheEntry.java
│                       │   ├── CacheMetrics.java
│                       │   └── EvictionPolicy.java
│                       │
│                       ├── eviction/
│                       │   ├── LRUCache.java
│                       │   ├── LFUCache.java
│                       │   └── AdaptiveCache.java
│                       │
│                       └── service/
│                           └── CacheService.java
│
├── fastapi-gateway/                ← THIN API + UI
│   │
│   ├── main.py                     ← FastAPI gateway
│   ├── requirements.txt
│   │
│   └── static/
│       └── index.html              ← Simple demo UI (PUT / GET / Metrics)
│
├── stress-test/                    ← LOAD TESTING
│   └── stress_test.py
│
└── README.md                     

```


## Contact / Connect
If you liked this project or want to collaborate, feel free to connect with me:
- [LinkedIn](https://www.linkedin.com/in/parv-gupta-323738309/)
- Email: parvguptajpr@gmail.com
