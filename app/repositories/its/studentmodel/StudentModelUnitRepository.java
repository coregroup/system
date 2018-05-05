package repositories.its.studentmodel;

import java.util.List;

import models.its.StudentModelUnit;

public interface StudentModelUnitRepository {

	public void save(StudentModelUnit studentModelUnit);
	
	public void delete(StudentModelUnit studentModelUnit);
	
	public void update(StudentModelUnit studentModelUnit);
	
	public List<StudentModelUnit> findAll();
	
	public StudentModelUnit findById(Long id);
}
