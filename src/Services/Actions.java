package Services;

import entities.Comment;
import entities.Feed;
import entities.Post;

import java.util.Scanner;

public class Actions {
    private static final Scanner sc = new Scanner(System.in);
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
            sb.append("2 - Edit title post\n");
            sb.append("3 - Edit post\n");
            sb.append("4 - Like  post\n");
            sb.append("5 - Remove like\n");
            sb.append("6 - Remove post\n");
            sb.append("7 - Comment a post\n");
            sb.append("8 - Edit comment\n");
            sb.append("9 - like comment\n");
            sb.append("10 - Remove like comment\n");
            sb.append("11 - Remove comment\n");
            sb.append("12 - Show quantity of posts\n");

            System.out.println(sb);

            switch (Integer.parseInt(sc.nextLine())) {
                case 1 -> newPost();
                case 2 -> editTitlePost();
                case 3 -> editPost();
                case 4 -> likePost();
                case 5 -> removeLikePost();
                case 6 -> removePost();
                case 7 -> commentPost();
                case 8 -> editComment();
                case 9 -> likeComment();
                case 10 -> removeLikeComment();
                case 11 -> removeComment();
                case 12 -> qdtPosts();

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

    private void editTitlePost() {
        System.out.print("Enter the post ID: ");
        int id = Integer.parseInt(sc.nextLine());
        this.feed.editOneTitlePost(id);
        this.feed.showOnePost(id);
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
        this.feed.addPostLike(id);
    }
    private void removeLikePost() {
        System.out.print("Enter the post ID: ");
        int id = Integer.parseInt(sc.nextLine());
        this.feed.removePostLike(id);
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
    private void editComment() {
        System.out.print("Enter the post ID: ");
        int postID = Integer.parseInt(sc.nextLine());

        System.out.print("Enter the Comment ID: ");
        int commentID = Integer.parseInt(sc.nextLine());
        this.feed.getPost(postID).editOneComment(commentID);
    }
    private void likeComment() {
        System.out.print("Enter the post ID: ");
        int postID = Integer.parseInt(sc.nextLine());

        System.out.print("Enter the Comment ID: ");
        int commentID = Integer.parseInt(sc.nextLine());
        this.feed.getPost(postID).editOneComment(commentID);
    }
    private void removeLikeComment() {
        System.out.print("Enter the post ID: ");
        int postID = Integer.parseInt(sc.nextLine());

        System.out.print("Enter the Comment ID: ");
        int commentID = Integer.parseInt(sc.nextLine());

        this.feed.getPost(postID).addCommentLike(commentID);
    }
    private void removeComment() {
        System.out.print("Enter the post ID: ");
        int postID = Integer.parseInt(sc.nextLine());

        System.out.print("Enter the Comment ID: ");
        int commentID = Integer.parseInt(sc.nextLine());

        this.feed.getPost(postID).removeCommentLike(commentID);
    }

    public void qdtPosts () {
        System.out.println("Posts quantity: " + this.feed.getQtdPost());
    }

}
