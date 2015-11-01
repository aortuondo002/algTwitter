package db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Eragiketak {
	DBKudeatzaile dbk = DBKudeatzaile.getInstantzia();
	private static Eragiketak mEragiketak = null;
	private Eragiketak(){}
	
	public static synchronized Eragiketak getEragiketak(){
		if(mEragiketak == null){
			mEragiketak = new Eragiketak();
		}return mEragiketak;
	}
	public void tokenGorde(String token, String tokenSecret) throws SQLException{
		ResultSet rs = dbk.execSQL("INSERT INTO token(accessToken)VALUES("+token+");");
		rs = dbk.execSQL("INSERT INTO token(accessTokenSecret)VALUES("+tokenSecret+");");
	}
	
	public String tokenBilatu(){
		String token = "";
		try {
			ResultSet rs = dbk.execSQL("SELECT accessToken FROM token );");
			int x = 0;
			while(rs.next()){
				//rs osoan dagoena sartuko du String -ean
				token= token+rs.getString(x);
			}
		} catch (SQLException e) {
			System.out.println("EZ DA GEHITU TOKEN!!");
			//e.printStackTrace();
		}return token;
	}
	public String tokenSecretBilatu(){
		String token = "";
		try {
			ResultSet rs = dbk.execSQL("SELECT accessTokenSecret FROM token );");
			int x = 0;
			while(rs.next()){
				//rs osoan dagoena sartuko du String -ean
				token= token+rs.getString(x);
			}
		} catch (SQLException e) {
			System.out.println("EZ DA GEHITU TOKEN!!");
			e.printStackTrace();
		}return token;
	}
	public String erabiltzaileIzena(){
		String userId = "";
		try {
			ResultSet rs = dbk.execSQL("SELECT izena FROM user;");
			int x = 0;
			while(rs.next()){
				//rs osoan dagoena sartuko du String -ean
				userId = userId+rs.getString(x);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}
	public void sartuErab(String izena) throws SQLException{
		dbk.execSQL("INSERT INTO `twittermysql`.`user`(`izena`) VALUES(`"+izena+"`);");
	}
	



}
