package mapper;

import base.BaseTest;
import com.hjl.base.utils.DateUtil;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * Created by hjl on 2017/1/18.
 */
public class MapperTest extends BaseTest {

	private static Logger logger = Logger.getLogger(MapperTest.class);

	

	@Test
	public void testActiveLoverMapper() throws IOException {
		Date time = DateUtil.getAfterDay(new Date() , -10);
	}
}
