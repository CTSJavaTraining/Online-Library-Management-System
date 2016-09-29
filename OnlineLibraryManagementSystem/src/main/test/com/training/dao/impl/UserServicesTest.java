/**
 * 
 */
package training.dao.impl;

import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.training.blayer.UserSignupDTO;
import com.training.entity.UserDetails;
import com.training.restservices.UserServices;

/**
 * @author 559207
 *
 */
public class UserServicesTest {

	@Mock
	UserServices userServices;
	@Mock
	UserDetails userdetails;
	
	@Mock
	UserSignupDTO userSignupDTO;
	
	@Before
	public void create()
	{
		MockitoAnnotations.initMocks(this);
//		userServices = mock(UserServices.class);
	//	UserDetails userdetails = mock(UserDetails.class);
		when(userServices.userNameExistance(userSignupDTO)).thenThrow(NumberFormatException.class);
	}
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
	}

}
