DROP TABLE IF EXISTS `process`;
CREATE TABLE `process` (
  `Id` int(11) NOT NULL default '0',
  `Name` text,
  `PrivateDirtyData` int(11) default NULL,
  `TotalPss` int(11) default NULL,
  `totalSharedDirty` int(11) default NULL,
  `timestamp` timestamp NOT NULL default '0000-00-00 00:00:00',
  `Sync` int(11) default 0,
  PRIMARY KEY  (`timestamp`,`Id`)
);
DROP TABLE IF EXISTS `service`;
CREATE TABLE `service` (
  `Id` int(11) NOT NULL default '0',
  `Name` text,
  `ClassName` varchar(800) NOT NULL default '',
  `timestamp` timestamp NOT NULL default '0000-00-00 00:00:00' on update CURRENT_TIMESTAMP,
  `Sync` int(11) default 0,
  PRIMARY KEY  (`timestamp`,`ClassName`,`Id`)
) ;
DROP TABLE IF EXISTS `wifi`;
CREATE TABLE `wifi` (
  `SSID` varchar(512) NOT NULL default '',
  `BSSID` text,
  `Capabilities` text,
  `TimeStamp` timestamp NOT NULL default '0000-00-00 00:00:00',
  `Sync` int(11) default 0,
  PRIMARY KEY  (`TimeStamp`,`SSID`)
) ;
DROP TABLE IF EXISTS `sensor`;
CREATE TABLE `sensor` (
  `Name` varchar(255) NOT NULL default '',
  `Vendor` text,
  `accuracy` int(11) default NULL,
  `Sensorvalues` text,
  `collectTime` timestamp NOT NULL default '0000-00-00 00:00:00',
  `timestamp` timestamp NULL default NULL,
  `Sync` int(11) default 0,
  PRIMARY KEY  (`collectTime`,`Name`)
) ;