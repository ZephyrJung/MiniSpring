/*
 * Copyright 2002-2017 the original author or authors.
 *
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
 */

package org.springframework.beans;

import java.util.Map;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.lang.Nullable;

public interface PropertyAccessor {

    /**
     * Path separator for nested properties.
     * Follows normal Java conventions: getFoo().getBar() would be "foo.bar".
     */
    String NESTED_PROPERTY_SEPARATOR = ".";
    char NESTED_PROPERTY_SEPARATOR_CHAR = '.';

    /**
     * Marker that indicates the start of a property key for an
     * indexed or mapped property like "person.addresses[0]".
     */
    String PROPERTY_KEY_PREFIX = "[";
    char PROPERTY_KEY_PREFIX_CHAR = '[';

    /**
     * Marker that indicates the end of a property key for an
     * indexed or mapped property like "person.addresses[0]".
     */
    String PROPERTY_KEY_SUFFIX = "]";
    char PROPERTY_KEY_SUFFIX_CHAR = ']';


    /**
     * Determine whether the specified property is readable.
     * <p>Returns {@code false} if the property doesn't exist.
     * @param propertyName the property to check
     * (may be a nested path and/or an indexed/mapped property)
     * @return whether the property is readable
     */
    boolean isReadableProperty(String propertyName);

    /**
     * Determine whether the specified property is writable.
     * <p>Returns {@code false} if the property doesn't exist.
     * @param propertyName the property to check
     * (may be a nested path and/or an indexed/mapped property)
     * @return whether the property is writable
     */
    boolean isWritableProperty(String propertyName);

    @Nullable
    Class<?> getPropertyType(String propertyName) throws BeansException;

    @Nullable
    TypeDescriptor getPropertyTypeDescriptor(String propertyName) throws BeansException;

    @Nullable
    Object getPropertyValue(String propertyName) throws BeansException;

    void setPropertyValue(String propertyName, @Nullable Object value) throws BeansException;

    void setPropertyValue(PropertyValue pv) throws BeansException;

    void setPropertyValues(Map<?, ?> map) throws BeansException;

    void setPropertyValues(PropertyValues pvs) throws BeansException;

    void setPropertyValues(PropertyValues pvs, boolean ignoreUnknown)
            throws BeansException;

    void setPropertyValues(PropertyValues pvs, boolean ignoreUnknown, boolean ignoreInvalid)
            throws BeansException;

}
