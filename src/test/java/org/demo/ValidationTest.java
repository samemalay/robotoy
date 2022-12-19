package org.demo;

import org.demo.validations.Validate;
import org.junit.Test;

import static org.demo.validations.Validate.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidationTest {

    @Test
    public void validateCommandTest() {
        assertFalse(getCommand("ABCD").isPresent());
        assertTrue(getCommand("PLACE 0,0,NORTH").isPresent());
        assertFalse(getCommand("PLACE 6,8,NORTH").isPresent());
    }
}
