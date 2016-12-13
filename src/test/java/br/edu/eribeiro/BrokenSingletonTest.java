package br.edu.eribeiro;

import org.jboss.byteman.contrib.bmunit.BMRule;
import org.jboss.byteman.contrib.bmunit.BMScript;
import org.jboss.byteman.contrib.bmunit.BMUnitConfig;
import org.jboss.byteman.contrib.bmunit.BMUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(BMUnitRunner.class)
@BMUnitConfig(loadDirectory="target/test-classes", debug=true)
@BMScript(value="concurrency.btm")
public class BrokenSingletonTest {

    @Test
    @BMRule(name="concurrency-test",
            targetClass = "br.edu.eribeiro.BrokenSingleton",
            targetMethod = "get"
    )
    public void testConcurrencyError() throws InterruptedException {
        Thread thread1 = new Thread(new SingletonAccessRunnable());
        Thread thread2 = new Thread(new SingletonAccessRunnable());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    public static class SingletonAccessRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println(BrokenSingleton.get());
        }
    }
}
