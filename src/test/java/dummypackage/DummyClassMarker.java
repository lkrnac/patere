package dummypackage;

import net.lkrnac.patere.Patere;
import net.lkrnac.patere.PatereTest;

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
	public String getResourcesPathForClass(Patere patere);

	/**
	 * @param patere
	 *            testing {@link Patere} instance
	 * @return Path of test resources for class when default base path is used
	 */
	public String getResourcesPathForMethod(Patere patere);
}
