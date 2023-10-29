import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Library {
    public List<item> items;
    private List<Borrower> borrowers;
    private Map<Integer, Integer> popularityCount;
    public Library() {
        items = new ArrayList<>();
        borrowers=new ArrayList<>();
        popularityCount = new HashMap<>();
    }
    public void addBorrower(Borrower borrower){
        borrowers.add(borrower);
    }
//    public void borrowItem(int borrowerId, int itemId) {
//        Borrower borrower = getBorrowerById(borrowerId);
//        item itemToBorrow = getitemById(itemId);
//
//        if (borrower != null && itemToBorrow != null) {
//                double cost = itemToBorrow.calculateCost();
//                borrower.borrowItem(itemToBorrow);
//                itemToBorrow.setCurrentBorrower(borrower);
//
//                System.out.println("Item borrowed successfully.");
//                System.out.println("Cost of borrowing: " + cost);
//        } else {
//            System.out.println("Item not available for borrowing.");
//            popularityCount.put(itemToBorrow, popularityCount.getOrDefault(itemToBorrow, 0) + 1);
//        }
//    }
public void borrowItem(int borrowerId, int itemId) {
    Borrower borrower = getBorrowerById(borrowerId);
    item itemToBorrow = getitemById(itemId);

    if (borrower != null && itemToBorrow != null) {
        if (itemToBorrow.getCurrentBorrower() == null) {
            double cost = itemToBorrow.calculateCost();
            borrower.borrowItem(itemToBorrow);
            itemToBorrow.setCurrentBorrower(borrower);

            // Update popularity count for the item
            popularityCount.put(itemId, popularityCount.getOrDefault(itemId, 0) + 1);

            System.out.println("Item borrowed successfully.");
            System.out.println("Cost of borrowing: " + cost);
        } else {
            popularityCount.put(itemId, popularityCount.getOrDefault(itemId, 0) + 1);
            System.out.println("Item is already borrowed by another borrower.");
        }
    } else {
        System.out.println("Item not available for borrowing.");
    }
}
    public void displayBorrowers() {
        System.out.println("Borrowers and their borrowed items:");
        for (Borrower borrower : borrowers) {
            System.out.println("Borrower ID: " + borrower.getUserID() + ", Name: " + borrower.getName());
            Set<item> borrowedItems = borrower.getBorrowedItems();
            if (!borrowedItems.isEmpty()) {
                System.out.println("Borrowed Items:");
                for (item borrowedItem : borrowedItems) {
                    if (borrowedItem != null) {
                        borrowedItem.display();
                    }
                }
            } else {
                System.out.println("No items borrowed.");
            }
        }
    }

//    public void displayHotPicks() {
//        System.out.println("Hot Picks :");
//        popularityCount.entrySet().stream()
//                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
//                .forEach(entry -> {
//                    item item = entry.getKey();
//                    int popularity = entry.getValue();
//                    System.out.println("Popularity: " + popularity + ", " + item.toString());
//                });
//    }
public void displayHotPicks() {
    System.out.println("Hot Picks :");
    popularityCount.entrySet().stream()
            .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
            .forEach(entry -> {
                int itemId = entry.getKey();
                int popularity = entry.getValue();
                item item = getitemById(itemId);
                if (item != null) {
                    System.out.println("Popularity: " + popularity + ", " + item.toString());
                }
            });}
    public void additem(item item) {

        items.add(item);
    }
    public Borrower getBorrowerById(int borrowerId) {
        for (Borrower borrower : borrowers) {
            if (borrower.getUserID() == borrowerId) {
                return borrower;
            }
        }
        return null;
    }
    public item getitemById(int id) {
        for (item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
    public void edititem(int id,String title,String author,int year,double cost)
    {
        item item=getitemById(id);
        if(item!=null){
            book bookItem=(book) item;
            bookItem.setTitle(title);
            bookItem.setAuthor(author);
            bookItem.setYear(year);
            bookItem.setCost(cost);
            System.out.println("Book edited.");
        }
    }
    public void edititem(int id,String title,ArrayList<String> author,String publisher,double cost)
    {
        item item=getitemById(id);
        if(item!=null){
            magazine magazineItem=(magazine) item;
            magazineItem.setTitle(title);
            magazineItem.setAuthor(author);
            magazineItem.setPublisher(publisher);
            magazineItem.setCost(cost);
            System.out.println("Magazine edited.");
        }
    }
    public void edititem(int id,String publisher,String date,double cost)
    {
        item item=getitemById(id);
        if(item!=null){
            newspaper newspaperItem=(newspaper) item;
            newspaperItem.setPublisher(publisher);
            newspaperItem.setDate(date);
            newspaperItem.setCost(cost);
            System.out.println("Newspaper edited.");
        }
    }

    public void deleteitem(int id) {
        item item = getitemById(id);
        if (item != null) {
            items.remove(item);
            System.out.println("Item deleted.");
        } else {
            System.out.println("Item not found.");
        }
    }

    public void displayAllitems() {
        for (item item : items) {
            item.display();
        }
    }
    public void displayItemDetails(item item){
        if(item!=null){
            item.display();
        }
        else{
            System.out.println("Item is null and not found");
        }
    }


    public void loadDataFromFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                int type = Integer.parseInt(parts[0]);
                if (type==1)
                {
                    String title=parts[1];
                    String author=parts[2];
                    int year = Integer.parseInt(parts[3]);
                    double cost=Double.parseDouble(parts[4]);
                    item newitem= new book(title,author,year,cost);
                    additem(newitem);
                }
                if (type==2)
                {
                    String title=parts[1];
                    ArrayList<String> authors=new ArrayList<String>();
                    for (int i=2;i<=(parts.length)-2;i++) {

                        authors.add(parts[i]);
                    }
                    String publisher = parts[(parts.length)-2];
                    double cost=Double.parseDouble(parts[parts.length-1]);
                    item newitem=new magazine(title,authors,publisher,cost);
                    additem(newitem);
                }
                if (type==3) {
                    String publisher = parts[1];
                    String date = parts[2];
                    double cost=Double.parseDouble(parts[3]);
                    item newitem=new newspaper(publisher,date,cost);
                    additem(newitem);
                }
            }
            System.out.println("Data loaded from file.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
