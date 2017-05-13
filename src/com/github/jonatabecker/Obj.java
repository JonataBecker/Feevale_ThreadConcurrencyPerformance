package com.github.jonatabecker;

import java.util.Arrays;

/**
 * Concurrent object
 * 
 * @author JonataBecker
 */
public class Obj {

    public synchronized void exec() {
        int[] teste = new int[]{
            5, 3, 2, 7, 6
        };
        Arrays.sort(teste);
    }
}
