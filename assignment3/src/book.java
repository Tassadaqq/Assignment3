class book extends item {
    private String title;
    private String author;
    private int year;
    private double cost;
    public book(String title ,String author,int year,double cost)
    {
        super();
        this.cost=cost;

        this.title=title;
        this.author=author;
        this.year=year;
    }
    public double getCost() {
        return cost;
    }

    @Override
    public void display()
    {
        System.out.println(this.toString());
    }
    @Override
    public String toString()
    {
        return "BookID: " + getId() + ", Title: " + getTitle() + ", Author: " + getAuthor() + ", Year: " + getYear()+" Cost: "+calculateCost();
    }
    @Override
    public double calculateCost()
    {
        double bookCost=getCost();
        double gst=(20.0/100)*bookCost+200.0;
        return bookCost+gst+10.0+5.0;
    }
    public String getTitle() {
        return title;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setTitle(String title) {
        this.title = title;
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
}