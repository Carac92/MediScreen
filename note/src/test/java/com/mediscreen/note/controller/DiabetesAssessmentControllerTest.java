package com.mediscreen.note.controller;

import com.mediscreen.note.constant.Assessment;
import com.mediscreen.note.service.DiabetesAssessmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DiabetesAssessmentController.class)
public class DiabetesAssessmentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DiabetesAssessmentService diabetesAssessmentService;

    @Test
    public void getAssessment_shouldReturnAssessment() throws Exception {
        given(diabetesAssessmentService.getAssessment("1")).willReturn(Assessment.IN_DANGER);

        mockMvc.perform(get("/diabetes/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("In Danger"));
    }
}
