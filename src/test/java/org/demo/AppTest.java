package org.demo;

import static org.demo.Resource.commandFile1;
import static org.demo.Resource.commandFile2;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppTest 
{

    @Test
    public void runTestSingle()
    {
        App.run(commandFile1);
        assertEquals("Output: 0,2,NORTH", App.getEndReport());
    }

}
