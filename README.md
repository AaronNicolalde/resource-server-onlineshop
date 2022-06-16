# resource-server-onlineshop
Resource server source code that relays on oauth2 server deployed with docker containers. 

**** Part 1 ****

- Authentication & Authorization\
    •	An Authentication & Authorization Server, let’s use Keycloak (or any other component that you feel comfortable using), you could use Docker to make it simple to setup and use. \
    •	A Resource Server (our API), that relays on the Authorization Server to restrict access to our endpoint.\
    Access to Actuator configured with the endpoints below and access levels:\
          o	/health: any user can access this endpoint.\
          o	/metrics: only authenticated users can access this endpoint\
          o	/loggers: only authenticated users can access this endpoint\
          o	Any other endpoint from actuator has to be disabled.

**** Part 2 **** 

- Checkout \
Now that we have everything secured, let’s start with the main goal of our API, which is Managing Checkout, Order and Delivery resources.\
Checkout is the process where a customer is setting up what he/she wants to buy so we will need to be able to handle different operations.\
What is required :\
    •	Be able to start a checkout with at least the customer identification and one product with its quantity.\
    •	Add a product to the checkout with a specific quantity.\
    •	Modify the quantity of a product already added to the checkout.\
    •	Remove a product from the checkout, if the checkout reaches the point that has no more products it should also delete the checkout.\
    •	Specify one of the user’s addresses where the products should be delivered once the checkout becomes an order.\
    •	Change the address that is related to the checkout.\
    •	Set one of the user’s payment methods to the checkout.\
    •	Change the payment method that is related to checkout.\
    •	Get the checkout information, products, shipping address, customer basic information.\
    •	The user must at least be authenticated to access any of the endpoints exposed by this functionality. 

**** Part 3 **** 

- Order & Delivery\
Once the user is ready to make the purchase, we should be able to create an Order into our system with the checkout information (customer, products), the Delivery with the address selected and register the payment with the selected information and the total of the purchase.
