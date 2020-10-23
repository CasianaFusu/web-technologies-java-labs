package beans;

import org.springframework.beans.factory.annotation.Autowired;

public class Book {
    //hasA (for author)
    //we concentrate on Book (each book has an owner)

    private String title;
    private Author author;
    private Owner owner;
    private Price price;

    public Book(String title) {
        this.title = title;
    }

    public Author getAuthor(){ return author; }

    public void setAuthor(Author author)
    {
        this.author = author;
    }

    public Owner getOwner()
    {
        return owner;
    }

    @Autowired
    public void setOwner(Owner owner)
    {
        this.owner = owner;
    }

    public void setPrice(Price price)
    {
        this.price = price;
    }

    public Price getPrice()
    {
        return price; //return the actual price
    }

    @Override
    public String toString() {
       return "Book - " + title;
    }
}
