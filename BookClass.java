/*
This file contains the Book class. Its attributes are the title, author and genres of the book, and a bookID
that will serve as an identifier for the bookstore, specifically for the shopping cart. Each attribute has a get and set method.
*/
public class BookClass{
    private String title;
    private String author;
    private String[] genres;
    private String bookID;

    //Now we set getters and setters. We could avoid this by not making attributes private, but this mantains data integrity (and is more fun).
    public String getTitle(){ return title;}
    public String getAuthor(){ return author;}
    public String[] getGenres(){ return genres;} 
    public String getBookID(){ return bookID;}

    public void setTitle(String newTitle){ this.title=newTitle;}
    public void setAuthor(String newAuthor){ this.author=newAuthor;}
    public void setGenres(String[] newGenres){this.genres=newGenres;}
    public void setBookID(String newBookID){ this.bookID=newBookID;}
}