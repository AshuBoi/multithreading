import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CaseStudy {
    public static final int MAX_PASSWORD = 9999;
    public static void main(String[] args) {

        Random rand = new Random();
        Vault vault = new Vault(rand.nextInt(MAX_PASSWORD));
        List<Thread> threads = new ArrayList<Thread>();

        threads.add(new AscendingHackerThread(vault));
        threads.add(new DescendingHackerThread(vault));
        threads.add(new PoliceThread());

        for (Thread thread : threads) {
            thread.start();
        }

    }

    private static class Vault {
        private int password;
        public Vault(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(int guess){
            try{
                Thread.sleep(5);
            } catch(InterruptedException e){}
            return this.password == guess;
        }
    }


    //creating hacker thread
    private static abstract class HackerThread extends Thread {
        protected Vault vault;
        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MIN_PRIORITY);
        }
        @Override
        public void start() {
            System.out.println("Starting " + this.getName());
            super.start();
        }
    }

    // after creating the concrete hacker thread, lets define ascending hacker thread, which extends the hacker thread
    private static class AscendingHackerThread extends HackerThread {
        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for(int guess =0; guess<MAX_PASSWORD; guess++){
                if(vault.isCorrectPassword(guess)){
                    System.out.println(this.getName() +" -> Guessed the password: " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingHackerThread extends HackerThread {
        public DescendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for(int guess =MAX_PASSWORD; guess>=0 ; guess--){
                if(vault.isCorrectPassword(guess)){
                    System.out.println(this.getName() +" -> Guessed the password: " + guess);
                    System.exit(0);
                }
            }
        }
    }

    // we are creating a police thread that extends Thread class directly and we are not related to hacker threads or classes and does not inherit finctionality from them
    // All the police thread is going to do in its run method is that its going to count down from 10 to 0 sleeping for 1 sec in-between each count
    // In each of the iteration, It's going to print out how many seconds we have left before it caches the hackers
    //and When the 10 seconds elapse, it will catch the hackers and stop the program
    private static class PoliceThread extends Thread {
        @Override
        public void run() {
            for(int i=10;i>0;i--){
                try{
                    Thread.sleep(1000);
                } catch(InterruptedException e){}
                System.out.println(i);
            }
            System.out.println("Game over for you hackers");
            System.exit(0);
        }
    }
}
