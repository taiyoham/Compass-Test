USE school;

SET NAMES utf8mb4;


-- クラス
CREATE TABLE classes (
    id INT PRIMARY KEY,
    name TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;;

INSERT INTO classes (id, name) VALUES
(1, 'クラスA'),
(2, 'クラスB'),
(3, 'クラスC');



-- 教師
CREATE TABLE teachers (
    id INT PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;;

INSERT INTO teachers (id) VALUES
(1),
(2);



-- 生徒
CREATE TABLE students (
    id INT PRIMARY KEY,
    name TEXT NOT NULL,
    login_id TEXT NOT NULL,
    class_id INT NOT NULL,
    teacher_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (class_id) REFERENCES classes(id),
    FOREIGN KEY (teacher_id) REFERENCES teachers(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;;



INSERT INTO students (id, name, login_id, class_id, teacher_id) VALUES
(1, '佐藤', 'foo123', 1, 1),
(2, '鈴木', 'bar456', 2, 2),
(3, '田中', 'baz789', 3, 1),
(4, '加藤', 'hoge0000', 1, 1),
(5, '太田', 'fuga1234', 2, 2),
(6, '佐々木', 'piyo5678', 3, 1),
(7, '小田原', 'fizz9999', 1, 1),
(8, 'Smith', 'buzz777', 2, 2),
(9, 'Johnson', 'fizzbuzz#123', 3, 1),
(10, 'Williams', 'xxx:42', 1, 1);