import java.util.*;
interface configure
{
    public double calculateCost();
    public void display();
}
public class Main {
    public static void main(String[] args)
    {
        Library library = new Library();
        library.loadDataFromFile("C:\\Users\\HP\\IdeaProjects\\assignment1part2\\src\\types.txt");
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Add a item");
            System.out.println("2. Edit a item");
            System.out.println("3. Delete a item");
            System.out.println("4. View All items");
            System.out.println("5. View item by ID");
            System.out.println("6. Hot Picks!");
            System.out.println("7. Borrow Item");
            System.out.println("8. View Borrower List");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter what type of item do you want to add\nPress 1 for Book\nPress 2 for Magazine\nPress 3 for Newspaper");
                    int choice1=scanner.nextInt();
                    scanner.nextLine();
                    switch (choice1) {
                        case 1:{
                            System.out.print("Enter Title: ");
                            String title = scanner.nextLine();
                            System.out.print("Enter Author: ");
                            String author = scanner.nextLine();
                            System.out.print("Enter Year: ");
                            int year = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter Cost: ");
                            double cost = scanner.nextDouble();
                            scanner.nextLine();
                            item newitem = new book(title, author, year,cost);
                            library.additem(newitem);
                            System.out.println("Book added.");
                            break;}
                        case 2:{
                            System.out.println("Enter Title: ");
                            String title1 = scanner.nextLine();
                            System.out.println("Enter Number of Authors: ");
                            int number= scanner.nextInt();
                            scanner.nextLine();
                            ArrayList<String> authors=new ArrayList<>();
                            for (int i = 0; i < number; i++) {
                                System.out.println("Enter Author " + (i + 1) + ":");
                                String auth = scanner.nextLine();
                                authors.add(auth);
                            }
                            System.out.println("Enter Publisher: ");
                            String publisher = scanner.nextLine();
                            System.out.println("Enter Cost: ");
                            double cost = scanner.nextDouble();
                            scanner.nextLine();
                            item newitem = new magazine(title1, authors,publisher,cost);
                            library.additem(newitem);
                            System.out.println("Book added.");
                            break;}
                        case 3:{
                            System.out.print("Enter Publisher: ");
                            String publisher = scanner.nextLine();
                            System.out.print("Enter Date ");
                            String date= scanner.nextLine();
                            scanner.nextLine();
                            System.out.print("Enter Cost ");
                            double cost= scanner.nextDouble();
                            item newitem = new newspaper(publisher,date,cost );
                            library.additem(newitem);
                            System.out.println("Book added.");
                            break;}
                    }
                    break;
                case 2:
                    System.out.print("Enter item ID to Edit: ");
                    int editId = scanner.nextInt();
                    scanner.nextLine();
                    item item= library.getitemById(editId);
                    if (item instanceof book){
                    System.out.print("Enter New Title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter New Author: ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("Enter New Year: ");
                    int newYear = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter New Cost");
                    double cost=scanner.nextDouble();
                    scanner.nextLine();
                    library.edititem(editId, newTitle, newAuthor, newYear,cost);
                    } else if (item instanceof magazine) {
                        System.out.print("Enter New Title: ");
                        String newTitle = scanner.nextLine();
                        System.out.print("Enter Number of New Authors: ");
                        int number = scanner.nextInt();
                        scanner.nextLine();
                        ArrayList<String> authors=new ArrayList<String>();
                        for (int i = 0; i < number; i++) {
                            System.out.println("Enter Author " + (i + 1) + ":");
                            String auth = scanner.nextLine();
                            authors.add(auth);
                        }
                        System.out.print("Enter New Publisher: ");
                        String newPublisher = scanner.nextLine();
                        System.out.println("Enter New Cost");
                        double cost=scanner.nextDouble();
                        library.edititem(editId,newTitle,authors,newPublisher,cost);
                    } else if (item instanceof newspaper) {
                        System.out.print("Enter New Publisher: ");
                        String newPublisher = scanner.nextLine();
                        System.out.print("Enter New Date: ");
                        String newDate = scanner.nextLine();
                        System.out.println("Enter New Cost");
                        double cost=scanner.nextDouble();
                        library.edititem(editId, newPublisher, newDate,cost);
                    }else {
                        System.out.println("Enter a valid ITEM ID");
                    }
                    break;
                case 3:
                    System.out.print("Enter item ID to Delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    library.deleteitem(deleteId);
                    break;
                case 4:
                    System.out.println("All items in Library:");
                    library.displayAllitems();
                    break;
                case 5:
                    System.out.print("Enter item ID to View: ");
                    int viewId = scanner.nextInt();
                    scanner.nextLine();
                    item viewitem = library.getitemById(viewId);
                    library.displayItemDetails(viewitem);
                    break;
                case 6:
                    System.out.println("Hot Picks are:");
                    library.displayHotPicks();
                    break;
                case 7:
                    System.out.println("Enter Item ID to Borrow: ");
                    int itemIdToBorrow = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter your name");
                    String name=scanner.nextLine()       ;
                    System.out.println("Enter your id number");
                    int id=scanner.nextInt();
                    scanner.nextLine();
                    Borrower borrower=new Borrower(name,id);
                    library.addBorrower(borrower);
                    library.borrowItem(id,itemIdToBorrow);
                    break;
                case 8:
                    library.displayBorrowers();
                    break;
                case 9:
                    System.out.println("Visit again.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}