import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
class item implements configure{
    private Borrower currentBorrower;
    private static int Nextid=1;
    private String content;

    private int id;
    private String title;
    private String author;
    private int year;
    private double cost;
    private static int pop=10;
    private int popularity;
    item()
    {
        this.id=Nextid++;
        pop+=10;
        this.popularity=pop;
    }
    public int getPopularity(){
        return popularity;
    }
    public item( String title, String author, int year) {
        this.id=Nextid++;
        this.title = title;
        this.author = author;
        this.year = year;
        this.currentBorrower=null;
        pop+=10;
        this.popularity=pop;
    }
    public Borrower getCurrentBorrower() {
        return currentBorrower;
    }
    public void setCurrentBorrower(Borrower borrower) {
        currentBorrower = borrower;
    }
    public int getId() {
        return id;
    }
    @Override
    public void display(){

    }
    public String getContent() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\HP\\Desktop\\source.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
            }

            reader.close();
            setContent(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    @Override
    public double calculateCost(){
        return 0.0;
    }
    public void readItem(){
        JTextArea textArea = new JTextArea();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\HP\\Desktop\\source.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
            JScrollPane scrollPane = new JScrollPane(textArea);
            JFrame frame = new JFrame("Read " + getTitle());
            frame.getContentPane().add(scrollPane);
            frame.pack();
            frame.setVisible(true);
            reader.close();
            setContent(line);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}