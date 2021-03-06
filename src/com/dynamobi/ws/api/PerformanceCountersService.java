/*
Dynamo Web Services is a web service project for administering LucidDB
Copyright (C) 2010 Dynamo Business Intelligence Corporation

This program is free software; you can redistribute it and/or modify it
under the terms of the GNU General Public License as published by the Free
Software Foundation; either version 2 of the License, or (at your option)
any later version approved by Dynamo Business Intelligence Corporation.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
*/
package com.dynamobi.ws.api;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.jws.WebMethod;

import javax.annotation.security.RolesAllowed;

import com.dynamobi.ws.domain.Counter;
import com.dynamobi.ws.util.AppException;
import com.dynamobi.ws.domain.CounterHolder;

/**
 * Service for retrieving some performance information about
 * LucidDB.
 */
@WebService(serviceName="PerformanceCountersService", name="PerformanceCountersService")
public interface PerformanceCountersService
{   

    /**
     * Read all current performance counters.
     * @return a list of Counters
     * @throws AppException
     */
    @WebMethod
    @GET
    @Path("/")
    @RolesAllowed( {"Admin", "Authenticated"} )
    CounterHolder getAllPerformanceCounters() throws AppException;

    /**
     * Return demanded counters.
     * @param names - Comma-separated list of names.
     * @return List of Counter structures.
     */
    @WebMethod
    @GET
    @Path("/list/{names}")
    @RolesAllowed( {"Admin", "Authenticated"} )
    CounterHolder getCountersByNames(@PathParam("names") String names)
    throws AppException;

    /**
     * Read special performance counter through counter name.
     * @param counterName - Name of the counter you want info on.
     * @return Returns a single Counter structure.
     * @throws AppException
     */
    @WebMethod
    @GET
    @Path("/{counter_name}")
    @RolesAllowed( {"Admin", "Authenticated"} )
    Counter findPerformanceCounterByName(@PathParam("counter_name") String counterName) throws AppException;
}
