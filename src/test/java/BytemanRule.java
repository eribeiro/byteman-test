/**
 * Code lifted from: http://aredko.blogspot.com.br/2013/04/fault-injection-with-byteman-and-junit.html
 *
 * Github repo: https://github.com/reta/spring-jpa-byteman
 */

import org.jboss.byteman.contrib.bmunit.BMUnitRunner;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class BytemanRule extends BMUnitRunner implements MethodRule {
    public static BytemanRule create( Class< ? > klass ) {
        try {
            return new BytemanRule( klass );
        } catch( InitializationError ex ) {
            throw new RuntimeException( ex );
        }
    }

    private BytemanRule( Class<?> klass ) throws InitializationError {
        super( klass );
    }

    public Statement apply( final Statement statement, final FrameworkMethod method, final Object target ) {
        Statement result = addMethodMultiRuleLoader( statement, method );

        if( result == statement ) {
            result = addMethodSingleRuleLoader( statement, method );
        }

        return result;
    }
}