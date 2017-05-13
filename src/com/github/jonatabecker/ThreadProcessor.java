package com.github.jonatabecker;

import java.util.ArrayList;
import java.util.List;

/**
 * Normal thread processor
 *
 * @author JonataBecker
 */
public class ThreadProcessor implements ProcessorParameters {

    public static void main(String[] args) throws InterruptedException {
        ThreadProcessor threadProcessor = new ThreadProcessor();
        Thread.sleep(INITIAL_SLEEP);
        threadProcessor.exec();
    }

    public void exec() {
        long time = System.currentTimeMillis();
        Obj obj = new Obj();
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread th = new Thread(new Processador(obj, time));
            list.add(th);
            th.start();
        }
    }

    private static class Processador implements Runnable {

        private final Obj obj;
        private final long initialTime;

        public Processador(Obj obj, long initialTime) {
            this.obj = obj;
            this.initialTime = initialTime;
        }

        @Override
        public void run() {
            for (int i = 0; i < NUMBER_TEST; i++) {
                obj.exec();
            }
            System.out.println("Time: " + (System.currentTimeMillis() - initialTime));
        }

    }
}
