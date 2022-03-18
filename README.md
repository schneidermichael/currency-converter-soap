# Currency Converter

## Getting Started

1. `git clone https://github.com/schneidermichael/currency-converter-soap.git`
2. `cd currency-converter-soap`
3. `mvn package`
4. `mvn tomee:run`
5. Open a browser and go to https://localhost:4321/currency-converter-1.0.0/services (Username/Password see Credentials)
6. To stop the service type `exit` in your terminal

## Endpoints

### CXF - Service List
https://localhost:4321/currency-converter-1.0.0/services

### HTTP (Redirect to https)
http://localhost:8080/currency-converter-1.0.0/services/CurrencyConverterService

### HTTPS
https://localhost:4321/currency-converter-1.0.0/services/CurrencyConverterService

### WSDL
https://localhost:4321/currency-converter-1.0.0/services/CurrencyConverterService?wsdl

### Credentials

* Username: group1 
* Password: group1

## Docker

1. `docker build -t currency-converter .`
2. `docker run -p 4321:4321 -p 8080:8080 currency-converter`

## Testing

### Client (written in Node.js)

works only when the service is running (see Getting Started or Docker)

1. Go to `cd currency-converter-soap/src/main/client`
2. `npm install`
3. node client.js

### SoapUI

Import Workspace from `currency-converter-soap/src/main/resources/soapUI/currency-converter-soapui-project.xml` 

## Reference Documentation

* [Apache CXF](https://cxf.apache.org/)
* [Apache TomEE 8.0.9 Plus](https://tomee.apache.org/)
* [Apache Maven](https://maven.apache.org/)
* [Node.js](https://nodejs.org/en/)
* [Docker](https://www.docker.com/)
* [SoapUI](https://www.soapui.org/)
