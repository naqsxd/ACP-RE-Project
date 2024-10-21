public class TestMain {
    public static void main(String[] args) {

        
        Thread serverThread = new Thread(() -> {
            Server.main(args);
        });
        serverThread.start();

        
        try {
            Thread.sleep(1000);  
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        
        Client.main(args);
    }

}