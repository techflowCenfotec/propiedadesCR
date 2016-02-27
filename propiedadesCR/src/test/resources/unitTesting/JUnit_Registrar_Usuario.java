package unitTesting;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.techflow.propiedadesCR.contracts.UsersRequest;
import com.techflow.propiedadesCR.controllers.UsersController;
import com.techflow.propiedadesCR.pojo.UserPOJO;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration("log4j.xml")
public class JUnit_Registrar_Usuario {


    @InjectMocks
    UsersController controllerUnderTest;

	@Test
	public void test() throws Exception {
			
		
		UsersRequest request = new UsersRequest();
		UserPOJO user = new UserPOJO();
		request.setPageSize(0);
		request.setPageNumber(0);
		request.setDirection("string");
		request.setSortBy(Arrays.asList("string"));
		request.setSearchColumn("string");
		request.setSearchTerm("string");
		request.setUser(user);
		
		assertNotNull(controllerUnderTest.getAll(request));
		
	
		
	}

}
