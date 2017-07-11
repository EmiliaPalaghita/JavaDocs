package com.timnet.examples;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Emilia.Palaghita on 10-Jul-17.
 */
public class MyClassTest {
    @Test
    public void testConcatenate() {
        MyClass myclass = new MyClass();
        String result = myclass.concatenate("ana", "gigel");
        assertEquals("anagigel", result);
    }

    @Test
    public void testConcatenateWithNulls() {
        MyClass myclass = new MyClass();
        String result = myclass.concatenate(null, null);
        assertEquals(null, result);


        result = myclass.concatenate(null, "gigi");
        assertEquals("gigi", result);
    }

    @Test
    public void testBoolean() {
        MyClass c = new MyClass();
        boolean b = c.getBoolean();
//        assertNotNull(String.valueOf(b), null);

        assertThat(123, is(123));
    }
}
