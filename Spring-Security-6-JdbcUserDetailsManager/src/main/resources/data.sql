INSERT INTO `spring`.`authorities`
(username, authority)
VALUES
('john', 'write');

INSERT INTO `spring`.`users`
(username, password, enabled)
VALUES
('john', '12345', '1');

INSERT INTO `spring`.`authorities`
(username, authority)
VALUES
('jacob', 'read');

INSERT INTO `spring`.`users`
(username, password, enabled)
VALUES
('jacob', '11111', '1');