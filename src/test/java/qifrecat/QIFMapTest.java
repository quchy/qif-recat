package qifrecat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Created by zskucsak on 15/07/2017.
 * Testing Map handling
 */
public class QIFMapTest {


    @org.junit.Test(expected = IOException.class)
    public void LoadTestJson_InvalidFile() throws Exception {

        String  args[]  = {"Invalid", "Invalid"};
        MappingCtrl ctrl = new MappingCtrl(args);

        ctrl.populateMap();

//      assert (false);
    }


    @org.junit.Test
    public void LoadTestJson() {

        try { Files.delete(Paths.get("/src/test/resources/Test_RC.qif")); } catch(Exception e) {}

        String  args[] = {"not needed", "src/test/resources/QIFTestMap.json"};
        MappingCtrl ctrl = new MappingCtrl(args);

        try {
            ctrl.populateMap();
        } catch (IOException e) {
            assertNull (e);
        }

        assert (ctrl.MappedEntries.KeyEntries.size() > 0);
        assertNotNull(Paths.get("/src/test/resources/Test_RC.qif") );
    }

}