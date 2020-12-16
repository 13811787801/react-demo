/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.yxxs.service.clp;

import com.yxxs.service.YxxsActionDealLogService;

/**
 * @author Administrator
 */
public class YxxsActionDealLogServiceClp implements YxxsActionDealLogService {
	private static String _soapMode = "json";

	private static void initMethodKey() {
		System.out.println("YxxsActionDealLogService method key start to init");
	}

	public YxxsActionDealLogServiceClp() {
		synchronized (_methodKeyLoad) {
			if (!_methodKeyLoad) {
				initMethodKey();
				_methodKeyLoad = true;
			}
		}
	}

	private static Boolean _methodKeyLoad = false;
}