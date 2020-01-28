# rubicon-coding-challenge


Rubicon Water Ordering Simulator

SETUP INSTRUCTIONS:

- Install JDK 1.8, maven, and setup environment variables on your machine.

- Download the source from GitHub into a folder on your machine.

        git clone https://github.com/benciojr/rubicon-coding-challenge.git

- In a command line / terminal, change the directory to the root folder of the application (rubicon-coding-challenge).

        cd rubicon-coding-challenge

- Run maven to compile the source

        mvn clean spring-boot:run

- After the successful build process, the web server should be up and running (http://localhost:8080/).

The API should be accessible via a ReST client such as postman.

For example:

- Viewing orders (GET):
        - http://localhost:8080/viewOrders?searchBy=all
        - http://localhost:8080/viewOrders?searchBy=orderId&searchValue=10
        - http://localhost:8080/viewOrders?searchBy=farmId&searchValue=20
        - http://localhost:8080/viewOrders?searchBy=orderStatus&searchValue=REQUESTED

- Placing an order (POST):
        - http://localhost:8080/placeOrder
Example request body:
	{
	    "orderId": 110,
	    "farmId": 200,
	    "duration": 60000
	}

- Cancelling orders (GET):
        - http://localhost:8080/cancelOrder?orderId=110

