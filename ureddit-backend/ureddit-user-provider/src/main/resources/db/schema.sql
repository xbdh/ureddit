CREATE TABLE user(
      id VARCHAR(36) NOT NULL,
      user_id VARCHAR(36) NOT NULL,
      username VARCHAR(255) UNIQUE NOT NULL ,
      image VARCHAR(255)  NOT NULL,
      create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
      update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      PRIMARY KEY (id)
);