# 📦 프로젝트 구조 지시사항

패키지 기본 경로는 `com.noaats.template`로 고정한다.  
각 도메인은 `com.noaats.template.{domain}` 하위에 4계층 구조로 나누어 생성한다.

## 1️⃣ Presentation Layer

- 위치: `com.noaats.template.{domain}.presentation`
- 포함 요소:
    - `{Domain}Api` interface
        - Swagger 어노테이션 포함 (`@Tag`, `@Operation`)
        - 각 엔드포인트에 대한 명세만 정의
    - `{Domain}Controller` class
        - `@RestController`, `@RequestMapping("/api/v1/{domain}s")`
        - `{Domain}Api` 구현
        - `ResponseEntity` 기반 응답 처리
    - Record DTO (`Response`, `Request`) 클래스 포함 가능

## 2️⃣ Domain Layer

- 위치: `com.noaats.template.{domain}.domain`
- 포함 요소:
    - Aggregate Root: `{Domain}`
        - 비즈니스 로직을 메서드로 구현
        - JPA 또는 외부 기술에 의존하지 않는 POJO
    - VO 또는 하위 엔티티
    - Repository 인터페이스: `{Domain}Repository`
        - 저장/조회/수정 등의 도메인 중심 메서드 정의
    - 도메인 이벤트 클래스: `xxxCreatedEvent` 등
    - 예외 클래스: `InvalidXxxException` 등

## 3️⃣ Application Layer

- 위치: `com.noaats.template.{domain}.application`
- 포함 요소:
    - `{Domain}Service` 클래스
        - 도메인 객체들을 조합하여 실제 Use Case 구현
        - 외부 요청 데이터를 받아 비즈니스 로직 실행 및 가공된 결과 반환
    - 필요 시 `@Transactional` 사용
    - 이벤트 핸들러, 커맨드 핸들러 등도 포함 가능

## 4️⃣ Infrastructure Layer

- 위치: `com.noaats.template.{domain}.infrastructure`
- 세부 구성:
    - `persistence`: DB 연동 관련 구성
        - JPA Entity: `{Domain}JpaEntity`
        - Repository 구현체: `{Domain}JpaRepository`
            - Spring Data JPA interface extends
        - Mapper: Domain ↔ JPA Entity 변환
    - ID 전략:
        - 기본적으로 도메인 객체 생성 시 TSID 또는 UUID를 활용해 ID를 할당
        - JPA Entity는 @Id만 명시하고 생성자 주입으로 ID 설정

---

# 🚦 생성 시 기본 규칙

- 각 계층은 단방향 참조만 허용한다.
    - `presentation → application → domain`
- 외부 기술은 domain 계층에서 절대 사용하지 않는다.
- 도메인 객체는 단위 테스트를 위한 POJO 구조로 유지한다.
- Service와 Controller는 API의 책임만 갖도록 분리한다.
- ID는 가능한 도메인 객체 생성 시점에 할당한다 (예: TSID 생성기 사용).

