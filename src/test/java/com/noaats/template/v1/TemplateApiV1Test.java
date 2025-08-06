package com.noaats.template.v1;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noaats.template.v1.application.TemplateServiceV1;
import com.noaats.template.v1.domain.TemplateV1;
import com.noaats.template.v1.presentation.TemplateControllerV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TemplateControllerV1.class)
@DisplayName("TemplateApiV1 API 테스트")
class TemplateApiV1Test {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  TemplateServiceV1 templateService;

  ObjectMapper objectMapper = new ObjectMapper();

  @Test
  @DisplayName("GET /api/v1/templates/{id} 호출 시 템플릿이 반환되어야 한다")
  void shouldReturnTemplate_whenGetTemplateById() throws Exception {
    // given
    TemplateV1 stub = new TemplateV1("fixed-id", "Title", "Body");
    given(templateService.getTemplate("fixed-id")).willReturn(stub);

    // when & then
    mockMvc.perform(get("/api/v1/templates/{id}", "fixed-id")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("fixed-id"))
        .andExpect(jsonPath("$.name").value("Title"))
        .andExpect(jsonPath("$.content").value("Body"));
  }
}
