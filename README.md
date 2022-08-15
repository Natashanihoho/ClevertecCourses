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
http://localhost:8080/command?type=receipt&value=2-4&value=8-1&value=card9
http://localhost:8080/command?type=receipt&value=12-7&value=15-2&value=21-5
```
</li> 

<li> Create product:

```http request
(POST) 
http://localhost:8080/command?type=product_create
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
http://localhost:8080/command?type=product_by_id&id=2
```
</li> 

<li> Get all products:

```http request
(GET)
http://localhost:8080/command?type=product_all
http://localhost:8080/command?type=product_all&page=3
```
</li> 

<li> Update product by id:

```http request
(PUT)
http://localhost:8080/command?type=product_update&id=1
{
        "id": 1,
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
http://localhost:8080/command?type=product_delete&id=11
```
</li> 


<li> Create discount card:

```http request
(POST) 
http://localhost:8080/command?type=discount_card_create
{
        "cardName": card88,
        "discountPercent": 6
}
```
</li> 

<li> Get discount card by name:

```http request
(GET)
http://localhost:8080/command?type=discount_card_by_name&name=card2
```
</li> 

<li> Update discount card by name:

```http request
(PUT)
http://localhost:8080/command?type=discount_card_update&name=card1
{
        "cardName": card1,
        "discountPercent": 5
}
```
</li> 

<li> Delete discount card by name:

```http request
(DELETE)
http://localhost:8080/command?type=discount_card_delete&name=card8
```
</li> 


</ul>






