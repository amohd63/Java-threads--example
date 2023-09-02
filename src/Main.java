// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        UserThread user1 = new UserThread("User1", 900);
        UserThread user2 = new UserThread("User2", 50);
        UserThread user3 = new UserThread("User3", 100);

        user1.start();
        user2.start();
        user3.start();
    }
}