package project.dao;

import project.Exception.AdmistartorException;
import project.Exception.StudentException;
import project.model.Student;

public interface StudentDao {

	public void registerstudent(Student s)throws StudentException;
	
	public void updatestudentdetails(int roll,String name,String address,String email,String password)throws StudentException;
	
	public void enrollastudentinabatch(int sid,int cid)throws StudentException,AdmistartorException;
	
	public void seatsavailability (String cname) throws StudentException;
	
	
}
