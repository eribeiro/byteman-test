package br.edu.eribeiro;

import br.edu.eribeiro.Foo;
import org.jboss.byteman.contrib.bmunit.BMScript;
import org.jboss.byteman.contrib.bmunit.BMUnitConfig;
import org.jboss.byteman.contrib.bmunit.BMUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * https://dzone.com/articles/byteman-swiss-army-knife-byte
 */

@RunWith(BMUnitRunner.class)
@BMUnitConfig(loadDirectory="src/test/resources/byteman", debug=true)
@BMScript(value="check.btm")
public class ScriptBytemanTests {


    @Test
    public void testFooWithScript() {
        new Foo().call();
    }


    @Test(expected = IOException.class)
    public void testFileInputStream() throws IOException {
        File tmpFile = File.createTempFile("test-", ".txt");
        try (FileOutputStream fis =  new FileOutputStream(tmpFile)) {
            fis.write("Hello".getBytes());
            fis.write("Hello".getBytes());
        }
    }
}
