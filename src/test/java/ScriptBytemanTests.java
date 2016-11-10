import org.jboss.byteman.contrib.bmunit.BMRule;
import org.jboss.byteman.contrib.bmunit.BMScript;
import org.jboss.byteman.contrib.bmunit.BMUnitConfig;
import org.jboss.byteman.contrib.bmunit.BMUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(BMUnitRunner.class)
@BMUnitConfig(loadDirectory="src/test/resources/byteman", debug=true)
@BMScript(value="check.btm")
public class ScriptBytemanTests {


    @Test
    public void testFooWithScript() {
        new Foo().call();
    }


    @Test
    public void testFileInputStream() throws IOException {
        File tmpFile = File.createTempFile("test-", ".txt");
        FileOutputStream fis = null;
        try {
            fis = new FileOutputStream(tmpFile);
            fis.write("Hello".getBytes());
            fis.write("Hello".getBytes());
            fis.write("Hello".getBytes());

        }
        finally {
            if (fis != null)
                fis.close();
        }
    }
}
