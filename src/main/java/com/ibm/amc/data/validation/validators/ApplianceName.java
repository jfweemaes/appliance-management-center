/**
 * Copyright 2014 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/
package com.ibm.amc.data.validation.validators;

import java.lang.annotation.Annotation;
import com.ibm.amc.data.validation.Validator;

/**
 * A validator that accepts only valid symbolic names for Datapower appliances.
 * Specifically, accept only alphanumerics (of either case) and the symbols
 * "-", "_" and ".". This implementation is not restricted to plain ASCII characters.
 */
public class ApplianceName implements Validator 
{
	// @CLASS-COPYRIGHT@

	@Override
	public boolean validate(Object value, Annotation constraints) 
	{
		if(value == null) return true;
		if(!(value instanceof String)) throw new IllegalArgumentException();
		
		// We could use a regex or something to do this in fewer lines of code, but
		// a straightforward scan has to be faster than firing up a regex engine.
		
		char[] name = ((String) value).toCharArray();
		nextChar: for (char c : name) 
		{
			if(Character.isLetterOrDigit(c)) continue nextChar;
			if(c == '_' || c =='.' || c == '-') continue nextChar;
			return false;
		}
		
		return true;
	}
}
