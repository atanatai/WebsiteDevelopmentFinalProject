USE rahmTest;

CREATE TABLE Users(
	FName VARCHAR(60),
    LName VARCHAR(60),
    Email VARCHAR(60),
    UName VARCHAR(30) NOT NULL,
    PWord VARCHAR(60),
    PRIMARY KEY(UName)
);

CREATE TABLE Items(
	INumber INT NOT NULL,
    IName VARCHAR(80),
    IPrice FLOAT,
    QtyAvailable INT CHECK(QtyAvailable>=0),
    IDescription VARCHAR(200),
    PRIMARY KEY(INumber)
);

CREATE TABLE Cart(
	CartID INT NOT NULL AUTO_INCREMENT,
	Username VARCHAR(30) NOT NULL,
	ItemNumber INT NOT NULL,
    Qty INT,
    PRIMARY KEY(CartID),
    constraint userIDFK
		FOREIGN KEY (Username) REFERENCES Users(UName)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT itemIDFK
		FOREIGN KEY (ItemNumber) REFERENCES Items(INumber)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Orders(
	ONumber INT NOT NULL AUTO_INCREMENT,
	ODate VARCHAR(20),
	OUName VARCHAR(30) NOT NULL,
	OItemNumber INT NOT NULL,
    Qty INT,
    UnitPrice FLOAT,
    OComplete VARCHAR(3),
    CardName VARCHAR(20),
    CardNumer DOUBLE,
    CSV INT,
    ExpDate VARCHAR(11),
    Email VARCHAR(60),
    PRIMARY KEY(ONumber),
    constraint ouserIDFK
		FOREIGN KEY (OUName) REFERENCES Users(UName)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT oitemIDFK
		FOREIGN KEY (OItemNumber) REFERENCES Items(INumber)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

