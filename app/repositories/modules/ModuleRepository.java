/**
 * 
 */
package repositories.modules;

import java.util.List;

import models.course.Module;

/**
 * @author Priscylla
 *
 */
public interface ModuleRepository {
	
	public void save(Module module);
	
	public void delete(Module module);
	
	public void update(Module module);
	
	public List<Module> findAll();
	
	public Module findById(Long id);

}
