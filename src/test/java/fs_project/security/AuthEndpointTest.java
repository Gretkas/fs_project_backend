package fs_project.security;

import fs_project.FsProjectApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Fra systemutvikling 2
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = MOCK, classes = FsProjectApplication.class)
@AutoConfigureMockMvc

public class AuthEndpointTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(username = "admin",password = "admin")
    public void TestEndpointWithCredentials() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/auth");
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void TestEndpointWithoutCredentials() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/auth");
        mvc.perform(request)
                .andExpect(status().is(401));
    }
}
