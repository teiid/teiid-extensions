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
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.teiid.logging.CommandLogMessage;
import org.teiid.logging.CommandLogMessage.Event;

@Entity(name="commandlog")
public class CommandEnitity implements Serializable {
	private static final long serialVersionUID = -8904500450865053632L;
    
    private long id = 0L;
    private Timestamp time;
    private String eventType;
    private String sessionId;
    private String requestId;
    private String vdbName;
    private int vdbVersion;
    private String applicationName;
    private String transactionId;
    private String principal;
    private Long sourceCommandId;
    private boolean sourceCommand;
    private String executionId;
    private String modelName;
    private String translatorName;
    private String sql;
    private Integer rowCount;
    private Blob plan;
    
    public CommandEnitity() {
        // default
    }
    
    public CommandEnitity(CommandLogMessage msg) {
    	this.time = new Timestamp(msg.getTimestamp());
    	this.eventType = msg.getStatus().name();
    	this.sessionId = msg.getSessionID();
    	this.requestId = msg.getRequestID();
    	this.vdbName = msg.getVdbName();
    	this.vdbVersion = msg.getVdbVersion();
    	this.applicationName = msg.getApplicationName();
    	this.transactionId = msg.getTransactionID();
    	this.principal = msg.getPrincipal();
    	this.sourceCommandId = msg.getSourceCommandID();
    	this.sourceCommand = msg.isSource();
        if (msg.getExecutionContext() != null) {
            this.executionId = msg.getExecutionContext().getExecutionCountIdentifier();
        }
        this.modelName = msg.getModelName();
        this.translatorName = msg.getTranslatorName();
        this.sql = msg.getSql();
        this.rowCount = msg.getRowCount();
        if ((msg.getPlan() != null && msg.getStatus() == Event.PLAN)) {
            try {
                this.plan = new SerialBlob(msg.getPlan().toString().getBytes());
            } catch (SerialException e) {
                //ignore
            } catch (SQLException e) {
                //ignore
            }
        }    	
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return this.id;
    }    
    
    public void setId(long id) {
        this.id = id;
    }     
    
    @Column(name = "logtime")
    public  Timestamp getTimestamp() {
        return this.time;
    }
    
    public void setTimestamp(Timestamp time) {
        this.time = time;
    }    
    
    @Column(name = "eventtype", length=10)
    public String getStatus() {
        return this.eventType;
    }
    
    public void setStatus(String value) {
        this.eventType = value;
    }    
    
    @Column(name = "sessionid", length=50)
    public String getSessionId() {
        return this.sessionId;
    }
    
    public void setSessionId(String value) {
        this.sessionId = value;
    }    
    
    @Column(name = "requestid", length=50)
    public String getRequestId() {
        return this.requestId;
    }    
    
    public void setRequestId(String value) {
    	this.requestId = value;
    }
    
    @Column(name = "vdbname", length=255)
    public String getVdbName() {
        return this.vdbName;
    }

    public void setVdbName(String value) {
        this.vdbName = value;
    }
    
    @Column(name = "vdbversion")
    public int getVdbVersion() {
        return this.vdbVersion;
    }

    public void setVdbVersion(int value) {
        this.vdbVersion = value;
    }

    @Column(name = "applicationname", length=255)
    public String getApplicationName() {
        return this.applicationName;
    }

    public void setApplicationName(String value) {
        this.applicationName = value;
    }
    
    @Column(name = "transactionid", length=100)
    public String getTransactionID() {
        return this.transactionId;
    }
    
    public void setTransactionID(String value) {
        this.transactionId = value;
    }
    
    @Column(name = "principal", length=50)
    public String getPrincipal() {
        return this.principal;
    }
    
    public void setPrincipal(String value) {
        this.principal = value;
    }
    
    @Column(name = "sourcecommand")
    public boolean isSource() {
        return sourceCommand;
    }
    
    public void setSource(boolean value) {
        this.sourceCommand = value;
    }    
    
    @Column(name = "sourcerequestid", length=50)
    public Long getSourceCommandId() {
        return this.sourceCommandId;
    }
    
    public void setSourceCommandId(Long value) {
        this.sourceCommandId = value;
    }
    
    @Column(name = "executionid", length=50)
    public String getExecutionIdentifier() {
        return this.executionId;
    }
    
    public void setExecutionIdentifier(String value) {
        this.executionId = value;
    }
    
    @Column(name = "modelname", length=255)
    public String getModelName() {
        return this.modelName;
    }

    public void setModelName(String value) {
        this.modelName = value;
    }
    
    @Column(name = "translatorname", length=50)
    public String getTranslatorName() {
        return this.translatorName;
    }

    public void setTranslatorName(String value) {
        this.translatorName = value;
    }    

    @Column(name = "sqlcmd", length=8196)
    public String getSql() {
        return this.sql;
    }
    
    public void setSql(String sql) {
        this.sql = sql;
    }
    
    @Column(name = "rowcount")
    public Integer getRowCount() {
        return this.rowCount;
    }
    
    public void setRowCount(Integer value) {
        this.rowCount = value;
    }

    @Column(name = "plan")
    public Blob getPlan() {
        return this.plan;
    }

    public void setPlan(Blob plan) {
        this.plan = plan;
    }
}
