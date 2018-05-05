package services.its.studentmodel.impl;

import java.util.List;

import models.its.StudentModel;
import repositories.its.studentmodel.StudentModelRepository;
import repositories.its.studentmodel.impl.StudentModelRepositoryImpl;
import services.its.studentmodel.StudentModelService;

public class StudentModelServiceImpl implements StudentModelService {
	
	private StudentModelRepository repository;
	
	public StudentModelServiceImpl() {
		super();
		this.repository = new StudentModelRepositoryImpl();
	}
	
	
	@Override
	public void save(StudentModel studentModel) {
		repository.save(studentModel);
	}

	@Override
	public void delete(StudentModel studentModel) {
		repository.delete(studentModel);
		
	}

	@Override
	public void update(StudentModel studentModel) {
		repository.update(studentModel);
		
	}

	@Override
	public List<StudentModel> findAll() {
		return repository.findAll();
	}

	@Override
	public StudentModel findById(Long id) {
		return repository.findById(id);
	}

}
