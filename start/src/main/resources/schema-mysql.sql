-- ----------------------------
-- Function structure for getChildrenDept
-- ----------------------------
CREATE FUNCTION `getChildrenDept`(deptId VARCHAR(255)) RETURNS text CHARSET utf8mb4
READS SQL DATA
BEGIN
  DECLARE oTemp TEXT;
  DECLARE oTempChild TEXT;

  SET oTemp = '';
  SET oTempChild = deptId;

  WHILE oTempChild IS NOT NULL
  DO
    SET oTemp = if(oTemp = '', oTempChild, CONCAT(oTemp, ',', oTempChild));
    SELECT GROUP_CONCAT(ID) INTO oTempChild FROM VAS_DEPARTMENT WHERE FIND_IN_SET(PARENT_ID,oTempChild) > 0;
  END WHILE;
  RETURN oTemp;
END|
-- ----------------------------
-- Function structure for getChildrenResTree
-- ----------------------------
CREATE FUNCTION `getChildrenResTree` (treeId VARCHAR (255)) RETURNS text CHARSET utf8mb4
READS SQL DATA
BEGIN
  DECLARE oTemp text;
  DECLARE oTempChild text;

  SET oTemp = '';
  SET oTempChild = treeId;

  WHILE oTempChild IS NOT NULL
  DO
    SET oTemp = IF (oTemp = '',oTempChild, CONCAT(oTemp, ',',oTempChild));
    SELECT GROUP_CONCAT( TREE_NODE ) INTO oTempChild FROM VGS_RES_TREE WHERE FIND_IN_SET( PARENT_TREE_NODE, oTempChild) > 0;
  END WHILE;
  RETURN oTemp;
END|

-- ----------------------------
-- Table structure for vas_baas_app
-- ----------------------------
CREATE TABLE `vas_baas_app`  (
  `ID` int(11) NOT NULL COMMENT '主键',
  `APP_CODE` varchar(255) NULL DEFAULT NULL COMMENT '系统码',
  `APP_NAME` varchar(255) NULL DEFAULT NULL COMMENT '系统名',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统码映射表' ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_baas_res_tree
-- ----------------------------
CREATE TABLE `vas_baas_res_tree`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `TREE_NODE` varchar(255) NULL DEFAULT NULL COMMENT '单位/区域编码（树节点）',
  `NAME` varchar(255) NULL DEFAULT NULL COMMENT '卡口名称',
  `PARENT_TREE_NODE` varchar(255) NULL DEFAULT NULL COMMENT '父单位/区域编码（树节点）',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `ORG_NUM` varchar(255) NULL DEFAULT NULL COMMENT '组织编号',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `REMARK` varchar(255) NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资源树' ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_baas_role
-- ----------------------------
CREATE TABLE `vas_baas_role`  (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `NAME` varchar(255) NULL DEFAULT NULL COMMENT '角色名称',
  `IS_SYSTEM` int(11) NULL DEFAULT NULL COMMENT '是否系统信息 1：是 0：否',
  `CREATE_USER_ID` varchar(255) NULL DEFAULT NULL COMMENT '创建用户ID',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `REMARK` varchar(255) NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_baas_role_menu_map
-- ----------------------------
CREATE TABLE `vas_baas_role_menu_map`  (
  `ROLE_ID` varchar(255) NOT NULL DEFAULT '' COMMENT '角色ID',
  `APP_CODE` varchar(255) NULL DEFAULT NULL COMMENT '应用编码',
  `MENU_ID` varchar(255) NOT NULL DEFAULT '' COMMENT '菜单编码',
  PRIMARY KEY (`ROLE_ID`, `MENU_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单映射表' ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_baas_role_res_map
-- ----------------------------
CREATE TABLE `vas_baas_role_res_map`  (
  `ROLE_ID` varchar(255) NOT NULL DEFAULT '' COMMENT '角色ID',
  `TREE_NODE` varchar(255) NOT NULL DEFAULT '' COMMENT '组织树ID',
  PRIMARY KEY (`ROLE_ID`, `TREE_NODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色资源映射表' ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_baas_user_role_map
-- ----------------------------
CREATE TABLE `vas_baas_user_role_map`  (
  `USER_ID` varchar(255) NOT NULL DEFAULT '' COMMENT '用户ID',
  `ROLE_ID` varchar(255) NOT NULL DEFAULT '' COMMENT '角色ID',
  PRIMARY KEY (`USER_ID`, `ROLE_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色映射表' ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_channel_info
-- ----------------------------
CREATE TABLE `vas_channel_info`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `DEVICE_ID` varchar(64) NULL DEFAULT NULL COMMENT '设备主键',
  `NAME` varchar(64) NULL DEFAULT NULL COMMENT '通道名称',
  `NUMBER` int(11) NULL DEFAULT NULL COMMENT '通道号',
  `TYPE` int(11) NULL DEFAULT 0 COMMENT '0其他, 1枪机,球机2',
  `STATUS` int(11) NULL DEFAULT 0 COMMENT '启用1，0不启用',
  `ONLINE` int(11) NULL DEFAULT 0 COMMENT ' 1在线，0不在线',
  `CHANNEL_IP` varchar(255) NULL DEFAULT NULL COMMENT 'IP',
  `CHANNEL_PORT` varchar(255) NULL DEFAULT NULL COMMENT '端口',
  `USER_NAME` varchar(255) NULL DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(255) NULL DEFAULT NULL COMMENT '密码',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `MAIN_STREAM` varchar(255) NULL DEFAULT NULL COMMENT '主码流',
  `SUB_STREAM` varchar(255) NULL DEFAULT NULL COMMENT '子码流',
  `ACCESS_KEY` varchar(255) NULL DEFAULT NULL,
  `SECRET_KEY` varchar(255) NULL DEFAULT NULL,
  `CHANNEL_INDEX` varchar(32) NULL DEFAULT NULL COMMENT '社会化监控平台设备名称',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备通道表' ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_department
-- ----------------------------
CREATE TABLE `vas_department`  (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `NAME` varchar(255) NULL DEFAULT NULL COMMENT '部门名称',
  `DEPT_CODE` varchar(255) NULL DEFAULT NULL COMMENT '部门编号',
  `PARENT_ID` varchar(255) NULL DEFAULT NULL COMMENT '父节点ID',
  `SHORT_LETTER` varchar(255) NULL DEFAULT NULL COMMENT '部门名称缩写',
  `CREATE_USER_ID` varchar(255) NULL DEFAULT NULL COMMENT '创建人ID',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `REMARK` varchar(255) NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门信息表' ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_device_info
-- ----------------------------
CREATE TABLE `vas_device_info`  (
  `ID` varchar(64) NOT NULL COMMENT '主键',
  `DEVICE_INDEX` varchar(32) NULL DEFAULT NULL COMMENT '设备编号',
  `GATEWAY_ID` varchar(32) NULL DEFAULT NULL COMMENT '所属网关',
  `NAME` varchar(255) NULL DEFAULT NULL COMMENT '设备名称',
  `FIRM` int(11) NULL DEFAULT NULL COMMENT '设备厂家',
  `TYPE` int(11) NULL DEFAULT NULL COMMENT '接入类型',
  `STATUS` int(11) NULL DEFAULT NULL COMMENT '设备状态',
  `IS_ONLINE` int(11) NULL DEFAULT NULL COMMENT '设备在线',
  `IP` varchar(255) NULL DEFAULT NULL COMMENT 'IP地址',
  `PORT` varchar(255) NULL DEFAULT NULL COMMENT '端口',
  `USER_NAME` varchar(255) NULL DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(255) NULL DEFAULT NULL COMMENT '密码',
  `STREAM_URL` varchar(255) NULL DEFAULT NULL COMMENT '流地址',
  `CONNECT_TYPE` int(11) NULL DEFAULT NULL COMMENT '连接方式',
  `LONGITUDE` varchar(255) NULL DEFAULT NULL COMMENT '经度',
  `LATITUDE` varchar(255) NULL DEFAULT NULL COMMENT '纬度',
  `HEIGHT` varchar(255) NULL DEFAULT NULL COMMENT '高度',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `REMARK` varchar(255) NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备表' ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_gateway_info
-- ----------------------------
CREATE TABLE `vas_gateway_info`  (
  `ID` varchar(64) NOT NULL COMMENT '主键',
  `GATEWAY_INDEX` varchar(32) NULL DEFAULT NULL COMMENT '网关编号',
  `USER_ID` varchar(32) NULL DEFAULT NULL COMMENT '用户Id',
  `NAME` varchar(255) NULL DEFAULT NULL COMMENT '网关名称',
  `TYPE` int(11) NULL DEFAULT 0 COMMENT '网关类型',
  `IP` varchar(255) NULL DEFAULT NULL COMMENT 'IP地址',
  `PASSWORD` varchar(255) NULL DEFAULT NULL COMMENT '密码',
  `CHANNEL` varchar(255) NULL DEFAULT NULL COMMENT '通道数',
  `IS_ONLINE` int(11) NULL DEFAULT NULL COMMENT '网关在线',
  `STATUS` int(11) NULL DEFAULT NULL COMMENT '网关状态',
  `LONGITUDE` varchar(255) NULL DEFAULT NULL COMMENT '经度',
  `LATITUDE` varchar(255) NULL DEFAULT NULL COMMENT '纬度',
  `HEIGHT` varchar(255) NULL DEFAULT NULL COMMENT '高度',
  `SOFT_VERSION` varchar(255) NULL DEFAULT NULL COMMENT '软件版本',
  `HARD_VERSION` varchar(255) NULL DEFAULT NULL COMMENT '硬件版本',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `REMARK` varchar(255) NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '网关表' ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_group_info
-- ----------------------------
CREATE TABLE `vas_group_info`  (
  `ID` varchar(32) NOT NULL COMMENT '组织ID',
  `NAME` varchar(255) NULL DEFAULT NULL COMMENT '组织名称',
  `TYPE` int(11) NULL DEFAULT NULL COMMENT '组织类型',
  `USCC` varchar(32) NULL DEFAULT NULL COMMENT '组织信用代码',
  `CONTACTS` varchar(255) NULL DEFAULT NULL COMMENT '组织联系人',
  `PHONE` varchar(32) NULL DEFAULT NULL COMMENT '组织联系电话',
  `ADDRESS` varchar(255) NULL DEFAULT NULL COMMENT '组织所在地',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '组织表' ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_kprm_deploy_camera_map
-- ----------------------------
CREATE TABLE `vas_kprm_deploy_camera_map`  (
  `DEPLOY_ID` int(11) NOT NULL COMMENT '布控单ID',
  `CAMERA_ID` varchar(64) NOT NULL COMMENT '摄像头ID',
  PRIMARY KEY (`DEPLOY_ID`, `CAMERA_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '布控信息和设备信息关联表' ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_kprm_deploy_object
-- ----------------------------
CREATE TABLE `vas_kprm_deploy_object`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '布控单ID(自增)',
  `OBJECT_ID` varchar(64) NULL DEFAULT NULL COMMENT '对象ID，整库布控，id为libID',
  `THRESHOLD` double NULL DEFAULT NULL COMMENT '阈值',
  `TYPE` int(11) NULL DEFAULT NULL COMMENT '布控类型，1图片布控，2身份证布控 3整库布控，4移动布控',
  `STATUS` int(11) NULL DEFAULT NULL COMMENT '布控状态，是否布控 1布控，0撤控',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `START_TIME` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `END_TIME` datetime(0) NULL DEFAULT NULL COMMENT '停止时间',
  `CREATE_USER_ID` varchar(255) NULL DEFAULT NULL COMMENT '创建用户ID',
  `DEPLOY_REASON` varchar(255) NULL DEFAULT NULL COMMENT '布控原因',
  `UNDEPLOY_REASON` varchar(255) NULL DEFAULT NULL COMMENT '撤控原因',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '布控人员信息表' ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_kprm_face_detect
-- ----------------------------
CREATE TABLE `vas_kprm_face_detect`  (
  `ID` varchar(64) NOT NULL COMMENT '抓拍UUID，人脸标识',
  `FACE_URI` varchar(255) NOT NULL COMMENT '人脸照片地址',
  `PICTURE_URI` varchar(255) NOT NULL COMMENT '场景照片地址',
  `CAPTURE_URI` varchar(255) NOT NULL COMMENT '抓拍肩膀以上地址',
  `COLLECT_TIME` datetime(0) NOT NULL COMMENT '采集时间',
  `KIND` int(11) NOT NULL COMMENT '信息分类 0-未知，1-自动采集，2-人工录入',
  `CLUSTER_ID` varchar(255) NULL DEFAULT NULL COMMENT '聚类人脸ID',
  `CAMERA_ID` varchar(255) NULL DEFAULT NULL COMMENT '摄像头编号',
  `CAMERA_NAME` varchar(255) NULL DEFAULT NULL COMMENT '摄像头名称',
  `LEFT_TOPX` int(11) NULL DEFAULT NULL COMMENT '左上角X坐标',
  `LEFT_TOPY` int(11) NULL DEFAULT NULL COMMENT '左上角Y坐标',
  `RIGHT_BTMX` int(11) NULL DEFAULT NULL COMMENT '右下角X坐标',
  `RIGHT_BTMY` int(11) NULL DEFAULT NULL COMMENT '右下角Y坐标',
  `FACE_WIDTH` int(11) NULL DEFAULT 0 COMMENT '人脸宽度',
  `FACE_HEIGHT` int(11) NULL DEFAULT 0 COMMENT '人脸高度',
  `LOCATION_MARK_TI` datetime(0) NULL DEFAULT NULL COMMENT '位置标记时间',
  `VIDEO_URI` varchar(255) NULL DEFAULT NULL COMMENT '人脸段视频',
  `FACE_APPEAR_TIME` datetime(0) NULL DEFAULT NULL COMMENT '人脸出现时间',
  `FACE_DISAPPEAR_TIME` datetime(0) NULL DEFAULT NULL COMMENT '人脸消失时间',
  `ACCOMPANY_NUMBER` int(11) NULL DEFAULT NULL COMMENT '同行人脸数',
  `GENDER` int(11) NULL DEFAULT NULL COMMENT '性别 0-未知，1-男，2-女',
  `AGE_RANGE` int(11) NULL DEFAULT NULL COMMENT '年龄段：0-未知，1-小孩，2-青年，3-中年，4-老年',
  `UYGUR` int(11) NULL DEFAULT NULL COMMENT '是否维族人 0-未知，1-维族人，2-非维族人',
  `FRINGE` int(11) NULL DEFAULT NULL COMMENT '是否长刘海 0-无刘海，1-短刘海，2-长刘海，默认0-无刘海',
  `ATTITUDE` int(11) NULL DEFAULT NULL COMMENT '姿态分布',
  `SKIN_COLOR` varchar(255) NULL DEFAULT NULL COMMENT '肤色',
  `HAIR_STYLE` varchar(255) NULL DEFAULT NULL COMMENT '发型',
  `HAIR_COLOR` varchar(255) NULL DEFAULT NULL COMMENT '发色',
  `FACE_STYLE` varchar(255) NULL DEFAULT NULL COMMENT '脸型',
  `FACIAL_FEATURE` varchar(255) NULL DEFAULT NULL COMMENT '脸部特征',
  `PHYSICAL_FEATURE` varchar(255) NULL DEFAULT NULL COMMENT '体貌特征',
  `IS_RESPIRATOR` int(11) NULL DEFAULT NULL COMMENT '口罩佩戴 0-未知，1-戴，2-不戴',
  `RESPIRATOR_COLOR` varchar(255) NULL DEFAULT NULL COMMENT '口罩颜色',
  `IS_CAP` int(11) NULL DEFAULT NULL COMMENT '帽子佩戴 0-未知，1-戴，2-不戴',
  `CAP_STYLE` varchar(255) NULL DEFAULT NULL COMMENT '帽子款式',
  `CAP_COLOR` varchar(255) NULL DEFAULT NULL COMMENT '帽子颜色',
  `IS_GLASS` int(11) NULL DEFAULT 0 COMMENT '眼镜佩戴 0-未知，1-戴眼镜，2-不戴眼镜，3-太阳镜',
  `GLASS_STYLE` varchar(255) NULL DEFAULT NULL COMMENT '眼镜款式',
  `GLASS_COLOR` varchar(255) NULL DEFAULT NULL COMMENT '眼镜颜色',
  `SIMILARITY` double NULL DEFAULT 0 COMMENT '相似度',
  `reliability` double NULL DEFAULT 0 COMMENT '可靠度 0-1',
  `SOURCE` int(11) NULL DEFAULT 0 COMMENT '1 数据来自前端，0来自ai',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '人脸检测信息表' ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_kprm_person
-- ----------------------------
CREATE TABLE `vas_kprm_person`  (
  `ID` varchar(64) NOT NULL COMMENT '人员ID',
  `NAME` varchar(255) NULL DEFAULT NULL COMMENT '人员姓名',
  `GENDER` int(11) NULL DEFAULT NULL COMMENT '性别 0-未知，1-男，2-女',
  `TYPE` int(11) NULL DEFAULT -1 COMMENT '人员类型',
  `STATUS` int(11) NULL DEFAULT 0 COMMENT '布控状态',
  `TAG` int(11) NULL DEFAULT 0 COMMENT '是否标记',
  `CREDENTIALS_TYPE` int(11) NULL DEFAULT NULL COMMENT '证件类型',
  `CREDENTIALS_NUM` varchar(255) NULL DEFAULT NULL COMMENT '证件号码',
  `PIC_URL` varchar(255) NULL DEFAULT NULL COMMENT '图片地址',
  `PIC_BASE64` varchar(255) NULL DEFAULT NULL COMMENT '图片Base64编码',
  `ADDRESS` varchar(255) NULL DEFAULT NULL COMMENT '地址',
  `HOMETOWN` varchar(255) NULL DEFAULT NULL COMMENT '户籍',
  `BIRTHDAY` varchar(64) NULL DEFAULT NULL COMMENT '出生日期',
  `PHONE` varchar(64) NULL DEFAULT NULL COMMENT '电话',
  `ETHIC_CODE` varchar(255) NULL DEFAULT NULL COMMENT '民族代码',
  `IS_DRIVER` int(11) NULL DEFAULT NULL COMMENT '是否驾驶员 0-否，1-是，-1-不确定',
  `IS_FOREIGNER` int(11) NULL DEFAULT NULL COMMENT '是否涉外人员 0-否，1-是，-1-不确定',
  `IMMIGRANT_TYPECODE` varchar(255) NULL DEFAULT NULL COMMENT '出入境人员类别代码',
  `IS_SUSPECTED_TERRORIST` int(11) NULL DEFAULT NULL COMMENT '是否涉恐人员 0-否，1-是，1--不确定',
  `SUSPECTED_TERRORIST_NUMBER` varchar(255) NULL DEFAULT NULL COMMENT '涉恐人员编号',
  `IS_CRIMINAL_INVOLVED` int(11) NULL DEFAULT NULL COMMENT '是否涉案人员 0-否，1-是，-1-不确定',
  `ESCAPED_CRIMINAL_NUMBER` varchar(255) NULL DEFAULT NULL COMMENT '在逃人员编号',
  `IS_DETAINEES` int(11) NULL DEFAULT NULL COMMENT '是否在押人员 0-否，1-是，-1-不确定',
  `DETENTION_HOUSE_CODE` varchar(255) NULL DEFAULT NULL COMMENT '看守所编码',
  `IS_SUSPICIOUS_PERSON` int(11) NULL DEFAULT NULL COMMENT '是否可疑人 0-否，1-是，-1-不确定',
  `OTHER_FEATURE` varchar(255) NULL DEFAULT NULL COMMENT '体貌特征',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `REMARK` varchar(255) NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '人员信息表' ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_kprm_person_alarm
-- ----------------------------
CREATE TABLE `vas_kprm_person_alarm`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `PERSON_ID` varchar(64) NULL DEFAULT NULL COMMENT '人员ID',
  `CAMERA_ID` varchar(64) NULL DEFAULT NULL COMMENT '摄像头ID',
  `FACE_DETECT_ID` varchar(64) NULL DEFAULT NULL COMMENT '抓拍UUID',
  `DEPLOY_ID` int(11) NULL DEFAULT NULL COMMENT '布控单ID',
  `ALARM_TIME` datetime(0) NULL DEFAULT NULL COMMENT '告警时间',
  `SIMILARITY` double NULL DEFAULT 0 COMMENT '相似度',
  `ALARM_TYPE` int(11) NULL DEFAULT NULL COMMENT '告警类型',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '人员报警表' ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_kprm_person_cluster
-- ----------------------------
CREATE TABLE `vas_kprm_person_cluster`  (
  `ID` varchar(64) NOT NULL COMMENT '聚类人脸ID',
  `DETECT_ID` varchar(64) NOT NULL COMMENT '抓拍UUID',
  `GENDER` int(11) NULL DEFAULT NULL COMMENT '性别 0-未知，1-男，2-女',
  `AGE_RANGE` int(11) NULL DEFAULT NULL COMMENT '年龄段：0-未知，1-小孩，2-青年，3-中年，4-老年',
  `TAG` int(11) NULL DEFAULT NULL COMMENT '是否标记',
  `IS_PYRAMID` int(11) NULL DEFAULT NULL COMMENT '是否传销 0-否 1-是',
  `CAPTURE_URI` varchar(255) NOT NULL COMMENT '抓拍肩膀以上图片地址',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '初次创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '人员聚类库' ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_kprm_person_lib_map
-- ----------------------------
CREATE TABLE `vas_kprm_person_lib_map`  (
  `LIB_ID` varchar(64) NOT NULL COMMENT '静态库ID',
  `PERSON_ID` varchar(64) NOT NULL COMMENT '人员ID',
  PRIMARY KEY (`LIB_ID`, `PERSON_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '静态库和人员关系表' ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_kprm_static_lib
-- ----------------------------
CREATE TABLE `vas_kprm_static_lib`  (
  `ID` varchar(64) NOT NULL COMMENT '静态库ID',
  `NAME` varchar(64) NULL DEFAULT NULL COMMENT '静态库名称',
  `CODE` varchar(64) NULL DEFAULT NULL COMMENT '唯一编码',
  `TYPE` int(11) NULL DEFAULT 0 COMMENT '是否系统库 1：是 0：否',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `CODE`(`CODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '静态库表' ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_kprm_sys_area
-- ----------------------------
CREATE TABLE `vas_kprm_sys_area`  (
  `id` int(50) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `code` varchar(20) NOT NULL DEFAULT '' COMMENT '行政区划编码',
  `parent_code` varchar(20) NULL DEFAULT NULL COMMENT '父级行政区划编码',
  `short_code` varchar(20) NULL DEFAULT NULL,
  `parent_short_code` varchar(20) NULL DEFAULT NULL,
  `name` varchar(128) NULL DEFAULT NULL COMMENT '名称',
  `grade` char(1) NULL DEFAULT NULL COMMENT '等级 省1 市2 县3',
  `province` varchar(64) NULL DEFAULT NULL COMMENT '省',
  `city` varchar(64) NULL DEFAULT NULL COMMENT '市',
  `district` varchar(64) NULL DEFAULT NULL COMMENT '县/区',
  `full_name` varchar(256) NULL DEFAULT NULL COMMENT '行政区划全称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '全国行政区划-省市县三级(2018最新版)' ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_license_sys
-- ----------------------------
CREATE TABLE `vas_license_sys`  (
  `ID` varchar(32) NOT NULL COMMENT '授权编号',
  `GROUP_ID` varchar(32) NULL DEFAULT NULL COMMENT '授权组织ID',
  `USER_ID` varchar(64) NULL DEFAULT NULL COMMENT '授权用户ID',
  `GRANT_GATEWAYS` varchar(255) NULL DEFAULT NULL COMMENT '授权网关数',
  `GRANT_CHANNELS` varchar(255) NULL DEFAULT NULL COMMENT '授权通道数',
  `GRANT_START_TIME` varchar(255) NULL DEFAULT NULL COMMENT '授权开始时间',
  `GRANT_END_TIME` varchar(255) NULL DEFAULT NULL COMMENT '授权结束时间',
  `ENCRYPTION_TYPE` varchar(255) NULL DEFAULT NULL COMMENT '加密类型',
  `LIC_TYPE` int(11) NULL DEFAULT NULL COMMENT '授权类型',
  `LIC_MODE` int(11) NULL DEFAULT NULL COMMENT '授权方式',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT='授权表'  ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_menu
-- ----------------------------
CREATE TABLE `vas_menu`  (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `NAME` varchar(255) NULL DEFAULT NULL COMMENT '名称',
  `MENU_CODE` varchar(255) NULL DEFAULT NULL COMMENT '菜单编码',
  `PATH_NAME` varchar(255) NULL DEFAULT NULL COMMENT '访问路径',
  `ICON` varchar(255) NULL DEFAULT NULL COMMENT '图标',
  `APP_CODE` varchar(255) NULL DEFAULT NULL COMMENT '应用编码',
  `TYPE` int(11) NULL DEFAULT NULL COMMENT '菜单类型0：按钮 1：菜单',
  `PARENT_ID` varchar(255) NULL DEFAULT NULL COMMENT '父节点ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单信息表' ROW_FORMAT = Dynamic|

-- ----------------------------

-- ----------------------------
-- Table structure for vas_stream
-- ----------------------------
CREATE TABLE `vas_stream`  (
  `ID` varchar(64) NOT NULL COMMENT '主键',
  `USER_NAME` varchar(255) NULL DEFAULT NULL COMMENT '用户名',
  `STREAM_NAME` varchar(255) NULL DEFAULT NULL COMMENT '直播流名称',
  `CHANNEL_ID` varchar(255) NULL DEFAULT NULL COMMENT '通道表ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '直播流管理表' ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_user_func_map
-- ----------------------------
CREATE TABLE `vas_user_func_map`  (
  `USER_ID` varchar(255) NOT NULL DEFAULT '' COMMENT '用户ID',
  `MENU_CODES` text NULL COMMENT '菜单code',
  PRIMARY KEY (`USER_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户功能映射表' ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_user_info
-- ----------------------------
CREATE TABLE `vas_user_info`  (
  `ID` varchar(64) NOT NULL COMMENT '用户ID',
  `GROUP_ID` varchar(32) NULL DEFAULT NULL COMMENT '组织ID',
  `NAME` varchar(255) NULL DEFAULT NULL COMMENT '用户名',
  `WX_NAME` varchar(255) NULL DEFAULT NULL COMMENT '微信号',
  `NICK_NAME` varchar(255) NULL DEFAULT NULL COMMENT '昵称',
  `PASSWORD` varchar(255) NULL DEFAULT NULL COMMENT '密码',
  `PHONE` varchar(32) NULL DEFAULT NULL COMMENT '手机号',
  `EMAIL` varchar(255) NULL DEFAULT NULL COMMENT '邮箱',
  `ID_CARD` varchar(64) NULL DEFAULT NULL COMMENT '身份证号',
  `IMAGE` varchar(255) NULL DEFAULT NULL COMMENT '照片',
  `HEADER_IMG` varchar(255) NULL DEFAULT NULL COMMENT '头像',
  `TYPE` int(11) NULL DEFAULT NULL COMMENT '用户类型',
  `LOGIN_TIMES` int(11) NULL DEFAULT NULL COMMENT '登录次数',
  `LAST_LOGIN_IP` varchar(255) NULL DEFAULT NULL COMMENT '上次登录IP',
  `LAST_LOGIN_TIME` datetime(0) NULL DEFAULT NULL COMMENT '上次登录时间',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `REMARK` varchar(255) NULL DEFAULT NULL COMMENT '备注',
  `DEPT_ID` varchar(255) NULL DEFAULT NULL COMMENT '部门id',
  `REAL_NAME` varchar(255) NULL DEFAULT NULL COMMENT '真实姓名',
  `CREATE_USER_ID` varchar(255) NULL DEFAULT NULL COMMENT '创建用户ID',
  `POLICE_CODE` varchar(255) NULL DEFAULT NULL COMMENT '警员编号',
  `SHORT_LETTER` varchar(255) NULL DEFAULT NULL COMMENT '真实姓名缩写',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `NAME`(`NAME`) USING BTREE,
  UNIQUE INDEX `PHONE`(`PHONE`) USING BTREE,
  UNIQUE INDEX `EMAIL`(`EMAIL`) USING BTREE,
  UNIQUE INDEX `ID_CARD`(`ID_CARD`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic|

-- ----------------------------
-- Table structure for vas_user_res_map
-- ----------------------------
CREATE TABLE `vas_user_res_map`  (
  `USER_ID` varchar(255) NOT NULL DEFAULT '' COMMENT '用户ID',
  `TREE_NODES` text NULL COMMENT '组织树ID',
  PRIMARY KEY (`USER_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户资源映射表' ROW_FORMAT = Dynamic|



CREATE TABLE `vas_strategy`  (
`id` varchar(200) NOT NULL  COMMENT '自增主键',
`flag` varchar(200) NULL DEFAULT NULL COMMENT '存放时段 0：每天，1：每周 ',
`schemename` varchar(200) NULL DEFAULT NULL COMMENT '方案名称',
`cycle` int(200) NULL DEFAULT NULL COMMENT '存放周期',
`firstcc` varchar(200) NULL DEFAULT NULL COMMENT '每天的存储方案,有跨天的情况第二天的时间放在secondcc中',
`secondcc` varchar(200) NULL DEFAULT NULL COMMENT '每天的存储方案,有跨天的情况第二天的时间放在secondcc中',
`mon` varchar(200) NULL DEFAULT NULL COMMENT '周一时间',
`tuse` varchar(200) NULL DEFAULT NULL COMMENT '周二时间',
`thr` varchar(200) NULL DEFAULT NULL COMMENT '周三时间',
`fou` varchar(200) NULL DEFAULT NULL COMMENT '周四时间',
`fri` varchar(200) NULL DEFAULT NULL COMMENT '周五时间',
`sate` varchar(200) NULL DEFAULT NULL COMMENT '周六时间',
`sun` varchar(200) NULL DEFAULT NULL COMMENT '周日时间',
`createTime` varchar(200) NULL DEFAULT NULL COMMENT '时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT='视频存储方案表' ROW_FORMAT = Dynamic|

CREATE TABLE `vas_strategy_channel`  (
 `id` varchar(200) NOT NULL  COMMENT '自增主键',
 `strategy_id` varchar(200) NULL DEFAULT NULL COMMENT '方案表ID',
 `channel_name` varchar(200) NULL DEFAULT NULL COMMENT '通道名称',
 `channel_number` varchar(200) NULL DEFAULT NULL COMMENT '通道号',
 `channel_index` varchar(200) NULL DEFAULT NULL COMMENT '通道index',
 `device_index` varchar(200) NULL DEFAULT NULL COMMENT '设备ID',
 `gateway_index` varchar(200) NULL DEFAULT NULL COMMENT '网关index',
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT='存储通道关联表' ROW_FORMAT = Dynamic|

CREATE TABLE `vas_code`  (
`code_id` varchar(200) NOT NULL COMMENT '对应各个表的主键',
`code` varchar(200) NOT NULL  COMMENT 'code值，用于区分各个租户'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT='关联code表' ROW_FORMAT = Dynamic|

CREATE TABLE `vas_user_code`  (
`id` varchar(200) NOT NULL  COMMENT '主键',
`user_code` varchar(200) NOT NULL  COMMENT '租户code值',
`user_code_name` varchar(200) NOT NULL  COMMENT '名称',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT='租户code码表' ROW_FORMAT = Dynamic|


CREATE TABLE `vas_money_type`  (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `histroy_count` double(255,4) NULL DEFAULT NULL COMMENT '历史视频回放量(G)',
  `histroy_price` double(255,2) NULL DEFAULT NULL COMMENT '历史视频回放单价',
  `remark` varchar(255) NULL DEFAULT NULL COMMENT '历史视频回放（数据读取0.05元/GB，流量0.16元/GB )',
  `usercode` varchar(255) NULL DEFAULT NULL COMMENT '租户码值',
  `type` char(2) NULL DEFAULT NULL COMMENT '(对应vas_financial_details表的type)4:历史存储',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '收费类型表' ROW_FORMAT = Dynamic|


CREATE TABLE `vas_collect_channel`  (
 `id` varchar(200) NOT NULL  COMMENT '主键',
 `user_id` varchar(200) NULL DEFAULT NULL COMMENT '登陆人ID主键',
 `channel_id` int(11) NULL DEFAULT NULL COMMENT '通道Id',
 `channel_name` varchar(200) NULL DEFAULT NULL COMMENT '通道名称',
 `channel_number` int(11) NULL DEFAULT NULL COMMENT '通道号',
 `device_index` varchar(200) NULL DEFAULT NULL COMMENT '设备index',
 `device_name` varchar(200) NULL DEFAULT NULL COMMENT '设备名称',
 `gateway_index` varchar(200) NULL DEFAULT NULL COMMENT '网关index',
 `user_code` varchar(200) NULL DEFAULT NULL COMMENT '租户user_code',
 `nickname` varchar(200) NULL DEFAULT NULL COMMENT '昵称',
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT='通道收藏表' ROW_FORMAT = Dynamic|

CREATE TABLE `vas_user_nickname`  (
 `id` varchar(200) NOT NULL  COMMENT '主键',
 `user_id` varchar(200) NULL DEFAULT NULL COMMENT '登陆人ID主键',
 `nickname` varchar(255) NULL DEFAULT NULL COMMENT '昵称',
 `imgurl` varchar(200) NULL DEFAULT NULL COMMENT '头像',
 `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
 `user_code` varchar(200) NULL DEFAULT NULL COMMENT '租户user_code',
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT='用户昵称头像表' ROW_FORMAT = Dynamic|


CREATE TABLE `vas_feedback`  (
 `id` varchar(200) NOT NULL  COMMENT '主键',
 `user_id` varchar(200) NULL DEFAULT NULL COMMENT '登陆人ID主键',
 `content` varchar(200) NULL DEFAULT NULL COMMENT '意见反馈',
 `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
 `user_code` varchar(200) NULL DEFAULT NULL COMMENT '租户user_code',
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT='意见反馈表' ROW_FORMAT = Dynamic|

CREATE TABLE `vas_log`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_name` varchar(255) NULL DEFAULT NULL COMMENT '登录用户',
  `remark` varchar(255) NULL DEFAULT NULL COMMENT '用户操作模块',
  `CREATE_TIME` varchar(255) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日志表' ROW_FORMAT = Dynamic|

CREATE TABLE `vas_user_img`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` varchar(255) NULL DEFAULT NULL COMMENT '用户id',
  `sysname` varchar(255) NULL DEFAULT NULL COMMENT '系统名称',
  `baseimg` varchar(255) NULL DEFAULT NULL COMMENT 'base64图片',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户logo表' ROW_FORMAT = Dynamic|

CREATE TABLE `vas_check_work`  (
`ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
`user_code` varchar(255) NULL DEFAULT NULL COMMENT '租户code',
`staticlib_id` varchar(255) NULL DEFAULT NULL COMMENT '人员库id',
`start_work` varchar(255) NULL DEFAULT NULL COMMENT '考勤开始时间',
`end_work` varchar(255) NULL DEFAULT NULL COMMENT '考勤结束时间',
`work_day` varchar(255) NULL DEFAULT NULL COMMENT '工作时间（以逗号隔开）',
`status` char(2) NULL DEFAULT NULL COMMENT '状态 0：启用，1：不启用',
`CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '考勤设置表' ROW_FORMAT = Dynamic|

CREATE TABLE `vas_work_day`  (
`ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
`work_id` int(11) NULL DEFAULT NULL COMMENT '考勤表id',
`user_code` varchar(255) NULL DEFAULT NULL COMMENT '租户code',
`person_id` varchar(255) NULL DEFAULT NULL COMMENT '人员id',
`staticlib_id` varchar(255) NULL DEFAULT NULL COMMENT '人员库id',
`start` varchar(255) NULL DEFAULT NULL COMMENT '签到',
`end` varchar(255) NULL DEFAULT NULL COMMENT '签退',
`start_faceId` varchar(255) NULL DEFAULT NULL COMMENT '签到图片id',
`end_faceId` varchar(255) NULL DEFAULT NULL COMMENT '签退图片id',
`status` char(2) NULL DEFAULT NULL COMMENT '状态 0：启用，1：迟到，2：早退，3：迟到、早退，4：旷工',
`CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '考勤表' ROW_FORMAT = Dynamic|


CREATE TABLE `vas_huikan_year`  (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `huikan_count` double(255,4) NULL DEFAULT NULL COMMENT '回看路数',
  `huikan_price` double(255,2) NULL DEFAULT NULL COMMENT '回看单价',
  `remark` varchar(255) NULL DEFAULT NULL COMMENT '回看license费用/年',
  `usercode` varchar(255) NULL DEFAULT NULL COMMENT '租户码值',
  `type` char(2) NULL DEFAULT NULL COMMENT '(对应vas_financial_details表的type)5:回看license费用/年',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '回看license费用/年表' ROW_FORMAT = Dynamic|
