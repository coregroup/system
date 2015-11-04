/**
 * 
 */
package repositories.modules.impl;

import java.util.List;

import models.course.Module;
import repositories.modules.ModuleRepository;

import com.avaje.ebean.Ebean;

/**
 * @author Priscylla
 *
 */
public class ModuleRepositoryImpl implements ModuleRepository {

	/* (non-Javadoc)
	 * @see repositories.modules.ModuleRepository#save(models.course.Module)
	 */
	@Override
	public void save(Module module) {
		Ebean.save(module);
	}

	/* (non-Javadoc)
	 * @see repositories.modules.ModuleRepository#delete(models.course.Module)
	 */
	@Override
	public void delete(Module module) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see repositories.modules.ModuleRepository#update(models.course.Module)
	 */
	@Override
	public void update(Module module) {
		Ebean.update(module);
	}

	/* (non-Javadoc)
	 * @see repositories.modules.ModuleRepository#findAll()
	 */
	@Override
	public List<Module> findAll() {
		List<Module> modules =  Ebean.find(Module.class).findList();
		return modules;
	}

	/* (non-Javadoc)
	 * @see repositories.modules.ModuleRepository#findById(java.lang.Long)
	 */
	@Override
	public Module findById(Long id) {
		Module module = Ebean.find(Module.class).where().eq("id", id.toString()).findUnique();
		return module;
	}

}
