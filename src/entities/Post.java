package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Post {
    private static final SimpleDateFormat sdft = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private final Date moment = new Date();
    private Date updateMoment = new Date();
    private final String author;
    private String title;
    private String content;
    private Integer likes = 0;
    private final List<Comment> comments = new ArrayList<>();
    private Integer id;

    public Post (String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public void editTitle(String title) {
        this.title = title;
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

    public void addComment (Comment comment) {
        this.comments.add(comment);
        sortComments();
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

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void editOneComment (int id) {
        Scanner sc = new Scanner(System.in);
        for (Comment comment : this.comments) {
            if (comment.getId() == id) {
                System.out.println("\nCurrent Content: " + comment.getContent());

                System.out.println("\n Enter new content: ");
                String newContent = sc.nextLine();

                comment.editContent(newContent);
                comment.setUpdateMoment();
            }
        }
    }

    private void sortComments () {
        int commentsSize = this.comments.size();
        Comment commentTemp;

        for (int i = 0; i < commentsSize; i++) {

            for ( int j = i + 1; j < commentsSize; j++){

                if(this.comments.get(i).getMoment().getTime() > this.comments.get(j).getMoment().getTime()) {
                    commentTemp = this.comments.get(i);
                    this.comments.set(i, this.comments.get(j));
                    this.comments.set(j, commentTemp);
                }
            }
        }

        //assign an id
        for (int i = 0; i < commentsSize; i++) {
            this.comments.get(i).setId(i+1);
        }
    }

    public void showComments() {
        for (Comment comment : this.comments) {
            System.out.printf("\t\t\nCOMMENT #%d", comment.getId());
            System.out.println("\t\tcomment at: " + sdft.format(comment.getMoment()));
            if(comment.getMoment() != comment.getUpdateMoment()) {
                System.out.println("\t\tupdated at: " + sdft.format(comment.getUpdateMoment()));
            }
            System.out.println("\t\tAuthor: " + comment.getAuthor());
            System.out.println("\t\tcomment: " + comment.getContent());
            System.out.println("\t\tLikes: " + comment.getLikes());
        }
    }

    public void addCommentLike(int id) {
        for (Comment comment : this.comments) {
            if (comment.getId() == id) {
                comment.addLikes();
            }
        }
    }
    public void removeCommentLike(int id) {
        for (Comment comment : this.comments) {
            if (comment.getId() == id) {
                comment.removeLikes();
            }
        }
    }
}
