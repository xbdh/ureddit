CREATE TABLE IF NOT EXISTS subreddit (
    id CHAR(36) NOT NULL,
    name VARCHAR(255) NOT NULL UNIQUE,
    creator_id VARCHAR(255) NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
    );