package com.noaats.template.v2.presentation;

import com.noaats.template.v2.application.TemplateServiceV2;
import com.noaats.template.v2.domain.TemplateV2;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ui/v2/templates")
public class TemplateViewControllerV2 {

  private final TemplateServiceV2 templateService;

  public TemplateViewControllerV2(TemplateServiceV2 templateService) {
    this.templateService = templateService;
  }

  /* 목록 + 작성 폼 */
  @GetMapping
  public String list(Model model) {
    List<TemplateV2> all = templateService.findAll();
    model.addAttribute("templates", all); // ✅ 반드시 이 라인이 있어야 함
    model.addAttribute("form", new TemplateForm("", ""));
    return "template-v2";
  }

  /* 생성 */
  @PostMapping
  public String create(
      @Validated @ModelAttribute("form") TemplateForm form,
      RedirectAttributes redirect
  ) {
    templateService.create(form.name(), form.content());
    redirect.addFlashAttribute("successMessage", "데이터 저장 완료!");
    return "redirect:/ui/v2/templates";
  }

  /* 수정 */
  @PostMapping("/{id}/edit")
  public String update(@PathVariable String id,
      @Validated @ModelAttribute TemplateForm form) {
    templateService.update(id, form.name(), form.content());
    return "redirect:/ui/v2/templates";
  }

  /* 삭제 */
  @PostMapping("/{id}/delete")
  public String delete(@PathVariable String id) {
    templateService.delete(id);
    return "redirect:/ui/v2/templates";
  }
}
