package com.noaats.template.v1;

import static org.assertj.core.api.Assertions.assertThat;

import com.noaats.template.v1.domain.TemplateV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("TemplateV1 도메인 단위 테스트")
class TemplateV1Test {

  @Test
  @DisplayName("이름과 내용으로 생성 시 템플릿이 정상적으로 초기화되어야 한다")
  void shouldInitializeTemplate_whenCreateWithNameAndContent() {
    // given
    String name = "Sample";
    String content = "Hello";

    // when
    TemplateV1 template = new TemplateV1(name, content);

    // then
    assertThat(template.getId()).isNotBlank();
    assertThat(template.getName()).isEqualTo(name);
    assertThat(template.getContent()).isEqualTo(content);
  }

  @Test
  @DisplayName("기존 ID·이름·내용으로 로드 시 필드 값이 유지되어야 한다")
  void shouldKeepFields_whenLoadExistingTemplate() {
    // given
    String id = "fixed-id";
    String name = "Old";
    String content = "Legacy";

    // when
    TemplateV1 template = new TemplateV1(id, name, content);

    // then
    assertThat(template.getId()).isEqualTo(id);
    assertThat(template.getName()).isEqualTo(name);
    assertThat(template.getContent()).isEqualTo(content);
  }

  @Test
  @DisplayName("changeName 호출 시 이름이 변경되어야 한다")
  void shouldChangeName_whenCallChangeName() {
    // given
    TemplateV1 template = new TemplateV1("A", "B");

    // when
    template.changeName("NewName");

    // then
    assertThat(template.getName()).isEqualTo("NewName");
  }

  @Test
  @DisplayName("changeContent 호출 시 내용이 변경되어야 한다")
  void shouldChangeContent_whenCallChangeContent() {
    // given
    TemplateV1 template = new TemplateV1("A", "B");

    // when
    template.changeContent("NewContent");

    // then
    assertThat(template.getContent()).isEqualTo("NewContent");
  }
}
