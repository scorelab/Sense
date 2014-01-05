DROP TABLE IF EXISTS `service`;
CREATE TABLE `service` (
  `Id` int NOT NULL default '0',
  `Name` text,
  `ClassName` text NOT NULL default '',
  `timestamp` text NOT NULL default '',
  `Sync` int default 0,
  PRIMARY KEY  (`timestamp`,`ClassName`,`Id`)
) ;
DROP TABLE IF EXISTS `wifi`;
CREATE TABLE `wifi` (
  `SSID` text NOT NULL default '',
  `BSSID` text,
  `Capabilities` text,
  `TimeStamp` text NOT NULL default '',
  `Sync` int default 0,
  PRIMARY KEY  (`TimeStamp`,`SSID`)
) ;
DROP TABLE IF EXISTS `sensor`;
CREATE TABLE `sensor` (
  `Name` text NOT NULL default '',
  `Vendor` text,
  `accuracy` int default NULL,
  `Sensorvalues` text,
  `collectTime` text NOT NULL default '',
  `timestamp` text NULL default NULL,
  `Sync` int default 0,
  PRIMARY KEY  (`collectTime`,`Name`)
) ;
DROP TABLE IF EXISTS `process`;
CREATE TABLE `process` (
  `Id` int NOT NULL default '0',
  `Name` text,
  `PrivateDirtyData` int default NULL,
  `TotalPss` int default NULL,
  `totalSharedDirty` int default NULL,
  `timestamp` text NOT NULL default '',
  `Sync` int default 0,
  PRIMARY KEY  (`timestamp`,`Id`)
);