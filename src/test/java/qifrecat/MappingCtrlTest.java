package qifrecat;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.IOException;

import static org.junit.Assert.assertNull;

/**
 * Created by zskucsak on 15/07/2017.
 */
public class MappingCtrlTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();


    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }


    @org.junit.Test
    public void createControllerNoArgs() {
        exit.expectSystemExit();
        String[]  args = new String[0];
        MappingCtrl ctrl = new MappingCtrl(args);
        assert (false);
    }


    @org.junit.Test
    public void createControllerOneArg() {
        exit.expectSystemExit();
        String[]  args = new String[1];
        MappingCtrl ctrl = new MappingCtrl(args);
        assert (false);
    }

    @org.junit.Test
    public void createControllerTwoArgs() {
        String[]  args = new String[2];
        MappingCtrl ctrl = new MappingCtrl(args);
        assert (true);
    }


    @org.junit.Test
    public void createController3Args() {
        exit.expectSystemExit();
        String[]  args = new String[3];
        MappingCtrl ctrl = new MappingCtrl(args);
        assert (false);
    }


    @org.junit.Test
    public void run() throws Exception {

        String  args[] = {"src/test/resources/Test.qif", "src/test/resources/QIFTestMap.json"};
        MappingCtrl ctrl = new MappingCtrl(args);


        ctrl.run();

        assert (ctrl.MappedEntries.KeyEntries.size() > 0);

    }

}