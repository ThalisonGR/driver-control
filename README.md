# Driver Control

Backend para gerenciamento de despesas de motoristas (Uber).

**Stack:** Spring Boot 4.0 | Java 21 | H2 | JPA | MapStruct | OpenAPI

## Endpoints

### Cars

| Method | Endpoint                | Description                    | Body                        |
|--------|-------------------------|--------------------------------|-----------------------------|
| POST   | `/api/cars`             | Create a new car               | `CreateCarRequestDto`       |
| GET    | `/api/cars`             | List all cars                  | -                           |
| GET    | `/api/cars/{id}`        | Find car by ID                 | -                           |
| PUT    | `/api/cars/{id}`        | Update car                     | `UpdateCarRequestDto`       |
| DELETE | `/api/cars/{id}`        | Delete car                     | -                           |
| GET    | `/api/cars/{id}/consumption` | Get consumption metrics   | -                           |

#### CreateCarRequestDto
```json
{
  "plate": "ABC1D23",
  "initialMileage": 0
}
```

#### UpdateCarRequestDto
```json
{
  "plate": "ABC1D23",
  "mileage": 10000
}
```

### Transactions

| Method | Endpoint                          | Description                  | Body                        |
|--------|-----------------------------------|------------------------------|-----------------------------|
| POST   | `/api/transactions`               | Create a transaction         | `TransactionRequestDto`     |
| GET    | `/api/transactions?carId={carId}` | List transactions by car     | -                           |

#### TransactionRequestDto
```json
{
  "carId": "550e8400-e29b-41d4-a716-446655440000",
  "value": 200.50,
  "currency": "BRL",
  "type": "Supply",
  "km": 50000,
  "description": "Gasolina - Posto Shell"
}
```

#### Transaction Types

| Type         | Mileage required |
|--------------|------------------|
| Supply       | Yes              |
| Maintenance  | Yes              |
| Tax          | No               |
| Others       | No               |
| UberIncome   | No               |

#### ConsumptionResponseDto
```json
{
  "carId": "...",
  "plate": "ABC1D23",
  "kmDriven": 500,
  "totalSpentOnSupplies": 200.50,
  "costPerKm": 0.4010,
  "cashFlowBalance": 1500.00
}
```

- **kmDriven**: KM rodados desde o último abastecimento
- **totalSpentOnSupplies**: Total gasto com abastecimentos
- **costPerKm**: Custo por KM rodado (gastos / KM)
- **cashFlowBalance**: Saldo do fluxo de caixa (receitas Uber - despesas)

## Swagger UI

```
http://localhost:8080/swagger-ui.html
```

## H2 Console

```
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:driver-control
User: sa
Password: (vazio)
```
