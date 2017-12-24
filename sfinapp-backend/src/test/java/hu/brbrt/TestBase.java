package hu.brbrt;

import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class TestBase {

    @Autowired
    private Flyway flyway;

    @Before
    public void initBase() {
        flyway.migrate();
    }

    @After
    public void destroyBase() {
        flyway.clean();
    }

}
