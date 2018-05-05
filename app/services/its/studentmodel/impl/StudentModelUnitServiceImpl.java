package services.its.studentmodel.impl;

import java.util.List;

import models.its.StudentModelUnit;
import repositories.its.studentmodel.StudentModelUnitRepository;
import repositories.its.studentmodel.impl.StudentModelUnitRepositoryImpl;
import services.its.studentmodel.StudentModelUnitService;

public class StudentModelUnitServiceImpl implements StudentModelUnitService{
	
	private StudentModelUnitRepository repository;
	
	public StudentModelUnitServiceImpl() {
		super();
		this.repository = new StudentModelUnitRepositoryImpl();
	}
	
	
	@Override
	public void save(StudentModelUnit studentModelUnit) {
		repository.save(studentModelUnit);
		
	}

	@Override
	public void delete(StudentModelUnit studentModelUnit) {
		repository.delete(studentModelUnit);
		
	}

	@Override
	public void update(StudentModelUnit studentModelUnit) {
		repository.update(studentModelUnit);
		
	}

	@Override
	public List<StudentModelUnit> findAll() {
		return repository.findAll();
	}

	@Override
	public StudentModelUnit findById(Long id) {
		return repository.findById(id);
	}
	

}
