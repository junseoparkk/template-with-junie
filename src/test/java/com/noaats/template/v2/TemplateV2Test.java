package com.noaats.template.v2;

import static org.assertj.core.api.Assertions.assertThat;

import com.noaats.template.v2.domain.TemplateV2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("TemplateV2 도메인 단위 테스트")
class TemplateV2Test {

  /* ---------- 생성 & 로드 ---------- */

  @Test
  @DisplayName("이름과 내용으로 생성 시 유효한 템플릿이어야 한다")
  void shouldBeValid_whenCreateWithNameAndContent() {
    // given
    String name = "Sample";
    String content = "Hello";

    // when
    TemplateV2 template = new TemplateV2(name, content);

    // then
    assertThat(template.isValid()).isTrue();
  }

  @Test
  @DisplayName("기존 ID, 이름, 내용으로 로드 시 유효한 템플릿이어야 한다")
  void shouldBeValid_whenLoadExistingTemplate() {
    // given
    String id = "fixed-id";
    String name = "Old";
    String content = "Legacy";

    // when
    TemplateV2 template = new TemplateV2(id, name, content);

    // then
    assertThat(template.isValid()).isTrue();
  }

  /* ---------- 이름 & 내용 변경 ---------- */

  @Test
  @DisplayName("changeName 호출 시 이름이 변경되어야 한다")
  void shouldChangeName_whenCallChangeName() {
    // given
    TemplateV2 template = new TemplateV2("A", "B");

    // when
    template.changeName("NewName");

    // then
    assertThat(template.getName()).isEqualTo("NewName");
  }

  @Test
  @DisplayName("changeContent 호출 시 내용이 변경되어야 한다")
  void shouldChangeContent_whenCallChangeContent() {
    // given
    TemplateV2 template = new TemplateV2("A", "B");

    // when
    template.changeContent("NewContent");

    // then
    assertThat(template.getContent()).isEqualTo("NewContent");
  }

  @Test
  @DisplayName("update 호출 시 이름과 내용이 모두 변경되어야 한다")
  void shouldUpdateBothFields_whenCallUpdate() {
    // given
    TemplateV2 template = new TemplateV2("A", "B");

    // when
    template.update("AA", "BB");

    // then
    assertThat(template.getName()).isEqualTo("AA");
    assertThat(template.getContent()).isEqualTo("BB");
  }

  /* ---------- 유효성 검사 ---------- */

  @Test
  @DisplayName("이름이 공백이면 isValid는 false를 반환해야 한다")
  void shouldReturnFalse_whenNameIsBlank() {
    // given
    TemplateV2 template = new TemplateV2(" ", "content");

    // when
    boolean valid = template.isValid();

    // then
    assertThat(valid).isFalse();
  }

  @Test
  @DisplayName("내용이 null이면 isValid는 false를 반환해야 한다")
  void shouldReturnFalse_whenContentIsNull() {
    // given
    TemplateV2 template = new TemplateV2("Title", null);

    // when
    boolean valid = template.isValid();

    // then
    assertThat(valid).isFalse();
  }
}
