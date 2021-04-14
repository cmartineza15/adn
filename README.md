# Identificador de mutantes

Identificador de mutantes realiza la comprobración de una cadena de ADN representada de la siguiente forma:

1. Vector de cadena de caracteres que debe tener un tamaño de 6 posiciones.
2. La cadena de caracteres debe tener un tamaño de 6 poisiciones.
3. Solamente se acepta las letras ATCG dentro de la cadena de ADN.

Ejemplo: ["ATCGAA", "ATCGAA", "ATCGAA", "ATCGAA", "ATCGAA", "ATCGAA"]

## Tecnologías utilizadas

- Java + Spring boot Web Flux
- Lombok
- Maven
- Git
- Junit
- Mockito
- r2dbc (Postgres)
- Spring Fox
- Swagger api

## ¿Que debemos tener antes de empezar?

Antes de empezar a utilizar el aplicativo de debemos tener instalado:

- Postgres 10.x
- Java 1.8.x
- maven 3.5.x

## Installation

Para poder generar el jar, debemos configurar primero los datos de BD, se encuentran en la ruta src/main/resources/application.yml

Una vez se cambie, debemos ejecutar el siguiente script para ejecutar la aplicación

```bash
mvn spring-boot:run
```

# Uso

/mutant
--
Para si el adn es de un mutante debemos ejecutar  la siguiente api '/mutant', si es un mutante nos dara como respuesta un HTTP Code 200, si es humano devolverá un HTTP Code 403.

```
curl --location --request POST 'http://localhost:5000/mutant' \
--header 'Content-Type: application/json' \
--data-raw '{
    "dna":["ATCGAT", "TCGATC", "AGCCAT", "CTCGTA", "ATCGAA", "ATAGAA"]
}'
```

Cuerpo del mensaje:

```json
{
    "dna":["ATCGAT", "TCGATC", "AGCCAT", "CTCGTA", "ATCGAA", "ATAGAA"]
}
```
/stats/reload
--

Para acutalizar las estadisticas de ADN validado, ejecutatamos '/stats/reload'. Nos actualizara el reporte de estadisticas de adns analizados.

```
curl --location --request GET 'http://localhost:5000/stats/reload'
```

Respuesta del servicio:

```json
{
    "id": 1,
    "count_mutant_dna": 1,
    "count_humna_dna": 1,
    "ratio": 1.0
}
```
/stats
--
Para validar las estadisticas de ADN validado, ejecutatamos '/stats'

```
curl --location --request GET 'http://localhost:5000/stats'
```
Respuesta del servicio:

```json
{
    "id": 1,//id
    "count_mutant_dna": 1, // número de mutantes
    "count_humna_dna": 1, // número de humanos
    "ratio": 1.0  //  = muantes/humanos
}
```




## DEMO

Puedes consumir el api, en la siguiente url:

```
 http://mutant-env.eba-xrgestqi.us-west-2.elasticbeanstalk.com/swagger-ui/index.html
```
