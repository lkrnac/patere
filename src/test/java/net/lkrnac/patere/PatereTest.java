package net.lkrnac.patere;

import java.io.File;

import junit.framework.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dummypackage.DummyClassInTopPackage;
import dummypackage.DummyClassMarker;
import dummypackage.dummysubpackage.DummyClassInSubPackage;

/**
 * Unit tests for {@link Patere}
 * 
 * @author lubos krnac
 */
public class PatereTest {
	private static final String TEST_CASES_PROVIDER = "testCases";
	private static final String DUMMY_CUSTOM_TEST_RESOURCES_PATH = "dummyCustomTestResourcesPath";

	/**
	 * Data provider for test
	 * {@link PatereTest#testGetResourcesPathForMethod(int, DummyClassMarker, Patere, String[])}
	 * and
	 * {@link PatereTest#testGetResourcesPathForClass(int, DummyClassMarker, Patere, String[])}
	 * .
	 * <p>
	 * Expected value in data provider is specified as array of String, because
	 * test needs to use {@link File#separator} between each directory level (to
	 * be platform independent) and this array seem to be easier to read.
	 * <p>
	 * {@link PatereTest#testGetResourcesPathForClass(int, DummyClassMarker, Patere, String[])}
	 * doesn't use last element from expected path array.
	 * 
	 * @return test cases
	 */
	@DataProvider
	public final Object[][] testCases() {
		int testCaseIndex = 1;
		//@formatter:off
		return new Object[][] { 
				{ //default configuration with top level package
					testCaseIndex++,
					new DummyClassInTopPackage(),
					new Patere(),
					new String[] { "src", "test", "resources", "dummypackage",
							"DummyClassInTopPackage", "getResourcesPathForMethod" } 
				}, 
				{ //default configuration with sub-packages
					testCaseIndex++,
					new DummyClassInSubPackage(),
					new Patere(),
					new String[] { "src", "test", "resources", "dummypackage.dummysubpackage",
							"DummyClassInSubPackage", "getResourcesPathForMethod" } 
				}, 
				{ //custom base path with top level package
					testCaseIndex++,
					new DummyClassInTopPackage(),
					new Patere(DUMMY_CUSTOM_TEST_RESOURCES_PATH),
					new String[] { DUMMY_CUSTOM_TEST_RESOURCES_PATH, "dummypackage",
							"DummyClassInTopPackage", "getResourcesPathForMethod" } 
				}, 
				{ //custom base path with sub-packages
					testCaseIndex++,
					new DummyClassInSubPackage(),
					new Patere(DUMMY_CUSTOM_TEST_RESOURCES_PATH),
					new String[] { DUMMY_CUSTOM_TEST_RESOURCES_PATH,
							"dummypackage.dummysubpackage", "DummyClassInSubPackage",
							"getResourcesPathForMethod" } 
				}, 
				{ //custom base path with top level package + removing file separator from custom base path
					testCaseIndex++,
					new DummyClassInTopPackage(),
					new Patere(DUMMY_CUSTOM_TEST_RESOURCES_PATH + File.separator),
					new String[] { DUMMY_CUSTOM_TEST_RESOURCES_PATH, "dummypackage",
							"DummyClassInTopPackage", "getResourcesPathForMethod" } 
				}, 
				{ //custom base path with sub-packages + removing file separator from custom base path
					testCaseIndex++,
					new DummyClassInSubPackage(),
					new Patere(DUMMY_CUSTOM_TEST_RESOURCES_PATH + File.separator),
					new String[] {DUMMY_CUSTOM_TEST_RESOURCES_PATH, "dummypackage.dummysubpackage",
							"DummyClassInSubPackage", "getResourcesPathForMethod" }
				},
		};
		//@formatter:on
	}

	/**
	 * Unit test for {@link Patere#getResourcesPathMethod()}
	 * <p>
	 * It uses dummy classes to call testing method, because Patere uses stack
	 * trace to build test resources path.
	 * 
	 * @param testCaseIndex
	 *            to easily identify failing test case
	 * @param dummyClass
	 *            dummy class in which Patere usage is simulated
	 * @param testingObject
	 *            initialized testing object
	 * @param expectedPathArray
	 *            array of expected directory structure
	 */
	@Test(dataProvider = TEST_CASES_PROVIDER)
	public void testGetResourcesPathForMethod(int testCaseIndex, DummyClassMarker dummyClass,
			Patere testingObject, String[] expectedPathArray) {
		//GIVEN see JavaDoc of test parameters

		//WHEN
		String resourcesPathForMethod = dummyClass.getResourcesPathForMethod(testingObject);

		//THEN
		Assert.assertEquals(buildPathString(expectedPathArray, false), resourcesPathForMethod);
	}

	/**
	 * Unit test for {@link Patere#getResourcesPathForClass(String)}
	 * <p>
	 * It uses dummy classes to call testing method, because Patere uses stack
	 * trace to build test resources path.
	 * 
	 * @param testCaseIndex
	 *            to easily identify failing test case
	 * @param dummyClass
	 *            dummy class in which Patere usage is simulated
	 * @param testingObject
	 *            initialized testing object
	 * @param expectedPathArray
	 *            array of expected directory structure
	 */
	@Test(dataProvider = TEST_CASES_PROVIDER)
	public void testGetResourcesPathForClass(int testCaseIndex, DummyClassMarker dummyClass,
			Patere testingObject, String[] expectedPathArray) {
		//GIVEN see JavaDoc of test parameters

		//WHEN
		String resourcesPathForMethod = dummyClass.getResourcesPathForClass(testingObject);

		//THEN
		Assert.assertEquals(buildPathString(expectedPathArray, true), resourcesPathForMethod);
	}

	/**
	 * Builds path string out of given array of directories
	 * 
	 * @param directoriesArray
	 *            array of directories
	 * @param ignoreLastDirectory
	 *            flag which is used for ignoring last directory in the array.
	 *            It allows
	 *            {@link PatereTest#testGetResourcesPathForClass(int, DummyClassMarker, Patere, String[])}
	 *            use same data provider as
	 *            {@link PatereTest#testGetResourcesPathForMethod(int, DummyClassMarker, Patere, String[])}
	 * @return string path
	 */
	private String buildPathString(String[] directoriesArray, boolean ignoreLastDirectory) {
		//blank last directory from path if needed
		if (ignoreLastDirectory) {
			directoriesArray[directoriesArray.length - 1] = null;
		}

		StringBuilder expectedPath = new StringBuilder();
		for (String directory : directoriesArray) {
			if (directory != null) {
				expectedPath.append(directory).append(File.separator);
			}
		}
		String expectedPathString = expectedPath.toString();
		return expectedPathString;
	}
}
