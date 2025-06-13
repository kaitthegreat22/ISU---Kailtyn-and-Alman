import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //arraylists to hold added services, categories, and rates
        ArrayList<String> services = new ArrayList<>();
        ArrayList<String> categories = new ArrayList<>();
        ArrayList<Double> rate = new ArrayList<>();

        // Cart ArrayLists
        ArrayList<String> cart_service = new ArrayList<>();
        ArrayList<String> cart_cat = new ArrayList<>();
        ArrayList<Double> cart_rate = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Pear Marketplace!");
        boolean repeat = true;
        //repeating the code until user wants to exit
        while (repeat) {
            System.out.println("===== Main Menu!! =====");
            System.out.println("Please choose an option number: ");
            System.out.println("1. Add a service");
            System.out.println("2. Book a service");
            System.out.println("3. View cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            int option = sc.nextInt();
            sc.nextLine();
            if (option == 1) {
                System.out.println("---- Adding a service ----"); //adding services
                boolean keep_adding = true;
                while (keep_adding){
                    System.out.println("Please enter the following: ");
                    System.out.print("Service name: ");
                    services.add(sc.nextLine());
                    System.out.print("Category: ");
                    categories.add(sc.nextLine());
                    System.out.print("Hourly rate: ");
                    rate.add(sc.nextDouble());
                    sc.nextLine();
                    //displaying the service added
                    System.out.println("New service added!!!");
                    System.out.println("Service name: "+ services.get(services.size()-1));
                    System.out.println("Category: " + categories.get(categories.size() - 1));
                    System.out.println("Rate: "+ rate.get(rate.size()-1) + "/hr");

                    //using an if statement to ask user if they want to add another service
                    System.out.println("Would you like to add another service? Type 1 for yes and 2 for no.");
                    int add = sc.nextInt();
                    sc.nextLine();
                    if (add == 1){
                    } else if (add == 2) {
                        keep_adding = false;
                    } else {
                        System.out.println("Invalid answer! Going back to main menu!");
                        keep_adding = false;
                    }
                    //stop adding services
                }
            } else if (option == 2) {
                System.out.println("---- Booking Services ----"); //booking services
                boolean keep_booking = true;
                while (keep_booking) {
                    // Get unique categories
                    ArrayList<String> filtered_categories = new ArrayList<>();
                    for (String category : categories) {
                        if (!filtered_categories.contains(category)) {
                            filtered_categories.add(category);
                        }
                    }
                    //going back to menu if there are no services
                    if (filtered_categories.size() == 0) {
                        System.out.println("Sorry, there are no services available. Please add services first.");
                        keep_booking = false;
                    } else {
                        // Display categories
                        System.out.println("\nAvailable Service Categories:");
                        for (int i = 0; i < filtered_categories.size(); i++) {
                            System.out.println((i + 1) + ". " + filtered_categories.get(i));
                        }

                        // Category selection
                        System.out.print("\nEnter category number (0 to cancel): ");
                        int catChoice = sc.nextInt() - 1; //matching choice with index
                        sc.nextLine();  // Consume newline to prevent skip

                        if (catChoice == -1) { //-1 since 0-1 = -1
                            System.out.println("Cancelled! ");
                            keep_booking = false;
                        } else if (catChoice < 0 || catChoice >= filtered_categories.size()) {
                            System.out.println("Invalid category selection!");
                        } else {
                            String chosenCat = filtered_categories.get(catChoice); //taking the category that matches with the number chosen
                            ArrayList<Integer> availableIndices = new ArrayList<>(); //holding the indices of the services and rates with that correspond with the category

                            // Find all indices for category
                            for (int j = 0; j < categories.size(); j++) {
                                if (categories.get(j).equals(chosenCat)) {
                                    availableIndices.add(j);
                                }
                            }

                            if (availableIndices.size() == 0) { //letting them restart if what they chose was empty
                                System.out.println("\nNo available services in " + chosenCat + " category!");
                                System.out.println("All services in this category are fully booked.");
                            } else {
                                // Display available services with rates
                                System.out.println("\nAvailable Services in " + chosenCat + ":");
                                for (int k = 0; k < availableIndices.size(); k++) {
                                    int idx = availableIndices.get(k);
                                    System.out.println((k + 1) + ". " + services.get(idx) +
                                            " - $" + rate.get(idx) + "/hr");
                                }


                                // Service selection
                                System.out.print("\nEnter service number to book (0 to restart booking): ");
                                int serviceChoice = sc.nextInt() - 1;
                                sc.nextLine();  // Consume newline


                                if (serviceChoice == -1) {
                                    // User cancelled, options repeat
                                } else if (serviceChoice < 0 || serviceChoice >= availableIndices.size()) {
                                    System.out.println("Invalid service selection!");
                                } else {
                                    int selectedIdx = availableIndices.get(serviceChoice);


                                    // Add to cart
                                    cart_service.add(services.get(selectedIdx));
                                    cart_cat.add(categories.get(selectedIdx));
                                    cart_rate.add(rate.get(selectedIdx));


                                    // Remove from availability
                                    services.remove(selectedIdx);
                                    categories.remove(selectedIdx);
                                    rate.remove(selectedIdx);

                                    //displaying service added
                                    System.out.println("\nService added to cart!");
                                    System.out.println("Current Cart:");
                                    for (int m = 0; m < cart_service.size(); m++) {
                                        System.out.println((m + 1) + ". " + cart_service.get(m) +
                                                " (" + cart_cat.get(m) + ") - $" +
                                                cart_rate.get(m) + "/hr");
                                    }

                                    //ending loop
                                    keep_booking = false;
                                }
                            }
                        }
                    }
                }
            } else if (option == 3) {
                // View cart
                System.out.println("---- Your Cart ----");
                if (cart_service.size() == 0) {
                    System.out.println("Your cart is empty!"); //going to main menu to let user add something
                } else {
                    //display cart
                    for (int i = 0; i < cart_service.size(); i++) {
                        System.out.println((i + 1) + ". " + cart_service.get(i) +
                                " (" + cart_cat.get(i) + ") - $" + cart_rate.get(i) + "/hr");
                    }

                    boolean remove = true;
                    while (remove) { //loop to keep removing if user wants
                        System.out.println("Would you like to remove any services? 1 for yes, 2 for no. ");
                        int remove_choice = sc.nextInt();
                        if (remove_choice == 1) {
                            System.out.println("Please choose which service to remove: ");
                            //displaying services again
                            for (int i = 0; i < cart_service.size(); i++) {
                                System.out.println((i + 1) + ". " + cart_service.get(i) +
                                        " (" + cart_cat.get(i) + ") - $" + cart_rate.get(i) + "/hr");
                            }
                            int removed = sc.nextInt();
                            sc.nextLine();
                            removed--; //matching choice with arraylist index
                            if (removed > cart_service.size()|| removed < 0){
                                System.out.println("Invalid answer!");
                            } else {
                                //adding back the removed items
                                categories.add(cart_cat.get(removed));
                                services.add(cart_service.get(removed));
                                rate.add(cart_rate.get(removed));

                                //removing items from cart
                                cart_cat.remove(removed);
                                cart_service.remove(removed);
                                cart_rate.remove(removed);

                                //displaying updated cart
                                System.out.println("Updated cart!");
                                for (int i = 0; i < cart_service.size(); i++) {
                                    System.out.println((i + 1) + ". " + cart_service.get(i) +
                                            " (" + cart_cat.get(i) + ") - $" + cart_rate.get(i) + "/hr");
                                }
                            }
                        } else if (remove_choice == 2) {
                            System.out.println("Going back to menu!");
                            remove = false;
                        } else {
                            System.out.println("Invalid option!");
                        }
                    }
                }

            } else if (option == 4) {
                // Checkout
                System.out.println("---- Checkout ----"); 
                if (cart_service.size() == 0) {
                    System.out.println("Your cart is empty! Nothing to checkout.");
                } else {
                    //getting total
                    double total = 0;
                    System.out.println("Your order:");
                    for (int i = 0; i < cart_service.size(); i++) {
                        System.out.println((i + 1) + ". " + cart_service.get(i) +
                                " (" + cart_cat.get(i) + ") - $" + cart_rate.get(i) + "/hr");
                        total += cart_rate.get(i);
                    }
                    System.out.println("Total: $" + total + "/hr");
                    System.out.println("Thank you for your purchase!");


                    // Clearing the cart after checking out
                    for (int i = cart_service.size() - 1; i >= 0; i--){
                        cart_service.remove(i);
                    }
                    for (int i = cart_cat.size() - 1; i >= 0; i--){
                        cart_cat.remove(i);
                    }
                    for (int i = cart_rate.size() - 1; i >= 0; i--){
                        cart_rate.remove(i);
                    }
                }
            } else if (option == 5) {
                System.out.println("Thanks for visiting!");
                repeat = false; //exiting code
            } else {
                System.out.println("Invalid option!");
            }
        }
        sc.close();
    }
}

