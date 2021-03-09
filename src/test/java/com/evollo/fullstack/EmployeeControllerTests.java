package com.evollo.fullstack;

import com.evollo.fullstack.model.CompanyModel;
import com.evollo.fullstack.model.EmployeeModel;
import com.evollo.fullstack.payload.JwtAuthenticationResponse;
import com.evollo.fullstack.payload.LoginRequestModel;
import com.evollo.fullstack.repository.CompanyRepository;
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
