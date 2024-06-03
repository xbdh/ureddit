CREATE TABLE IF NOT EXISTS post (
    id CHAR(36) NOT NULL,
    title VARCHAR(255) NOT NULL UNIQUE,
    content VARCHAR(1000) NOT NULL,
    author_id VARCHAR(255) NOT NULL,
    subreddit_id VARCHAR(255) NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
    );

-- //   String id;
--     String title;
--     String content;
--     String authorId;
--     String subredditId;