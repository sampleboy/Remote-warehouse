package com.heima.maven;

import org.junit.jupiter.api.Test;

public class HelloTest {
    @Test
    public void testHello() {
        Hello hello = new Hello();
        String maven = hello.sayHello("Maven");
        System.out.println(maven);
    }

}
