/**
 * 
 */
package models.curriculum;

import java.util.List;

import models.users.Author;

/**
 * @author priscylla
 *
 */
public class Course {
	
	private String name;
	private String description;
	private List<Section> sections;
	private List<Author> authors;
	private List<Stage> stages;

}
