package br.edu.eribeiro;

import org.jboss.byteman.contrib.bmunit.BMRule;
import org.jboss.byteman.contrib.bmunit.BMUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(BMUnitRunner.class)
public class BarTest {

    @BMRule(name="logging",
            targetClass = "org.apache.logging.log4j.Logger",
            isInterface = true,
            targetMethod = "info",
            targetLocation = "AT ENTRY",
            action = "traceln(\"\\n Parameter: \"+ $1);"
    )
    @Test
    public void testLogging() {
        new Bar().call();
    }
}
