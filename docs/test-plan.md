# Test Plan – Asset Management REST API

This document defines test cases to ensure correct functionality of the Asset Management REST API.

---

## 1. Controller Layer Tests

| Test ID | Test Description | Input | Expected Result |
|--------|-----------------|-------|----------------|
| C1 | Get empty asset list | GET `/assets` | Returns `[]` and status `200` |
| C2 | Get populated asset list | GET `/assets` after adding items | Returns list of items with correct data |
| C3 | Add valid asset item | POST `/assets` with `{"symbol":"AAPL","shares":10,"purchasePrice":150.0}` | Returns item with status `201` |
| C4 | Add item with missing fields | POST `/assets` with `{"symbol":"AAPL"}` | Returns `400` with error message |
| C5 | Add item with negative shares | POST `/assets` with `{"symbol":"AAPL","shares":-5}` | Returns `400` with error message |
| C6 | Remove existing asset | DELETE `/assets/1` | Returns `204` |
| C7 | Remove non-existent asset | DELETE `/assets/999` | Returns `404` |
| C8 | View performance of populated asset list | GET `/assets/performance` | Returns performance metrics (JSON) with status `200` |
| C9 | View performance of empty asset list | GET `/assets/performance` | Returns zeroed or empty metrics with status `200` |

---

## 2. Service Layer Tests

| Test ID | Test Description | Input | Expected Result |
|--------|-----------------|-------|----------------|
| S1 | Add valid asset item | Asset object | Item saved and returned |
| S2 | Add invalid asset item | Null or invalid fields | Throws validation exception |
| S3 | Calculate total asset value | Items with known values | Returns correct total |
| S4 | Remove asset item by ID | Valid ID | Item removed |
| S5 | Remove asset item by invalid ID | Invalid ID | Throws exception |

---

## 3. Repository Layer Tests

| Test ID | Test Description | Input | Expected Result |
|--------|-----------------|-------|----------------|
| R1 | Save item and retrieve by ID | Asset object | Retrieved item matches saved item |
| R2 | Retrieve all items | Multiple saved items | Returns list matching all saved items |
| R3 | Delete item by ID | Valid ID | Item is deleted and not retrievable |
| R4 | Auto-generate ID | Asset object without ID | ID generated automatically |

---

## 4. Integration Tests

| Test ID | Test Description | Steps | Expected Result |
|--------|-----------------|-------|----------------|
| I1 | Add and retrieve asset list | POST item → GET assets | Item appears in GET response |
| I2 | Remove item and confirm deletion | POST item → DELETE item → GET assets | Item no longer in GET response |
| I3 | Performance calculation | POST multiple items → GET `/assets/performance` | Correct calculation returned |

---

## 5. Negative & Edge Case Tests

| Test ID | Test Description | Input | Expected Result |
|--------|-----------------|-------|----------------|
| N1 | Invalid JSON request | POST `/assets` with malformed JSON | Returns `400` with error |
| N2 | Large asset dataset | Asset list with 10,000+ items | API responds within acceptable time |
| N3 | Empty asset performance | No items in DB | Performance endpoint returns zero values |

---

*Last Updated: 2025-08-25*
