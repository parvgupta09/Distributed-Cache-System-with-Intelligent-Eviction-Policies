import requests
import time
import random
from concurrent.futures import ThreadPoolExecutor, as_completed


BASE_URL = "http://localhost:8000"   # FastAPI server
TOTAL_REQUESTS = 10_000              # increase to 20_000 / 50_000 later
CONCURRENCY = 50                     # number of parallel threads

# Number of distinct keys
keys = [f"key{i}" for i in range(50)]

def send_request():
    key = random.choice(keys)

    try:
        # 70% GET, 30% PUT
        if random.random() < 0.85:
            r = requests.get(f"{BASE_URL}/cache/{key}", timeout=5)
        else:
            value = random.randint(1, 1000)
            ttl = random.randint(2000, 5000)
            r = requests.post(
                f"{BASE_URL}/cache/{key}",
                params={"value": value, "ttl": ttl},
                timeout=5
            )

        return r.status_code

    except Exception:
        return None

def run_stress_test():
    start = time.time()
    success = 0
    failure = 0

    with ThreadPoolExecutor(max_workers=CONCURRENCY) as executor:
        futures = [executor.submit(send_request) for _ in range(TOTAL_REQUESTS)]

        for future in as_completed(futures):
            status = future.result()
            if status == 200:
                success += 1
            else:
                failure += 1

    end = time.time()
    duration = end - start

    print("\n===== STRESS TEST RESULT =====")
    print(f"Total Requests : {TOTAL_REQUESTS}")
    print(f"Concurrency    : {CONCURRENCY}")
    print(f"Success        : {success}")
    print(f"Failure        : {failure}")
    print(f"Total Time     : {duration:.2f} seconds")
    print(f"Throughput     : {TOTAL_REQUESTS / duration:.2f} req/sec")

if __name__ == "__main__":
    run_stress_test()
