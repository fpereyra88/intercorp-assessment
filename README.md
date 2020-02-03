# Intercorp Assessment
## Documentación

http://intercorpclientsappv2-env.mf3metxaxb.us-east-1.elasticbeanstalk.com/swagger-ui.html#/

## Endpoints disponibles

----
Crear un nuevo cliente

* **URL**

  http://intercorpclientsappv2-env.mf3metxaxb.us-east-1.elasticbeanstalk.com/api/v1/creacliente
  
* **Method:**

  `POST`
  
* **Headers:**
  
  `Content-Type: application/json`
  
* **BODY**

```
{
	"name": "Javier",
	"surname": "Garcia",
	"age": 35,
	"birthdate": "1990-04-15"
}
```
  
* **URL Params**

    None

----

Obtener KPI de los clientes (promedio edad entre todos los clientes y desviación estándar entre las edades de todos los clientes)

* **URL**

  http://intercorpclientsappv2-env.mf3metxaxb.us-east-1.elasticbeanstalk.com/api/v1/kpideclientes
  
* **Method:**

  `GET`
  
* **Headers:**
  
  `Content-Type: application/json`
  
* **BODY**

    None
  
* **URL Params**

    None
    
----
Lista de personas con todos los datos con su fecha probable de muerte de cada una

* **URL**

  http://intercorpclientsappv2-env.mf3metxaxb.us-east-1.elasticbeanstalk.com/api/v1/listclientes
  
* **Method:**

  `GET`
  
* **Headers:**
  
  `Content-Type: application/json`
  
* **BODY**

    None
  
* **URL Params**

    None
