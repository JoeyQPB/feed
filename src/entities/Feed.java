package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Feed {
    private static SimpleDateFormat sdft = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private List<Post> posts = new ArrayList<Post>();
    private String name;
    public Feed (String name) {
        this.name = name;
    }
    public void addPost (Post post) {
        this.posts.add(post);
        sortPost();
    }

    private void sortPost () {
        int minor_date;
        int postsSize = this.posts.size();
        Post postAux;

        for ( int i = 0; i < postsSize; i++ ) {
            minor_date = i;
            for( int j = i + 1; j < postsSize; j++) {
                if ( this.posts.get(j).getMoment().getTime() <  this.posts.get(minor_date).getMoment().getTime()) {
                minor_date = j;
                }
            }
            postAux = this.posts.get(minor_date);
            this.posts.set(minor_date, this.posts.get(i));
            this.posts.set(i, postAux);
        }

        for (int i = 0; i < postsSize; i++) {
            this.posts.get(i).setId(i+1);
        }
    }
    // show all feed by date order
    public void showFeed() {
        StringBuilder sb = new StringBuilder();
        for (Post post : this.posts) {
            sb.append("\nPOST #" + post.getId()  + "\n");
            sb.append("Tittle: " + post.getTitle().toUpperCase() + "\n");
            sb.append("by: " + post.getAuthor() + "\n");
            sb.append("Posted at: " + this.sdft.format(post.getMoment()) + "\n");
            if (post.getMoment().getTime() != post.getUpdateMoment().getTime()) {
                sb.append("updated at: " + this.sdft.format(post.getMoment()) + "\n");
            }
            sb.append("CONTENT: " + post.getContent() + "\n");
            sb.append("Likes: " + post.getLikes() + "\n");
            System.out.println(sb.toString());
            post.showComments();
        }
    }

    public void showOnePost (int id) {
        for (Post post : this.posts) {
            if (post.getId() == id) {
                StringBuilder sb = new StringBuilder();
                sb.append("\nPOST #" + post.getId()  + "\n");
                sb.append("Tittle: " + post.getTitle().toUpperCase() + "\n");
                sb.append("by: " + post.getAuthor() + "\n");
                sb.append("Posted at: " + this.sdft.format(post.getMoment()) + "\n");
                if (post.getMoment().getTime() != post.getUpdateMoment().getTime()) {
                    sb.append("updated at: " + this.sdft.format(post.getMoment()) + "\n");
                }
                sb.append("CONTENT: " + post.getContent() + "\n");
                sb.append("Likes: " + post.getLikes() + "\n");
                System.out.println(sb.toString());
            }
        }
    }

    public void editOnePost (int id) {
        Scanner sc = new Scanner(System.in);
        for (Post post : this.posts) {
            if (post.getId() == id) {
                System.out.println("\nCurrent Content: " + post.getContent());

                System.out.println("\n Enter new content: ");
                String newContent = sc.nextLine();

                post.editContent(newContent);
            }
        }
    }

    public void removePost (int id) {
        for (Post post : this.posts) {
            if (post.getId() == id) {
                this.posts.remove(post);
            }
        }
    }

    public void addLike(int id) {
        for (Post post : this.posts) {
            if (post.getId() == id) {
                post.addLikes();
            }
        }
    }
    public void removeLike(int id) {
        for (Post post : this.posts) {
            if (post.getId() == id) {
                post.removeLikes();
            }
        }
    }

    public Integer getQtdPost () {
        return this.posts.size();
    }

    public Post getPost (int id) {
        boolean notFound = true;
        for ( Post post : this.posts ) {
            if(post.getId() == id) {
                notFound = false;
                return post;
            }
        }
        if(notFound) System.out.println("Post Not found!");
        return null;
    }
}
