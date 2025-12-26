from fastapi import FastAPI
from fastapi.staticfiles import StaticFiles
from fastapi.responses import FileResponse
import requests

JAVA_CACHE = "http://localhost:8080/cache"

app = FastAPI()

# Serve static files
app.mount("/static", StaticFiles(directory="static"), name="static")

@app.get("/")
def serve_ui():
    return FileResponse("static/index.html")

@app.get("/cache/{key}")
def get(key: str):
    return requests.get(f"{JAVA_CACHE}/{key}").text

@app.post("/cache/{key}")
def put(key: str, value: str, ttl: int):
    requests.post(
        f"{JAVA_CACHE}/{key}",
        params={"value": value, "ttl": ttl}
    )
    return {"status": "ok"}

@app.get("/metrics")
def metrics():
    return requests.get(f"{JAVA_CACHE}/metrics").json()
