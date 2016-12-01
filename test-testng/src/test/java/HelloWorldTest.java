import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by dipak on 12/1/16.
 */
public class HelloWorldTest extends Arquillian {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar").addClass(
                HelloWorld.class);
    }

    @Test
    public void test1() {
        Assert.assertNotNull(null);
    }

    @Test
    public void test2() {
        HelloWorld helloWorld = new HelloWorld();
        Assert.assertEquals("Hello greeting", helloWorld.greet());
    }



}
