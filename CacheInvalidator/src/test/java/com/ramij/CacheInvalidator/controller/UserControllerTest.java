package com.ramij.CacheInvalidator.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramij.CacheInvalidator.model.Student;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

@SpringBootTest
//@WebMvcTest(UserController.class)
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    private <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

//    @Test
//    public void getStudentById() throws Exception {
//        String studentJson = "    {\n" +
//                "        \"id\": 5,\n" +
//                "        \"name\": \"ramij roy\",\n" +
//                "        \"email\": \"ramijnalpeerr@gmail.com\",\n" +
//                "        \"course\": \"CHM\",\n" +
//                "        \"regNo\": \"bc48e415-d448-46cb-98be-96dbc9f5ea89\"\n" +
//                "    }";
//
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/student/5"))
//                .andReturn();
//        Student s = mapFromJson(studentJson,Student.class);
//        JSONAssert.assertEquals(result.getResponse().getContentAsString(), mapper.writeValueAsString(s), false);
//    }

    @Test
    public void createStudent() throws Exception {
        Student s = new Student();
        s.setName("ramij");
        s.setCourse("CSE");
        s.setEmail("abc@gmail.com");
        String inputJson = mapToJson(s);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/student/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson)
                )
                .andReturn();
        String str = result.getResponse().getContentAsString();
        JSONAssert.assertNotEquals(result.getResponse().getContentAsString(), new JSONObject(), false);
    }

}
