public class secondWayToInitializeThread {
    public static void main(String[] args)
    {
        Thread thread = new newThread();
        thread.start();
    }

    private static class newThread extends Thread {
        @Override
        public void run() {
            // in this way of initialization of thread we can remove the static methods and use this. instead to refer the thread
//            System.out.println("Hello from "+ Thread.currentThread().getName());
            System.out.println("Hello from "+ this.getName());
        }
    }
}
