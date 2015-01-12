/**
 * 
 */
package models.curriculum;

import java.util.List;

import models.users.Student;
import models.users.Teacher;
import play.db.ebean.Model;

/**
 * @author priscylla
 *
 */
public class Section extends Model{
	
	private String name;
	private String description;
	private List<Student> students;
	private List<Teacher> teachers;
	private Course course;

}
