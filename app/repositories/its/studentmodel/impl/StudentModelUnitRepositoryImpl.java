package repositories.its.studentmodel.impl;

import java.util.List;

import com.avaje.ebean.Ebean;

import models.its.StudentModel;
import models.its.StudentModelUnit;
import repositories.its.studentmodel.StudentModelUnitRepository;

public class StudentModelUnitRepositoryImpl implements StudentModelUnitRepository{

	@Override
	public void save(StudentModelUnit studentModelUnit) {
		Ebean.save(studentModelUnit);
	}

	@Override
	public void delete(StudentModelUnit studentModelUnit) {
		Ebean.delete(studentModelUnit);
		
	}

	@Override
	public void update(StudentModelUnit studentModelUnit) {
		Ebean.update(studentModelUnit);
		
	}

	@Override
	public List<StudentModelUnit> findAll() {
		List<StudentModelUnit> studentModelUnit = Ebean.find(StudentModelUnit.class).findList();
		return studentModelUnit;
	}

	@Override
	public StudentModelUnit findById(Long id) {
		StudentModelUnit studentModelUnit = Ebean.find(StudentModelUnit.class).where().eq("id", id.toString()).findUnique();
		return studentModelUnit;
	}

}
