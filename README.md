# Restaurant DDD

This is an academic project to apply DDD (Domain Drive Design) only with business and domain layers.

In this project we focus on the management of orders of a restaurant coneccting the client side at the tables with the kitchen, 
When the client add their info, add items to the order from a menu.


# Points to have a better understanding of the project

Below you can find the process that was carried out starting from the conception of the idea to the execution.

## Big picture and domain

![image](https://user-images.githubusercontent.com/98781195/224407998-3949f37e-7872-4ef5-b866-6b8552e6a2f7.png)

Image above describes in general terms how the business is distributed.
I propose to the kitchen be the core subdomain since the unique creations from there are the main characterstic of the restaurant.
An inventory support subdomain because the kitchen needs that supplies to build the creations.
Finally a Client, Delivery, Events and Marketing as generic subdomains.

Different bounded context were proposed, but in this case we will focus on **Order Manager App** 

## Bounded context, ubiquitous language and domain model

In the image below you can find the solution that I developed to connect the Client with the Kitchen. I have 3 aggregates roots (Client, Menu and Order).

![image](https://user-images.githubusercontent.com/98781195/224409155-829e774a-455b-425d-8359-522a01118a31.png)

At these image is showed the ubiquitous used at the bounded context for the behaviours the solution will has.

![image](https://user-images.githubusercontent.com/98781195/224410142-bc028db5-f4ff-4c06-ab11-aa0f199b052d.png)

Next image shows domain model, how aggregates, entities and value objects interact and their respective behaviors

### Client Aggregate Root

![image](https://user-images.githubusercontent.com/98781195/224410737-757de43e-c091-4043-adab-e9843e1fe3b6.png)

### Order Aggregate Root

![image](https://user-images.githubusercontent.com/98781195/224410952-576b6495-beba-48b0-bc3c-9895a9704c01.png)

### Menu Aggregate Root

![image](https://user-images.githubusercontent.com/98781195/224411109-1fb73ca8-896d-49a0-bca1-206db955cb26.png)



## Command driven use cases

* Client aggregate
	- **Create Client Use Case**:  Create a client with date information .
	- **Add Review Use Case**: Add a Review with score, descriptio and date info.
	- **Add Account Use Case**: Add an account to the client with email, password and username.
* Menu aggregate:
	- **Add Item  Use Case**: Add an item entity to the set list of items
	- **Add Promo Use Case**: Add a new promo to the menu
	- **Create Menu Use Case**: Create a new Menu
  - **Removed Item Use Case**: Remove the Item from the list only with the id
  - **Remove Promo Use Case**: Update the promo on the menu to empty values
* Order Order aggregate:
	- **Add Item To Order Use Case**: Add a new Item to the set list of Order items.
	- **Add Waiter To Order Use Case**: Assign a Waiter to the order.
	- **Change State Use Case**: Change the state value, wich represents the state of the order inside the kitchen.
  - **Create Order Use Case**: Create a new Order it needs the date, menuId such as clientId to perform the association event use case
  - **Remove Item From Order Use Case**: Remove the Order item from the list only with the id.

## Event driven use cases

* Menu aggregate:
	- **Apply Default Prices Event Use Case**: When the Remove Promo Use Case is performed the behaviour resulted, apart of removing the promo, is this event which will set the prices on the item list as the original price, it means without the discount.
	- **Promo Applied Event Use Case**: When the Add Promo Use Case is perfomed the behaviour resulted, apart of adding the promo, is update the prices at the list with the discount.

* Order aggregate:
	- **Associate Client To Order Event Use Case**: When the Create Order Use Case is performed the behaviour resulted, apart of create the order, is to set the value Object ClientId with the value sended through the command to this event use case.
	- **Calculate Total Event Use Case**: When the Add and remove Item to the list on Order Use Cases this use case will calculate the total of the order based on the prices of each Order item.

## Coverage
![image](https://user-images.githubusercontent.com/98781195/224422059-74af0d13-b3ff-4c6a-91ef-713c4729b9eb.png)

