--ver.2

-- tb_brand
DROP TABLE tb_brand CASCADE CONSTRAINTS;

DROP SEQUENCE tb_brand_seq;

CREATE SEQUENCE tb_brand_seq START WITH 26 INCREMENT BY 1;

CREATE TABLE tb_brand (
    brand_seq     NUMBER DEFAULT tb_brand_seq.NEXTVAL,
    brand_name    VARCHAR2(255) NOT NULL,
    brand_imgurl  VARCHAR2(255) NOT NULL,
    brand_img1    VARCHAR2(1024),
    brand_img2    VARCHAR2(1024),
    brand_img3    VARCHAR2(1024),
    brand_img4    VARCHAR2(1024)
);

ALTER TABLE tb_brand ADD CONSTRAINT tb_brand_pk PRIMARY KEY ( brand_seq );

-- tb_user
DROP TABLE tb_user CASCADE CONSTRAINTS;

DROP SEQUENCE tb_user_seq;

CREATE SEQUENCE tb_user_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE tb_user (
    user_seq           NUMBER DEFAULT tb_user_seq.NEXTVAL,
    role_num           NUMBER DEFAULT 0,
    user_name          VARCHAR2(255) NOT NULL,
    user_mail          VARCHAR2(255) NOT NULL,
    user_pw            VARCHAR2(255) NOT NULL,
    user_registration  NUMBER NOT NULL,
    user_mobile        NUMBER,
    FOREIGN KEY ( role_num )
        REFERENCES tb_brand ( brand_seq )
);

ALTER TABLE tb_user ADD CONSTRAINT tb_user_pk PRIMARY KEY ( user_seq );

-- tb_reservation
DROP TABLE tb_reservation CASCADE CONSTRAINTS;

CREATE TABLE tb_reservation (
    user_seq          NUMBER NOT NULL,
    brand_seq         NUMBER NOT NULL,
    reservation_date  DATE NOT NULL,
    FOREIGN KEY ( user_seq )
        REFERENCES tb_user ( user_seq ),
    FOREIGN KEY ( brand_seq )
        REFERENCES tb_brand ( brand_seq )
);

ALTER TABLE tb_reservation ADD CONSTRAINT tb_reservation_pk PRIMARY KEY ( user_seq,
                                                                          brand_seq );

-- tb_category
DROP TABLE tb_category CASCADE CONSTRAINTS;

CREATE TABLE tb_category (
    category VARCHAR2(255) NOT NULL
);

ALTER TABLE tb_category ADD CONSTRAINT tb_category PRIMARY KEY ( category );

-- tb_product
DROP TABLE tb_product CASCADE CONSTRAINTS;

DROP SEQUENCE tb_product_seq;

CREATE SEQUENCE tb_product_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE tb_product (
    product_seq       NUMBER DEFAULT tb_product_seq.NEXTVAL,
    brand_seq         NUMBER NOT NULL,
    product_name      VARCHAR2(255) NOT NULL,
    product_price     VARCHAR2(255),
    product_imgurl    VARCHAR2(255),
    product_category  VARCHAR2(255),
    FOREIGN KEY ( brand_seq )
        REFERENCES tb_brand ( brand_seq ),
    FOREIGN KEY ( product_category )
        REFERENCES tb_category ( category )
);

ALTER TABLE tb_product ADD CONSTRAINT tb_product_pk PRIMARY KEY ( product_seq );