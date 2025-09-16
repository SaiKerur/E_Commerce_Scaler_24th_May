## Ecommerce Scaler - Architecture and APIs

### Load Balancer and API Gateway
- Use ELB in front of the application. An API Gateway such as Kong routes requests to the service.
- Public routes: `/api/auth/**`, `GET /api/products/**`, `GET /api/categories/**`.
- Authenticated routes: `/api/user/**`, `/api/cart/**`, `/api/orders/**`.

### Microservices (monolith-style modules in this repo)
- User Management (MySQL): Registration, Login (JWT), Profile, Password reset event.
- Product Catalog (MySQL + Elasticsearch): CRUD and `/api/products/search?q=...`.
- Cart (MongoDB + Redis): User cart operations with Redis caching.
- Order Management (MySQL + Kafka): Create orders, list history, get details.
- Payment (Kafka): Listens to `order.events`, simulates payment, emits `payment.events` and notifies.
- Notification (Kafka): Consumes `notification.events` and logs; integrate with SES in prod.

### Kafka Topics
- `user.events`, `notification.events`, `order.events`, `payment.events`.

### Local Setup
- MySQL running and configured in `application.properties`.
- MongoDB at `mongodb://localhost:27017/ecommerce`.
- Redis at `localhost:6379`.
- Kafka at `localhost:9092`.
- Elasticsearch at `http://localhost:9200`.

### JWT
- Configure `security.jwt.secret` and `security.jwt.expiration-ms` in `application.properties` or environment variables.

### Key Endpoints
- Auth: `POST /api/auth/register`, `POST /api/auth/login`, `POST /api/auth/password/reset?email=`
- User: `GET /api/user/me`, `PUT /api/user/profile`
- Products: `GET /api/products`, `GET /api/products/{id}`, `POST /api/products`, `PUT /api/products/{id}`, `DELETE /api/products/{id}`, `GET /api/products/search?q=`
- Categories: `GET /api/categories`, `POST /api/categories`, ...
- Cart: `GET /api/cart`, `POST /api/cart/items`, `PUT /api/cart/items`, `DELETE /api/cart/items/{productId}`, `DELETE /api/cart`
- Orders: `POST /api/orders`, `GET /api/orders`, `GET /api/orders/{id}`

### Notes
- This repo models a microservices architecture within a Spring Boot monolith for simplicity. In production, split modules and use Kong/ELB for routing and scaling.


