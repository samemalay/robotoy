package org.demo;

import org.demo.utils.FileUtil;
import org.junit.Test;

import java.util.List;

import static org.demo.Resource.*;
import static org.junit.Assert.*;

public class FileUtilTest {


    @Test
    public void readEmptyFileTest() {
        assertEquals(0, FileUtil.readLines(emptyFile).get().size());
        List<String> lines = FileUtil.readLines(commandFile1).get();
        assertEquals("MOVE", lines.get(2));
    }
}
