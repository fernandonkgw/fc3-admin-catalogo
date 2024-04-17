package com.fullcycle.admin.catalogo.infrastructure.configuration.properties.google;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

public class GoogleCloudProperties implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(GoogleCloudProperties.class);

    private String credencials;

    private String projectId;


    public String getCredencials() {
        return credencials;
    }

    public void setCredencials(String credencials) {
        this.credencials = credencials;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public void afterPropertiesSet() {
        log.debug(toString());
    }

    @Override
    public String toString() {
        return "GoogleCloudProperties{" +
               "projectId='" + projectId + '\'' +
               '}';
    }
}
