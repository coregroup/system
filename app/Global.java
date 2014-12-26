import java.util.Calendar;

import converters.Html5CalendarFormatter;
import play.Application;
import play.GlobalSettings;
import play.data.format.Formatters;

/**
 * 
 */

/**
 * @author priscylla
 *
 */
public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app) {
		super.onStart(app);
		Formatters.register(Calendar.class, new Html5CalendarFormatter());
	}

}
