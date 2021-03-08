package com.evollo.fullstack;


import com.evollo.fullstack.model.CompanyModel;
import com.evollo.fullstack.payload.JwtAuthenticationResponse;
import com.evollo.fullstack.payload.LoginRequestModel;
import com.evollo.fullstack.service.AuthService;
import com.evollo.fullstack.service.CompanyService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CompanyControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AuthService authService;

    @Test
    public void deveCriarUmaEmpresa() throws Exception {

        CompanyModel companyModel = new CompanyModel();
        companyModel.setCompanyName("TESTE EMPRESA");
        companyModel.setCnpj("64564564564");
        companyModel.setCity("RIO TESTE");
        companyModel.setState("PARAIBA");
        companyModel.setAddress("Rua Teste");



        mockMvc.perform(MockMvcRequestBuilders.post("/company")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", getToken())
                .content(new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(companyModel)))
                .andExpect(status().isCreated());

    }

    private String getToken() {
        LoginRequestModel loginRequestModel = new LoginRequestModel();
        loginRequestModel.setUsername("admin");
        loginRequestModel.setPassword("123");
        JwtAuthenticationResponse token = this.authService.authenticateUser(loginRequestModel);
        return token.getTokenType() + " " + token.getAccessToken();
    }
}
