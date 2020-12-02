# 创建订单表的sql
CREATE TABLE `DemoOrder`  (
  `Id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `OrderId` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Money` int(11) UNSIGNED NOT NULL DEFAULT 0,
  `ItemId` int(11) UNSIGNED NOT NULL,
  `CreateTime` int(11) NOT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;