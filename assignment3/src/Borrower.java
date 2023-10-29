import java.util.HashSet;
import java.util.Set;

class Borrower{
    private String name;
    private int userID;
    private Set<item > borrowedItems;
    public Borrower (String name,int id)
    {
        this.userID=id;
        this.name=name;
        this.borrowedItems=new HashSet<>();
    }

    public int getUserID(){
        return userID;
    }
    public String getName()
    {
        return name;
    }
    public Set<item> getBorrowedItems()
    {
        return borrowedItems;
    }
    public void borrowItem(item item)
    {
        borrowedItems.add(item);
    }
}