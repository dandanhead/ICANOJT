-----------------------------------DROP ---------------------------------------------------------
-----------------------------------DROP ---------------------------------------------------------
-----------------------------------DROP ---------------------------------------------------------
-- 1. ican_member(회원 정보) TABLE
DROP TABLE ICAN_MEMBER CASCADE CONSTRAINTS;
DROP SEQUENCE MEMBER_SEQ;
-- 2.  사원 경력 관리 TABLE
DROP TABLE ICAN_MEM_EXP CASCADE CONSTRAINTS;
-- 3. 사원 자격증 관리 TABLE
DROP TABLE ICAN_MEM_LICENSE CASCADE CONSTRAINTS;
-- 4. ICAN_MEM_SKILL
--DROP TABLE ICAN_MEM_SKILL CASCADE CONSTRAINTS;
-- 5. PROJECT SKILL
--DROP TABLE ICAN_PROJECT_SKILL CASCADE CONSTRAINTS;
-- 6. ICAN_PROJECT_LIST (프로젝트 리스트)
DROP TABLE ICAN_PROJECT_LIST CASCADE CONSTRAINTS;
DROP SEQUENCE PROJECT_LIST_SEQ;
-- 7. ICAN_PROJECT_JOIN_LIST (프로젝트 참여)
DROP TABLE ICAN_PROJECT_JOIN_LIST CASCADE CONSTRAINTS;
DROP SEQUENCE PROJECT_JOIN_SEQ;

SHOW PARAMETER UNDO;

-----------------------------------DROP ---------------------------------------------------------
-----------------------------------DROP ---------------------------------------------------------
-----------------------------CREATE TABLE -----------------------------------------------------------
-- 1. 사원 기본정보 
-------------------------------------1 . ICAN_MEMBER -------------------------------------------------
CREATE TABLE ICAN_MEMBER( 
    IM_IDX NUMBER NOT NULL, --사번(PRIMARY KEY)
    IM_PW VARCHAR2(100) NOT NULL, -- PASSWORD 
    IM_DNAME VARCHAR2(100) NOT NULL, -- 부서명 
	IM_NAME VARCHAR2(50) NOT NULL, -- NAME		
	IM_PHONE VARCHAR2(50) NOT NULL, -- 전화번호	
	IM_EMAIL VARCHAR2(50) NOT NULL, --이메일	
	IM_RESIGN NUMBER NOT NULL, -- 퇴사여부    
    IM_STATUS NUMBER(1) NOT NULL, -- 현재 상태
    IM_SCNUM VARCHAR2(100) NOT NULL, -- 주민등록번호
    IM_ADDRESS VARCHAR2(200) NOT NULL, -- 집주소
    IM_DETAILADDR VARCHAR2(200) NOT NULL, -- 상세주소
    IM_POSTCODE VARCHAR2(200) NOT NULL, -- 우편 번호
    IM_AUTH NUMBER(1) NOT NULL, -- 직책, 직위, 직급 
    IM_SKILL VARCHAR2(400) NOT NULL -- 스킬명
);
ALTER TABLE ICAN_MEMBER ADD CONSTRAINT PK_IM_ID PRIMARY KEY(IM_IDX); -- 사번 seq PRIMARY KEY
ALTER TABLE ICAN_MEMBER ADD CONSTRAINT UK_IM_PHONE UNIQUE (IM_PHONE); -- 전화번호 유니크 키
ALTER TABLE ICAN_MEMBER ADD CONSTRAINT UK_IM_EMAIL UNIQUE (IM_EMAIL); -- 이메일 유니크 키 
ALTER TABLE ICAN_MEMBER ADD CONSTRAINT UK_IM_SCNUM UNIQUE (IM_SCNUM); -- 주민등록 번호 유니크 키

CREATE SEQUENCE MEMBER_SEQ START WITH 1001 INCREMENT BY 1; -- 사번 1001 부터 시작

-- 임의 데이터 넣기
INSERT INTO ICAN_MEMBER(IM_IDX, IM_PW, IM_DNAME, IM_NAME, IM_PHONE, IM_EMAIL, IM_RESIGN, IM_STATUS, IM_SCNUM, IM_ADDRESS,IM_DETAILADDR,IM_POSTCODE, IM_AUTH, IM_SKILL) 
VALUES(MEMBER_SEQ.NEXTVAL, '1111', '관리부','이일동','010-0000-0000', 'AAA1@MAIL.COM', 0, 0, '888888-1000000', '서울시 구로구','상세구로구','232-34',1, 'JAVA');

SELECT * FROM ICAN_MEMBER;
COMMIT;
------------------------------------------------------------------------------------------------------------------
-- 2.  사원 경력 관리 TABLE
-------------------------------------2 . ICAN_MEM_EXP -------------------------------------------------
-- ICAN_MEMBER 에 할당된 SEQ 사용(사번) 
CREATE TABLE ICAN_MEM_EXP(
	IME_IM_IDX NUMBER NOT NULL, -- MEMBER 의 사번 참초 (FOREIGN KEY)
    IME_REGI_DATE DATE NOT NULL, -- 입사일
    IME_EXIT_DATE DATE, -- 퇴사일
    IME_CONAME VARCHAR2(100) NOT NULL, -- 회사명
    IME_AUTH NUMBER(1) NOT NULL, -- 직책
    IME_ROLL VARCHAR2(200) NOT NULL -- 역할
);
ALTER TABLE ICAN_MEM_EXP ADD CONSTRAINT FK_IME_IM_IDX FOREIGN KEY(IME_IM_IDX) REFERENCES ICAN_MEMBER(IM_IDX);
---- 임의의 데이터 받기
INSERT INTO ICAN_MEM_EXP
VALUES(1001, SYSDATE, NULL, '아이캔매니지먼트(주)', 1,'미배정');
-- 3.  사원 자격증 관리 TABLE
-------------------------------------3 . ICAN_MEM_LICENSE -------------------------------------------------
CREATE TABLE ICAN_MEM_LICENSE(

    IML_IM_IDX NUMBER NOT NULL, -- 사번
    IML_LNAME VARCHAR2(200) NOT NULL, -- 자격증 이름
    IML_ACQDATE DATE NOT NULL, -- 취득일
    IML_ORGANIZATION VARCHAR2(100) NOT NULL -- 발급기관
);
ALTER TABLE ICAN_MEM_LICENSE ADD CONSTRAINT FK_IML_IM_IDX FOREIGN KEY (IML_IM_IDX) REFERENCES ICAN_MEMBER(IM_IDX);
INSERT INTO ICAN_MEM_LICENSE(IML_IM_IDX, IML_LNAME , IML_ACUDATE , IML_ORGANIZATION)
VALUES(1001, '정보 처리기사' , SYSDATE , '상공회의소');
-- 5. ICAN_MEM_SKILL
-------------------------------------5. ICAN_MEM_SKILL -------------------------------------------------
--CREATE TABLE ICAN_MEM_SKILL(
--
--    IMS_IS_SNAME VARCHAR2(400) NOT NULL,
--    IMS_IM_IDX NUMBER NOT NULL
--);

--ALTER TABLE ICAN_MEM_SKILL ADD CONSTRAINT FK_IMS_IS_SCODE FOREIGN KEY (IMS_IS_SCODE) REFERENCES ICAN_SKILL(IS_SCODE);
--ALTER TABLE ICAN_MEM_SKILL ADD CONSTRAINT FK_IMS_IM_IDX FOREIGN KEY (IMS_IM_IDX) REFERENCES ICAN_MEMBER(IM_IDX);
-- 6. PROJECT SKILL
-------------------------------------6. ICAN_PROJECT_SKILL -------------------------------------------------
--CREATE TABLE ICAN_PROJECT_SKILL (
--    IPS_IS_SNAME VARCHAR2(100) NOT NULL,
--    IPS_IPL_IDX NUMBER NOT NULL    
--);

--ALTER TABLE ICAN_PROJECT_SKILL ADD CONSTRAINT FK_IPS_IS_SCODE FOREIGN KEY (IPS_IS_SCODE) REFERENCES ICAN_SKILL(IS_SCODE);
-- 7. ICAN_PROJECT_LIST (프로젝트 리스트)
-------------------------------------7. ICAN_PROJECT_LIST -------------------------------------------------
CREATE TABLE ICAN_PROJECT_LIST(
	IPL_IDX NUMBER NOT NULL, -- 프로젝트 번호(PRIMARY KEY)    
	IPL_PANME VARCHAR2(50) NOT NULL, --프로젝트 명
	IPL_SDATE DATE NOT NULL, -- 프로젝트 시작일
	IPL_EPTDATE DATE, -- 프로젝트 예상 종료일
	IPL_EDATE DATE, -- 프로젝트 종료일
	IPL_CONTENT VARCHAR2(400), -- 프로젝트 내용 설명    
	IPL_DOC VARCHAR2(100), -- 프로젝트 문서 파일Name 
	IPL_CHARGE VARCHAR2(50) NOT NULL, -- 책임자
	IPL_CLIENT VARCHAR2(100) NOT NULL, -- 고객사
	IPL_ADDRESS VARCHAR2(100) NOT NULL, -- 프로젝트 서비스 지역
    IPL_DETAILADDR VARCHAR2(200) NOT NULL, -- 서비스 지역 상세 주소
    IPL_POSTCODE VARCHAR2(100) NOT NULL, --서비스 지역 우편번호
    IPL_SKILL VARCHAR2(400) NOT NULL	
); 
ALTER TABLE ICAN_PROJECT_LIST ADD CONSTRAINT PK_IPL_IDX PRIMARY KEY(IPL_IDX);
--ALTER TABLE ICAN_PROJECT_SKILL ADD CONSTRAINT FK_IPS_IPN_IDX FOREIGN KEY (IPS_IPL_IDX) REFERENCES ICAN_PROJECT_LIST(IPL_IDX); ---------------프로젝트 스킬

CREATE SEQUENCE PROJECT_LIST_SEQ START WITH 1 INCREMENT BY 1;

-- 8. ICAN_PROJECT_JOIN_LIST (프로젝트 참여)
-------------------------------------8. ICAN_PROJECT_JOIN_LIST -------------------------------------------------
CREATE TABLE ICAN_PROJECT_JOIN_LIST(
    IPJL_IM_IDX NUMBER NOT NULL, -- 사번(FOREIGN KEY)
    IPJL_IPL_IDX NUMBER NOT NULL, -- 프로젝트 고유번호(FOREIGN KEY)
    IPJL_ROLL NUMBER(1) NOT NULL -- PROJECT ROLL (관리자/개발자)
);
ALTER TABLE ICAN_PROJECT_JOIN_LIST ADD CONSTRAINT FK_IPJL_IM_IDX FOREIGN KEY(IPJL_IM_IDX) REFERENCES ICAN_MEMBER(IM_IDX);
ALTER TABLE ICAN_PROJECT_JOIN_LIST ADD CONSTRAINT FK_IPJL_IPL_IDX FOREIGN KEY(IPJL_IPL_IDX) REFERENCES ICAN_PROJECT_LIST(IPL_IDX);

CREATE SEQUENCE PROJECT_JOIN_SEQ START WITH 1 INCREMENT BY 1;

commit;
--9 . ICAN_NOTICE 
-------------------------------------9. ICAN_NOTICE -------------------------------------------------
--CREATE TABLE ICAN_NOTICE(
--    IN_IDX NUMBER NOT NULL, -- 글번호
--    IN_IM_IDX NUMBER NOT NULL, -- 작성자
--    IN_TITLE VARCHAR2(100) NOT NULL, -- 제목
--    IN_CONTENTS VARCHAR2(4000) NOT NULL, -- 내용
--    IN_WDATE DATE NOT NULL, -- 작성일
--    IN_READCOUNT NUMBER NOT NULL, -- 조회수
--    IN_DEL NUMBER(1) NOT NULL -- 삭제여부
--);
--
--CREATE SEQUENCE BBS_SEQ START WITH 1 INCREMENT BY 1;
--ALTER TABLE ICAN_NOTICE ADD CONSTRAINT PK_IN_IDX PRIMARY KEY (IN_IDX);
--ALTER TABLE ICAN_NOTICE ADD CONSTRAINT FK_IB_IM_IDX FOREIGN KEY(IN_IM_IDX) REFERENCES ICAN_MEMBER(IM_IDX);

---------------------------------------- 10 . 외래키 설정 -------------------------------------------------------
--ALTER TABLE ICAN_MEMBER ADD CONSTRAINT FK_IM_DCODE FOREIGN KEY(IM_DCODE) REFERENCES ICAN_DEP(ID_DCODE);
--COMMIT;

----------------------------------------------테이블 생성 끝 ------------------------------------------------------------------
----------------------------------------------테이블 생성 끝 ------------------------------------------------------------------
----------------------------------------------테이블 생성 끝 ------------------------------------------------------------------
----------------------------------------------테이블 생성 끝 ------------------------------------------------------------------
----------------------------------------------테이블 생성 끝 ------------------------------------------------------------------


--3 . 부서 TABLE 
-------------------------------------3 . ICAN_DEP -------------------------------------------------
--CREATE TABLE ICAN_DEP(
--    ID_DCODE NUMBER,
--    ID_DNAME VARCHAR2(100)
--);
--ALTER TABLE ICAN_DEP ADD CONSTRAINT PK_ID_DCODE PRIMARY KEY (ID_DCODE);
--
--CREATE SEQUENCE DEP_SEQ START WITH 1 INCREMENT BY 1;
--
--INSERT INTO ICAN_DEP VALUES(DEP_SEQ.NEXTVAL, '대표이사');
--INSERT INTO ICAN_DEP VALUES(DEP_SEQ.NEXTVAL, '전무이사');
--INSERT INTO ICAN_DEP VALUES(DEP_SEQ.NEXTVAL, '상무이사');
--INSERT INTO ICAN_DEP VALUES(DEP_SEQ.NEXTVAL, '관리부');
--INSERT INTO ICAN_DEP VALUES(DEP_SEQ.NEXTVAL, 'SI사업부');
--INSERT INTO ICAN_DEP VALUES(DEP_SEQ.NEXTVAL, 'SW사업부');
--INSERT INTO ICAN_DEP VALUES(DEP_SEQ.NEXTVAL, 'SO사업부');
--INSERT INTO ICAN_DEP VALUES(DEP_SEQ.NEXTVAL, 'FREELANCER');

--4. SKILL TABLE


--CREATE TABLE ICAN_SKILL(
--    IS_SCODE NUMBER,
--    IS_SNAME VARCHAR2(100)
--);
--ALTER TABLE ICAN_SKILL ADD CONSTRAINT PK_IS_SCODE PRIMARY KEY (IS_SCODE);
--
--CREATE SEQUENCE SKILL_SEQ START WITH 1 INCREMENT BY 1;
--
--INSERT INTO ICAN_SKILL VALUES(SKILL_SEQ.NEXTVAL, 'JAVA');
--INSERT INTO ICAN_SKILL VALUES(SKILL_SEQ.NEXTVAL, 'JQUERY');
--INSERT INTO ICAN_SKILL VALUES(SKILL_SEQ.NEXTVAL, 'SPRING');
--INSERT INTO ICAN_SKILL VALUES(SKILL_SEQ.NEXTVAL, 'AJAX');
--INSERT INTO ICAN_SKILL VALUES(SKILL_SEQ.NEXTVAL, 'JSP');
--INSERT INTO ICAN_SKILL VALUES(SKILL_SEQ.NEXTVAL, 'ORACLE');
--INSERT INTO ICAN_SKILL VALUES(SKILL_SEQ.NEXTVAL, 'MYSQL');
--INSERT INTO ICAN_SKILL VALUES(SKILL_SEQ.NEXTVAL, 'MSSQL');
--INSERT INTO ICAN_SKILL VALUES(SKILL_SEQ.NEXTVAL, 'JSTL');
--INSERT INTO ICAN_SKILL VALUES(SKILL_SEQ.NEXTVAL, 'EL');
--INSERT INTO ICAN_SKILL VALUES(SKILL_SEQ.NEXTVAL, 'ASP');
--INSERT INTO ICAN_SKILL VALUES(SKILL_SEQ.NEXTVAL, 'C');
--INSERT INTO ICAN_SKILL VALUES(SKILL_SEQ.NEXTVAL, 'C++');
--INSERT INTO ICAN_SKILL VALUES(SKILL_SEQ.NEXTVAL, 'C#');
--INSERT INTO ICAN_SKILL VALUES(SKILL_SEQ.NEXTVAL, 'PHP');
--INSERT INTO ICAN_SKILL VALUES(SKILL_SEQ.NEXTVAL, 'EGVER');
--INSERT INTO ICAN_SKILL VALUES(SKILL_SEQ.NEXTVAL, 'R');
--INSERT INTO ICAN_SKILL VALUES(SKILL_SEQ.NEXTVAL, 'PYTHON');
--. ICAN_NOTICE 
--DROP TABLE ICAN_NOTICE CASCADE CONSTRAINTS;
--DROP SEQUENCE BBS_SEQ;
--. 부서 TABLE 
--DROP TABLE ICAN_DEP CASCADE CONSTRAINTS;
--DROP SEQUENCE DEP_SEQ;
--. SKILL TABLE
--DROP TABLE ICAN_SKILL CASCADE CONSTRAINTS;
--DROP SEQUENCE SKILL_SEQ;

--SELECT IM_IDX, IM_NAME, IM_DNAME, IM_PHONE, IM_EMAIL, IM_AUTH, IM_STATUS "
--          		+ " FROM (SELECT ROW_NUMBER() OVER (ORDER BY IM_IDX) AS RNUM, IMB.IM_IDX ,IMB.IM_NAME ,IDP.ID_DNAME, IMB.IM_PHONE, IMB.IM_EMAIL, IMB.IM_SRANK, IMB.IM_AUTH, IMB.IM_STATUS"
--          		+ " FROM ICAN_MEMBER IMB INNER JOIN ICAN_DEP IDP "
--          		+ " ON IMB.IM_DCODE = IDP.ID_DCODE "
--          		+ " WHERE IMB.IM_RESIGN = 0) "
--          		+ " WHERE RNUM BETWEEN ? AND ? ";
--                
-- SELECT IM_IDX, IM_NAME, IM_DNAME, IM_PHONE, IM_EMAIL, IM_AUTH, IM_STATUS 
-- FROM (
--    SELECT ROW_NUMBER() OVER (ORDER BY IM_IDX) AS RNUM, IM_IDX, IM_NAME, IM_DNAME, IM_PHONE, IM_EMAIL, IM_AUTH, IM_STATUS
--    FROM ICAN_MEMBER
--    WHERE IM_RESIGN = 0)
--WHERE RNUM BETWEEN 1 AND 15 ;
--
---------- 경력 계산기 -------------------------
--SELECT TRUNC(DATETERM / 12) , TRUNC (MONTHS_BETWEEN (SYSDATE , ADD_MONTHS ( MINDATE , 12 * TRUNC( DATETERM / 12))))
--FROM ( SELECT MIN(IME_REGI_DATE) AS MINDATE ,MONTHS_BETWEEN (SYSDATE, MIN(IME_REGI_DATE)) AS DATETERM FROM ICAN_MEM_EXP WHERE IME_IM_IDX = '1002');
----------------------------------------------

--SELECT IM_IDX, IM_NAME, IM_DNAME, IM_PHONE, IM_EMAIL, IM_AUTH, IM_STATUS 
-- FROM (
--    SELECT ROW_NUMBER() OVER (ORDER BY IM_IDX) AS RNUM, IM_IDX, IM_NAME, IM_DNAME, IM_PHONE, IM_EMAIL, IM_AUTH, IM_STATUS
--    FROM ICAN_MEMBER IM , ICAN_MEM_EXP IME
--    WHERE IM.IM_RESIGN = 0 )
--WHERE RNUM BETWEEN 1 AND 15 ;
--SELECT IM_IDX, IM_NAME, IM_DNAME, IM_PHONE , IM_AUTH, IM_STATUS , YEAR#, MONTH#
--FROM(
--    SELECT ROW_NUMBER() OVER (ORDER BY IM_IDX) AS RNUM, IM_IDX, IM_NAME, IM_DNAME, IM_PHONE, IM_AUTH, IM_STATUS, TRUNC(DATETERM / 12) AS YEAR#, TRUNC(MONTHS_BETWEEN (SYSDATE, ADD_MONTHS (MINDATE, 12 * TRUNC (DATETERM / 12)))) AS MONTH#
--    FROM ICAN_MEMBER IM LEFT JOIN (SELECT IME_IM_IDX, MIN(IME_REGI_DATE) AS MINDATE ,MONTHS_BETWEEN (SYSDATE, MIN(IME_REGI_DATE)) AS DATETERM FROM ICAN_MEM_EXP group by IME_IM_IDX) IME
--    ON IM.IM_IDX = IME.IME_IM_IDX
--    WHERE IM_RESIGN = 0)
--WHERE RNUM BETWEEN 1 AND 15 ;
--COMMIT;
--
--SELECT *
--FROM(
--    SELECT ROW_NUMBER() OVER (ORDER BY IM_IDX) AS RNUM, IM_IDX, IM_NAME, IM_DNAME, IM_PHONE, IM_AUTH, IM_STATUS
--    FROM ICAN_MEMBER IM    
--    WHERE IM_RESIGN = 0)
--WHERE RNUM BETWEEN 1 AND 15 ;
--
--
--
--
--SELECT IM_IDX, IM_NAME, IM_DNAME, IM_PHONE , IM_AUTH, IM_STATUS , YEAR#, MONTH# 
--       		  FROM( 
--       		        SELECT 
--       		             ROW_NUMBER() OVER (ORDER BY IM_IDX) AS RNUM, 
--       	             IM_IDX, 
--                  IM_NAME,
--       	            IM_DNAME, 
--       		             IM_PHONE, 
--       		              IM_AUTH, 
--       		             IM_STATUS,
--       		              TRUNC(DATETERM / 12) AS YEAR#, 
--       		             TRUNC(MONTHS_BETWEEN (SYSDATE, ADD_MONTHS (MINDATE, 12 * TRUNC (DATETERM / 12)))) AS MONTH#
--       		 	  FROM 
--       		            ICAN_MEMBER IM LEFT JOIN (
--       		                                       SELECT 
--       		                                               IME_IM_IDX,
--       		                                              MIN(IME_REGI_DATE) AS MINDATE, 
--       		                                              MONTHS_BETWEEN (SYSDATE, MIN(IME_REGI_DATE)) AS DATETERM 
--       		                                       FROM ICAN_MEM_EXP group by IME_IM_IDX 
--       		                                      ) IME
--                          ON IM.IM_IDX = IME.IME_IM_IDX                         
--       		       WHERE 
--       		              IM_RESIGN = 0 
--       		       )
--       		 WHERE RNUM BETWEEN 1 AND 15 ;
--SELECT * FROM ICAN_MEMBER WHERE IM_IDX = '1005';
--SELECT * FROM ICAN_MEM_SKILL WHERE IMS_IM_IDX = '1005';
--SELECT * FROM ICAN_MEM_EXP WHERE IME_IM_IDX = '1005';
--SELECT * FROM ICAN_MEM_LICENSE WHERE IML_IM_IDX = '1005';
--SELECT * FROM ICAN_PROJECT_JOIN_LIST WHERE IPJL_IM_IDX = '1005';
--SELECT IME_REGI_DATE FROM ICAN_MEM_EXP WHERE IME_IM_IDX = '1009' AND IME_EXIT_DATE IS NULL;
--SELECT * FROM ICAN_MEM_EXP;
--commit;

--sql = " SELECT IM_IDX, IM_NAME, IM_DNAME, IM_PHONE , IM_AUTH, IM_STATUS , YEAR#, MONTH# "
--       		+ " FROM( "
--       		+ "       SELECT "
--       		+ "              ROW_NUMBER() OVER (ORDER BY IM_IDX) AS RNUM, "
--       		+ "              IM_IDX, "
--       		+ "              IM_NAME, "
--       		+ "              IM_DNAME, "
--       		+ "              IM_PHONE, "
--       		+ "              IM_AUTH, "
--       		+ "              IM_STATUS, "
--       		+ "              TRUNC(DATETERM / 12) AS YEAR#, "
--       		+ "              TRUNC(MONTHS_BETWEEN (SYSDATE, ADD_MONTHS (MINDATE, 12 * TRUNC (DATETERM / 12)))) AS MONTH#"
--       		+ "  	  FROM "
--       		+ "             ICAN_MEMBER IM LEFT JOIN ("
--       		+ "                                       SELECT "
--       		+ "                                               IME_IM_IDX,"
--       		+ "                                               MIN(IME_REGI_DATE) AS MINDATE, "
--       		+ "                                               MONTHS_BETWEEN (SYSDATE, MIN(IME_REGI_DATE)) AS DATETERM "
--       		+ "                                       FROM ICAN_MEM_EXP group by IME_IM_IDX "
--       		+ "                                       ) IME"
--       		+ "              ON IM.IM_IDX = IME.IME_IM_IDX "
--       		+ "       WHERE "
--       		+ "               IM_RESIGN = 0 "
--       		+ "       )"
--       		+ " WHERE RNUM BETWEEN ? AND ? ";
--
--COMMIT;
--SELECT * FROM ICAN_MEM_LICENSE WHERE IML_IM_IDX = 1001;
--SELECT * FROM ICAN_MEM_EXP WHERE IME_IM_IDX = 1001 AND IME_EXIT_DATE IS NOT NULL;
--
--SELECT * FROM ICAN_MEMBER WHERE IM_IDX = 1009;
--UPDATE ICAN_MEMBER SET IM_RESIGN = 1 WHERE IM_IDX = 1009; -- MEMBER UPDATE
--
--SELECT * FROM ICAN_MEM_EXP WHERE IME_IM_IDX = 1009 AND IME_EXIT_DATE IS NULL;
--UPDATE ICAN_MEM_EXP SET IME_EXIT_DATE = SYSDATE WHERE IME_IM_IDX = 1009 AND IME_EXIT_DATE IS NULL; -- MEMBER ICAN SYSDATE UPDATE
--COMMIT;
--
--SELECT * FROM ICAN_MEMBER;
--UPDATE ICAN_MEM_EXP SET IME_EXIT_DATE = SYSDATE WHERE IME_IM_IDX = 1007 AND IME_EXIT_DATE IS NULL;
--select * from ican_mem_exp where ime_exit_date is null and ime_im_idx = 1001;
--SELECT * FROM ICAN_MEM_LICENSE;
--SELECT * FROM ICAN_MEM_LICENSE WHERE IML_IM_IDX = 1002;
--SELECT IML_IM_IDX ,IML_LNAME ,TRUNC(IML_ACQDATE, 'yyyyMMdd') AS YYMMDD ,IML_ORGANIZATION  FROM ICAN_MEM_LICENSE WHERE IML_IM_IDX = 1002;
--COMMIT;
commit;