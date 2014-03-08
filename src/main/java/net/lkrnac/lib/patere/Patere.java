package net.lkrnac.lib.patere;

import java.nio.file.FileSystems;

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
	private static final String SEPARATOR = FileSystems.getDefault().getSeparator();

	/**
	 * Default base path for test resources. Maven default test resources
	 * relative path is used: 'src/test/resources'.
	 */
	private static final String DEFAULT_TEST_RESOURCES_PATH = "src" + SEPARATOR + "test"
			+ SEPARATOR + "resources";

	private String baseTestResourcesPath;
	private boolean flatPackageRepresentation;

	/**
	 * Creates Patere utility instance with default test resources path (that is
	 * Maven default "src/test/resources") and uses flat package representation
	 * (package is represented as one directory with dots -> e.g.
	 * net.lkrnac.lib.patere)
	 */
	public Patere() {
		this(true, DEFAULT_TEST_RESOURCES_PATH);
	}

	/**
	 * Creates Patere utility instance with given base path. Uses flat package
	 * representation (package is represented as one directory with dots -> e.g.
	 * net.lkrnac.lib.patere)
	 * 
	 * @param baseTestResourcesPath
	 *            custom base path for test resources
	 */
	public Patere(String baseTestResourcesPath) {
		this(true, baseTestResourcesPath);
	}

	/**
	 * Creates Patere utility instance with given base path and package
	 * representation flag.
	 * 
	 * @param flatPackageRepresentation
	 *            <code>true</code> - flat package representation is used
	 *            (package is one directory with dots -> e.g.
	 *            net.lkrnac.lib.patere); <code>false</code> - hierarchical
	 *            package representation is used (package structure represented
	 *            as various sub-directories -> e.g. net/lkrnac/lib/patere)
	 * @param baseTestResourcesPath
	 *            custom base path for test resources
	 */
	public Patere(boolean flatPackageRepresentation, String baseTestResourcesPath) {
		this.baseTestResourcesPath = StringUtils.removeEnd(baseTestResourcesPath, SEPARATOR);
		this.flatPackageRepresentation = flatPackageRepresentation;

	}

	/**
	 * Creates Patere utility instance with default test resources path (that is
	 * Maven default "src/test/resources") and given package representation
	 * flag.
	 * 
	 * @param flatPackageRepresentation
	 *            <code>true</code> - flat package representation is used
	 *            (package is one directory with dots -> e.g.
	 *            net.lkrnac.lib.patere); <code>false</code> - hierarchical
	 *            package representation is used (package structure represented
	 *            as various sub-directories -> e.g. net/lkrnac/lib/patere)
	 */
	public Patere(boolean flatPackageRepresentation) {
		this(flatPackageRepresentation, DEFAULT_TEST_RESOURCES_PATH);
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
	 * Uses SEPARATOR constant so it is platform independent.
	 * 
	 * @return relative path to belonging test resources
	 */
	public String getResourcesPathForMethod() {
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		StackTraceElement stackTraceElement = ste[2];
		return getResourcesPathForClass(stackTraceElement.getClassName())
				+ stackTraceElement.getMethodName() + SEPARATOR;
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
		StringBuilder pathBuilder = new StringBuilder();

		//@formatter:off
		pathBuilder
			.append(baseTestResourcesPath)
			.append(SEPARATOR)
			.append(fullClassName);
		//@formatter:on

		int lastIndexOf = pathBuilder.lastIndexOf(".");

		//update path based on desired package representation
		if (flatPackageRepresentation) {
			pathBuilder.replace(lastIndexOf, lastIndexOf + 1, SEPARATOR);
		} else {
			String tmpPath = StringUtils.replace(pathBuilder.toString(), ".", SEPARATOR);
			pathBuilder = new StringBuilder(tmpPath);
		}
		pathBuilder.append(SEPARATOR);
		return pathBuilder.toString();
	}
}
