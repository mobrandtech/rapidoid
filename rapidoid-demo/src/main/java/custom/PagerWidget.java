package custom;

import org.rapidoid.html.Tag;
import org.rapidoid.var.Var;

/*
 * #%L
 * rapidoid-demo
 * %%
 * Copyright (C) 2014 Nikolche Mihajlovski
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

public class PagerWidget extends org.rapidoid.pages.PagerWidget {

	public PagerWidget(int from, int to, Var<Integer> pageNumber) {
		super(from, to, pageNumber);
	}

	@Override
	protected Tag prev() {
		return a_void("Previous");
	}

	@Override
	protected Tag next() {
		return a_void("Next");
	}

}
