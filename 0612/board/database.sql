-- 기존 테이블이 있다면 삭제
DROP TABLE IF EXISTS tbl_board;

-- 게시판 테이블 생성
CREATE TABLE tbl_board
(
    no          INTEGER AUTO_INCREMENT PRIMARY KEY, -- 게시글 번호 (자동증가)
    title       VARCHAR(200) NOT NULL,              -- 게시글 제목
    content     TEXT,                               -- 게시글 내용
    writer      VARCHAR(50)  NOT NULL,              -- 작성자
    reg_date    DATETIME DEFAULT CURRENT_TIMESTAMP, -- 등록일시
    update_date DATETIME DEFAULT CURRENT_TIMESTAMP  -- 수정일시
);

-- 테스트 데이터 삽입
INSERT INTO tbl_board(title, content, writer)
VALUES ('테스트 제목1', '테스트 내용1', 'user00'),
       ('테스트 제목2', '테스트 내용2', 'user00'),
       ('테스트 제목3', '테스트 내용3', 'user00'),
       ('테스트 제목4', '테스트 내용4', 'user00'),
       ('테스트 제목5', '테스트 내용5', 'user00');

-- 데이터 확인
SELECT *
FROM tbl_board;