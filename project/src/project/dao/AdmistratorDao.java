package project.dao;

import project.Exception.AdmistartorException;
import project.Exception.StudentException;
import project.model.Admistrator;

public interface AdmistratorDao {
	
	public String addacourse(Admistrator a) throws AdmistartorException;
	
	public void updatefee(int cid,int fee)throws AdmistartorException;
	
	public void deletecourse(String s)throws AdmistartorException; 
	
	public void SearchInfoAboutCourse( String s);
	
	public void createabatch() throws AdmistartorException;
	
	public void allocateastudent() throws AdmistartorException;
	
	public void studentfromeverybatch(int a) throws AdmistartorException,StudentException;
}
