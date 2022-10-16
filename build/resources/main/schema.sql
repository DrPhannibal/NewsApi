//DROP TABLE IF EXISTS TBL_User;

CREATE TABLE TB_News
(
    id          long AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(255)   NOT NULL,
    fk_cat      long,
    link        VARCHAR(255)   NOT NULL,
    description varchar(10000) NOT NULL,
    author      VARCHAR(255)   NOT NULL,
    enclosure   VARCHAR(255)   NOT NULL,
    pubDate     DATE,
);

CREATE TABLE TB_WEB_SITE
(
    id       long AUTO_INCREMENT PRIMARY KEY,
    category varchar(255) not null,
    url      varchar(255) not null
);


alter table TB_News
    add foreign key (fk_cat)
        references TB_WEB_SITE (id);
