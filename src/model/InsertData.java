package model;

import java.sql.Connection;
import java.sql.Statement;

public class InsertData {
	
	private final String DB_NAME = "testDB";
	

	InsertData() {

		// use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {

			// inserting vehicles into database
			stmt.executeUpdate(
					"insert into vehicle values('C101',2019,'Mercedes','M1',4,'Car','Available','car',NULL)");
			stmt.executeUpdate(
					"insert into vehicle values('C102',2018,'Hyundai','Verna',4,'Car','Rented','hycarverna',NULL)");
			stmt.executeUpdate(
					"insert into vehicle values('C103',2017,'Mercedes','A250',7,'Car','Under Maintainance','mercara250',NULL)");
			stmt.executeUpdate(
					"insert into vehicle values('V104',2016,'Hyundai','H1',15,'Van','Rented','hyvanh1','06/01/2019')");
			stmt.executeUpdate(
					"insert into vehicle values('V105',2019,'Maruti','EECO',15,'Van','Available','marvaneeco',NULL)");
			stmt.executeUpdate(
					"insert into vehicle values('C106',2006,'Maruti','Suzuki',7,'Car','Under Maintainance','marcarsuzuki',NULL)");
			stmt.executeUpdate(
					"insert into vehicle values('V107',2005,'Mercedes','Metris',15,'Van','Available','mervanmetris',NULL)");
			stmt.executeUpdate(
					"insert into vehicle values('C108',2016,'Mercedes','AMGS3',7,'Car','Available','mercaramgs3',NULL)");
			stmt.executeUpdate(
					"insert into vehicle values('C109',2015,'Hyundai','H1',4,'Car','Rented','no image',NULL)");
			stmt.executeUpdate(
					"insert into vehicle values('V110',2004,'Mercedes','V40',15,'Van','Under Maintainance','mervan1',NULL)");
			stmt.executeUpdate(
					"insert into vehicle values('C111',2003,'Mercedes','GLE150',7,'Car','Available','mercargleclass',NULL)");
			stmt.executeUpdate(
					"insert into vehicle values('C112',2019,'Maruti','M1',7,'Car','Under Maintainance','no image',NULL)");
			stmt.executeUpdate(
					"insert into vehicle values('C113',2019,'Mercedes','F450',7,'Car','Available','mercarcclass',NULL)");
			stmt.executeUpdate(
					"insert into vehicle values('V114',2019,'Hyundai','Afort',15,'Van','Available','no image',NULL)");
			stmt.executeUpdate(
					"insert into vehicle values('C115',2019,'Mercedes','Bingd',4,'Car','Rented','no image',NULL)");

			// inserting rental records into database
			stmt.executeUpdate(
					"insert into rentalrecords values('C101_C101_01012019','01/01/2019','05/01/2019','06/01/2019',456.20,110.1,'C101')");
			stmt.executeUpdate(
					"insert into rentalrecords values('C101_C102_08012019','08/01/2019','10/01/2019','10/01/2019',210.0,0,'C101')");
			stmt.executeUpdate(
					"insert into rentalrecords values('C102_C101_10022019','10/02/2019','13/02/2019','11/02/2019',290.30,0,'C102')");
			stmt.executeUpdate(
					"insert into rentalrecords values('C102_C102_15032019','15/03/2019','20/03/2019',NULL,NULL,NULL,'C102')");
			stmt.executeUpdate(
					"insert into rentalrecords values('C103_C101_16012019','16/01/2019','19/01/2019','19/01/2019',310.2,00,'C103')");
			stmt.executeUpdate(
					"insert into rentalrecords values('C103_C103_22012019','22/01/2019','27/01/2019','29/01/2019',1100.0,210.2,'C103')");
			stmt.executeUpdate(
					"insert into rentalrecords values('V104_C101_01012019','01/01/2019','05/01/2019',NULL,NULL,NULL,'V104')");
			stmt.executeUpdate(
					"insert into rentalrecords values('V104_C104_08012019','08/01/2019','11/01/2019','14/01/2019',240.20,320.2,'V104')");
			stmt.executeUpdate(
					"insert into rentalrecords values('V105_C101_06042019','06/04/2019','07/04/2019','07/04/2019',210.2,0,'V105')");
			stmt.executeUpdate(
					"insert into rentalrecords values('V105_C102_15052019','15/05/2019','18/05/2019','19/05/2019',290.8,100,'V105')");
			stmt.executeUpdate(
					"insert into rentalrecords values('V107_C101_20052019','20/05/2019','21/05/2019','27/05/2019',250.9,630.3,'V107')");
			stmt.executeUpdate(
					"insert into rentalrecords values('V107_C104_01012019','28/05/2019','31/05/2019','01/06/2019',340.5,150.9,'V107')");
			stmt.executeUpdate(
					"insert into rentalrecords values('C108_C101_01012019','01/01/2019','05/01/2019','06/01/2019',456.20,110.1,'C108')");
			stmt.executeUpdate(
					"insert into rentalrecords values('C108_C102_08012019','08/01/2019','10/01/2019','10/01/2019',210.0,0,'C108')");
			stmt.executeUpdate(
					"insert into rentalrecords values('C109_C101_10022019','10/02/2019','13/02/2019',NULL,NULL,NULL,'C109')");
			stmt.executeUpdate(
					"insert into rentalrecords values('C109_C102_15032019','15/03/2019','20/03/2019','21/03/2019',840.50,120.12,'C109')");
			stmt.executeUpdate(
					"insert into rentalrecords values('C111_C101_16012019','16/01/2019','19/01/2019','19/01/2019',310.2,00,'C111')");
			stmt.executeUpdate(
					"insert into rentalrecords values('C111_C103_22012019','22/01/2019','27/01/2019','29/01/2019',1100.0,210.2,'C111')");
			stmt.executeUpdate(
					"insert into rentalrecords values('C112_C101_01012019','01/01/2019','05/01/2019','06/01/2019',200,100,'C112')");
			stmt.executeUpdate(
					"insert into rentalrecords values('C112_C104_08012019','08/01/2019','11/01/2019','14/01/2019',240.20,320.2,'C112')");
			stmt.executeUpdate(
					"insert into rentalrecords values('V114_C101_06042019','06/04/2019','07/04/2019','07/04/2019',210.2,0,'V114')");
			stmt.executeUpdate(
					"insert into rentalrecords values('V114_C102_15052019','15/05/2019','18/05/2019','19/05/2019',290.8,100,'V114')");
			stmt.executeUpdate(
					"insert into rentalrecords values('C115_C101_20052019','20/05/2019','21/05/2019','27/05/2019',250.9,630.3,'C115')");
			stmt.executeUpdate(
					"insert into rentalrecords values('C115_C104_01012019','28/05/2019','31/05/2019',NULL,NULL,NULL,'C115')");

			con.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
