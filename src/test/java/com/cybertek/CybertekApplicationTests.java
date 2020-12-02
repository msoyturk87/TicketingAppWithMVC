package com.cybertek;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class CybertekApplicationTests {

    @Test
    void name() {
        System.out.println("UUID.randomUUID().getMostSignificantBits() = " + UUID.randomUUID().getMostSignificantBits());
        System.out.println("UUID.randomUUID().toString() = " + UUID.randomUUID().toString());


    }

}
