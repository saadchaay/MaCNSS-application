public class Main {
    public static void main(String[] args) {
        ConnectionDB cx = new ConnectionDB("cnss-app", "admin", "admin");
        System.out.println(cx.testConnection());
//        System.out.println("Hello world!");
    }
}