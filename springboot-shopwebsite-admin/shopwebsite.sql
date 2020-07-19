/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : shopwebsite

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-07-12 22:20:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `addressid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `getname` varchar(20) NOT NULL,
  `getnum` int(11) NOT NULL,
  `getaddress` varchar(255) NOT NULL,
  PRIMARY KEY (`addressid`),
  KEY `FK_ADDRESS_REFERENCE_USER` (`userid`),
  CONSTRAINT `FK_ADDRESS_REFERENCE_USER` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('1', '1', '张三', '1234567890', '四川省成都市');

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `adminid` int(11) NOT NULL,
  `adminname` varchar(20) DEFAULT NULL,
  `adminpassword` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`adminid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'adminNo1', '123');

-- ----------------------------
-- Table structure for buyshopping
-- ----------------------------
DROP TABLE IF EXISTS `buyshopping`;
CREATE TABLE `buyshopping` (
  `shoppinggoodsid` int(11) NOT NULL,
  `userid` int(11) DEFAULT NULL,
  `goodsid` int(11) DEFAULT NULL,
  PRIMARY KEY (`shoppinggoodsid`),
  KEY `FK_BUYSHOPP_REFERENCE_GOODS` (`goodsid`),
  KEY `FK_BUYSHOPP_REFERENCE_USER` (`userid`),
  CONSTRAINT `FK_BUYSHOPP_REFERENCE_GOODS` FOREIGN KEY (`goodsid`) REFERENCES `goods` (`goodsid`),
  CONSTRAINT `FK_BUYSHOPP_REFERENCE_USER` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of buyshopping
-- ----------------------------
INSERT INTO `buyshopping` VALUES ('1', '1', '2020070001');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goodsid` int(11) NOT NULL,
  `adminid` int(11) NOT NULL,
  `goodsname` varchar(128) NOT NULL,
  `price` varchar(20) NOT NULL,
  `picture` varchar(255) NOT NULL,
  `goodsnumber` int(11) NOT NULL,
  PRIMARY KEY (`goodsid`),
  KEY `FK_GOODS_REFERENCE_ADMIN` (`adminid`),
  CONSTRAINT `FK_GOODS_REFERENCE_ADMIN` FOREIGN KEY (`adminid`) REFERENCES `admin` (`adminid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('17778', '1', '达成巴哈尽快', '111', '11', '1111');
INSERT INTO `goods` VALUES ('2020070001', '1', '华丰三鲜伊面桶面整箱网红泡面 怀旧速食方便面快餐面多口味12桶', '11元', 'image/华丰三鲜伊面.png', '67');
INSERT INTO `goods` VALUES ('2020070002', '1', '幸运方便面整箱装包邮批发红烧排骨面袋装安徽泡面干吃干脆面零食', '35元', 'image/幸运方便面整箱.png', '56');
INSERT INTO `goods` VALUES ('2020070004', '1', '统一汤达人方便面12杯装桶装整箱装泡面日式豚骨酸辣罗宋汤面速食', ' 50元', 'image/统一汤达人方便面12杯.png', '100');
INSERT INTO `goods` VALUES ('2020070005', '1', '韩国进口三养火鸡面5袋超辣方便面辣鸡面特辣速食泡面拉面干拌面', '22.9元', 'image/韩国进口三养火鸡面5袋.png', '37');
INSERT INTO `goods` VALUES ('2020070006', '1', '统一汤达人方便面30包袋装混合整箱批发 日式豚骨拉面罗宋汤泡面', '82元', 'image/统一汤达人方便面30包.png', '78');
INSERT INTO `goods` VALUES ('2020070007', '1', '幸运方便面蟹黄面蟹皇方便面整箱装批发干吃面袋装干脆面泡面75g', '37.8元', 'image/幸运方便面蟹黄面蟹皇方便面.png', '45');
INSERT INTO `goods` VALUES ('2020070008', '1', '韩国进口方便面 三养火鸡面超辣变态辣泡面奶油炸酱拌面140g*10包 ', '45元', 'image/韩国进口方便面.png ', '36');
INSERT INTO `goods` VALUES ('2020070009', '1', '康师傅方便面整箱大食桶装红烧牛肉混合多口味泡面速食旗舰店官网', '25.9元', 'image/康师傅方便面整箱.png', '25');
INSERT INTO `goods` VALUES ('2020070010', '1', '阿宽红油面皮10袋装凉皮面皮拌面懒人速食食品宿舍即食方便面泡面 ', '11.9元', 'image/阿宽红油面皮10袋.png', '58');
INSERT INTO `goods` VALUES ('2020070011', '1', '老北京方便面南街村整箱袋装河南特产泡面65g麻辣包邮干吃干脆面', '34元', 'image/老北京方便面.png', '47');
INSERT INTO `goods` VALUES ('2020070012', '1', '韩国进口三养超辣火鸡面干拌面速食泡面方便面整箱批发140g*40袋', '95元', 'image/韩国进口三养超辣火鸡面.png', '48');
INSERT INTO `goods` VALUES ('2020070013', '1', '农心辛拉面韩国进口方便面辣白菜泡面香菇牛肉炸酱面速食食品袋装', '21.9元', 'image/农心辛拉面.png', '75');
INSERT INTO `goods` VALUES ('2020070014', '1', '韩国进口三养超辣火鸡面干拌面速食泡面方便面整箱批发140g*40袋', '95元', 'image/韩国进口三养超辣火鸡面.png', '48');
INSERT INTO `goods` VALUES ('2020070015', '1', '华丰三鲜伊面24包整箱装鸡汁泡面袋装干吃速食食品怀旧老式方便面', '29.9元', 'image/华丰三鲜伊面24包.png', '38');
INSERT INTO `goods` VALUES ('2020070017', '1', '火鸡面超辣网红泡面变态辣干吃面酱料国产拌面拉面条组合118g*5袋', '23.8元', 'image/火鸡面超辣网红.png', '48');
INSERT INTO `goods` VALUES ('2020070018', '1', '百亿补贴【三只松鼠_酸辣粉130gx5桶】桶装速食方便面泡面', '64.5元', 'image/三只松鼠_酸辣粉.png', '37');
INSERT INTO `goods` VALUES ('2020070019', '1', '五谷道场非油炸方便面番茄牛腩肥牛20味20袋速食即食不辣泡面组合', '59.8元', 'image/五谷道场非油炸方便面.png', '29');
INSERT INTO `goods` VALUES ('2020070020', '1', '杨掌柜暖心螺新派螺蛳粉粉面搭档酸爽酸笋礼品非油炸拉面粉丝', '32.8元', 'image/杨掌柜暖心螺新派螺蛳粉.png', '17');
INSERT INTO `goods` VALUES ('2020070501', '1', '今麦郎方便面一袋半酸菜葱香红烧牛肉香辣16袋装整箱速食泡面包邮', '22元', 'image/今麦郎方便面一袋半酸菜.png', '99');
INSERT INTO `goods` VALUES ('2020070502', '1', '白象方便面传统经典20袋装红烧香辣老坛超大面饼整箱速食泡面', '28元', 'image/白象方便面传统经典20袋.png', '34');
INSERT INTO `goods` VALUES ('2020070503', '1', '康师傅方便面整箱袋装混合多口味红烧牛肉泡面速食夜宵', '47.5元', 'image/康师傅方便面整箱袋装.png', '55');
INSERT INTO `goods` VALUES ('2020070504', '1', '酸辣粉桶装嗨吃家柳州螺蛳粉整箱火鸡方便面泡面速食夜宵粉丝米线', '30元', 'image/酸辣粉桶装嗨吃家柳州螺蛳粉.png', '55');
INSERT INTO `goods` VALUES ('2020070505', '1', '莫小仙正宗重庆酸辣粉桶装早餐速食懒人食品方便泡面整箱方便面', '29.8元', 'image/莫小仙正宗重庆酸辣粉桶装.png', '55');
INSERT INTO `goods` VALUES ('2020070506', '1', '韩太火鸡面10包国产方便面网红速食超辣炸酱料干拌面袋装泡面整箱', '30元', 'image/韩太火鸡面10包国产方便面.png', '55');
INSERT INTO `goods` VALUES ('2020070507', '1', '杨掌柜高人拉面非油炸方便泡面拉面速食夜宵休闲加班组合', '40元', 'image/杨掌柜高人拉面非油炸.png', '55');
INSERT INTO `goods` VALUES ('2020070508', '1', '嗨吃家酸辣粉桶装重庆正宗泡面方便面整箱速食火鸡面螺蛳粉丝米线', '9.9元', 'image/嗨吃家酸辣粉桶装.png', '55');
INSERT INTO `goods` VALUES ('2020070509', '1', '康师傅方便面整箱袋装好滋味混搭老坛酸菜红烧牛肉泡面', '17.8元', 'image/康师傅方便面整箱袋.png', '55');
INSERT INTO `goods` VALUES ('2020070510', '1', '华丰食品旗舰店三鲜伊面原味泡面老式怀旧速食方便面整箱装24袋装', '39元', 'image/华丰三鲜面24袋.png', '55');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `ordersid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `goodsid` int(11) NOT NULL,
  `orderdate` date NOT NULL,
  `orderstate` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`ordersid`),
  KEY `FK_orders_REFERENCE_USER` (`userid`),
  KEY `FK_orders_REFERENCE_GOODS` (`goodsid`),
  CONSTRAINT `FK_orders_REFERENCE_GOODS` FOREIGN KEY (`goodsid`) REFERENCES `goods` (`goodsid`),
  CONSTRAINT `FK_orders_REFERENCE_USER` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('2020001', '1', '2020070501', '2020-07-07', '未处理未付款未收货');
INSERT INTO `orders` VALUES ('2020002', '1', '2020070502', '2020-07-07', '已处理已付款未收货');
INSERT INTO `orders` VALUES ('2020003', '2', '2020070504', '2020-07-07', '未处理已付款未收货');
INSERT INTO `orders` VALUES ('2020004', '2', '2020070506', '2020-07-07', '未处理已付款未收货');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL,
  `adminid` int(11) DEFAULT NULL,
  `username` varchar(20) NOT NULL,
  `userpassword` varchar(20) NOT NULL,
  PRIMARY KEY (`userid`),
  KEY `FK_USER_REFERENCE_ADMIN` (`adminid`),
  CONSTRAINT `FK_USER_REFERENCE_ADMIN` FOREIGN KEY (`adminid`) REFERENCES `admin` (`adminid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0', '1', '1111', 'hhhhhhhhhh');
INSERT INTO `user` VALUES ('1', '1', 'userNo1', '123');
INSERT INTO `user` VALUES ('2', '1', 'userNo2', '123');
INSERT INTO `user` VALUES ('3', '1', 'adminNo4', '11111111111111');
INSERT INTO `user` VALUES ('5', '1', '1111122', '22222');
INSERT INTO `user` VALUES ('7', '1', '12344', '111111');
