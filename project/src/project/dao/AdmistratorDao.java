package project.dao;

import java.util.List;

import project.Exception.AdmistartorException;
import project.Exception.StudentException;
import project.model.Admistrator;
import project.model.Batch;
import project.model.Student;

public interface AdmistratorDao {
	
	public String addacourse(Admistrator a) throws AdmistartorException;
	
	public void updatefee(int cid,int fee)throws AdmistartorException;
	
	public void deletecourse(String s)throws AdmistartorException; 
	
	public void SearchInfoAboutCourse( String s);
	
	public String createabatch(Batch batch) throws AdmistartorException;
	
	public String addstudenttobatchincourse(int roll,int bid, int cid)throws AdmistartorException;

	public void updateseatofbatch(int bid, int updateseat)throws AdmistartorException;
	
	public List<Student> getallstudent() throws AdmistartorException;
}
