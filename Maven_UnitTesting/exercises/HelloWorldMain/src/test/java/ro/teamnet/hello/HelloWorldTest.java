package ro.teamnet.hello;

import org.junit.Test;

/**
 * Created by Emilia.Palaghita on 10-Jul-17.
 */
public class HelloWorldTest {

    @Test
    public void testSayHello() {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.sayHello();
    }

    @Test
    public void testReturnHelloKey() {
        HelloWorld helloWorld = new HelloWorld();
        System.out.println("From Test: " + helloWorld.returnHelloKey());

        assert helloWorld.returnHelloKey().equals("HelloKey");
    }

}
