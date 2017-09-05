# e-commerce
Ecommerce website

There are 5 micro-services in total and 1 seperate module.
1. Product micro-service contains all product related API.
2. Merchant micro-service contains all merchant related API.
3. Cart and order micro-services contains all cart and order related API.


There is one microservice named as "shopping-cart" where all API are getting called by HTTP methods.


FLow of project-
->Product will be added by merchant only through shopping-cart microservice after verification of merchant. 
->Default product is configurable which depends on merchant rating, product price and product stock.
->Users will need to provide their email to add products in thier cart. Users can remove product and update product quantity in their cart.
->Users can place the order.
->Email will be sent to customer with their order details.
->Email will be sent to merchant with their profit details.
