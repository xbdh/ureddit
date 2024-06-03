CREATE TABLE IF NOT EXISTS comment (
    id CHAR(36) NOT NULL,
    content VARCHAR(1000) NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    post_id VARCHAR(36) NOT NULL,
    reply_to_id VARCHAR(36) ,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

-- //   String id;
--     String title;
--     String content;
--     String authorId;
--     String subredditId;