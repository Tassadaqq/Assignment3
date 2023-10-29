import java.util.ArrayList;
import java.util.List;

class magazine extends item {
    private String title;
    private String publisher;
    private List<String> authors;
    private double cost;
    private int fix=35;
    public magazine(String title1, List<String> authors, String publisher,double cost) {
        super();
        this.cost=cost;

        this.title=title1;
        this.publisher = publisher;
        this.authors = authors;
    }
    public double getCost() {
        return cost;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(ArrayList<String> authors) {
        this.authors = authors;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    @Override
    public double calculateCost(){
        double c=getCost();
        return c*fix;
    }
    @Override
    public void display() {
        System.out.println(this.toString());
    }

    @Override
    public String toString()
    {
        StringBuilder authorString = new StringBuilder();
        for (String author : authors) {
            authorString.append(author).append(", ");
        }
        authorString.setLength(authorString.length() - 2);

        return "MagazineID: " + getId() + ", Title: " + title + ", Authors: " + authorString + ", Publisher: " + publisher+" Cost: "+calculateCost();
    }
}