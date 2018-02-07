package com.my.pipl;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class PiplClientTest {

    PiplClient p = new PiplClient("alex.radka@gmail.com", "Alex", "Radka","");

    @Test
    public void searchInformation() {
    }

    @Test
    public void testWriteToFile_valid() {
        try {
            p.writeToFile("./out", p.getFirstName()+"_"+p.getLastName(),"The sample text");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File f = new File("./out/"+ p.getFirstName()+"_"+p.getLastName());
        assertTrue(f.exists());
    }
}