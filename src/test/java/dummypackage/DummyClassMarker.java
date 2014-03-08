package dummypackage;

//CHECKSTYLE:OFF
import net.lkrnac.lib.patere.Patere;
import net.lkrnac.lib.patere.PatereTest;

//CHECKSTYLE:ON

/**
 * Marker interface for dummy classes used for testing {@link Patere}
 * functionality. Is used by {@link PatereTest} class.
 * 
 * @author lubos krnac
 */
public interface DummyClassMarker {
	/**
	 * @param patere
	 *            testing {@link Patere} instance
	 * @return Path of test resources for class when default base path is used
	 */
	String getResourcesPathForClass(Patere patere);

	/**
	 * @param patere
	 *            testing {@link Patere} instance
	 * @return Path of test resources for class when default base path is used
	 */
	String getResourcesPathForMethod(Patere patere);
}
