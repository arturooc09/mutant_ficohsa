## Descripción
Microservicio para validar si el ADN de una persona pertenece al de un mutante

## Endpoint
`POST /mutant/v1/mutant`
- Headers:
  <br>_(string)_ session_id - Id de sesión (uuid)
  <br>_(string)_ channel-id - Canal (131)
  <br>_(string)_ application - Aplicación (BancaMovil - APP PN)
  <br>_(string)_ timestamp - Fecha actual (2019-09-01T08:58:10)

- Ejemplos:
  <br>http://localhost:8080/mutant/v1/mutant

- Respuestas:
    <br>
    <br>Status: 200 OK<br>
    <br>Status: 403 ADN no compatible with mutant<br>
    <br>Status: 400 Bad Request
    ```json
    {
        "codigo": "",
        "mensagem": "{\"code\":\"E0400\",\"message\":\"Bad Request\",\"error_message\":\"Required String parameter 'first_surname' is not present\"}"
    }
    ```
    Status: 404 Not Found
    ```json
    {
        "codigo": "",
        "mensagem": "{\"code\":\"E0404\",\"message\":\"Not Found\",\"error_message\":\"No handler found for POST /mutant/v1/mutant\"}"
    }
    ```
    Status: 500 Internal Server Error
    ```json
    {
        "codigo": "",
        "mensagem": "Internal Server Error"
    }
    ```

## Documentación Microservicio
- [Actuator Info](http://localhost:8080/actuator/info)
- [Actuator Status](http://localhost:8080/actuator/health)
- [Swagger](http://localhost:8080/swagger-ui.html)
