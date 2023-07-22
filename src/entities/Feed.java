package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Feed {
    private static final SimpleDateFormat SDFT = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private final List<Post> posts = new ArrayList<>();
    private final String name;
    public Feed (String name) {
        this.name = name;
    }
    public void addPost (Post post) {
        this.posts.add(post);
        sortPost();
    }

    public String getFeedName () {
        return this.name;
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
            sb.append("\nPOST #").append(post.getId()) .append("\n");
            sb.append("Tittle: ").append(post.getTitle().toUpperCase()).append("\n");
            sb.append("by: ").append(post.getAuthor()).append("\n");
            sb.append("Posted at: ").append(SDFT.format(post.getMoment())).append("\n");
            if (post.getMoment().getTime() != post.getUpdateMoment().getTime()) {
                sb.append("updated at: ").append(SDFT.format(post.getMoment())).append("\n");
            }
            sb.append("CONTENT: ").append(post.getContent()).append("\n");
            sb.append("Likes: ").append(post.getLikes()).append("\n");
            System.out.println(sb);
            post.showComments();
        }
    }

    public void showOnePost (int id) {
        for (Post post : this.posts) {
            if (post.getId() == id) {
                StringBuilder sb = new StringBuilder();
                sb.append("\nPOST #").append(post.getId()).append("\n");
                sb.append("Tittle: ").append(post.getTitle().toUpperCase()).append("\n");
                sb.append("by: ").append(post.getAuthor()).append("\n");
                sb.append("Posted at: ").append(SDFT.format(post.getMoment())).append("\n");
                if (post.getMoment().getTime() != post.getUpdateMoment().getTime()) {
                    sb.append("updated at: ").append(SDFT.format(post.getMoment())).append("\n");
                }
                sb.append("CONTENT: ").append(post.getContent()).append("\n");
                sb.append("Likes: ").append(post.getLikes()).append("\n");
                System.out.println(sb);
            }
        }
    }

    public void editOneTitlePost (int id) {
        Scanner sc = new Scanner(System.in);
        for (Post post : this.posts) {
            if (post.getId() == id) {
                System.out.println("\nCurrent Title: " + post.getTitle());

                System.out.println("\n Enter new Title: ");
                String newTitle = sc.nextLine();

                post.editTitle(newTitle);
                post.setUpdateMoment();
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
                post.setUpdateMoment();
            }
        }
    }

    public void removePost (int id) {
        this.posts.removeIf(post -> post.getId() == id);
    }

    public void addPostLike(int id) {
        for (Post post : this.posts) {
            if (post.getId() == id) {
                post.addLikes();
            }
        }
    }
    public void removePostLike(int id) {
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
