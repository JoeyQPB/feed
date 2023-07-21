package entities;

import java.util.Date;

public class Comment {
    private final Date moment = new Date();
    private Date updateMoment = new Date();
    private String author;
    private String content;
    private Integer likes = 0;
    private Integer id;

    public Comment (String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getContent() {
        return this.content;
    }

    public void editContent(String content) {
        this.content = content;
    }

    public Integer getLikes() {
        return this.likes;
    }

    public void addLikes() {
        this.likes++;
    }
    public void removeLikes() {
        if (this.likes > 0) {
            this.likes--;
        }
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getMoment() {
        return this.moment;
    }

    public Date getUpdateMoment() {
        return this.updateMoment;
    }

    public void setUpdateMoment() {
        this.updateMoment = new Date();
    }
}
