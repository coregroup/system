package repositories.its.studentmodel;

import java.util.List;

import models.its.StudentModel;

public interface StudentModelRepository {

	public void save(StudentModel studentModel);
	
	public void delete(StudentModel studentModel);
	
	public void update(StudentModel studentModel);
	
	public List<StudentModel> findAll();
	
	public StudentModel findById(Long id);
}
