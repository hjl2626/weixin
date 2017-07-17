package base;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hjl on 2016/12/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-main.xml","classpath:spring/spring-servlet.xml"})
@Rollback
@Transactional(transactionManager = "transactionManager")
public class BaseTest extends AbstractJUnit4SpringContextTests {

		@Autowired
		protected ApplicationContext ctx;


		@BeforeClass
		public static void init() {

		}

		@Before
		public void setUp() {

		}

		@Test
		public void commonTest() {

		}
}
