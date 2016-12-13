package br.edu.eribeiro;

import org.jboss.byteman.contrib.bmunit.BMRule;
import org.jboss.byteman.contrib.bmunit.BMRules;
import org.jboss.byteman.contrib.bmunit.BMUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(BMUnitRunner.class)
public class SimpleBytemanTest {

    @Test(expected= RuntimeException.class)
    @BMRule(name = "ForceRuntimeException",
            targetClass="Foo",
            targetMethod="call",
            action="throw new RuntimeException()",
            targetLocation = "AT ENTRY"
    )
    public void shouldRaiseRuntimeException() {
        new Foo().call();
    }

    @Test
    @BMRule(
            name = "ReturnDifferentResult",
            targetClass = "Foo",
            targetMethod = "call",
            action = "return \"World, Hello\"", // could `return null`
            targetLocation = "AT EXIT" // optional, in this case, as it will be always executed at the exiting
    )
    public void returnShouldBeDifferent() {
        assertEquals("World, Hello", new Foo().call());
    }

    @Test(expected= IOException.class)
    @BMRule(name = "ForceIOException",
            targetClass="Foo",
            targetMethod="call2",
            action="throw new java.io.IOException(\"Byteman says: disk is full!\")",
            targetLocation = "AT ENTRY"
    )
    public void shouldRaiseIOException() throws Exception {
        // call2 should raise a checked exception if we want to make
        // Byteman call this exception, otherwise it will throw the following error:
        // org.jboss.byteman.rule.exception.TypeException: ThrowExpression.typeCheck : exception type not declared by trigger method java.io.IOException
        new Foo().call2();
    }

    @Test
    @BMRules(rules = {
                    @BMRule(name = "EnteringMethod",
                            targetClass = "Foo",
                            targetMethod = "call",
                            action = "traceln(\"entering method\");",
                            targetLocation = "AT ENTRY"
                            ),
                    @BMRule(name = "ExitingMethod",
                            targetClass = "Foo",
                            targetMethod = "call",
                            action = "traceln(\"exiting method\");",
                            targetLocation = "AT EXIT"
                    )

    })
    public void logStuff() {
        new Foo().call();
    }

}
