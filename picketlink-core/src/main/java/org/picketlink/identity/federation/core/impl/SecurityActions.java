/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.picketlink.identity.federation.core.impl;

import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Privileged Blocks
 *
 * @author Anil.Saldhana@redhat.com
 * @since Dec 9, 2008
 */
class SecurityActions {

    /**
     * <p>
     * Loads a {@link Class} using the <code>fullQualifiedName</code> supplied. This method tries first to load from the
     * specified {@link Class}, if not found it will try to load from using TCL.
     * </p>
     *
     * @param theClass
     * @param fullQualifiedName
     * @return
     */
    static Class<?> loadClass(final Class<?> theClass, final String fullQualifiedName) {
        SecurityManager sm = System.getSecurityManager();
        
        if (sm != null) {
            return AccessController.doPrivileged(new PrivilegedAction<Class<?>>() {
                public Class<?> run() {
                    ClassLoader classLoader = theClass.getClassLoader();

                    Class<?> clazz = loadClass(classLoader, fullQualifiedName);
                    if (clazz == null) {
                        classLoader = Thread.currentThread().getContextClassLoader();
                        clazz = loadClass(classLoader, fullQualifiedName);
                    }
                    return clazz;
                }
            });
        } else {
            ClassLoader classLoader = theClass.getClassLoader();

            Class<?> clazz = loadClass(classLoader, fullQualifiedName);
            if (clazz == null) {
                classLoader = Thread.currentThread().getContextClassLoader();
                clazz = loadClass(classLoader, fullQualifiedName);
            }
            return clazz;
        }
    }

    /**
     * <p>
     * Loads a class from the specified {@link ClassLoader} using the <code>fullQualifiedName</code> supplied.
     * </p>
     *
     * @param classLoader
     * @param fullQualifiedName
     * @return
     */
    static Class<?> loadClass(final ClassLoader classLoader, final String fullQualifiedName) {
        SecurityManager sm = System.getSecurityManager();
        
        if (sm != null) {
            return AccessController.doPrivileged(new PrivilegedAction<Class<?>>() {
                public Class<?> run() {
                    try {
                        return classLoader.loadClass(fullQualifiedName);
                    } catch (ClassNotFoundException e) {
                    }
                    return null;
                }
            });
        } else {
            try {
                return classLoader.loadClass(fullQualifiedName);
            } catch (ClassNotFoundException e) {
            }
            return null;
        }
    }

    /**
     * <p>Returns a system property value using the specified <code>key</code>. If not found the <code>defaultValue</code> will be returned.</p>
     *
     * @param key
     * @param defaultValue
     * @return
     */
    static String getSystemProperty(final String key, final String defaultValue) {
        SecurityManager sm = System.getSecurityManager();

        if (sm != null) {
            return AccessController.doPrivileged(new PrivilegedAction<String>() {
                public String run() {
                    return System.getProperty(key, defaultValue);
                }
            });
        } else {
            return System.getProperty(key, defaultValue);
        }
    }

    /**
     * Load a resource based on the passed {@link Class} classloader. Failing which try with the Thread Context CL
     *
     * @param clazz
     * @param resourceName
     * @return
     */
    static URL loadResource(final Class<?> clazz, final String resourceName) {
        SecurityManager sm = System.getSecurityManager();
        
        if (sm != null) {
            return AccessController.doPrivileged(new PrivilegedAction<URL>() {
                public URL run() {
                    URL url = null;
                    ClassLoader clazzLoader = clazz.getClassLoader();
                    url = clazzLoader.getResource(resourceName);

                    if (url == null) {
                        clazzLoader = Thread.currentThread().getContextClassLoader();
                        url = clazzLoader.getResource(resourceName);
                    }

                    return url;
                }
            });
        } else {
            URL url = null;
            ClassLoader clazzLoader = clazz.getClassLoader();
            url = clazzLoader.getResource(resourceName);

            if (url == null) {
                clazzLoader = Thread.currentThread().getContextClassLoader();
                url = clazzLoader.getResource(resourceName);
            }

            return url;
        }
    }

    /**
     * Get a system property
     *
     * @param key the key for the property
     * @param defaultValue A default value to return if the property is not set (Can be null)
     * @return
     */
    static String getProperty(final String key, final String defaultValue) {
        SecurityManager sm = System.getSecurityManager();

        if (sm != null) {
            return AccessController.doPrivileged(new PrivilegedAction<String>() {
                public String run() {
                    return System.getProperty(key, defaultValue);
                }
            });
        } else {
            return System.getProperty(key, defaultValue);
        }
   }

}