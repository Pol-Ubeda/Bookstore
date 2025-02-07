import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class BookDatabaseReader {
    private static ArrayList<BookClass> allBooks = new ArrayList<>();
    public static void main(String[] args) {
        // Path to your CSV file.
        String filePath = "C:\\Users\\polub\\Desktop\\Java\\Bookstore\\Book Database.csv";
        
        
        try {
            // Create a Scanner object to read the file.
            Scanner sc = new Scanner(new File(filePath));
            sc.useDelimiter(";");  // Set the delimiter to comma.
            
            while (sc.hasNextLine()) {  // Check if there's another line in the file.
                String line = sc.nextLine();  // Read the entire line.
                String[] bookData = line.split(";");    //Splits each line of CSV using delimiter ;.
                
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


        System.out.println(Arrays.toString(allBooks.get(5).getGenres()));  //Example of how to print out all genres.
    }


    //Create method to be able to have access to books in the other java file.
    public static ArrayList<BookClass> getAllBooks(){
        return allBooks;
    }

}