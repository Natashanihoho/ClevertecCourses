#Introduction
Software represents an application, that implements the functionality of generating a receipt in a store.

#Non-functional requirements
<ul>
<li> JDK 16+;
<li> API: HTTP, request and response message - TXT format;
</ul>

#Functional requirements
The application must receive order from the user and generate the cash receipt with discounts, if they are available.
It must be run from the command line.

The application has several ways of execution:
<ul>
<li> Console

    Run example: java ConsoleRunner 3-1 2-5 5-1 card5

Description: java ConsoleRunner {id-number} {id-number} ... {id-number} card№</br>
id - id of products in range 1-20,</br>
number - number of products,</br>
{id-number} {id-number} ... {id-number} - count of products are able to be different,</br>
card№ - card numbers in range 1-9, and card may be missing.</br>
<br>Cash receipt will be printed to the console.</br>

<br>The range of products is set in the code by a collection of objects.

<br>The order is taken from the command line.

<li> File System

    Run example: java FileRunner
Cash receipt will be printed to the file "receipt.txt" into this project.

<br>The range of products is taken from the file "stockProducts.txt" into this project. 

<br>The order is taken from the file "args.txt" into this project, it can be modified.
<li> Web Interface

    Run example: java WebRunner 
Cash receipt will be printed to the http://localhost:8081/check?id=5&value=3&card4

Description: http://localhost:8081/check?{id=number}&{value=number}& ... &card№</br>
id - id of products in range 1-20,</br>
value - number of products,</br>
{id=number}&{value=number}& ... &{id=number}&{value=number} - count of products are able to be different,</br>
card№ - card numbers in range 1-9, and card may be missing.</br>
<br>The range of products is set in the code by a collection of objects.

<br>The order is taken from the http request.
</ul>





