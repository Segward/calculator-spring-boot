DROP TABLE IF EXISTS history;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    salt BINARY(16) NOT NULL
);

CREATE TABLE history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    action VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Insert users with auto-incrementing IDs
INSERT INTO users (username, password, salt) 
VALUES ('admin', 'admin', RANDOM_BYTES(16));

INSERT INTO users (username, password, salt) 
VALUES ('user', 'user', RANDOM_BYTES(16));

-- Insert history records
INSERT INTO history (user_id, action) 
SELECT id, 'login' FROM users WHERE username = 'admin';

INSERT INTO history (user_id, action) 
SELECT id, 'logout' FROM users WHERE username = 'admin';

INSERT INTO history (user_id, action) 
SELECT id, 'login' FROM users WHERE username = 'user';

INSERT INTO history (user_id, action) 
SELECT id, 'logout' FROM users WHERE username = 'user';