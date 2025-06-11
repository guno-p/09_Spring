DROP TABLE todo;

CREATE TABLE todo
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(255) NOT NULL COMMENT '할 일 제목',
    description TEXT COMMENT '할 일 상세 설명',
    done        BOOLEAN   DEFAULT FALSE COMMENT '완료 여부'
);

-- 샘플 데이터 삽입
INSERT INTO todo (title, description, done)
VALUES ('Spring Framework 학습', 'Spring Framework 기본 개념과 실습', false),
       ('MyBatis 연동', 'MyBatis 연동 실습', false),
       ('TODO 앱 완성', '기본적인 CRUD 기능 구현', false),
       ('코드 리뷰', '작성한 코드 검토 및 개선', true);


SELECT * FROM todo;