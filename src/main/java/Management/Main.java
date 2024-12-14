package Management;

import java.util.Scanner;

public class Main {	 
	    public static void main(String[] args) throws ClassNotFoundException {
	        Scanner scanner = new Scanner(System.in);
	        BookManger bookManager = new BookManger();
	        MemberManager memberManager = new MemberManager();
	        LoanManager loanManager = new LoanManager();

	        while (true) {
	            System.out.println("\nLibrary Management System");
	            System.out.println("1. Add Book");
	            System.out.println("2. List Books");
	            System.out.println("3. Add Member");
	            System.out.println("4. List Members");
	            System.out.println("5. Borrow Book");
	            System.out.println("6. Return Book");
	            System.out.println("7. Exit");
	            System.out.print("Choose an option: ");
	            int choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                	 System.out.println("Enter id ");
		                    int id=scanner.nextInt();
	                    System.out.print("Enter book title: ");
	                    scanner.nextLine();  // Consume newline
	                    String title = scanner.nextLine();
	                    System.out.print("Enter book author: ");
	                    String author = scanner.nextLine();
	                    bookManager.addBook(id,title, author);
	                    break;
	                case 2:
	                    bookManager.listBooks();
	                    break;
	                case 3:
	                	System.out.println("Enter id");
	                	int id1=scanner.nextInt();
	                    System.out.print("Enter member name: ");
	                    scanner.nextLine();  // Consume newline
	                    String name = scanner.nextLine();
	                    memberManager.addMember(id1,name);
	                    break;
	                case 4:
	                    memberManager.listMembers();
	                    break;
	                case 5:
	                    System.out.print("Enter member ID: ");
	                    int memberId = scanner.nextInt();
	                    System.out.print("Enter book ID: ");
	                    int bookId = scanner.nextInt();
	                    loanManager.borrowBook(memberId, bookId);
	                    break;
	                case 6:
	                    System.out.print("Enter book ID to return: ");
	                    int returnBookId = scanner.nextInt();
	                    loanManager.returnBook(returnBookId);
	                    break;
	                case 7:
	                    System.out.println("Exiting...");
	                    scanner.close();
	                    return;
	                default:
	                    System.out.println("Invalid option. Try again.");
	            }
	        }
	    }
	}
