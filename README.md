# basket-service
Simple basket micro-service

## Running the project
For running the project, you need to install docker and docker-compose.
Then simply run the following script:
```
./buildAndRun.sh
```
This will first run the build process, which includes building a docker image,
and then boot up both a MariaDB database for persistance, and the application itself.


## Usage
Currently there is no way to add a new basket, except in the import SQL script.
That being said, the application boots up with 10 baskets, and for those you can do the following:

### Add to basket
```
curl -X POST "http://localhost:8080/basket/{basketId}?productId={productId}&quantity={quantity}"
```
This will add {quantity} number of the product with product id {productId} into the basket with id {basketId}.


### Remove from basket
```
curl -X DELETE "http://localhost:8080/basket/{basketId}?productId={productId}&quantity={quantity}"
```
This will remove {quantity} number of the product with product id {productId} from the basket with id {basketId}.


### List basket
```
curl -X GET "http://localhost:8080/basket/{basketId}"
```
This will list all the quantities of all the products within the basket with id {basketId}.

### Change quantity
You can use either the add or remove methods to change the quantity of a product within the basket.
