/*
 * JBoss, Home of Professional Open Source.
 * See the COPYRIGHT.txt file distributed with this work for information
 * regarding copyright ownership.  Some portions may be licensed
 * to Red Hat, Inc. under one or more contributor license agreements.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */
package org.teiid.service;

import java.io.Serializable;

import javax.persistence.*;

import org.teiid.core.util.StringUtil;
import org.teiid.logging.AuditMessage;

@Entity(name="auditlog")
public class AuditEnitity implements Serializable {
    private static final long serialVersionUID = 8457913413319474639L;

    private long id = 0L;
    private String context;
    private String activity;
    private String resources;
    private String requestId;
    private String principal;
    private String vdbName;
    private String vdbVersion;
    private String sessionId;
    private String applicationName;
    private String authType;
    private String userName;
    private String clientHostName;
    private String clientIpAddress;
    private String clientMac;
    private boolean passThrough;
    
    
    public AuditEnitity() {
        // default
    }
    
    public AuditEnitity(AuditMessage msg) {
        this.context = msg.getContext();
        this.activity = msg.getActivity();
        this.resources = StringUtil.toString(msg.getResources());
        if (msg.getCommandContext() != null) {
            this.requestId = msg.getCommandContext().getRequestId();
            this.principal = msg.getCommandContext().getUserName();
            this.vdbName = msg.getCommandContext().getVdbName();
            this.vdbVersion = msg.getCommandContext().getVdbVersion();
            this.sessionId = msg.getCommandContext().getSession().getSessionId();
            this.applicationName = msg.getCommandContext().getSession().getApplicationName();
        } else if (msg.getLogonInfo() != null) {
            this.authType = msg.getLogonInfo().getAuthType();
            this.userName = msg.getLogonInfo().getUserName();
            this.clientHostName = msg.getLogonInfo().getClientHostName();
            this.clientIpAddress = msg.getLogonInfo().getClientIpAddress();
            this.clientMac = msg.getLogonInfo().getClientMac();
            this.passThrough = msg.getLogonInfo().isPassThrough();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public long getId() {
        return this.id;
    }    
    
    public void setId(long id) {
        this.id = id;
    }       
    
    @Column(name = "context", length=25)
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Column(name = "activity", length=255)
    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Column(name = "resources", length=2048)
    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    @Column(name = "requestid", length=50)
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Column(name = "principal", length=50)
    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    @Column(name = "vdbname", length=255)
    public String getVdbName() {
        return vdbName;
    }

    public void setVdbName(String vdbName) {
        this.vdbName = vdbName;
    }

    @Column(name = "vdbversion")
    public String getVdbVersion() {
        return vdbVersion;
    }

    public void setVdbVersion(String vdbVersion) {
        this.vdbVersion = vdbVersion;
    }

    @Column(name = "sessionid", length=50)
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Column(name = "applicationname", length=255)
    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    @Column(name = "authType", length=50)
    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    @Column(name = "userName", length=255)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "clientHostName", length=255)
    public String getClientHostName() {
        return clientHostName;
    }

    public void setClientHostName(String clientHostName) {
        this.clientHostName = clientHostName;
    }

    @Column(name = "clientIpAddress", length=50)
    public String getClientIpAddress() {
        return clientIpAddress;
    }

    public void setClientIpAddress(String clientIpAddress) {
        this.clientIpAddress = clientIpAddress;
    }

    @Column(name = "clientMac", length=50)
    public String getClientMac() {
        return clientMac;
    }

    public void setClientMac(String clientMac) {
        this.clientMac = clientMac;
    }

    @Column(name = "passThrough", length=10)
    public boolean isPassThrough() {
        return passThrough;
    }

    public void setPassThrough(boolean passThrough) {
        this.passThrough = passThrough;
    }    
}
