package com.plabs.backend;

import com.plabs.backend.payload.JwtAuthenticationResponse;
import com.plabs.backend.payload.LoginRequestModel;
import com.plabs.backend.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AuthControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthService authService;

    @Test
    public void deveAutenticarUserAdmin() throws Exception {

        //user default
        LoginRequestModel loginRequestModel = new LoginRequestModel();
        loginRequestModel.setUsername("admin");
        loginRequestModel.setPassword("123");

        JwtAuthenticationResponse token = this.authService.authenticateUser(loginRequestModel);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(loginRequestModel)))
                .andExpect(status().isOk());

    }
}
