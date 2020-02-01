# Intercorp Assessment
## Documentación

http://intercorpclientsappv2-env.mf3metxaxb.us-east-1.elasticbeanstalk.com/swagger-ui.html#/

## Endpoints disponibles

- Crear un nuevo cliente
### POST
```
url
http://intercorpclientsappv2-env.mf3metxaxb.us-east-1.elasticbeanstalk.com/api/v1/creacliente

body
{
	"name": "Javier",
	"surname": "Garcia",
	"age": 35,
	"birthdate": "1990-04-15"
}
```

- Obtener KPI de los clientes (promedio edad entre todos los clientes y desviación estándar entre las edades de todos los clientes)
### GET
```
url
http://intercorpclientsappv2-env.mf3metxaxb.us-east-1.elasticbeanstalk.com/api/v1/kpideclientes
```

- Lista de personas con todos los datos con su fecha probable de muerte de cada una
### GET
```
url
http://intercorpclientsappv2-env.mf3metxaxb.us-east-1.elasticbeanstalk.com/api/v1/listclientes
```
