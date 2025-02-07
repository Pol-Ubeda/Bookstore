/*
This is the main file of the project. In it we can find all of the methods needed to navigate the bookstore 
such as adding/removing books, browsing them by title/author/genres, go to checkout, and display navigation 
menu of the bookstore.
*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

public class Bookstore{
    //Global scanner for efficiency and avoiding errors
    static Scanner scanner = new Scanner(System.in);
    //allBooks is where we will store all book data using BookDatabaseReader.java file
    static ArrayList<BookClass> allBooks = new ArrayList<>();
    //shoppingCart serves as the shopping cart the user handles
    static ArrayList<String> shoppingCart = new ArrayList<>();

    public static void main(String args[]){
        BookDatabaseReader.main(args);  //We first read book data and store it in allBooks.
        allBooks = BookDatabaseReader.getAllBooks();

        int userSelection = showOptionsMenu();  //We then show the options menu to the user and he chooses one of the options using an integer
        optionsSwitch(userSelection);   //Use the switch method to run method respective to user selection

        return;
    }


    //This method is just to show the option menu when required.
    static int showOptionsMenu(){
        int count=0;
        Set<Integer> validValues = new HashSet<>(Set.of(0,1,2,3,4,5));
        
        //Listing out options. Only show checkout option if shopping cart not empty.
        System.out.println("Hi! What would you like to do? Please type one of the following numbers to proceed.");
        System.out.println("0. Exit\n1. Browse book by genre.\n2. Browse by Title.\n3. Browse by author.\n4. Add book to shopping cart.\n5. Remove book from shopping cart.");
        if(shoppingCart.size()!=0){
            System.out.println("6. Proceed to checkout.");
            validValues.add(6);
        }

        //Handling error of user typing invalid values.
        int userSelection = scanner.nextInt();
        while(!validValues.contains(userSelection)){
            System.out.println("Try again. Remember to only type one of the numbers shown!");
            userSelection = scanner.nextInt();
            scanner.nextLine();
            count++;
            if(count==5){
                System.out.println("Enough. Goodbye.");
                scanner.close();
                return 0;
            }
        }

        return userSelection;
    }

    //This method is to enter the switch cases depending on user input.
    static void optionsSwitch(int userInput){
        switch(userInput){
            case 0:
                System.exit(0);
            
            case 1:
                System.out.println("What genre are you interested in?");
                scanner.nextLine();
                String genre = scanner.nextLine();                
                browseByGenre(genre);

            case 2:
                System.out.println("What book are you looking for? Make sure to type the complete name of the book, with capital letters.");
                scanner.nextLine();
                String title = scanner.nextLine();
                browseByTitle(title);
            
            case 3:
                System.out.println("What author are you looking for? Make sure to type the complete name of the author, with capital letters.");
                scanner.nextLine();
                String author = scanner.nextLine();
                browseByTitle(author);
            
            case 4:
                System.out.println("To add a book to your shopping cart please type its book ID code.");
                scanner.nextLine();
                String bookToAdd = scanner.nextLine();
                addItemToCart(shoppingCart, bookToAdd);
            
            case 5:
                System.out.println("To remove a book from your shopping cart please type its book ID code.");
                scanner.nextLine();
                String bookToRemove = scanner.nextLine();
                removeItem(shoppingCart, bookToRemove);

            case 6:
                int n = checkout(shoppingCart);
                if(n==1){
                    System.out.println("Thank you, goodbye!");
                    System.exit(0);
                }else{
                    int userSelection = showOptionsMenu();
                    optionsSwitch(userSelection);
                }
        }
        return;
    }

    //This method is to browse by genre.
    static void browseByGenre(String genre){
        int count=0;
        for(BookClass book : allBooks){
            for(String s : book.getGenres()){
                if(s.equals(genre)){
                    System.out.println(book.getTitle() + ", " + book.getAuthor() + ", " + Arrays.toString(book.getGenres()) + ", " + book.getBookID());
                    count++;
                    break;
                }
            }
        }
        
        if(count==0){System.out.println("Sorry, we don't have books characterised by that genre.");}

        System.out.println("\nWhen you are ready to return to the menu please click the enter key.");
        scanner.nextLine();
        int userSelection = showOptionsMenu();
        optionsSwitch(userSelection);
        return;
    }

    //Browse by title method.
    static void browseByTitle(String title){
        int count=0;
        for(BookClass book : allBooks){
            if(title.equals(book.getTitle())){
                System.out.println(book.getTitle() + ", " + book.getAuthor() + ", " + Arrays.toString(book.getGenres()) + ", " + book.getBookID());
                count++;
                break;
            }
        }

        if(count==0){System.out.println("Sorry, we don't have that book. Make sure you have written the title correctly, with capital letters and punctuation where necessary.");}
        
        System.out.println("\nWhen you are ready to return to the menu please click the enter key.");
        scanner.nextLine();
        int userSelection = showOptionsMenu();
        optionsSwitch(userSelection);
        return;

    }

    //Browse by author method.
    static void browseByAuthor(String author){
        int count=0;
        for(BookClass book : allBooks){
            if(author.equals(book.getAuthor())){
                System.out.println(book.getTitle() + ", " + book.getAuthor() + ", " + Arrays.toString(book.getGenres()) + ", " + book.getBookID());
                count++;
                break;
            }
        }

        if(count==0){System.out.println("Sorry, we don't have books by that author. Make sure you have written their name correctly, with capital letters and punctuation where necessary.");}
        
        System.out.println("\nWhen you are ready to return to the menu please click the enter key.");
        scanner.nextLine();
        int userSelection = showOptionsMenu();
        optionsSwitch(userSelection);
        return;
    }

    //The following method is to add items to the shopping cart.
    static void addItemToCart(ArrayList<String> cart, String bookID){
        if(!cart.contains(bookID)){
            cart.add(bookID);
            System.out.println("\nThe book has been added to your cart!\n");
        }
        else{System.out.println("\nThat item is already in your cart.\n");}
        
        
        int userSelection = showOptionsMenu();
        optionsSwitch(userSelection);
    }

    //Now to remove items from the cart.
    static void removeItem(ArrayList<String> cart, String bookID){
        if(cart.contains(bookID)){
            cart.remove(bookID);
            System.out.println("\nThe book has been removed from your cart!\n");
        }
        else{System.out.println("\nThat item is not in your cart.\n");}
        
        
        int userSelection = showOptionsMenu();
        optionsSwitch(userSelection);
        return;

    }

    //This one is to checkout.
    static int checkout(ArrayList<String> cart){
        int count=0;
        System.out.println("\nThis is your current shopping cart:\n");
        
        for(BookClass book : allBooks){
            if(shoppingCart.contains(book.getBookID())){
                System.out.println(book.getTitle());
                count++;
                break;
            }
        }

        System.out.println("\nDo you wish to pay and exit? Please type Yes or No.\n");
        
        scanner.nextLine();
        String userResponse = scanner.nextLine();
        while(!userResponse.equals("Yes") && !userResponse.equals("No")){
            System.out.println("\nPlease type Yes or No.\n");
            userResponse=scanner.nextLine();
            count++;
            if(count==5){
                System.out.println("\nReally? I'm out.");
                scanner.close();
                System.exit(0);
            }
        }

        if(userResponse.equals("Yes")){return 1;}
        else{return 0;}
    }
}