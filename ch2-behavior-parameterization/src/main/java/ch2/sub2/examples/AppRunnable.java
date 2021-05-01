package ch2.sub2.examples;

public class AppRunnable {
    public static void main(String[] args) {

        // Runnable : 익명 함수
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Thread - anonymous function");
            }
        });
        t.run();

        // Runnable : 람다
        Thread t2 = new Thread(() -> System.out.println("Hello Thread - lambda"));
        t2.run();
    }
}
