/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.aries.jax.rs.jackson.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import org.osgi.annotation.bundle.Requirement;
import org.osgi.framework.Constants;
import org.osgi.namespace.service.ServiceNamespace;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * This annotation can be used to require a JAX-RS extender providing
 * a {@link MessageBodyReader} and a {@link MessageBodyWriter} for media
 * type {@code application/json}. It can be used directly, or as a meta-
 * annotation.
 * <p>
 * This annotation can be applied to a JAX-RS resource to declare its
 * requirement for JSON serialization/deserialization.
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.PACKAGE})
@Requirement(
    namespace = ServiceNamespace.SERVICE_NAMESPACE,
    filter = RequireJaxrsJsonProvider.FILTER_COMPOSED
)
public @interface RequireJaxrsJsonProvider {

    static final String FILTER_MEDIA_TYPE = "(" + JaxrsWhiteboardConstants.JAX_RS_MEDIA_TYPE + "=" + MediaType.APPLICATION_JSON + ")";
    static final String FILTER_OBJECTCLASS_READER = "(" + Constants.OBJECTCLASS + "=javax.ws.rs.ext.MessageBodyReader)";
    static final String FILTER_OBJECTCLASS_WRITER = "(" + Constants.OBJECTCLASS + "=javax.ws.rs.ext.MessageBodyWriter)";
    static final String FILTER_COMPOSED = "(&" + FILTER_MEDIA_TYPE + FILTER_OBJECTCLASS_READER + FILTER_OBJECTCLASS_WRITER + ")";

    // This is a marker annotation.

}