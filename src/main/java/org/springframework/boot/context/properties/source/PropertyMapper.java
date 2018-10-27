/*
 * Copyright 2012-2017 the original author or authors.
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

package org.springframework.boot.context.properties.source;

import org.springframework.core.env.PropertySource;

interface PropertyMapper {

    PropertyMapping[] NO_MAPPINGS = {};

    /**
     * Provide mappings from a {@link ConfigurationPropertySource}
     * {@link ConfigurationPropertyName}.
     * @param configurationPropertyName the name to map
     * @return a stream of mappings or {@code Stream#empty()}
     */
    PropertyMapping[] map(ConfigurationPropertyName configurationPropertyName);

    /**
     * Provide mappings from a {@link PropertySource} property name.
     * @param propertySourceName the name to map
     * @return a stream of mappings or {@code Stream#empty()}
     */
    PropertyMapping[] map(String propertySourceName);

}
