/**
com.eagen.propertyutils.PropertyUtils.java

Copyright 2018 David Eagen

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.eagen.propertyutils;

/** Utility methods to read system properties and handle SecurityExceptions. */
public final class PropertyUtils {
  /** Private constructor, since this is a purely static class. */
  private PropertyUtils() {
    throw new UnsupportedOperationException();
  }

  /**
   * Get a system property in a manner cognizant of the SecurityManager.
   *
   * @param key the name of the system property.
   * @return the string value of the system property, or null if there is no
   *     property with that key or if the property read was denied.
   * @exception NullPointerException if <code>key</code> is <code>null</code>.
   * @exception IllegalArgumentException if <code>key</code> is empty.
   */
  public static String getSystemProperty(String key) {
    String value = null;
    try {
      value = System.getProperty(key);
    } catch (SecurityException e) {
      // Do nothing, leaving the value equal to null.
    }
    return value;
  }

  /**
   * Get a system property in a manner cognizant of the SecurityManager.
   *
   * @param key the name of the system property.
   * @param defaultValue the default value to return
   * @return the string value of the system property. If there is no property
   *     with that key or if access to the property is denied by the
   *     SecurityManager, the default value is returned.
   * @exception NullPointerException if <code>key</code> is <code>null</code>.
   * @exception IllegalArgumentException if <code>key</code> is empty.
   */
  public static String getSystemProperty(String key, String defaultValue) {
    String propertyValue = PropertyUtils.getSystemProperty(key);
    return propertyValue != null ? propertyValue : defaultValue;
  }
}
