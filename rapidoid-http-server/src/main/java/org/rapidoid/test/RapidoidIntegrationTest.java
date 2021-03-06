package org.rapidoid.test;

/*
 * #%L
 * rapidoid-http-server
 * %%
 * Copyright (C) 2014 - 2016 Nikolche Mihajlovski and contributors
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.junit.After;
import org.junit.Before;
import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.ScanPackages;
import org.rapidoid.annotation.Since;
import org.rapidoid.beany.Metadata;
import org.rapidoid.commons.Env;
import org.rapidoid.ioc.IoC;
import org.rapidoid.log.Log;
import org.rapidoid.log.LogLevel;
import org.rapidoid.setup.App;
import org.rapidoid.sql.JDBC;
import org.rapidoid.u.U;

@Authors("Nikolche Mihajlovski")
@Since("5.1.6")
public abstract class RapidoidIntegrationTest extends RapidoidTest {

	public static final int TEST_PORT = 8888;

	private static final String H2_DRIVER = "org.h2.Driver";

	@Before
	public void beforeTest() {
		isTrue(Env.test());
		isTrue(Env.profiles().contains("test"));
		Log.setLogLevel(LogLevel.DEBUG);

		IoC.autowire(this);

		ScanPackages scan = Metadata.getAnnotationRecursive(getClass(), ScanPackages.class);

		if (scan != null) {
			String[] pkgs = scan.value();
			U.must(U.notEmpty(pkgs), "@ScanPackages requires a list of packages to scan!");
			App.scan(pkgs);
		}
	}

	@After
	public void afterTest() {
		if (H2_DRIVER.equals(JDBC.defaultApi().driver())) {
			Log.info("Dropping all objects in the H2 database");
			JDBC.execute("DROP ALL OBJECTS");
		}
	}

	protected String localhost(String uri) {
		return localhost(TEST_PORT, uri);
	}

	protected String localhost(int port, String uri) {
		return "http://localhost:" + port + uri;
	}

}
