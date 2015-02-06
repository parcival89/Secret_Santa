package com.sander.secret_santa;

import com.sander.secret_santa.controller.persoon.PersoonControllerTest;
import com.sander.secret_santa.domain.persoon.PersoonTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.runners.Suite.*;

/**
 * Created by SanderP on 2/02/2015.
 */
@RunWith(Suite.class)
@SuiteClasses({PersoonTest.class,
        PersoonControllerTest.class})
public class JUnitTests {
}
