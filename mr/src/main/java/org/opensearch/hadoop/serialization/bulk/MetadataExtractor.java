/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 *
 * Modifications Copyright OpenSearch Contributors. See
 * GitHub history for details.
 */
 
/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.opensearch.hadoop.serialization.bulk;

import org.opensearch.hadoop.serialization.field.FieldExtractor;

/**
 * Runtime field extractor registry. Useful when the metadata might be available at runtime as oppose to being part of the document itself.
 * Only used internally.
 */
public interface MetadataExtractor {

    enum Metadata {
        INDEX("_index"),
        TYPE("_type"),
        ID("_id"),
        PARENT("_parent"),
        ROUTING("_routing"),

        /**
         * @deprecated TTL is not allowed on index or update requests in Elasticsearch 6.x
         */
        @Deprecated
        TTL("_ttl"),

        /**
         * @deprecated Timestamp is not allowed on index or update requests in Elasticsearch 6.x
         */
        @Deprecated
        TIMESTAMP("_timestamp"),

        VERSION("_version"),
        VERSION_TYPE("_version_type"),

        // UPDATE specific headers
        RETRY_ON_CONFLICT("_retry_on_conflict"),
        DOC("doc"),
        UPSERT("upsert"),
        DOC_AS_UPSERT("doc_as_upsert"),
        SCRIPT("script"),
        PARAMS("params"),
        LANG("lang");


        private final String name;

        private Metadata(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    FieldExtractor get(Metadata metadata);
    void setObject(Object entity);
}