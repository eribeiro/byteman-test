package br.edu.eribeiro;

import org.jboss.byteman.contrib.bmunit.BMRule;
import org.jboss.byteman.contrib.bmunit.BMScript;
import org.jboss.byteman.contrib.bmunit.BMUnitConfig;
import org.junit.Rule;
import org.junit.Test;

/**
 * Code lifted from: http://aredko.blogspot.com.br/2013/04/fault-injection-with-byteman-and-junit.html
 *
 * Github repo: https://github.com/reta/spring-jpa-byteman
 */

@BMUnitConfig(loadDirectory="src/test/resources/byteman", debug=true)
@BMScript(value="check.btm")
public class CustomBytemanTests {

    @Rule public BytemanRule byteman = BytemanRule.create(CustomBytemanTests.class);

    @Test(expected= RuntimeException.class)
    @BMRule(name = "ForceRuntimeException",
            targetClass="br.edu.eribeiro.Foo",
            targetMethod="call",
            action="throw new RuntimeException()",
            targetLocation = "AT ENTRY"
    )
    public void shouldRaiseRuntimeException() {
        new Foo().call();
    }

}
