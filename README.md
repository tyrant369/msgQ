# msgQ - Message Streaming System

**msgQ** is a lightweight, pluggable message streaming system built using **Spring Boot** and **H2 Database**. It emulates core concepts of systems like Apache Kafka, including **topics**, **partitions**, **producers**, **consumers**, **offset tracking**, and **message ordering** — all with a pluggable **partition strategy** mechanism.

---

## Features

### Topics
- Create new topics with configurable number of partitions
- Edit topic configuration
- Delete topics
- Use different **partitioning strategies** (`KEY_HASH`, etc.)

### Producers
- Publish messages with `key` and `payload`
- Messages are assigned to partitions based on the topic’s partitioning strategy
- Partitioning is pluggable and implemented via the **Strategy Pattern**

### Consumers
- Subscribe/unsubscribe to topics
- Consume **all new messages** after current offset per partition
- Tracks **offsets per partition per consumer** to avoid duplicate processing
- Ensures **message ordering within each partition**

### Messages
- Messages contain a `key`, `payload`, `timestamp`, and partition info
- Strict ordering maintained **within a partition**
- Concurrency-safe delivery for multiple consumers

---

## 📁 Project Structure

```
msgQ/
├── controller/              # REST APIs
├── dto/                     # DTOs
│   ├── error/
|   ├── request/
│   └── response/
├── model/                   # JPA entities
├── repository/              # Spring Data JPA Repositories
├── service/                 # Service Layer
├── partition/               # Partition strategy
|   ├── factory/
│   └── strategy/
├── exception/               # Custom Exceptions
├── MsgQApplication.java     # Spring Boot Entry Point
└── resources/
    └── application.properties
```

---

## 🛠 Technologies Used

- **Java 17**
- **Spring Boot**
- **H2 In-Memory Database**
- **Lombok**
- **Spring Validation (Jakarta)**
- **Maven**

---

## Partition Strategies (Pluggable)

Implemented via the **Strategy Pattern**, each topic can use a different strategy:

| Strategy      | Description                                      |
|---------------|--------------------------------------------------|
| `KEY_HASH`    | `hash(key) % partitionCount`                     |

---

## Example API Usage

### Create Topic
```http
POST /topics
{
  "name": "orders",
  "partitions": 3,
  "strategy": "KEY_HASH"
}
```

### Publish Message
```http
POST /producers/orders/publish
{
  "key": "order-123",
  "payload": "Order placed"
}
```

### Subscribe To Topic
```http
POST /consumers/orders/subscribe
{
  "consumerId": "consumer-A"
}
```

### Consume Messages
```http
POST /consumers/orders/consume
{
  "consumerId": "consumer-A"
}
```

---

## How to Run

```bash
# Clone the repo
git clone https://github.com/yourname/msgQ.git
cd msgQ

# Run the Spring Boot app
./mvnw spring-boot:run
```

- Visit H2 Console: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`

---

## Extendable Design

Add new `PartitionStrategy` by implementing:

```java
public interface PartitionStrategy {
    int selectPartition(String key, int partitionCount);
}
```

Register it in `PartitionStrategyFactory` for auto-selection.

---

## Author

Made with ❤️ by **Parthib Dhar**     
Phone : +91 9674115528   
Email : prthbdhr@gmail.com.com   
LinkedIn : [LinkedIn](https://linkedin.com/in/prthbdhr)   

---

## License

This project is open-source and available under the [MIT License](LICENSE).

---
