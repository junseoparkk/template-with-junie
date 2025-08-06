# 📦 프로젝트 구조 지시사항 (v3)

패키지 기본 경로는 `com.noaats.template`로 고정하며, 각 도메인은 `com.noaats.template.{domain}.v3` 하위에 생성한다.

> ⚠️ 모든 클래스명 끝에 반드시 `V3`를 붙인다. (예: TemplateControllerV3, TemplateServiceV3)

---

## 1️⃣ Presentation Layer

- 위치: `com.noaats.template.{domain}.v3.presentation`
- 포함 요소:
    - `{Domain}ApiV3` interface
        - Swagger 명세 포함 (`@Tag`, `@Operation`)
        - 기본 CRUD 명세 정의
            - `POST /` create
            - `GET /{id}` get
            - `PUT /{id}` update
            - `DELETE /{id}` delete
            - `GET /` list
    - `{Domain}ControllerV3` class
        - `@RestController`, `@RequestMapping("/api/v3/{domain}s")`
        - `{Domain}ApiV3` 구현
        - 응답은 `ResponseEntity` 형태
    - Request/Response DTO 클래스 정의 (record 형태 가능)

---

## 2️⃣ Domain Layer

- 위치: `com.noaats.template.{domain}.v3.domain`
- 포함 요소:
    - `{Domain}V3` 클래스 (Aggregate Root)
        - 필드는 VO(Value Object)로 감싼다
        - ID는 `UUID` 타입
        - 비즈니스 로직 포함 (`update`, `validate` 등)
    - VO 클래스: `Xxx` (예: `TemplateName`, `TemplateContent`)
        - 불변 객체로 정의, `record` 또는 final 클래스
        - 생성 시 유효성 검사 수행
    - Repository 인터페이스: `{Domain}RepositoryV3`
        - 핵심 도메인 중심 메서드 정의
    - 예외 클래스 및 도메인 이벤트 클래스

---

## 3️⃣ Application Layer

- 위치: `com.noaats.template.{domain}.v3.application`
- 포함 요소:
    - `{Domain}ServiceV3` 클래스
        - 도메인 객체 조합 및 유스케이스 구현
        - 비즈니스 로직 실행 및 가공
        - `@RequiredArgsConstructor` 사용
        - `@Transactional` 필요 시 적용
    - 이벤트 리스너, 커맨드 핸들러 등 포함 가능

---

## 4️⃣ Infrastructure Layer

- 위치: `com.noaats.template.{domain}.v3.infrastructure`
- 세부 구성:
    - `persistence`:
        - JPA Entity: `{Domain}JpaEntityV3`
            - 필드는 기본 타입 사용 (`String`, `UUID`)
            - 생성자는 `@NoArgsConstructor(PROTECTED)`와 `@Builder`로 구성
        - Repository 구현체: `{Domain}JpaRepositoryV3`
            - Spring Data JPA interface extends
        - Mapper 클래스: `{Domain}MapperV3`
            - Domain <-> Entity 변환
            - 모든 메서드는 `static`으로 정의

---

## 🔄 중복 처리 규칙

- 이미 존재하는 클래스나 메서드가 있는 경우:
    - 새로운 버전에 맞게 **수정, 대체 또는 삭제**한다.
    - 불필요한 중복 코드는 제거하고 최신 사양을 따른다.

---

## 🔗 클래스 생성 예시

### 도메인 클래스
```java
public class TemplateV3 {
    private final UUID id;
    private TemplateName name;
    private TemplateContent content;

    public TemplateV3(TemplateName name, TemplateContent content) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.content = content;
    }

    public void update(TemplateName name, TemplateContent content) {
        this.name = name;
        this.content = content;
    }
}
