package todolist;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"database-test", "test"})
class TodolistSpringApplicationTests {

    @Test
    void contextLoads() {
    }

}
