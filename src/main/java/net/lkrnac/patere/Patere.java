package net.lkrnac.patere;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

/**
 * Simple utility class for building test resources path. It reads stack trace
 * of current thread and uses the packages, class name and optionally test
 * method name to build belonging test resources path. This can be handy when
 * test suite uses resources heavily. Helps categorize resources into similar
 * directory structure as tests.
 * 
 * @author lubos krnac
 */
public class Patere {
	/**
	 * Default base path for test resources. Maven default test resources
	 * relative path is used: 'src/test/resources'.
	 */
	private static final String DEFAULT_TEST_RESOURCES_PATH = "src" + File.separator + "test"
			+ File.separator + "resources";

	private String baseTestResourcesPath;

	/**
	 * Creates Patere utility instance with default test resources path (that is
	 * Maven default "src/test/resources").
	 */
	public Patere() {
		this.baseTestResourcesPath = DEFAULT_TEST_RESOURCES_PATH;
	}

	/**
	 * Creates Patere utility instance with given base path.
	 * 
	 * @param baseTestResourcesPath
	 *            custom base path for test resources
	 */
	public Patere(String baseTestResourcesPath) {
		this.baseTestResourcesPath = StringUtils.removeEnd(baseTestResourcesPath, File.separator);
	}

	/**
	 * Returns relative path where test resources for calling test method should
	 * be stored.
	 * <p>
	 * <b>This method expects that is called directly from testing method (not
	 * from some "helper" method)</b>
	 * <p>
	 * Desired path would be [base_test_resources_path]
	 * /[test_package]/[test_class]/[test_method]
	 * <p>
	 * Uses File.separator constant so it is platform independent.
	 * 
	 * @return relative path to belonging test resources
	 */
	public String getResourcesPathMethod() {
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		StackTraceElement stackTraceElement = ste[2];
		return getResourcesPathForClass(stackTraceElement.getClassName())
				+ stackTraceElement.getMethodName() + File.separator;
	}

	/**
	 * Returns relative path where test resources for given test class should be
	 * stored.
	 * <p>
	 * Desired path would be [base_test_resources_path]
	 * /[test_package]/[test_class]
	 * 
	 * @param fullClassName
	 *            full class name of unit test class
	 * @return relative path to belonging test resources
	 */
	public String getResourcesPathForClass(String fullClassName) {
		StringBuilder stringBuilder = new StringBuilder();

		//@formatter:off
		stringBuilder
			.append(baseTestResourcesPath)
			.append(File.separator)
			.append(fullClassName);
		//@formatter:on

		int lastIndexOf = stringBuilder.lastIndexOf(".");
		stringBuilder.replace(lastIndexOf, lastIndexOf + 1, File.separator);
		stringBuilder.append(File.separator);
		return stringBuilder.toString();
	}
}
