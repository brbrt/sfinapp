package hu.brbrt.healthcheck;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HealthCheckTest {

	@Autowired
	private HealthCheckController healthCheckController;

	@Test
	public void contextLoads() {
		String result = healthCheckController.healthcheck();
		assertThat(result, is("Sfinapp service is available!"));
	}

}
