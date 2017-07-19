package qifrecat;

import org.junit.Rule;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertNotNull;

/**
 * Created by zskucsak on 15/07/2017.
 */
public class MappingCtrlTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();


    @org.junit.Test
    public void createControllerNoArgs() {
        exit.expectSystemExit();
        String[]  args = new String[0];
        MappingCtrl ctrl = new MappingCtrl(args);
        assertNull(ctrl);
    }


    @org.junit.Test
    public void createControllerOneArg() {
        exit.expectSystemExit();
        String[]  args = new String[1];
        MappingCtrl ctrl = new MappingCtrl(args);
        assertNull(ctrl);
    }

    @org.junit.Test
    public void createControllerTwoArgs() {
        String[]  args = new String[2];
        MappingCtrl ctrl = new MappingCtrl(args);
        assertNotNull(ctrl);
    }


    @org.junit.Test
    public void createController3Args() {
        exit.expectSystemExit();
        String[]  args = new String[3];
        MappingCtrl ctrl = new MappingCtrl(args);
        assertNull(ctrl);
    }


    @org.junit.Test
    public void run() throws Exception {

        String  args[] = {"src/test/resources/Test.qif", "src/test/resources/QIFTestMap.json"};
        MappingCtrl ctrl = new MappingCtrl(args);

        ctrl.run();

        assert (ctrl.MappedEntries.KeyEntries.size() > 0);
    }

}