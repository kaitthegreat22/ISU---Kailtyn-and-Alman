ReadMe File – Pear Marketplace (ISU) - *Also submitted as a google doc for better readability*

Service ArrayLists: Holds Service Information
ArrayList<String> services
Stores all services available to book. When a user inputs a service name, it is stored here (eg., “Dog Walking,” “Cleaning,” etc)
ArrayList<String> categories
Stores the category of each corresponding service (eg., “Pets”). This ArrayList is used to help filter services by category while booking. 
ArrayList<Double> rate
Stores the rate of service ($/hr).  Used in checkout to calculate total cost.
Cart Management ArrayLists: Holds Booking Information
 ArrayList<String> cart_service
Holds service name in cart. Used to accurately output a “View Cart” function and invoice that has the name of the service booked.
ArrayList<String> cart_cat
Holds service category in cart. Used to organize items in cart based on category.
ArrayList<String> cart_rate
Holds service rate in cart. Used to calculate total cost at checkout.
Q1: Why are Two Sets of Variables Required For Service and Cart? Why Can We Not Reuse The Same ArrayLists?
Preventing Double-Bookings: When a service is booked, it is removed from the original services ArrayList, and added to cart_services ArrayList. This ensures no double-bookings. However, if a user cancels, we can add the service back from cart_services to services, which you could not have done without two ArrayLists
Other Important Variables: Used to Filter, Select, and Loops for Successful Output
ArrayList<String> filtered_categories: Holds unique categories for display, removes any duplicate categories
 ArrayList<Integer> availableIndices: Stores the indices of service for a selected category. Required to track the positions of services in a selected category so they can be correctly displayed, booked, or removed without errors caused by shifting indices in the main ArrayLists.
total (double): Outputs the sum of all service rates in cart in checkout.
Loop Control Variables (repeat, keep_adding, keep_booking, remove) 
Purpose: Manage the flow of menu navigation and user prompts. 
repeat:  Keeps the main menu running until the user exits.
keep_adding: Controls the "Add a service" loop. 
keep_booking:  Manages the service booking process. 
remove: Handles item removal from the cart.
Part 1: Menu
Inside a while loop named (repeat) for the user to always come back to the menu before exiting, the user will input a number 1-5 based on what they want to access.
Part 2: Service
Inside a while loop named (keep_adding), the user inputs the service name, category, and rate. Held inside services, categories, and rate ArrayLists. Outputs their data for confirmation.
Using if-else statements, the program asks if the user wants to add another service, turning (keep_adding) false if they do not.
Part 3: Booking
Initialize boolean (keep_booking), used to not stay within booking menu, and not go back to main menu
Firstly removes duplicate categories via ArrayList<String> filtered_categories. If a category (s) exists, then it uses a loop to display all categories. If not, it outputs that there are no categories.
int catChoice allows the user to select their category to book a service. By accounting for index shifts, catChoice checks for which category the user wants to access. String chosenCat is then used to take the category that matches with the number chosen.
 ArrayList<Integer> availableIndices holds the indices of the services and rates with that correspond with the category, working with int serviceChoice to select a service by using if-else statements, adding them to cart ArrayLists using .add, and removing them from availability using .remove
Using a loop, the program prints out the service name, category and rate booked.
(keep_booking) is turned false, going back to the main menu.
Part 4: Viewing & Editing Cart
While (remove), a loop to keep removing services if the user wants,  int remove_choice is used to remove a service from the cart ArrayLists, adding it back to the service ArrayLists
A loop is used to output the outdated cart.
If the user did not want to remove any services from the cart, it takes them back to the menu, or if the cart is empty it tells that to the user as well.
Part 5: Checkout
Checks if cart is empty, if it is, it tells the user it is empty, however, if it is not, it does the following:
Using a for loop, the program outputs the cart; as well as the total hourly cost of the services ($/hr), using double total
Using 3 for loops, the cart is then cleared using .remove method 
Part 6: Exit
If the user chooses to exit, a thank you message is displayed, and turns (repeat) to false, to not display the menu again.
