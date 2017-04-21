package services.module.impl;

import java.util.List;

import models.course.Module;
import repositories.modules.ModuleRepository;
import repositories.modules.impl.ModuleRepositoryImpl;
import services.module.ModuleService;

public class ModuleServiceImpl implements ModuleService {
	
	private ModuleRepository repository;
	

	public ModuleServiceImpl() {
		repository = new ModuleRepositoryImpl();
	}

	@Override
	public void save(Module module) {
		repository.save(module);
	}

	@Override
	public void delete(Module module) {
		repository.delete(module);
	}

	@Override
	public void update(Module module) {
		repository.update(module);
	}

	@Override
	public List<Module> findAll() {
		return repository.findAll();
	}

	@Override
	public Module findById(Long id) {
		return repository.findById(id);
	}

}
