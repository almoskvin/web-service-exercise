# Web service for calculating math operations for a list of values.
Available operations:
- get minimum value(s) for a list of values and a quantifier
- get maximum value(s) for a list of values and a quantifier
- get average value(s) for a list of values
- get median value(s) for a list of values
- get percentile value for a list of values and a quantifier using the closest-rank method

Additional operation:
- compare versions. Versions format is 1.1.1.1 etc. Only digits and dots allowed. The result value 0 if x == y; -1 if x < y; and 1 if x > y 

## Software versions used in development
Maven  3.8.1\
Java 17\
Spring Boot 2.6.2\
Lombok 1.18.22

## Running instructions
Navigate to the project root in terminal and execute `mvn spring-boot:run`. Application will run on the port 8080.

## Test coverage
- Unit tests (JUnit 5). Run on `mvn test`. Cover utils methods.
- Intellij HTTP requests (%PROJECT_ROOT%/requests). Run from Intellij Idea environment. Cover controllers' endpoints.

## Endpoints
### Version Controller
#### Compare versions
`GET http://localhost:8080/api/v1/version/compare/{{version_1}}/{{version_2}}`

_Expected result_\
**Status** `200`\
**Body**\
`0`, `1` or `-1`

_Request example_\
`GET http://localhost:8080/api/v1/version/compare/1.0.3/1.2.1`

_Response body example_
```Json
-1
```

### Math Controller
#### Get minimum value
`GET http://localhost:8080/api/v1/math/min/{{quantifier}}/{{list_of_values}}`

_Expected result_\
**Status** `200`\
**Body**
```
[
  %LIST_OF_VALUES%
]
```

_Request example_\
`GET http://localhost:8080/api/v1/math/min/2/1,2,3,4,0`

_Response body example_
```Json
[
  0,
  1
]
```

#### Get maximum value
`GET http://localhost:8080/api/v1/math/max/{{quantifier}}/{{list_of_values}}`

_Expected result_\
**Status** `200`\
**Body**
```
[
  %LIST_OF_VALUES%
]
```

_Request example_\
`GET http://localhost:8080/api/v1/math/max/2/1,2,3,4,0`

_Response body example_
```Json
[
  3,
  4
]
```

#### Get average value
`GET http://localhost:8080/api/v1/math/avg/{{list_of_values}}`

_Expected result_\
**Status** `200`\
**Body**
```
%DOUBLE_VALUE%
```

_Request example_\
`http://localhost:8080/api/v1/math/avg/100,2,3,4,5`

_Response body example_
```Json
22.8
```

#### Get median value
`GET http://localhost:8080/api/v1/math/median/{{list_of_values}}`

_Expected result_\
**Status** `200`\
**Body**
```
%DOUBLE_VALUE%
```

_Request example_\
`http://localhost:8080/api/v1/math/median/100,2,3,4,5,10`

_Response body example_
```Json
3.5
```

#### Get percentile value
`GET http://localhost:8080/api/v1/math/percentile/{{quantifier}}/{{list_of_values}}`

_Expected result_\
**Status** `200`\
**Body**
```
%VALUE%
```

_Request example_\
`GET http://localhost:8080/api/v1/math/percentile/75/3,6,7,8,8,10,13,15,16,20`

_Response body example_
```Json
15
```
