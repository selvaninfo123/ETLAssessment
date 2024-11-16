-- Customer_IND definition

CREATE TABLE `Customer_IND` (
  `Record_Type` longtext,
  `Customer_Name` longtext,
  `Customer_ID` longtext,
  `Open_Date` date DEFAULT NULL,
  `Last_Consulted_Date` date DEFAULT NULL,
  `Vaccination_Type` longtext,
  `Dr_Name` longtext,
  `State` longtext,
  `Country` longtext,
  `Post_Code` longtext,
  `Date_Of_Birth` date DEFAULT NULL,
  `Is_Active` longtext,
  `Age` int DEFAULT NULL,
  `Days_Since_Last_Consulted` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



-- Customer_AUS definition

CREATE TABLE `Customer_AUS` (
  `Customer_Name` varchar(255) NOT NULL,
  `Customer_ID` varchar(18) NOT NULL,
  `Open_Date` date NOT NULL,
  `Last_Consulted_Date` date DEFAULT NULL,
  `Vaccination_Type` char(5) DEFAULT NULL,
  `Doctor_Name` varchar(255) DEFAULT NULL,
  `State` char(5) DEFAULT NULL,
  `Country` char(5) DEFAULT NULL,
  `Post_Code` int DEFAULT NULL,
  `Date_Of_Birth` date DEFAULT NULL,
  `Is_Active` char(1) DEFAULT NULL,
  `Age` int DEFAULT NULL,
  `Days_Since_Last_Consulted` date DEFAULT NULL,
  PRIMARY KEY (`Customer_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- Customer_USA definition

CREATE TABLE `Customer_USA` (
  `Record_Type` longtext,
  `Customer_Name` longtext,
  `Customer_ID` longtext,
  `Open_Date` date DEFAULT NULL,
  `Last_Consulted_Date` date DEFAULT NULL,
  `Vaccination_Type` longtext,
  `Dr_Name` longtext,
  `State` longtext,
  `Country` longtext,
  `Post_Code` longtext,
  `Date_Of_Birth` date DEFAULT NULL,
  `Is_Active` longtext,
  `Age` int DEFAULT NULL,
  `Days_Since_Last_Consulted` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;