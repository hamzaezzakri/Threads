package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Sommeur implements Runnable {

    private int [] entiers;
    private int debut;
    private int fin;

    public Sommeur(int[] entiers, int debut, int fin) {
        this.entiers = entiers;
        this.debut = debut;
        this.fin = fin;
    }

    public int getSomme(){
        return IntStream.rangeClosed(debut, fin)
                .map(i-> entiers[i])
                .sum();
    }

    @Override
    public void run() {

    }

    public static void main(String[] args) throws InterruptedException {

        int [] entiers = {5,6,7,9,8,4,2};
        int arrayLength = entiers.length;
        int nThreads = 4;

        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);

        Sommeur [] sommeurs = new Sommeur[nThreads];

        IntStream.range(0, nThreads)
                .forEach(i->{
                    int debut = (int) ((Math.random() * arrayLength)),
                            fin = (int) ((Math.random() * arrayLength));

                    sommeurs[i] = new Sommeur(entiers,debut,fin);
                    executorService.execute(sommeurs[i]);
                });

        executorService.shutdown();
        while(!executorService.isTerminated()){
            System.out.println("waiting for other threads to terminate");
        }

        System.out.println(IntStream.range(0, nThreads)
                .map(i-> sommeurs[i].getSomme())
                .sum());

    }
}
