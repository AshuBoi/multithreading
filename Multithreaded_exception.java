public class Multithreaded_exception {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("Intentional Exception");
            }
        });
        thread.setName("Misbehaving thread");

        // normally unchecked exceptions that happen in java, simply bring down the entire thread unless we catch them explicitly and handle them in a particular way
        //with thread.setUncaughtExceptionHandler for the entire thread at its inception, that handler will be called if an exception was thrown inside the thread
        // and did not get caught anywhere in this case, we're simply going to print out the thread and the exception that was not caught
        //but more realistically, this would be a place where we could clean up some of the resources or log additional data to enable us to
        //troubleshoot this issue ,
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error occurred in thread: " + t.getName()
                        + " -> the error is: " + e.getMessage());
            }
        });
        thread.start();
    }
}
