package com.teamonline;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Using the spring framework runner, it is possible to do test vs a functioning runtime. This is very powerful as,
 * as we can do all dependency injection within the scope of the tests.
 * @author Johannes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SuperSimpleLoginApp.class)
public abstract class BaseSpringTest {
	
}
