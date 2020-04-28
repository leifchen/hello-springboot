-- 初始化数据
INSERT INTO `user` VALUES (1, 'admin', 'admin');
INSERT INTO `user` VALUES (2, 'test', 'test');
INSERT INTO `role` VALUES (1, 'ROLE_ADMIN');
INSERT INTO `role` VALUES (2, 'ROLE_TEST');
INSERT INTO `menu` VALUES (1, 'add');
INSERT INTO `menu` VALUES (2, 'delete');
INSERT INTO `menu` VALUES (3, 'update');
INSERT INTO `menu` VALUES (4, 'select');
INSERT INTO `user_role` VALUES (1, 1);
INSERT INTO `user_role` VALUES (2, 2);
INSERT INTO `role_menu` VALUES (1, 1);
INSERT INTO `role_menu` VALUES (1, 2);
INSERT INTO `role_menu` VALUES (1, 3);
INSERT INTO `role_menu` VALUES (1, 4);
INSERT INTO `role_menu` VALUES (2, 4);