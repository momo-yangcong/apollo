/*
 * Copyright 2021 Apollo Authors
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
 *
 */
package com.ctrip.framework.apollo.portal.listener;

import com.ctrip.framework.apollo.common.entity.AppNamespace;
import com.google.common.base.Preconditions;
import org.springframework.context.ApplicationEvent;

public class AppNamespaceDeletionEvent extends ApplicationEvent {

    public AppNamespaceDeletionEvent(Object source) {
        super(source);
    }

    public AppNamespace getAppNamespace() {
        Preconditions.checkState(source != null);
        return (AppNamespace) this.source;
    }
}
