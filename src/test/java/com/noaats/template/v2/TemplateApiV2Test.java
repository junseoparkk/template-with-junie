package com.noaats.template.v2;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noaats.template.v2.application.TemplateServiceV2;
import com.noaats.template.v2.domain.TemplateV2;
import com.noaats.template.v2.presentation.TemplateApiV2.TemplateRequest;
import com.noaats.template.v2.presentation.TemplateControllerV2;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TemplateControllerV2.class)
@DisplayName("TemplateApiV2 API 테스트")
class TemplateApiV2Test {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  TemplateServiceV2 templateServiceV2;

  ObjectMapper objectMapper = new ObjectMapper();

  @Test
  @DisplayName("GET /api/v2/templates/{id} 호출 시 템플릿이 반환되어야 한다")
  void shouldReturnTemplate_whenGetTemplateById() throws Exception {
    // given
    TemplateV2 stub = new TemplateV2("fixed-id", "Title", "Body");
    given(templateServiceV2.findById("fixed-id")).willReturn(stub);

    // when & then
    mockMvc.perform(get("/api/v2/templates/{id}", "fixed-id"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("fixed-id"))
        .andExpect(jsonPath("$.name").value("Title"))
        .andExpect(jsonPath("$.content").value("Body"));
  }

  @Test
  @DisplayName("GET /api/v2/templates 호출 시 전체 템플릿 목록이 반환되어야 한다")
  void shouldReturnTemplateList_whenGetAllTemplates() throws Exception {
    // given
    List<TemplateV2> stubs = List.of(
        new TemplateV2("id1", "A", "AA"),
        new TemplateV2("id2", "B", "BB")
    );
    given(templateServiceV2.findAll()).willReturn(stubs);

    // when & then
    mockMvc.perform(get("/api/v2/templates"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].id").value("id1"))
        .andExpect(jsonPath("$[1].id").value("id2"));
  }

  @Test
  @DisplayName("POST /api/v2/templates 호출 시 템플릿이 생성되어야 한다")
  void shouldCreateTemplate_whenPostTemplate() throws Exception {
    // given
    TemplateRequest req = new TemplateRequest("New", "Content");
    TemplateV2 saved = new TemplateV2("new-id", "New", "Content");
    given(templateServiceV2.create("New", "Content")).willReturn(saved);

    // when & then
    mockMvc.perform(post("/api/v2/templates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(req)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("new-id"))
        .andExpect(jsonPath("$.name").value("New"))
        .andExpect(jsonPath("$.content").value("Content"));
  }

  @Test
  @DisplayName("PUT /api/v2/templates/{id} 호출 시 템플릿이 수정되어야 한다")
  void shouldUpdateTemplate_whenPutTemplate() throws Exception {
    // given
    TemplateRequest req = new TemplateRequest("Updated", "UpdatedContent");
    TemplateV2 updated = new TemplateV2("upd-id", "Updated", "UpdatedContent");
    given(templateServiceV2.update("upd-id", "Updated", "UpdatedContent")).willReturn(updated);

    // when & then
    mockMvc.perform(put("/api/v2/templates/{id}", "upd-id")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(req)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("upd-id"))
        .andExpect(jsonPath("$.name").value("Updated"))
        .andExpect(jsonPath("$.content").value("UpdatedContent"));
  }

  @Test
  @DisplayName("DELETE /api/v2/templates/{id} 호출 시 204 No Content 응답이 반환되어야 한다")
  void shouldReturnNoContent_whenDeleteTemplate() throws Exception {
    // given
    doNothing().when(templateServiceV2).delete("del-id");

    // when & then
    mockMvc.perform(delete("/api/v2/templates/{id}", "del-id"))
        .andExpect(status().isNoContent());
  }
}
