CREATE TABLE subscription (
        id VARCHAR(36) NOT NULL,
        user_id VARCHAR(255) NOT NULL ,
        subreddit_id VARCHAR(36) NOT NULL,
        PRIMARY KEY (id),
        INDEX idx_user (user_id),
        INDEX idx_subreddit (subreddit_id)
--             //This is the unique constraint new added

);