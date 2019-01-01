package ars.utility.library.models;

public class Book {
    private String title;
    private String link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Book(){}

    public Book(String aTitle, String aLink){
        title = aTitle;
        link = aLink;
    }
}
