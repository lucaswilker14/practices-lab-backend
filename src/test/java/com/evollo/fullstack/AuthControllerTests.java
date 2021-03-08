package com.evollo.fullstack;

import com.evollo.fullstack.payload.JwtAuthenticationResponse;
import com.evollo.fullstack.payload.LoginRequestModel;
import com.evollo.fullstack.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONUtil;
import org.apache.catalina.Authenticator;
import org.h2.util.geometry.GeoJsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
