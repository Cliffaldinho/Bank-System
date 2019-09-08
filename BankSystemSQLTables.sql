CREATE TABLE Roles (
	roleId CHAR(5) PRIMARY KEY NOT NULL,
	roleName VARCHAR(50) NOT NULL,
	roleDesc VARCHAR(400)
);
Go

CREATE TABLE Users (
	userId CHAR(4) PRIMARY KEY,
	firstName VARCHAR(20),
	lastName VARCHAR(20),
	address VARCHAR(50),
	phoneNumber VARCHAR(10),
	roleId CHAR(5) UNIQUE
	CONSTRAINT roleId FOREIGN KEY(roleId)
	REFERENCES Roles(roleId)
);
Go

CREATE TABLE Incidents (
	id INT PRIMARY KEY NOT NULL,
	title VARCHAR(20),
	category VARCHAR(15),
	dateOfMonth INTEGER NOT NULL,
	month VARCHAR(10) NOT NULL,
	year INTEGER NOT NULL,
	time TIMESTAMP,
	reportedBy CHAR(4) NOT NULL,
	priority VARCHAR(6) NOT NULL,
	description VARCHAR(500),
	status VARCHAR(25),
	isRead BIT, --can be 0=false, 1=true, null
	keywords VARCHAR(150),
	CONSTRAINT reportedBy FOREIGN KEY(reportedBy)
	REFERENCES Users(userId)
);
Go

CREATE TABLE PostIncident (
	incidentId INT PRIMARY KEY NOT NULL,
	possibleCauses VARCHAR(500),
	possibleSolutions VARCHAR(500),
	riskEvaluation INT,
	strategyImplemented VARCHAR(500),
	isStrategyImplementedAlready BIT, --allows: 0=false, 1=true, null
	staffWhoRatedStrategy CHAR(4),
	strategyTimestamp TIMESTAMP,
	amountOfRatingsReceived INT,
	ratingOverall DECIMAL(5,2),
	ratingEffectiveness DECIMAL(5,2),
	ratingImprovementFromSituationBefore DECIMAL(5,2),
	ratingPractical DECIMAL(5,2),
	ratingRelevanceToIncident DECIMAL(5,2),
	ratingSatisfactionOfStrategy DECIMAL(5,2),
	--simulations string?
	CONSTRAINT inc_post FOREIGN KEY(incidentId)
	REFERENCES Incidents(id),
	CONSTRAINT user_post FOREIGN KEY(staffWhoRatedStrategy)
	REFERENCES Users(userId)
);
Go

CREATE TABLE IncidentStatistics(
	incidentId INT PRIMARY KEY NOT NULL,
	categoryAmount INT,
	categoryPercentage DECIMAL(4,2),
	CONSTRAINT inc_stat FOREIGN KEY(incidentId)
	REFERENCES Incidents(id)
);
Go

CREATE TABLE Simulations (
	incidentId INT PRIMARY KEY NOT NULL,
	simulation VARCHAR(500),
	CONSTRAINT inc_sim FOREIGN KEY(incidentId)
	REFERENCES Incidents(id)
);
Go

INSERT INTO Roles VALUES('R1','Financial Analyst','Advice individual bank clients and businesses on investments (i.e. stocks and bonds to grow portfolio).');
INSERT INTO Roles VALUES('R2','Internal Auditor','Prepares and assesses financial records. Ensures bank is following compliance and regulatory laws concerning bank operations and procedures. Responsible for creating audit programs for all areas of operation. Monitors data processing, data security, and disaster recovery strategies for bank.');
INSERT INTO Roles VALUES('R3','Data Processing Officer','Responsible for operation, maintenance, and security of bank information systems and offline terminals. Ensures daily transaction exception reports are reviewed. Create security profile for employees.');
INSERT INTO Roles VALUES('R4','Branch Manager','Oversee branch operation. Create branch work schedules. Provide budget to executive management. Ensure bank policies are followed at branch level.');
INSERT INTO Roles VALUES('R5','IT','Installing and configuring computer hardware, software, systems, networks, printers and scanners. Monitoring and maintaining computer systems and networks. Responding in a timely manner to service issues and requests. Providing technical support across the company (this may be in person or over the phone).');
INSERT INTO Roles VALUES('R6','Bank Teller','Responsible for basic customer account transactions such as servicing savings and checking accounts. Provide account inquiry answers to customers (including bank products i.e. loans etc).');
INSERT INTO Roles VALUES('R7','Bank Marketing Representative','Markets banking products to customers, ranging from savings accounts to special deposits accounts. Responsible for understanding strategic plan for marketing various products and implementing the marketing plans for bank.');
INSERT INTO Roles VALUES('R8','Loan Officer','Market loan products to customers. Process necessary documents for customers to get a loan. Examine credit reports of individuals to receive loan.');
INSERT INTO Roles VALUES('R9','Accountant','Analyze, plan, and evaluate business expenses and income.');
INSERT INTO Roles VALUES('R10','Personal Financial Advisor','Help individuals manage money to meet long and short-term goals.');
Go

--userId, firstName, lastName, address, phoneNumber, roleId
INSERT INTO Users VALUES('b1', 'Bob', 'Smith', 'Elm Street', '0403526395', 'R1');
INSERT INTO Users VALUES('b2', 'Alice', 'Diaz', 'Avoca Lane', '0423436405', 'R2');
INSERT INTO Users VALUES('b3', 'Henry', 'Stewart', 'Mahogany Lane', '0435243964', 'R3');
INSERT INTO Users VALUES('b4', 'Enzo', 'Rogers', 'Wilsons Creek', '0432364354', 'R4');
INSERT INTO Users VALUES('b5', 'Chloe', 'Morgan', 'Chicago Lane', '0473423537', 'R5');
Go

--1. Bob Smith, Elm Street, 0403526395, Financial Analyst, b1						already added in CreateIncidentReportServlet.java code
--2. Alice Diaz, Avoca Lane, 0423436405, Internal Auditor, b2						already added in CreateIncidentReportServlet.java code
--3. Henry Stewart, Mahogany Lane, 0435243964, Data Processing Officer, b3				already added in CreateIncidentReportServlet.java code
--4. Enzo Rogers, Wilsons Creek, 0432364354, Branch Manager, b4						already added in CreateIncidentReportServlet.java code
--5. Chloe Morgan, Chicago Lane, 0473423537, IT, b5

SELECT * FROM Roles;
SELECT * FROM Users;
SELECT * FROM Incidents;
SELECT * FROM PostIncident;
SELECT * FROM IncidentStatistics;
SELECT * FROM Simulations;