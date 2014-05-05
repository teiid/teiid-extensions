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
package org.teiid.logger;


import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.teiid.logging.AuditMessage;
import org.teiid.logging.CommandLogMessage;

public class DatabaseAppender extends Handler {
	
	@Override
    public void publish(LogRecord record) {
    	if (record == null){
    		return;
    	}
    	
        Object obj = record.getParameters()[0];
        DatabaseService service = getService();
        if (service != null) {
            if (obj instanceof CommandLogMessage) {
                CommandLogMessage msg = (CommandLogMessage)obj;
                service.log(msg);
            }
            else if (obj instanceof AuditMessage) {
                AuditMessage msg = (AuditMessage)obj;
                service.log(msg);
            }
        }
    }

    @Override
    public void flush() {
        
    }

    @Override
    public void close() throws SecurityException {
        
    }
    
    private DatabaseService getService() {
    	 try {
			InitialContext ctx = new InitialContext();  
			return (DatabaseService) ctx.lookup("java:global/database-service/teiid-database-service"); //$NON-NLS-1$
		} catch (NamingException e) {
			return null;
		}      	
    }
}
