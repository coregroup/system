package repositories.its.studentmodel.impl;

import java.util.List;

import com.avaje.ebean.Ebean;

import models.its.StudentModel;
import repositories.its.studentmodel.StudentModelRepository;

public class StudentModelRepositoryImpl implements StudentModelRepository{

	@Override
	public void save(StudentModel studentModel) {
		Ebean.save(studentModel);
		
	}

	@Override
	public void delete(StudentModel studentModel) {
		Ebean.delete(studentModel);
		
	}

	@Override
	public void update(StudentModel studentModel) {
		Ebean.update(studentModel);
	}

	@Override
	public List<StudentModel> findAll() {
		List<StudentModel> studentModel = Ebean.find(StudentModel.class).findList();
		return studentModel;
	}

	@Override
	public StudentModel findById(Long id) {
		StudentModel studentModel = Ebean.find(StudentModel.class).where().eq("id", id.toString()).findUnique();
		return studentModel;
	}

	
}
