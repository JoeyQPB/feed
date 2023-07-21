package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

    // get one post by id
    // edit a post by id
    // remove post by id
    // add like
    // remove like

}
