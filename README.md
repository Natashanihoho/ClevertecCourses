#Introduction
Software represents an application, that implements the functionality of generating a receipt in a store.

#Non-functional requirements
<ul>
<li> JDK 16+;
<li> API: HTTP(request - JSON, URL and response message - JSON, PDF);
</ul>

#Functional requirements
The application must receive order from the user and generate the cash receipt with discounts, if they are available.
Also the application must receive CRUD requests.

*Examples of URL request:*
<ul>

<li> Get receipt:   

```http request
(GET)
http://localhost:8080/receipt/value=1-1&2-1
http://localhost:8080/receipt/value=1-1&2-1&card1
```
</li> 

<li> Create product:

```http request
(POST)
http://localhost:8080/product/create
{
        "description": "TestProduct",
        "price": 7.77,
        "availableNumber": 10,
        "isSpecialOffer": true
}
```
</li> 

<li> Get product by id:

```http request
(GET)
http://localhost:8080/product/find/1
```
</li> 

<li> Get all products:

```http request
(GET)
http://localhost:8080/product/find_all
http://localhost:8080/product/find_all/1
```
</li> 

<li> Update product by id:

```http request
(PUT)
http://localhost:8080/product/update/1
{
        "description": "UpdatedDescription",
        "price": 1.00,
        "availableNumber": 10,
        "isSpecialOffer": true
}
```
</li> 

<li> Delete product by id:

```http request
(DELETE)
http://localhost:8080/product/delete/3
```
</li> 


<li> Create discount card:

```http request
(POST)
http://localhost:8080/card/create
{
        "cardName": "card88",
        "discountPercent": 6
}
```
</li> 

<li> Get discount card by id:

```http request
(GET)
http://localhost:8080/card/find/1
```
</li> 

<li> Update discount card by id:

```http request
(PUT)
http://localhost:8080/card/update/1
{
        "cardName": "card1",
        "discountPercent": 5
}
```
</li> 

<li> Delete discount card by id:

```http request
(DELETE)
http://localhost:8080/card/delete/1
```
</li> 


</ul>






