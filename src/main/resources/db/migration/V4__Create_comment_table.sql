CREATE TABLE `COMMENT`(
    id BIGINT AUTO_INCREMENT PRIMARY  KEY,
    parent_id BIGINT NOT NULL,
    type INT NOT NULL,
    commentator BIGINT NOT NULL,
    gmt_creat BIGINT NOT NULL,
    gmt_modified BIGINT NOT NULL,
    like_count BIGINT DEFAULT 0,
    content VARCHAR(1024) NULL,
    comment_count INT DEFAULT 0;
);