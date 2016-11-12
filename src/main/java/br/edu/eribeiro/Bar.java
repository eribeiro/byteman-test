package br.edu.eribeiro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bar {

    private static final Logger LOGGER = LogManager.getLogger(Bar.class);

    public void call() {
        LOGGER.info("Hello, world");
    }

    public static void main(String[] args) {
        new Bar().call();
    }
}
