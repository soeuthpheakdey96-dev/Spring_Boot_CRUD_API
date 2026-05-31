# Spring_Boot_CRUD_API
A simple RESTful API built with Spring Boot application that performs Create, Read, Update, and Delete (CRUD) operation on a "Product" table in MySQL database using JDBC Driver.
## API Endpoint
1. `GET http://localhost:8000/api/product`  

Retrieve all product data from database   

Respond:
```
[
    {
        "id": 1,
        "name": "Almond",
        "price": 24000
    },
    {
        "id": 2,
        "name": "Chocolate",
        "price": 8000
    }
]
```
2. `GET http://localhost:8000/api/product/2`

Retrieve a single product data identified by id 2

Respond:
```
[
    {
        "id": 2,
        "name": "Chocolate",
        "price": 8000
    }
]
```
3. `POST http://localhost:8000/api/product`

Create a new product and store it on database

Request:
```
{
    "name":"Blueberry",
    "price":6000
}
```
Respond:
```
{
    "message": "Product Created Sucessfully"
}
```
4. `PUT http://localhost:8000/api/product/2`

Make a update to specified product that identified by id 2

Request:
```
{
    "name":"Gummy Bear",
    "price":11000
}
```

Respond:
```
{
    "message": "Product Updated Sucessfully"
}
```
5. `DELETE http://localhost:8000/api/product/2`

Delete a certain product on database identified by id 2

Respond:
```
{
    "message": "Product Deleted Sucessfully"
}
```
## DOCKER USAGE
1. Pull image
```
docker pull wisnup001binus/spring-boot-crudapi:1.0
```
2. Run following command, change the environment variable `PORT`,`URL`,`USERNAME`,`PASSWORD`
```
docker run -d \
    --name spring-boot-api \
    --network spring_network \
    -p 8080:3000 \
    -e PORT:{port} \
    -e URL={url} \
    -e USERNAME={username} \
    -e PASSWORD={password} \
    wisnup001binus/spring-boot-crudapi:1.0
```
### Quick Setup
1. Run following script
```
docker network create spring_network

docker run -d \
    --name mysql \
    --network spring_network \
    -p 7000:3306 \
    wisnup001binus/mysql_product_table:1.0
    
docker network inspect spring_network
```
2. copy the ` "IPv4Address"` of `mysql`'s container, below is the example output  
  
![img](https://drive.google.com/uc?export=view&id=16fi-0ccYZrKtXTZHLrF9zAXr6awsFpaD) 
3. Paste it on `URL="jdbc:mysql://172.21.0.2:3306/my_db"` and run the script
```
docker run -d \
    --name spring-boot-api \
    --network spring_network \
    -p 8080:3000 \
    -e PORT=3000 \
    -e URL="jdbc:mysql://172.21.0.2:3306/my_db" \
    -e USERNAME="root" \
    -e PASSWORD="root" \
    wisnup001binus/spring-boot-crudapi:1.0
```
4. Open browser and paste following URL
```
http://localhost:8080/api/product
```
Note: Please wait around 2 - 3 minutes and refresh the page to see the product data  
  
5. Clean Up
```
docker stop spring-boot-api mysql
docker remove spring-boot-api mysql
docker network remove spring_network
```