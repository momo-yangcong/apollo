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
package com.ctrip.framework.apollo.common.entity;

import com.ctrip.framework.apollo.common.utils.InputValidator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "App")
@SQLDelete(sql = "Update App set isDeleted = 1 where id = ?")
@Where(clause = "isDeleted = 0")
public class App extends BaseEntity {

    @NotBlank(message = "Name cannot be blank")
    @Column(name = "Name", nullable = false)
    private String name;

    @NotBlank(message = "AppId cannot be blank")
    @Pattern(
            regexp = InputValidator.CLUSTER_NAMESPACE_VALIDATOR,
            message = InputValidator.INVALID_CLUSTER_NAMESPACE_MESSAGE
    )
    @Column(name = "AppId", nullable = false)
    private String appId;

    @Column(name = "OrgId", nullable = false)
    private String orgId;

    @Column(name = "OrgName", nullable = false)
    private String orgName;

    @NotBlank(message = "OwnerName cannot be blank")
    @Column(name = "OwnerName", nullable = false)
    private String ownerName;

    @NotBlank(message = "OwnerEmail cannot be blank")
    @Column(name = "OwnerEmail", nullable = false)
    private String ownerEmail;

    public static Builder builder() {
        return new Builder();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String toString() {
        return toStringHelper().add("name", name).add("appId", appId)
                .add("orgId", orgId)
                .add("orgName", orgName)
                .add("ownerName", ownerName)
                .add("ownerEmail", ownerEmail).toString();
    }

    public static class Builder {

        private App app = new App();

        public Builder() {
        }

        public Builder name(String name) {
            app.setName(name);
            return this;
        }

        public Builder appId(String appId) {
            app.setAppId(appId);
            return this;
        }

        public Builder orgId(String orgId) {
            app.setOrgId(orgId);
            return this;
        }

        public Builder orgName(String orgName) {
            app.setOrgName(orgName);
            return this;
        }

        public Builder ownerName(String ownerName) {
            app.setOwnerName(ownerName);
            return this;
        }

        public Builder ownerEmail(String ownerEmail) {
            app.setOwnerEmail(ownerEmail);
            return this;
        }

        public App build() {
            return app;
        }

    }


}
