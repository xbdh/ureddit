CREATE TABLE vote(
      user_id VARCHAR(36) NOT NULL,
      post_id VARCHAR(36) NOT NULL,
      vote_type VARCHAR(4) NOT NULL ,
      create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
      update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      PRIMARY KEY (user_id, post_id)
);

CREATE TABLE comment_vote(
        user_id VARCHAR(36) NOT NULL,
        comment_id VARCHAR(36) NOT NULL,
        vote_type VARCHAR(4) NOT NULL ,
        create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
        update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        PRIMARY KEY (user_id, comment_id)
);