package dummypackage.dummysubpackage;

//CHECKSTYLE:OFF
import net.lkrnac.patere.Patere;
import net.lkrnac.patere.PatereTest;
import dummypackage.DummyClassInTopPackage;
import dummypackage.DummyClassMarker;

//CHECKSTYLE:ON
/**
 * Dummy class used for testing {@link Patere} functionality. Is used by
 * {@link PatereTest} class.
 * <p>
 * Class contains same code as {@link DummyClassInTopPackage}. It is in purpose,
 * because {@link Patere} is reading from stack trace. So values returned by
 * methods here will be different to {@link DummyClassInTopPackage} return
 * values.
 * 
 * @author lubos krnac
 */
public class DummyClassInSubPackage implements DummyClassMarker {
	/**
	 * {@inheritDoc}
	 */
	public String getResourcesPathForClass(Patere patere) {
		return patere.getResourcesPathForClass(this.getClass().getName());
	}

	/**
	 * {@inheritDoc}
	 */
	public String getResourcesPathForMethod(Patere patere) {
		return patere.getResourcesPathForMethod();
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
