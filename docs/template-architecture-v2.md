# 📦 프로젝트 구조 지시사항

패키지 기본 경로는 `com.noaats.template`로 고정한다.  
각 도메인은 `com.noaats.template.{domain}` 하위에 4계층 구조로 나누어 생성한다.

---

## 1️⃣ Presentation Layer

- 위치: `com.noaats.template.{domain}.presentation`
- 포함 요소:
    - `{Domain}Api` interface
        - Swagger 어노테이션 포함 (`@Tag`, `@Operation`)
        - 각 엔드포인트에 대한 명세만 정의
        - 기본 제공 CRUD API:
            - `GET /api/v2/{domain}s/{id}`: 단건 조회
            - `GET /api/v2/{domain}s`: 전체 목록 조회
            - `POST /api/v2/{domain}s`: 등록
            - `PUT /api/v2/{domain}s/{id}`: 수정
            - `DELETE /api/v2/{domain}s/{id}`: 삭제
    - `{Domain}Controller` class
        - `@RestController`, `@RequestMapping("/api/v2/{domain}s")`
        - `{Domain}Api` 구현
        - `ResponseEntity` 기반 응답 처리
        - CRUD 로직은 Service 계층에 위임
    - DTO 클래스 포함 가능:
        - `record {Domain}Request(...)`
        - `record {Domain}Response(...)`

---

## 2️⃣ Domain Layer

- 위치: `com.noaats.template.{domain}.domain`
- 포함 요소:
    - Aggregate Root: `{Domain}`
        - 비즈니스 로직을 메서드로 구현
        - JPA 또는 외부 기술에 의존하지 않는 POJO
    - VO 또는 하위 엔티티
    - Repository 인터페이스: `{Domain}Repository`
        - 저장/조회/수정 등의 도메인 중심 메서드 정의
    - 도메인 이벤트 클래스: `XxxCreatedEvent`, `XxxUpdatedEvent` 등
    - 예외 클래스: `InvalidXxxException` 등

---

## 3️⃣ Application Layer

- 위치: `com.noaats.template.{domain}.application`
- 포함 요소:
    - `{Domain}Service` 클래스
        - 도메인 객체들을 조합하여 실제 Use Case 구현
        - 외부 요청 데이터를 받아 비즈니스 로직 실행 및 가공된 결과 반환
        - 기본 제공 메서드:
            - `findById(...)`
            - `findAll()`
            - `create(...)`
            - `update(...)`
            - `delete(...)`
    - 필요 시 `@Transactional` 사용
    - 이벤트 핸들러, 커맨드 핸들러 등도 포함 가능

---

## 4️⃣ Infrastructure Layer

- 위치: `com.noaats.template.{domain}.infrastructure`
- 세부 구성:
    - `persistence`: DB 연동 관련 구성
        - JPA Entity: `{Domain}JpaEntity`
        - Repository 구현체: `{Domain}JpaRepository`
            - Spring Data JPA interface 상속
        - Mapper 클래스:
            - Domain ↔ JPA Entity 간 변환 책임
    - ID 전략:
        - 도메인 객체 생성 시 TSID 또는 UUID로 식별자 할당
        - JPA Entity에서는 `@Id`만 명시하고, 생성자 주입으로 ID를 설정

---

# 🚦 생성 시 기본 규칙

- 각 계층은 단방향 참조만 허용한다.
    - `presentation → application → domain`
- 외부 기술은 domain 계층에서 절대 사용하지 않는다.
- 도메인 객체는 단위 테스트를 위한 POJO 구조로 유지한다.
- Service와 Controller는 API의 책임만 갖도록 분리한다.
- ID는 가능한 도메인 객체 생성 시점에 할당한다 (예: TSID 생성기 사용).

