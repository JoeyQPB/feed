package Services;

import entities.Comment;
import entities.Feed;
import entities.Post;

import java.util.Scanner;

public class Actions {
    private static Scanner sc = new Scanner(System.in);
    private Feed feed;

    public Actions() {
        System.out.print("Enter the text social media: ");
        createFeed(sc.nextLine());
        this.feed.showFeed();
    }
    private void createFeed(String name) {
        this.feed = new Feed(name);
    }

    public void doSomething() {
        StringBuilder sb = new StringBuilder();

        if (this.feed.getQtdPost() <= 0) {
            System.out.println("You must do a new post");
            newPost();

        } else {
            sb.append("\nSelect the Action: \n");
            sb.append("1 - New post\n");
            sb.append("2 - Edit post\n");
            sb.append("3 - Like  post\n");
            sb.append("4 - Remove like\n");
            sb.append("5 - Remove post\n");
            sb.append("6 - Comment a post\n");
            sb.append("7 - Edit comment\n");
            sb.append("8 - like comment\n");
            sb.append("9 - Remove like comment\n");
            sb.append("10 - Remove comment\n");

            System.out.println(sb.toString());
            ;
            switch (Integer.parseInt(sc.nextLine())) {
                case 1 -> newPost();
                case 2 -> editPost();
                case 3 -> likePost();
                case 4 -> removeLikePost();
                case 5 -> removePost();
                case 6 -> commentPost();
                case 7 -> editComment();
                case 8 -> likeComment();
                case 9 -> removeLikeComment();
                case 10 -> removeComment();

                default -> System.out.println("Invalid Option!");
            }
        }
    }

    private void newPost() {
        System.out.print("Author: ");
        String author = sc.nextLine();

        System.out.print("Tittle: ");
        String title = sc.nextLine();

        System.out.print("Content: ");
        String content = sc.nextLine();
        Post post = new Post(author, title, content);
        this.feed.addPost(post);
    }

    private void editPost() {
        System.out.print("Enter the post ID: ");
        int id = Integer.parseInt(sc.nextLine());
        this.feed.editOnePost(id);
        this.feed.showOnePost(id);
    }
    private void likePost() {
        System.out.print("Enter the post ID: ");
        int id = Integer.parseInt(sc.nextLine());
        this.feed.addLike(id);
    }
    private void removeLikePost() {
        System.out.print("Enter the post ID: ");
        int id = Integer.parseInt(sc.nextLine());
        this.feed.removeLike(id);
    }
    private void removePost() {
        System.out.print("Enter the post ID: ");
        int id = Integer.parseInt(sc.nextLine());
        this.feed.removePost(id);
    }
    private void commentPost() {
        System.out.print("Enter the post ID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Author: ");
        String author = sc.nextLine();

        System.out.print("Content: ");
        String content = sc.nextLine();

        Comment newComment = new Comment(author, content);
        this.feed.getPost(id).addComment(newComment);
    }
    private void editComment() {}
    private void likeComment() {}
    private void removeLikeComment() {}
    private void removeComment() {}

}
