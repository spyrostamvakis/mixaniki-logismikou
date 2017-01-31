import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class queryDB {

	ConnectToDB database = new ConnectToDB();
	
	
	public void insertConcert(String name,String place,String date, int price )
	{
		Connection conn =  database.start();		
		String sql = "INSERT INTO concert VALUES (?,?,?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(sql);
            
            stmt.setNull(1,Types.INTEGER);
            stmt.setString(2, name);
            stmt.setString(3, place);
            stmt.setDate(4, java.sql.Date.valueOf(date));
            stmt.setInt(5, price);
                                    
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
	}

	public String[] selectStadium()
	{
		Connection conn =  database.start();
		ArrayList<String> list = new ArrayList<String>();
		
		String sql = "SELECT NAME FROM stadium";
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
            	list.add(rs.getString("NAME"));
            }

            stmt.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        String[] array = new String[list.size()];
        return list.toArray(array);
	}
	
	public String[] selectAllConcerts()
	{
		Connection conn =  database.start();
		ArrayList<String> list = new ArrayList<String>();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		String sql = "SELECT CONCERT_ID,Name,Place,Date FROM concert";
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
            	list.add(rs.getString("CONCERT_ID") + ") " + rs.getString("Name") + ", " + rs.getString("Place") + ", " + df.format(rs.getDate("Date")));
            }

            stmt.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        String[] array = new String[list.size()];
        return list.toArray(array);
	}
	
	public Integer[] getSeats(int concertid)
	{
		Connection conn =  database.start();
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		String sql = "SELECT SEAT_ID FROM reservations WHERE CONCERT_ID = ?";
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, concertid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
            	list.add( Integer.parseInt(rs.getString("SEAT_ID")) );
            }

            stmt.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Integer[] array = new Integer[list.size()];
        return list.toArray(array);
	}
	
	public void reserveSeats(int concert_id, int seat_id)
	{
		Connection conn =  database.start();		
		String sql = "INSERT INTO reservations VALUES (?,?)";
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, concert_id);
            stmt.setInt(2, seat_id);
                                    
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
	}
	
	public String getStatistics(int concert_id)
	{
		Connection conn =  database.start();
		String stats="";
		int ticketCost=0;
				
		String sql = "SELECT Name,Place,Date,Cost FROM concert WHERE CONCERT_ID = ?";
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, concert_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
            	stats = "Artist: " +rs.getString("Name") + "\nPlace: " + rs.getString("Place") + "\nDate: " + rs.getString("Date") +"\n\n";  
            	ticketCost = Integer.parseInt(rs.getString("Cost"));
            }
            stmt.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        sql = "SELECT COUNT(SEAT_ID) AS seatCount FROM reservations WHERE CONCERT_ID = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, concert_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
            	int seats = rs.getInt("seatCount");
            	int money = seats * ticketCost;
            	stats = stats + "Seats: " + seats + "/60 \nTotal earnings: " + money;             	
            }
            stmt.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }               
        return stats;
	}
}
