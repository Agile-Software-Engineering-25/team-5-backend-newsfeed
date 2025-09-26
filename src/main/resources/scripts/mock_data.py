#!/usr/bin/env python3
"""
Mock client for Newsfeed API (OpenAPI sample).
Requires: pip install requests
Usage: python3 mock_newsfeed.py
"""

import requests
import uuid
import json
import random
from datetime import datetime, timezone, timedelta

BASE = "http://localhost:8080"
HEADERS = {"Content-Type": "application/json"}
TIMEOUT = 5


def iso_now():
    return datetime.now(timezone.utc).isoformat()


def build_sample_post():
    now = datetime.now(timezone.utc)
    status = random.choice(["draft", "published", "archived", "deleted"])
    post = {
        "title": f"Test Post {uuid.uuid4().hex[:6]}",
        "summary": "Automated test summary for API mocking.",
        "status": status,
        "content": {"format": "html", "body": "<p>Auto-generated body</p>"},
        "author": {"user_id": str(uuid.uuid4()), "name": "Mock Author"},
        # optional fields
        "featured_image": {
            "url": "http://example.local/image.jpg",
            "alt_text": "example",
            "caption": "caption"
        },
        "publish_date": (now.isoformat() if status == "published" else None),
        "expiration": {
            "expires_at": (now + timedelta(days=7)).isoformat(),
            "auto_archive": False
        },
        "permissions": {"read": [], "write": [], "delete": []},
        "settings": {"featured": False, "sticky": False}
    }
    # remove None fields so payload is cleaner
    return {k: v for k, v in post.items() if v is not None}


def print_response(resp):
    print(f"> {resp.request.method} {resp.url} -> {resp.status_code}")
    ct = resp.headers.get("content-type", "")
    try:
        if "application/json" in ct:
            print(json.dumps(resp.json(), indent=2))
        else:
            print(resp.text[:1000])
    except Exception:
        print(resp.text[:1000])


def create_post(payload):
    url = f"{BASE}/newsfeed"
    return requests.post(url, json=payload, headers=HEADERS, timeout=TIMEOUT)


# def list_posts(page=0, pageSize=10, query=None, frm=None, to=None):
#     url = f"{BASE}/newsfeed"
#     params = {"page": page, "pageSize": pageSize}
#     if query: params["query"] = query
#     if frm: params["from"] = frm
#     if to: params["to"] = to
#     return requests.get(url, params=params, headers=HEADERS, timeout=TIMEOUT)


def update_post(post_id, payload):
    url = f"{BASE}/newsfeed/{post_id}"
    return requests.put(url, json=payload, headers=HEADERS, timeout=TIMEOUT)


def delete_post(post_id):
    url = f"{BASE}/newsfeed/{post_id}"
    return requests.delete(url, headers=HEADERS, timeout=TIMEOUT)


def get_history(post_id):
    url = f"{BASE}/newsfeed/{post_id}/history"
    return requests.get(url, headers=HEADERS, timeout=TIMEOUT)


def find_id(obj):
    """Recursive small helper to find an 'id' value in response JSON."""
    if isinstance(obj, dict):
        if "id" in obj and isinstance(obj["id"], str):
            return obj["id"]
        for v in obj.values():
            found = find_id(v)
            if found:
                return found
    if isinstance(obj, list):
        for item in obj:
            found = find_id(item)
            if found:
                return found
    return None


def main():
    # 1) List (empty or existing)
    # print("\n--- LIST (page=0,pageSize=5) ---")
    # r = list_posts(page=0, pageSize=5)
    # print_response(r)

    # 2) Create
    payload = build_sample_post()
    print("\n--- CREATE ---")
    r = create_post(payload)
    print_response(r)

    # try to extract id from create response
    created_id = None
    try:
        j = r.json()
        created_id = find_id(j)
    except Exception:
        created_id = None

    if not created_id:
        print("\nNo id found in create response. The script will stop here for id-dependent calls.")
        return

    # 3) Update
    print("\n--- UPDATE ---")
    updated_payload = payload.copy()
    updated_payload["summary"] = payload.get("summary", "") + " (updated)"
    updated_payload["title"] = payload.get("title", "") + " (updated)"
    r = update_post(created_id, updated_payload)
    print_response(r)

    # 4) History
    print("\n--- HISTORY ---")
    r = get_history(created_id)
    print_response(r)

    # 5) Delete
    print("\n--- DELETE ---")
    r = delete_post(created_id)
    print_response(r)

    # 6) List again to verify
    # print("\n--- LIST AFTER DELETE (page=0,pageSize=5) ---")
    # r = list_posts(page=0, pageSize=5)
    # print_response(r)


if __name__ == "__main__":
    main()
