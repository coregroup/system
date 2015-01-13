/**
 * 
 */
package models.curriculum;

import java.util.List;

import models.users.Student;
import models.users.Teacher;

/**
 * @author priscylla
 *
 */
public class Section {
	
	private String name;
	private String description;
	private List<Student> students;
	private List<Teacher> teachers;
	private Course course;

}
