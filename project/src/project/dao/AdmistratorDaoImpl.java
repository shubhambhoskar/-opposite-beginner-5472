package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.Exception.AdmistartorException;
import project.Exception.StudentException;
import project.Utility.DButil;
import project.model.Admistrator;
import project.model.Batch;
import project.model.Student;

public class AdmistratorDaoImpl implements AdmistratorDao{

	@Override
	public String addacourse(Admistrator a) throws AdmistartorException {
		String s="Not inserted.." ;
		
		try(Connection conn=DButil.provideConnection()) {
			
			PreparedStatement ps=conn.prepareStatement("insert into Admistrator(cid,cname,duration,fee)values(?,?,?,?)");
		
			ps.setInt(1, a.getCid());
			ps.setString(2, a.getCname());
			ps.setInt(3, a.getFee());
			ps.setString(4, a.getDuration());
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return s;
	}

	@Override
	public void updatefee(int cid,int fee) throws AdmistartorException {
		
		
		try(Connection conn=DButil.provideConnection()) {
			
			PreparedStatement ps=conn.prepareStatement("update Admistrator set fee=fee+? where cid=?");
		
			ps.setInt(1,fee);
			ps.setInt(2,cid);
			
			int x=ps.executeUpdate();
		
			if(x>0) {
				System.out.println(x+" course fee is update");
			}else {
				System.out.println("No record found");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		
	}

	@Override
	public void deletecourse(String s) throws AdmistartorException {
		
try(Connection conn=DButil.provideConnection()) {
			
			PreparedStatement ps=conn.prepareStatement("delete from Admistrator where cname=?");
		
			ps.setString(1,s);
			
			
			int x=ps.executeUpdate();
		
			if(x>0) {
				System.out.println(x+" course is delete");
			}else {
				System.out.println("No record found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void SearchInfoAboutCourse(String s) {
		
		if(s=="Java") {
			System.out.println("Welcome to Java course");
			System.out.println("Course duration : 3 Month");
			System.out.println("Course fee : 30000");
		}else if(s=="Android") {
			System.out.println("Welcome to Android course");
			System.out.println("Course duration : 2 Month");
			System.out.println("Course fee : 20000");
		}else if(s=="JEE") {
			System.out.println("Welcome to JEE course");
			System.out.println("Course duration : 6 Month");
			System.out.println("Course fee : 60000");
		}else if(s=="PHP") {
			System.out.println("Welcome to PHP course");
			System.out.println("Course duration : 3 Month");
			System.out.println("Course fee : 25000");
		}else if(s=="Oracle") {
			System.out.println("Welcome to Oracle course");
			System.out.println("Course duration : 4 Month");
			System.out.println("Course fee : 40000");
		}else {
			System.out.println("Course not found");
		}
		
	}

	@Override
	public String createabatch(Batch batch) throws AdmistartorException {
String message = null;
		
		try(Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps =  conn.prepareStatement("INSERT INTO batch VALUES (?,?,?,?,?)");
			ps.setInt(1, batch.getBid() );
			ps.setString(2, batch.getName());
			ps.setInt(3, batch.getDuration());
			ps.setInt(4, batch.getCid());
			ps.setInt(5, batch.getSeats());
			
			int res = ps.executeUpdate();
			
			if(res > 0) message = "Batch is"+batch.getName() + " Added to Course ID : "+ batch.getCid();
			else throw new AdmistartorException("Batch not found");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AdmistartorException(e.getMessage());
		}

		return message;
	}

	@Override
	public String addstudenttobatchincourse(int roll, int bid, int cid) throws AdmistartorException {
		
		String message = "Not inserted";
		
		try(Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from student where roll = ?");
			ps.setInt(1, roll);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				String studentName = rs.getString("name");
				PreparedStatement ps2 =  conn.prepareStatement("select * from course where cid = ?");
				ps2.setInt(1, cid);
				
				ResultSet rs2 = ps2.executeQuery();
				
				if(rs2.next()) {
					
					String courseName = rs2.getString("cname");
					PreparedStatement ps3 = conn.prepareStatement("select bname,seats from batch where bid = ? AND cid = ?");
					ps3.setInt(1, bid);
					ps3.setInt(2, cid);
					
					ResultSet rs3 = ps3.executeQuery();
					
					if(rs3.next()) {
						
						String batchName = rs3.getString("bname");
						int availableSeats = rs3.getInt("seats");
						
						if(availableSeats > 0) {
							
							availableSeats--;
							PreparedStatement up = conn.prepareStatement("UPDATE batch SET seats = ? WHERE bid = ?");
							up.setInt(1, availableSeats);
							up.setInt(2, bid);
							
							int r = up.executeUpdate();
							
							PreparedStatement p = conn.prepareStatement("INSERT INTO student_batch VALUES (?,?,?)");
							p.setInt(1, roll);
							p.setInt(2, cid);
							p.setInt(3, bid);
							
							int res = p.executeUpdate();
							
							if(res > 0) {
								
								//message = "Student "+studentName+" Added to Batch "+ batchName+" of Course "+courseName+" Successfully.";
							message="Successfuly Student is added to batch with batch id "+bid +"in course"+cid;
							}
							else {
								throw new AdmistartorException("Batch and Course Not Matching.");
							}
		
						}
						else {
							throw new AdmistartorException("No Seats Available.");
						}
					}else {
						throw new AdmistartorException("Batch with Batch ID "+bid+" not Found !");
					}

				}else {
					throw new AdmistartorException("Course with course ID "+ cid + " not Found !");
				}
				
			}else {
				throw new AdmistartorException(" Roll number "+ roll+ " not Found !");
			}
			
		}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AdmistartorException(e.getMessage());
		}
		
		return message;
	}

	@Override
	public void updateseatofbatch(int bid, int updateseat) throws AdmistartorException {
		
		String message = "Not updated";
		
		try(Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps =  conn.prepareStatement("UPDATE batch SET seats = ? WHERE bid = ?");
			ps.setInt(1, updateseat);
			ps.setInt(2, bid);
			
			int x = ps.executeUpdate();
			
			if(x>0) System.out.println("Seats are updated of batch id "+bid); 
//				message = "Batch ID : "+bid+" is Updated with Seats : "+ updateseat+" Successfully.";
			else throw new AdmistartorException("Batch ID Not found.");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AdmistartorException(e.getMessage());
		}
		
	}

	@Override
	public List<Student> getallstudent() throws AdmistartorException {
		
		List<Student> students = new ArrayList<>();
		
		try(Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps =  conn.prepareStatement("select * from student");
		
			ResultSet rs = ps.executeQuery();
			
			boolean flag = true;
			
			while(rs.next()) {
				
				flag = false;
				int roll = rs.getInt("roll");
				String name = rs.getString("name");
				String address= rs.getString("address");
				String email= rs.getString("email");
				String pass= rs.getString("password");
				
				Student student = new Student(roll,name,address,email,pass);
				
				students.add(student);
			}
			
			if(flag) throw new AdmistartorException("Student not found");
			
			
		}catch (SQLException e) {
			// TODO: handle exception
			throw new AdmistartorException(e.getMessage());
		}
		
		return students;
	}	

}
