package test;

import java.util.stream.IntStream;

public class Talkative implements Runnable {

    private int attribut;
    public Talkative(int attribut) {
        this.attribut = attribut;
    }

    @Override
    public void run() {
        IntStream.range(0, 100)
                .forEach(t-> System.out.println(attribut));
    }

    public static void main(String[] args) {

        IntStream.range(0,10).forEach(i-> {
            Talkative talkative = new Talkative(i);
            Thread thread = new Thread(talkative);
            thread.start();
        });
    }
}