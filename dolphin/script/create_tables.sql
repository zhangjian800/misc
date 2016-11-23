####[client]
[client]
default-character-set=utf8
[mysqld]
datadir=/var/lib/mysql
socket=/var/lib/mysql/mysql.sock
user=mysql
# Disabling symbolic-links is recommended to prevent assorted security risks
symbolic-links=0

default-character-set=utf8 
init_connect='SET NAMES utf8' 
###

###########System Level######################
--Change Charset
ALTER DATABASE `sms` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;


############Code Table########################
CREATE TABLE `t_province` (
  `provicecode` varchar(40) NOT NULL,
  `provicedesc` varchar(40) NOT NULL,
  `status` varchar(40) NOT NULL DEFAULT 'Enable',
   PRIMARY KEY (`provicecode`),
   UNIQUE KEY `provicecode` (`provicecode`)
) DEFAULT   CHARSET=utf8;

CREATE TABLE `t_city` (
  `citycode` varchar(40) NOT NULL,
  `provicecode` varchar(40) NOT NULL,
  `citydesc` varchar(40) NOT NULL,
   PRIMARY KEY (`citycode`),
   UNIQUE KEY `citycode` (`citycode`)
) DEFAULT   CHARSET=utf8;;

CREATE TABLE `t_phone_num` (
   `numID` int(11) NOT NULL AUTO_INCREMENT,
  `phoneprefix` varchar(40) NOT NULL,
  `citycode` varchar(40) NOT NULL,
  `simtype` varchar(40) NOT NULL,
   PRIMARY KEY (`numID`),
   UNIQUE KEY `numID` (`numID`)
) DEFAULT CHARSET='utf8';

CREATE TABLE `t_version` (
  `versioncode` varchar(40) NOT NULL,
  `versiondesc` varchar(40) NULL,
  `status` varchar(40) NOT NULL DEFAULT 'Enable',
   PRIMARY KEY (`versioncode`),
   UNIQUE KEY `versioncode` (`versioncode`)
)  DEFAULT   CHARSET=utf8;

INSERT INTO t_version (versioncode,versiondesc,status ) values ('7.0.1.k','7.0.1.k','Enable');
INSERT INTO t_version (versioncode,versiondesc,status ) values ('7.0.2.k','7.0.2.k','Enable');
INSERT INTO t_version (versioncode,versiondesc,status ) values ('7.0.3.k','7.0.3.k','Enable');


INSERT INTO t_version (versioncode,versiondesc,status ) values ('7.1.1.k','7.1.1.k','Enable');
INSERT INTO t_version (versioncode,versiondesc,status ) values ('7.1.3.k','7.1.3.k','Enable');
INSERT INTO t_version (versioncode,versiondesc,status ) values ('7.5.0.k','7.5.0.k','Enable');

===
INSERT INTO t_version (versioncode,versiondesc,status ) values ('7.0.0.k','7.0.0.k','Enable');
INSERT INTO t_version (versioncode,versiondesc,status ) values ('7.0.1.k','7.0.1.k','Enable');
INSERT INTO t_version (versioncode,versiondesc,status ) values ('7.0.2.k','7.0.2.k','Enable');
INSERT INTO t_version (versioncode,versiondesc,status ) values ('7.0.3.k','7.0.3.k','Enable');
INSERT INTO t_version (versioncode,versiondesc,status ) values ('7.0.4.k','7.0.4.k','Enable');
INSERT INTO t_version (versioncode,versiondesc,status ) values ('7.0.5.k','7.0.5.k','Enable');


INSERT INTO t_version (versioncode,versiondesc,status ) values ('7.0.6.k','7.0.6.k','Enable');
INSERT INTO t_version (versioncode,versiondesc,status ) values ('7.0.7.k','7.0.7.k','Enable');
INSERT INTO t_version (versioncode,versiondesc,status ) values ('7.0.8.k','7.0.8.k','Enable');
INSERT INTO t_version (versioncode,versiondesc,status ) values ('7.0.9.k','7.0.9.k','Enable');
INSERT INTO t_version (versioncode,versiondesc,status ) values ('7.1.0.k','7.1.0.k','Enable');
INSERT INTO t_version (versioncode,versiondesc,status ) values ('7.1.1.k','7.1.1.k','Enable');
INSERT INTO t_version (versioncode,versiondesc,status ) values ('7.1.2.k','7.1.2.k','Enable');
INSERT INTO t_version (versioncode,versiondesc,status ) values ('7.1.3.k','7.1.3.k','Enable');
INSERT INTO t_version (versioncode,versiondesc,status ) values ('7.5.0.k','7.5.0.k','Enable');



CREATE TABLE `t_phone_product` (
  `productcode` varchar(40) NOT NULL,
  `productdesc` varchar(40) NOT NULL,
   `version` varchar(40) NOT NULL,
  `status` varchar(40) NOT NULL DEFAULT 'Enable'
) DEFAULT   CHARSET=utf8;

INSERT INTO t_phone_product (productcode,productdesc,version, status ) values ('M6_BASE.PDA3.0.HVGA.76P.V9.16','M6_BASE.PDA3.0.HVGA.76P.V9.16','7.0.5.k', 'Enable');

#################System basic table########################
CREATE TABLE `t_user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(40) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(20) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updateTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(40) DEFAULT NULL,
  `updater` varchar(40) DEFAULT NULL,
  `status` varchar(20) DEFAULT 'Normal',
  
  PRIMARY KEY (`userID`),
  UNIQUE KEY `userID` (`userID`),
  UNIQUE KEY `email` (`email`)
) 

CREATE TABLE `t_system_conf` (
  `confID` int(11) NOT NULL AUTO_INCREMENT,
  `confcode` varchar(60) DEFAULT 'SYSTEM_RULE',
  `maxmonthhits` int(11) NOT NULL default 50,
  `maxmonthcharge` int(11) NOT NULL default 10,
  `maxadsbuffersize` int(11) NOT NULL default 500,
  `defaultintevaldays` int(11) NOT NULL default 7,
  `blockintervaldays` int(11) NOT NULL default 30,
  `begintime` varchar(40) NULL,
  `endtime` varchar(40) NULL,
  `isblockworkingtime` int(1) NOT NULL default 1,
  `isblockweekend` int(1) NOT NULL default 1,
  `defautinteval4noads` int(11) NOT NULL default 30,
   `sizeofpercall` int(11) NOT NULL default 100,
   `maxadsdata` int(11) NOT NULL default 1000,
   PRIMARY KEY (`confID`),
   UNIQUE KEY `confID` (`confID`)
) DEFAULT CHARSET=utf8;


INSERT INTO t_system_conf (confcode,maxmonthhits,maxmonthcharge, maxadsbuffersize, defaultintevaldays,blockintervaldays,begintime,endtime )
 values ('SYSTEM_RULE',50,10, 500, 7,30, '8:00' ,'21:00');

update t_system_conf  set isblockworkingtime = '0';

update t_system_conf  set isblockworkingtime = '1';

update t_system_conf  set sizeofpercall = '0' , maxadsbuffersize = 5;

		
#########################################################
CREATE TABLE `t_access` (
  `accessID` int(10) NOT NULL AUTO_INCREMENT,
  `imsi` varchar(40) ,
  `producttype` varchar(40) DEFAULT NULL,
  `version` varchar(40) DEFAULT NULL,
  `province` varchar(60) DEFAULT NULL,
  `city` varchar(60) DEFAULT NULL,
  `smsc` varchar(40) DEFAULT NULL,
  `pid` varchar(40) DEFAULT NULL,
  `lbs` varchar(40) DEFAULT NULL,
  `platformtype` varchar(40) DEFAULT NULL,
  `accesstime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `accesstimeym` varchar(60) DEFAULT NULL,
  `requeststring` VARCHAR(4096) DEFAULT NULL,  
  `respscriptcontent` VARCHAR(8192) DEFAULT NULL,
  `respstatus` varchar(40) DEFAULT NULL,
  `respappid` int(10) DEFAULT NULL,
  `gapdays` int(10) DEFAULT NULL,
  `adsdetailID` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`accessID`),
  UNIQUE KEY `accessID` (`accessID`)
) DEFAULT CHARSET='utf8';

ALTER TABLE `t_access` ADD INDEX imsi_yyymm ( `imsi`, `accesstimeym` );
ALTER TABLE `t_access` ADD INDEX imsi_yyymm_respstatus ( `imsi`, `accesstimeym`,`respstatus`);
ALTER TABLE `t_access` ADD INDEX respstatus ( `respstatus`);
ALTER TABLE `t_access` ADD INDEX imsi ( `imsi` );


CREATE TABLE `t_phone` (
  `imsi` varchar(40) NOT NULL,
  `producttype` varchar(40) DEFAULT NULL,
  `version` varchar(40) DEFAULT NULL,  
   `smsc` varchar(40) NOT NULL,
  `phoneNo` varchar(60) DEFAULT NULL,
  `province` varchar(60) DEFAULT NULL,
  `city` varchar(60) DEFAULT NULL,
  `publishDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
   `firstaccessTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(40) NOT NULL DEFAULT 'Enable',
  PRIMARY KEY (`imsi`),
  UNIQUE KEY `imsi` (`imsi`)
) DEFAULT CHARSET='utf8';

##This table will be populated by job in first day of each month....
CREATE TABLE `t_access_sum` (
  `imsi` varchar(40) NOT NULL,
  `yearmonth` int(11),
  `maxhits` int(11) NOT NULL default 0,
  `maxcharge` int(11) NOT NULL default 0
) DEFAULT CHARSET='utf8';


#############################################################
CREATE TABLE `t_app` (
  `appID` int(10) NOT NULL AUTO_INCREMENT,
  `apptype` varchar(40) NOT NULL DEFAULT 1,
  `appcode` varchar(40) NOT NULL,
  `appdesc` varchar(40) NOT NULL,
  `version` varchar(40) NOT NULL,
  `appfilename` varchar(64) DEFAULT NULL,
  `status` varchar(20) DEFAULT 'Enable',
  `appstream` blob,
  `creator` varchar(40) DEFAULT NULL,
  `updater` varchar(40) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updateTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`appID`),
  UNIQUE KEY `appID` (`appID`)
);

CREATE TABLE `t_rule` (
  `ruleID` int(10) NOT NULL AUTO_INCREMENT,
  `rulecode` varchar(40) NOT NULL,
  `ruletype` varchar(40) NOT NULL DEFAULT 'CITY',
  `province` varchar(40),
  `city` varchar(40) DEFAULT NULL,  
  `productcode` varchar(40) DEFAULT NULL,
  `version` varchar(40) DEFAULT NULL,
  `pid` varchar(40) DEFAULT NULL,
  `gapdays` int(10) default 7,
  `appID` int(10) DEFAULT NULL,
  `status` varchar(40) NOT NULL DEFAULT 'Enable',
  `intervalday` int(11) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updateTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `creator` varchar(40) DEFAULT NULL,
  `updater` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`ruleID`)
) DEFAULT   CHARSET=utf8;


CREATE TABLE `t_ads` (
  `adsID` int(20) NOT NULL AUTO_INCREMENT,
  `instanceID` varchar(40) DEFAULT NULL,
  `size` int(10) NOT NULL,
  `seq` int(10) NOT NULL,  
  `adsprovider` varchar(120) DEFAULT NULL,
  `adscontent` VARCHAR(8192) DEFAULT NULL,
  `generatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,  
  `status` varchar(40) NOT NULL DEFAULT 'New',
  PRIMARY KEY (`adsID`),
  UNIQUE KEY `adsID` (`adsID`)
) DEFAULT CHARSET='utf8';


CREATE TABLE `t_ads_detail` (
  `detailID` varchar(40) NOT NULL,
  `adsID` int(20) NOT NULL,
  `seq` int(10) NOT NULL,  
  `targetphonenum` VARCHAR(8192) DEFAULT NULL,
  `adscontent` VARCHAR(8192) DEFAULT NULL ,
  `status` varchar(40) NOT NULL DEFAULT 'New',
  PRIMARY KEY (`detailID`),
  UNIQUE KEY `adsID` (`detailID`)  
) DEFAULT CHARSET='utf8';

ALTER TABLE `t_access` ADD COLUMN `intervalmins` int(10) DEFAULT NULL;
update t_access set intervalmins = 119;

ALTER TABLE `t_ads` ADD COLUMN `adsproviderurl` varchar(120) DEFAULT NULL;
ALTER TABLE `t_ads` ADD COLUMN `yearmonth` varchar(120) DEFAULT NULL;
ALTER TABLE `t_ads` ADD COLUMN `generatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP;


update t_ads set yearmonth = '201304';
update t_ads set adsproviderurl = adsprovider where adsprovider != 'TEST';
update t_ads set adsprovider = 'P1' where adsprovider != 'TEST';


ALTER TABLE `t_ads_detail` ADD COLUMN `generatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE `t_ads_detail` ADD COLUMN `adsprovider` varchar(120) DEFAULT NULL;
ALTER TABLE `t_ads_detail` ADD COLUMN `yearmonth` varchar(120) DEFAULT NULL;

update t_ads_detail set yearmonth = '201304';

update t_ads_detail a set yearmonth = '201304',adsprovider = 'TEST' where not exists (
	 select 1 from t_ads b where a.adsID = b.adsID and adsprovider != 'TEST');

update t_ads_detail a set yearmonth = '201304',adsprovider = 'P1' where  not exists (
	 select 1 from t_ads b where a.adsID = b.adsID and adsprovider = 'TEST');


ALTER TABLE `t_ads_detail` ADD INDEX adsprovider_yearmonth ( `adsprovider`,`yearmonth`);


##########################Since 4/11 for calc premium##########################

ALTER TABLE `t_rule` ADD COLUMN `serviceprovider` varchar(120) DEFAULT 'DEFAULT';
 update t_rule set serviceprovider='DEFAULT' where serviceprovider is null;
 
ALTER TABLE `t_app` ADD COLUMN `charge` DECIMAL(5,2) DEFAULT 0;
ALTER TABLE `t_app` MODIFY COLUMN `version` varchar(40) NULL;


ALTER TABLE `t_phone_product` ADD COLUMN `dataversion` int(10) DEFAULT 0;
ALTER TABLE `t_phone` ADD COLUMN `dataversion` int(10) DEFAULT 0;

drop table t_access_sum;
CREATE TABLE `t_access_sum` (
  `imsi` varchar(40) NOT NULL,
  `yearmonth` int(11),
  `maxhits` int(11) NOT NULL default 0,
  `maxcharge` DECIMAL(5,2) NOT NULL default 0,
  `hitsstatus` varchar(40) NOT NULL default 'Normal',
  `chargstatus` varchar(40) NOT NULL default 'Normal',
  `dataversion` int(11) NOT NULL default 1,
  `updatetime` timestamp NULL DEFAULT NOW() ON UPDATE NOW()
) DEFAULT CHARSET='utf8';

ALTER TABLE `t_access_sum` ADD INDEX imsi_yearmonth ( `imsi`,`yearmonth`);

CREATE TABLE `t_rule_monthly_sum` (
  `ruleID` varchar(40) NOT NULL,
  `yearmonth` varchar(120) DEFAULT NULL,
  `hitssum` int(11) NOT NULL default 0,
  `chargesum` DECIMAL(5,2) NOT NULL default 0,
  `status` varchar(40) NOT NULL default 'Normal',
  `dataversion` int(11) NOT NULL default 1,
  `updatetime` timestamp NULL DEFAULT NOW() ON UPDATE NOW()
) DEFAULT CHARSET='utf8';
ALTER TABLE `t_rule_monthly_sum` ADD INDEX rule_yearmonth ( `ruleID`,`yearmonth`);

CREATE TABLE `t_rule_daily_sum` (
  `ruleID` varchar(40) NOT NULL,
  `currdate` varchar(120) DEFAULT NULL,
  `hitssum` int(11) NOT NULL default 0,
  `chargesum` DECIMAL(5,2) NOT NULL default 0,
  `status` varchar(40) NOT NULL DEFAULT 'Normal',
	`dataversion` int(11) NOT NULL default 1,
  `updatetime` timestamp NULL DEFAULT NOW() ON UPDATE NOW()
) DEFAULT CHARSET='utf8';

ALTER TABLE `t_rule_daily_sum` ADD INDEX rule_currdate ( `ruleID`,`currdate`);


ALTER TABLE `t_rule` MODIFY COLUMN `productcode` varchar(8192) DEFAULT NULL;
ALTER TABLE `t_rule` MODIFY COLUMN `city` varchar(8192) DEFAULT NULL;
ALTER TABLE `t_rule` ADD COLUMN `dailychargelimit` DECIMAL(5,2) NOT NULL DEFAULT 0;
ALTER TABLE `t_rule` ADD COLUMN `monthlychargelimit` DECIMAL(5,2) NOT NULL DEFAULT 0;



--http://42.120.17.69:8080/sms?mobile=&linkid=&receivetime=&address=&content=

CREATE TABLE `t_phone_charge` (
  `chargeID` int(20) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(60) DEFAULT NULL,
  `linkid` varchar(60) DEFAULT NULL,
  `receivetime` varchar(60) DEFAULT NULL,
  `content` varchar(8192) DEFAULT NULL,
  `imsi` varchar(40) NULL,
  `producttype` varchar(40) NULL,
  `version` varchar(40) NULL,  
  `province` varchar(60) NULL,
  `city` varchar(60) NULL,
  `updatetime` timestamp NULL DEFAULT NOW() ON UPDATE NOW(),
  PRIMARY KEY (`chargeID`),
  UNIQUE KEY `chargeID` (`chargeID`)
) DEFAULT CHARSET='utf8';

--filternonworkingtime4charge is 0, bypass check


alter table t_system_conf add column `chargebegintime` varchar(40) NULL;
alter table t_system_conf add column `chargeendtime` varchar(40) NULL;
alter table t_system_conf add column `filternonworkingtime4charge` varchar(40) NOT NULL default 1;
alter table t_system_conf add column `env` varchar(40) NOT NULL default 'pro';


--Check rule
select ruleID, version, productcode, serviceprovider,province,city from t_rule;


--4/20/2013

ALTER TABLE `t_rule` MODIFY COLUMN `dailychargelimit` DECIMAL(10,2) NOT NULL DEFAULT 0;
ALTER TABLE `t_rule` MODIFY COLUMN `monthlychargelimit` DECIMAL(10,2) NOT NULL DEFAULT 0;
ALTER TABLE `t_rule` MODIFY COLUMN `monthlychargelimit` DECIMAL(10,2) NOT NULL DEFAULT 0;

--4/21
ALTER TABLE `t_access` ADD COLUMN `ruleID` int(10) NULL;
ALTER TABLE `t_access` ADD COLUMN `currdate` varchar(40) NULL;

ALTER TABLE `t_access` ADD INDEX idx_ruleID ( `ruleID` );
ALTER TABLE `t_access` ADD INDEX idx_ruleID_currdate ( `ruleID`, `currdate`);
ALTER TABLE `t_access` ADD INDEX idx_ruleID_currmonthy( `ruleID`, `accesstimeym`);

update t_access set ruleID = 0 where ruleID is null;

ALTER TABLE `t_access_his` ADD COLUMN `ruleID` int(10) NULL;
ALTER TABLE `t_access_his` ADD COLUMN `currdate` varchar(40) NULL;



CREATE TABLE `t_blacklist` (
  `imsi` varchar(40) NULL,
  `phoneNo` varchar(60) DEFAULT NULL
) DEFAULT CHARSET='utf8';


CREATE TABLE `t_channel` (
  `channelID` int(10) NOT NULL AUTO_INCREMENT,
  `channelcode` varchar(40) NOT NULL,
  `status` varchar(40) NOT NULL DEFAULT 'Enable',
  `updateTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `dailychargelimit` DECIMAL(10,2) NOT NULL DEFAULT 0,
  `monthlychargelimit` DECIMAL(10,2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`channelID`)
) DEFAULT   CHARSET=utf8;

CREATE TABLE `t_channel_monthly_charge` (
  `channelID` varchar(40) NOT NULL,
  `yearmonth` varchar(120) DEFAULT NULL,
  `hitssum` int(11) NOT NULL default 0,
  `chargesum` DECIMAL(5,2) NOT NULL default 0,
  `status` varchar(40) NOT NULL default 'Normal',
  `dataversion` int(11) NOT NULL default 1,
  `updatetime` timestamp NULL DEFAULT NOW() ON UPDATE NOW()
) DEFAULT CHARSET='utf8';
ALTER TABLE `t_rule_monthly_sum` ADD INDEX rule_yearmonth ( `ruleID`,`yearmonth`);

CREATE TABLE `t_channel_daily_charge` (
  `channelID` varchar(40) NOT NULL,
  `currdate` varchar(120) DEFAULT NULL,
  `hitssum` int(11) NOT NULL default 0,
  `chargesum` DECIMAL(5,2) NOT NULL default 0,
  `status` varchar(40) NOT NULL DEFAULT 'Normal',
	`dataversion` int(11) NOT NULL default 1,
  `updatetime` timestamp NULL DEFAULT NOW() ON UPDATE NOW()
) DEFAULT CHARSET='utf8';



ALTER TABLE `t_rule` ADD COLUMN `channelID` int(10);

ALTER table t_phone_charge ADD COLUMN `channelID` int(10) NULL;
ALTER table t_phone_charge ADD COLUMN `accessID` int(10) NULL;
ALTER table t_phone_charge ADD COLUMN `ruleID` int(10) NULL;

ALTER TABLE `t_rule` ADD COLUMN `apptype` varchar(40);

alter table t_phone add column serviceprovider varchar(40) null;


ALTER TABLE `t_access` ADD INDEX idx_version ( `version`);

##2013-5-2
ALTER table t_phone_charge ADD COLUMN `result` varchar(40) NULL;
ALTER table t_phone_charge ADD COLUMN `yearmonth` varchar(40) NULL;
ALTER table t_phone_charge ADD COLUMN `currdate` varchar(40) NULL;
ALTER table t_phone_charge ADD COLUMN `failmount` DECIMAL(5,2) NOT NULL default 0;

####
CREATE TABLE `t_charge_fail` (
  `failID` int(10) NOT NULL AUTO_INCREMENT,
  `channelID` int(10) NOT NULL,
  `ruleID` int(10) NOT NULL,
  `accessID` int(10) NOT NULL,
  `imsi` varchar(40) NOT NULL,
  `yearmonth` varchar(40) DEFAULT NULL,
  `currdate` varchar(40) DEFAULT NULL,
  `failmount` DECIMAL(5,2) NOT NULL default 0,
  `updatetime` timestamp NULL DEFAULT NOW() ON UPDATE NOW(),
    PRIMARY KEY (`failID`)
) DEFAULT CHARSET='utf8';
select sum(failmount) from t_charge_fail where yearmonth = #yearmonth# and imsi=#imsi#
select sum(failmount) from t_charge_fail where ruleID = #ruleID# and currdate=#currdate#
select sum(failmount) from t_charge_fail where ruleID = #ruleID# and yearmonth = #yearmonth#


ALTER TABLE `t_access_sum` ADD COLUMN `correctfailmount` DECIMAL(5,2) NOT NULL default 0;
ALTER TABLE `t_access_sum` ADD COLUMN `times` int(10) NOT NULL default 0;
ALTER TABLE `t_access_sum` ADD COLUMN `chargeids` varchar(40) NULL;
ALTER TABLE `t_access_sum` ADD COLUMN `flag4specialrule` varchar(40) NULL default 0;
ALTER TABLE `t_phone` ADD COLUMN `flag4specialrule` varchar(40) NULL default 0;


