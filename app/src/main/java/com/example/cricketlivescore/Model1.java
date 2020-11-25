package com.example.cricketlivescore;

public class Model1 {
    String  author,title,description,url,content;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }

    public Model1(String author, String title, String description, String url, String content) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.content = content;
    }
}
