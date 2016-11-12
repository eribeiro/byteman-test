package br.edu.eribeiro;

/**
 Code from: https://dzone.com/articles/byteman-swiss-army-knife-byte
 **/

public class BrokenSingleton {

    private static volatile BrokenSingleton instance;

    private BrokenSingleton() {
    }

    public static BrokenSingleton get() {
        if (instance == null) {
            instance = new BrokenSingleton();
        }
        return instance;
    }
}