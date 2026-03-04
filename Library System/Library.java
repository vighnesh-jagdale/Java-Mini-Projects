import java.util.*;

class Book{
    private final int id;
    private String title;
    private String author;
    private boolean isAvailable;

    private static int counter = 1;
    Book(String title, String author){
        this.id = counter++;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }
    //setters
    public void setAvailable(boolean status){
        this.isAvailable = status;
    }

    //getters
    public int getBookId(){
        return id;
    } 
    public String getBookTitle(){
        return title;
    } 
    public String getBookAuthor(){
        return author;
    } 
    public boolean isAvailable(){
        return isAvailable;
    }

    @Override
    public String toString(){
        return "Book Id: " + id +
       ", Title: " + title +
       ", Author: " + author +
       ", Available: " + isAvailable;
    }
}

class LibraryRoom{
    List<Book> bookList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public void addBook(){
        int id;
        String title;
        String author;

        System.out.println("Enter Book Title: ");
        title = sc.nextLine();

        System.out.println("Enter Book Author: ");
        author = sc.nextLine();

        bookList.add(new Book(title, author));
        System.out.println("Book Added Successfully!");
    }

    public void showBooks(){
        if(bookList.isEmpty()){
            System.out.println("No books in library.");
            return;
        }
        for(int i = 0; i < bookList.size(); i++){
            System.out.println(bookList.get(i));
        }
    }

    private Book findBookById(int id){
        for(Book b : bookList){
            if(b.getBookId() == id){
                return b;
            }
        }
        return null;
    }

    public void issueBook(int id){
        if(id < 0){
            System.out.println("Enter Valid Id!");
            return;
        }

        Book book = findBookById(id);

        if(book == null){
            System.out.println("Book not found");
            return;
        }

        if(!book.isAvailable()){
            System.out.println("Book already issued");
            return;
        }

        book.setAvailable(false);
        System.out.println("Book issued successfully!");
    }

    public void returnBook(int id){
        if(id < 0){
            System.out.println("Enter valid id");
            return;
        }

        Book book = findBookById(id);

        if(book == null){
            System.out.println("Book not found");
            return;
        }

        if(book.isAvailable()){
            System.out.println("Can't return the book the book is already in library!");
            return;
        }

        book.setAvailable(true);
        System.out.println("Book returned successfully!");
    }


    public void showAvailableBooks(){
        for(Book b : bookList){
            if(b.isAvailable()){
                System.out.println(b);
            }
        }

    }

    public void showIssuedBooks(){
        for(Book b : bookList){
            if(!b.isAvailable()){
                System.out.println(b);
            }
        }
    }
}

public class Library{
    public static void main(String[] args){
        LibraryRoom lib = new LibraryRoom();
        Scanner sc = new Scanner(System.in);

        int choice;

        while(true) {

            System.out.println("\n===== LIBRARY MENU =====");
            System.out.println("1. Add Book");
            System.out.println("2. Show Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch(choice) {

                case 1:
                    lib.addBook();
                    break;

                case 2:
                    System.out.println("1. Show all Available Books");
                    System.out.println("2. Show Available Books");
                    System.out.println("3. Show Issued Books");
                    int options = sc.nextInt();
                    sc.nextLine();
                    switch(options){
                        case 1:
                            lib.showBooks();
                            break;
                        case 2:
                            lib.showAvailableBooks();
                            break;
                        case 3:
                            lib.showIssuedBooks();
                            break;
                        default:
                            System.out.println("Enter valid choice");
                            break;
                    }
                    break;

                case 3:
                    System.out.print("Enter Book ID to issue: ");
                    int issueId = sc.nextInt();
                    lib.issueBook(issueId);
                    break;

                case 4:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();
                    lib.returnBook(returnId);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}