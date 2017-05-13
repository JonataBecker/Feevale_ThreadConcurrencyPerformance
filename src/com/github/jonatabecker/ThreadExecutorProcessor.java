/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jonatabecker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executor service processor
 *
 * @author jonatabecker
 */
public class ThreadExecutorProcessor implements ProcessorParameters {

    public static void main(String[] args) throws InterruptedException {
        ThreadExecutorProcessor threadExecutorProcessor = new ThreadExecutorProcessor();
        Thread.sleep(INITIAL_SLEEP);
        threadExecutorProcessor.exec();
    }

    public void exec() throws InterruptedException {
        long time = System.currentTimeMillis();
        ExecutorService threadpool = Executors.newFixedThreadPool(4);
        Obj obj = new Obj();
        List<Callable<Processador>> callables = new ArrayList<>();
        for (int i = 0; i < NUMBER_THREAD; i++) {
            callables.add(new Processador(obj, time));
        }
        threadpool.invokeAll(callables)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                });
        threadpool.shutdown();
    }
    
    private static class Processador implements Callable {

        private final Obj obj;
        private final long initialTime;
        
        public Processador(Obj obj, long initialTime) {
            this.obj = obj;
            this.initialTime = initialTime;
        }

        @Override
        public Object call() {
            for (int i = 0; i < NUMBER_TEST; i++) {
                obj.exec();
            }
            System.out.println("Time: " + (System.currentTimeMillis() - initialTime));
            return null;
        }

    }

}
