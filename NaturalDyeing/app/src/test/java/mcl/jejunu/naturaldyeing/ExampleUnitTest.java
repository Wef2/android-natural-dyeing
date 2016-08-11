package mcl.jejunu.naturaldyeing;

import junit.framework.Assert;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import dalvik.annotation.TestTargetClass;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void autoIncrementTest(){
        AtomicInteger integer = new AtomicInteger(0);
        for(int i = 0; i < 5; i++){
            integer.getAndIncrement();
        }
        Assert.assertEquals(integer.intValue(), 5);
    }

    @Test
    public void split(){
        String str = "10 20 30";
        String[] split = str.split(" ");
        Assert.assertEquals(split[0], String.valueOf(10));
    }
}