
import java.util.Scanner;

class Book {
    public int id;
    public String name;
    public boolean available;

    public Book(int id, String name) {
        this.id = id;
        this.name = name;
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Student {
    public int id;
    public String name;
    public Book borrowedBook;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Book getBorrowedBook() {
        return borrowedBook;
    }

    public void setBorrowedBook(Book borrowedBook) {
        this.borrowedBook = borrowedBook;
    }
}

class Library {
    public Book[] books;
    public Student[] students;
    public Scanner sc;

    public Library() {
        books = new Book[] {
                new Book(1, "The Great Gatsby"),
                new Book(2, "To Kill a Mockingbird"),
                new Book(3, "One Hundred Years of Solitude")
        };

        students = new Student[] {
                new Student(1, "Pravir"),
                new Student(2, "Raghav"),
                new Student(3, "Seema")
        };

        sc = new Scanner(System.in);
    }

    public void borrowBook() {
        System.out.println("Enter student id: ");
        int studentId = sc.nextInt();
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Invalid student id");
            return;
        }

        System.out.println("Enter book id: ");
        int bookId = sc.nextInt();
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Invalid book id");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book is not available");
            return;
        }

        student.setBorrowedBook(book);
        book.setAvailable(false);
        System.out.println("Book borrowed successfully");
    }

    public void returnBook() {
        System.out.println("Enter student id: ");
        int studentId = sc.nextInt();
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Invalid student id");
            return;
        }

        Book book = student.getBorrowedBook();
        if(book == null) {
            System.out.println("Student has not borrowed any book");
            return;
        }

        student.setBorrowedBook(null);
        book.setAvailable(true);
        System.out.println("Book returned successfully");
    }

    private Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }

        return null;
    }

    private Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    public void showBooks() {
        System.out.println("ID\tName\tAvailability");
        for (Book book : books) {
            System.out.println(book.getId() + "\t" + book.getName() + "\t" + (book.isAvailable() ? "Available" : "Not available"));
        }
    }

    public void showStudents() {
        System.out.println("ID\tName\tBorrowed Book");
        for (Student student : students) {
            Book book = student.getBorrowedBook();
            System.out.println(student.getId() + "\t" + student.getName() + "\t" + (book == null ? "Not borrowed" : book.getName()));
        }
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter user type (admin/student): ");
        String userType = sc.nextLine();

        if (userType.equals("admin")) {
            while (true) {
                System.out.println("1. Show books");
                System.out.println("2. Show students");
                System.out.println("3. Exit");
                System.out.println("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    library.showBooks();
                } else if (choice == 2) {
                    library.showStudents();
                } else if (choice == 3) {
                    break;
                } else {
                    System.out.println("Invalid choice");
                }
            }
        } else if (userType.equals("student")) {
            while (true) {
                System.out.println("1. Borrow book");
                System.out.println("2. Return book");
                System.out.println("3. Exit");
                System.out.println("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    library.borrowBook();
                } else if (choice == 2) {
                    library.returnBook();
                } else if (choice == 3) {
                    break;
                }
            }
        }
    }
}