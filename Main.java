public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = getThread();
        //for now you wont see any difference but for more complex problems where some threads need more responsiveness than others
        //this set priority method plays a important part

        System.out.println("We are in thread: " + Thread.currentThread().getName() + " before starting a new thread");
        thread.start();
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " after starting a new thread");

        // the thread class also has a static method called sleep, which puts the current thread to sleep for a given number of milliseconds
        //its important to point out that businesses, not spinning a loop or anything like that, Instead the thread sleep methods instructs the operating system
        //to not schedule the current thread that time passes, During this time the thread is not consuming any cpu
        Thread.sleep(10000);

        // normally unchecked exceptions that happen in java, simply bring down the entire thread unless we catch them explicitly and handle them in a particular way
        //with thread.setUncaughtExceptionHandler for the entire thread at its inception, that handler will be called if an exception was thrown inside the thread
        // and did not get caught anywhere in this case, we're simply going to print out the thread and the exception that was not caught
        //but more realistically, this would be a place where we could clean up some of the resources or log additional data to enable us to
        //troubleshoot this issue , open Multithreaded_exception file to explore this
    }

    private static Thread getThread() {
        Thread thread = new Thread(() -> {
            System.out.println("We are now in thread: " + Thread.currentThread().getName() + " ,thread id: " + Thread.currentThread().getId());
            System.out.println("Current thread priority is: " + Thread.currentThread().getPriority() + " ,thread id: " + Thread.currentThread().getId());
        });

        // to set the name of a thread to help us during debugging
        thread.setName("New Worker Thread");

        // remember when we talk about thread scheduling and how the operating system maintains dynamic priority for each thread
        //so using the thread object, we can set the static component of that dynamic priority programmatically
        //By calling the set priority method, we can pass a value, which ranges from one to 10 or use the pre-defined values
        //which are the MAX_PRIORITY, MIN_PRIORITY or NORM_PRIORITY which is the default
        thread.setPriority(Thread.MAX_PRIORITY);
        return thread;
    }
}
