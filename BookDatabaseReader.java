/*
This file is used to read data of books from the CSV file. It uses Scanner to read this data
and the set and get methods from the class BookClass.
*/

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class BookDatabaseReader {
    private static ArrayList<BookClass> allBooks = new ArrayList<>();   //This ArrayList of BookClass is where we store all the books from the CSV file
    public static void main(String[] args) {
        // Path to the CSV file.
        String filePath = "C:\\Users\\polub\\Desktop\\Java\\Bookstore\\Book Database.csv";
        
        //Use try-catch to handle file errors.
        try {
            // Create a Scanner object to read the file.
            Scanner sc = new Scanner(new File(filePath));
            sc.useDelimiter(";");  // Set the delimiter to comma.
            
            while (sc.hasNextLine()) {  // Check if there's another line in the file.
                String line = sc.nextLine();  // Read the entire line.
                String[] bookData = line.split(";");    //Splits each line of CSV using delimiter ; and stores in bookData.
                
                //Setting book data into our Book class, then we add it to books array.
                BookClass auxBook = new BookClass();
                auxBook.setTitle(bookData[0]);
                auxBook.setAuthor(bookData[1]);
                auxBook.setGenres(bookData[2].split(", "));
                auxBook.setBookID((String) bookData[3]);
                allBooks.add(auxBook);

            }


            sc.close();  // Close the scanner.

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }


    //Create method to be able to have access to books in the other java file.
    public static ArrayList<BookClass> getAllBooks(){
        return allBooks;
    }

}