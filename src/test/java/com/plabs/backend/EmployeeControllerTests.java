package com.plabs.backend;

import com.plabs.backend.model.EmployeeModel;
import com.plabs.backend.payload.JwtAuthenticationResponse;
import com.plabs.backend.payload.LoginRequestModel;
import com.plabs.backend.repository.CompanyRepository;
import com.plabs.backend.service.AuthService;
import com.plabs.backend.service.CompanyService;
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
public class EmployeeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AuthService authService;

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void deveCriarUmFuncionario() throws Exception {

        EmployeeModel employee = new EmployeeModel();
        employee.setName("Teste 1");
        employee.setCpf("000.000.000-11");
        employee.setEmail("teste@gmail.com");
        employee.setJobRole("Dev");
        employee.setSalary(10000F);
        employee.setCompany(companyRepository.findByCnpj("111222333444-52"));
        employee.setPermission("USER");


        mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", getToken())
                .content(new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(employee)))
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
