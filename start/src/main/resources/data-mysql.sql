-- App系统码对应表
INSERT INTO `vas_baas_app` VALUES (1, '80000', '牛利-智能网关', '2019-03-04 09:00:00', '2019-03-04 09:00:00');

-- 用户表[admin:abc@123]
INSERT INTO `vas_user_info` VALUES ('1', NULL, 'admin', 'admin', 'admin', 'e5857b335afdf35ca81a110bc81f38682f8a89892cc597f5398dfef82d42b513', '18888888888', 'admin@newly.ai', '370000000000000000', NULL, NULL, 0, 1, '123.123.123.123', '2019-03-04 09:00:00', '2019-03-04 09:00:00', '2019-03-04 09:00:00', 'Administrator', NULL , 'newly', '1', '110', 'nlzn');

-- 角色表
INSERT INTO `vas_baas_role` VALUES ('1', 'admin', 1, '1', '2019-03-04 09:00:00', '2019-03-04 09:00:00', '系统管理员');

-- 角色用户映射表
INSERT INTO `vas_baas_user_role_map` VALUES ('1', '1');

-- 角色功能表
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','800');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','803');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','804');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','805');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','806');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','80501');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','80502');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','80503');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','8060101');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','8060102');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','8060103');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','8060104');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','8060105');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','8060106');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','806010301');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','806010302');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','806010303');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','80601030101');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','80601030102');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','80601030103');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','80601030104');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','80601030105');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','80601030201');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','80601030202');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','80601030301');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','80601030302');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','806010201');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','806010202');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','806010203');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','806010601');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','806010602');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','806010603');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','806010604');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','806010401');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','806010402');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','806010403');
insert into `vas_baas_role_menu_map` (`ROLE_ID`,`APP_CODE`,`MENU_ID`) VALUES ('1','80000','806010404');

-- 插入菜单权限数据 - BAAS(10000,100-199)
INSERT INTO `vas_menu` VALUES ('800', '首页', '8000001', 'index', 'icon icon-home', '80000', 1, '-1');
INSERT INTO `vas_menu` VALUES ('803', '注册', '8000003', 'register', '', '80000', 1, '-1');
INSERT INTO `vas_menu` VALUES ('804', '忘记密码', '8000004', 'forgetPwd', '', '80000', 1, '-1');

INSERT INTO `vas_menu` VALUES ('805', '视频广场', '8000005', 'index', 'icon icon-home', '80000', 1, '-1');
INSERT INTO `vas_menu` VALUES ('80501', '实时视频', '800000501', 'index', NULL, '80000', 1, '805');
INSERT INTO `vas_menu` VALUES ('80502', '历史回放', '800000502', 'history', NULL, '80000', 1, '805');
INSERT INTO `vas_menu` VALUES ('80503', '下载中心', '800000503', 'download', NULL, '80000', 1, '805');

INSERT INTO `vas_menu` VALUES ('806', '管理中心', '8000006', 'myvgs', 'icon icon-home', '80000', 1, '-1');
INSERT INTO `vas_menu` VALUES ('8060101', '我的网关', '80000060101', 'vgsList', '', '80000', 1, '806');
INSERT INTO `vas_menu` VALUES ('8060102', '添加网关', '80000060102', 'addvgs', '', '80000', 1, '806');
INSERT INTO `vas_menu` VALUES ('8060103', '权限管理', '80000060103', 'manage', 'icon icon-control', '80000', 1, '806');
INSERT INTO `vas_menu` VALUES ('8060104', '视频存储', '80000060104', 'videoStorage', '', '80000', 1, '806');
INSERT INTO `vas_menu` VALUES ('8060105', '系统设置', '80000060105', 'system', '', '80000', 1, '806');
INSERT INTO `vas_menu` VALUES ('8060106', '视频设备', '80000060106', 'deviceList', '', '80000', 1, '806');
INSERT INTO `vas_menu` VALUES ('806010301', '用户管理', '8000006010301', 'userManage', 'icon icon-admin', '80000', 1, '8060103');
INSERT INTO `vas_menu` VALUES ('806010302', '资源管理', '8000006010302', 'devManage', 'icon icon-resource', '80000', 1, '8060103');
INSERT INTO `vas_menu` VALUES ('806010303', '角色管理', '8000006010303', 'roleManage', 'icon icon-resource', '80000', 1, '8060103');
INSERT INTO `vas_menu` VALUES ('80601030101', '导入', '8060103010101', '', '', '80000', 1, '806010301');
INSERT INTO `vas_menu` VALUES ('80601030102', '编辑', '8060103010201', NULL, NULL, '80000', 1, '806010301');
INSERT INTO `vas_menu` VALUES ('80601030103', '删除', '8060103010301', NULL, NULL, '80000', 1, '806010301');
INSERT INTO `vas_menu` VALUES ('80601030104', '重置密码', '8060103010401', NULL, NULL, '80000', 1, '806010301');
INSERT INTO `vas_menu` VALUES ('80601030105', '部门变更', '8060103010501', NULL, NULL, '80000', 1, '806010301');
INSERT INTO `vas_menu` VALUES ('80601030201', '编辑', '8060103020101', NULL, NULL, '80000', 1, '806010302');
INSERT INTO `vas_menu` VALUES ('80601030202', '删除', '8060103020201', NULL, NULL, '80000', 1, '806010302');
INSERT INTO `vas_menu` VALUES ('80601030301', '编辑', '8060103030101', NULL, NULL, '80000', 1, '806010303');
INSERT INTO `vas_menu` VALUES ('80601030302', '删除', '8060103030201', NULL, NULL, '80000', 1, '806010303');
INSERT INTO `vas_menu` VALUES ('806010201', '添加网关', '80601020101', NULL, NULL, '80000', 1, '8060101');
INSERT INTO `vas_menu` VALUES ('806010202', '网关编辑', '80601020201', NULL, NULL, '80000', 1, '8060101');
INSERT INTO `vas_menu` VALUES ('806010203', '网关删除', '80601020301', NULL, NULL, '80000', 1, '8060101');
INSERT INTO `vas_menu` VALUES ('806010601', '快速添加摄像头', '80601060101', NULL, NULL, '80000', 1, '8060106');
INSERT INTO `vas_menu` VALUES ('806010602', '手动添加摄像头', '80601060201', NULL, NULL, '80000', 1, '8060106');
INSERT INTO `vas_menu` VALUES ('806010603', '设备编辑', '80601060301', NULL, NULL, '80000', 1, '8060106');
INSERT INTO `vas_menu` VALUES ('806010604', '设备删除', '80601060401', NULL, NULL, '80000', 1, '8060106');
INSERT INTO `vas_menu` VALUES ('806010401', '添加存储方案', '80601040101', NULL, NULL, '80000', 1, '8060104');
INSERT INTO `vas_menu` VALUES ('806010402', '编辑存储方案', '80601040201', NULL, NULL, '80000', 1, '8060104');
INSERT INTO `vas_menu` VALUES ('806010403', '存储方案重命名', '80601040301', NULL, NULL, '80000', 1, '8060104');
INSERT INTO `vas_menu` VALUES ('806010404', '存储方案删除', '80601040401', NULL, NULL, '80000', 1, '8060104');