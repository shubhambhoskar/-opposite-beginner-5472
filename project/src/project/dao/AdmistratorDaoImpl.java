package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import project.Exception.AdmistartorException;
import project.Utility.DButil;
import project.model.Admistrator;

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

}
