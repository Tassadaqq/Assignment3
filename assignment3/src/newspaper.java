class newspaper extends item {
    private String publisher;
    private String date;
    private double cost;

    public newspaper(String publisher ,String date,double cost)
    {
        super();

        this.cost=cost;
        this.publisher=publisher;
        this.date=date;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public double getCost(){
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    @Override
    public double calculateCost(){
        return (10+5);
    }
    @Override
    public void display()
    {
        System.out.println(this.toString());
    }
    @Override
    public String toString()
    {
        return "NewspaperID : "+getId()+" Publisher : "+publisher+" Date: "+date+" Cost: "+calculateCost();
    }
}
