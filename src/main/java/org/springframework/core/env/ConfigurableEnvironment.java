/*
 * Copyright 2002-2018 the original author or authors.
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

package org.springframework.core.env;

import java.util.Map;

public interface ConfigurableEnvironment extends Environment, ConfigurablePropertyResolver {

    void setActiveProfiles(String... profiles);

    /**
     * Add a profile to the current set of active profiles.
     * @throws IllegalArgumentException if the profile is null, empty or whitespace-only
     * @see #setActiveProfiles
     */
    void addActiveProfile(String profile);

    void setDefaultProfiles(String... profiles);

    MutablePropertySources getPropertySources();

    /**
     * Return the value of {@link System#getProperties()} if allowed by the current
     * {@link SecurityManager}, otherwise return a map implementation that will attempt
     * to access individual keys using calls to {@link System#getProperty(String)}.
     * <p>Note that most {@code Environment} implementations will include this system
     * properties map as a default {@link PropertySource} to be searched. Therefore, it is
     * recommended that this method not be used directly unless bypassing other property
     * sources is expressly intended.
     * <p>Calls to {@link Map#get(Object)} on the Map returned will never throw
     * {@link IllegalAccessException}; in cases where the SecurityManager forbids access
     * to a property, {@code null} will be returned and an INFO-level log message will be
     * issued noting the exception.
     */
    Map<String, Object> getSystemProperties();

    /**
     * Return the value of {@link System#getenv()} if allowed by the current
     * {@link SecurityManager}, otherwise return a map implementation that will attempt
     * to access individual keys using calls to {@link System#getenv(String)}.
     * <p>Note that most {@link Environment} implementations will include this system
     * environment map as a default {@link PropertySource} to be searched. Therefore, it
     * is recommended that this method not be used directly unless bypassing other
     * property sources is expressly intended.
     * <p>Calls to {@link Map#get(Object)} on the Map returned will never throw
     * {@link IllegalAccessException}; in cases where the SecurityManager forbids access
     * to a property, {@code null} will be returned and an INFO-level log message will be
     * issued noting the exception.
     */
    Map<String, Object> getSystemEnvironment();

    /**
     * Append the given parent environment's active profiles, default profiles and
     * property sources to this (child) environment's respective collections of each.
     * <p>For any identically-named {@code PropertySource} instance existing in both
     * parent and child, the child instance is to be preserved and the parent instance
     * discarded. This has the effect of allowing overriding of property sources by the
     * child as well as avoiding redundant searches through common property source types,
     * e.g. system environment and system properties.
     * <p>Active and default profile names are also filtered for duplicates, to avoid
     * confusion and redundant storage.
     * <p>The parent environment remains unmodified in any case. Note that any changes to
     * the parent environment occurring after the call to {@code merge} will not be
     * reflected in the child. Therefore, care should be taken to configure parent
     * property sources and profile information prior to calling {@code merge}.
     * @param parent the environment to merge with
     * @since 3.1.2
     * @see org.springframework.context.support.AbstractApplicationContext#setParent
     */
    void merge(ConfigurableEnvironment parent);

}
