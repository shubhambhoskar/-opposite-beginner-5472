package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import project.Exception.AdmistartorException;
import project.Exception.StudentException;
import project.Utility.DButil;
import project.model.Student;

public class StudentDaoImpl implements StudentDao{

	@Override
	public void registerstudent(Student s) throws StudentException {
		
		try(Connection conn=DButil.provideConnection()){
			
			PreparedStatement ps=conn.prepareStatement("insert into student(name,address,email,password)");
			
			ps.setString(1, s.getName());
			ps.setString(2, s.getPassword());
			ps.setString(3, s.getEmail());
			ps.setString(4, s.getPassword());
			
			int x=ps.executeUpdate();
			
			if(x>0) {
				System.out.println(x+" student registerd Successfully");
			}else {
				System.out.println("Not registered");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updatestudentdetails(int roll, String name, String address, String email, String password)
			throws StudentException {
	
		try(Connection conn=DButil.provideConnection()) {
			
			PreparedStatement ps=conn.prepareStatement("update student set name=?,address=?,email=?,password=? where cid=?");
		
			ps.setString(1,name);
			ps.setString(2,address);
			ps.setString(3,email);
			ps.setString(4,password);
			ps.setInt(5, roll);
			
			int x=ps.executeUpdate();
		
			if(x>0) {
				System.out.println(x+" course fee is update");
			}else {
				System.out.println("No record found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	@Override
	public void enrollastudentinabatch(int sid, int cid) throws StudentException, AdmistartorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void seatsavailability(String cname) throws StudentException {
		
		
	}

	
}
