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

import com.fasterxml.jackson.databind.module.SimpleModule;

import com.yxxs.common.service.HttpServiceUtil;
import com.yxxs.common.service.HttpServiceUtil.ClassGener;

import java.util.HashMap;

/**
 * @author Administrator
 */
public class YxxsActionServPortletServiceClpClassGener {
	public static class T_com_yxxs_model_YxxsAction extends ClassGener<com.yxxs.model.YxxsAction> {
		@Override
		public SimpleModule addMapping(SimpleModule module) {
			return addModelMapping(super.addMapping(module));
		}
	}

	public static class T_java_util_List_L_com_yxxs_model_YxxsAction_R_
		extends ClassGener<java.util.List<com.yxxs.model.YxxsAction>> {
		@Override
		public SimpleModule addMapping(SimpleModule module) {
			return addModelMapping(super.addMapping(module));
		}
	}

	public static class T_com_yxxs_model_YxxsActionDealLog extends ClassGener<com.yxxs.model.YxxsActionDealLog> {
		@Override
		public SimpleModule addMapping(SimpleModule module) {
			return addModelMapping(super.addMapping(module));
		}
	}

	public static class T_java_util_List_L_com_yxxs_model_YxxsActionDealLog_R_
		extends ClassGener<java.util.List<com.yxxs.model.YxxsActionDealLog>> {
		@Override
		public SimpleModule addMapping(SimpleModule module) {
			return addModelMapping(super.addMapping(module));
		}
	}

	public static class T_java_util_List_L_com_yxxs_model_YxxsActionUser_R_
		extends ClassGener<java.util.List<com.yxxs.model.YxxsActionUser>> {
		@Override
		public SimpleModule addMapping(SimpleModule module) {
			return addModelMapping(super.addMapping(module));
		}
	}

	public static class T_java_lang_Object_LL__RR_ extends ClassGener<java.lang.Object[]> {
		@Override
		public SimpleModule addMapping(SimpleModule module) {
			return addModelMapping(super.addMapping(module));
		}
	}

	public static class T_com_yxxs_model_YxxsActionUser extends ClassGener<com.yxxs.model.YxxsActionUser> {
		@Override
		public SimpleModule addMapping(SimpleModule module) {
			return addModelMapping(super.addMapping(module));
		}
	}

	public static class T_com_yxxs_model_YxxsWechatWall extends ClassGener<com.yxxs.model.YxxsWechatWall> {
		@Override
		public SimpleModule addMapping(SimpleModule module) {
			return addModelMapping(super.addMapping(module));
		}
	}

	public static class T_java_util_List_L_com_yxxs_model_YxxsWechatWall_R_
		extends ClassGener<java.util.List<com.yxxs.model.YxxsWechatWall>> {
		@Override
		public SimpleModule addMapping(SimpleModule module) {
			return addModelMapping(super.addMapping(module));
		}
	}

	static {
		HttpServiceUtil.addJsonModelMapping(getModelMapping());
	}

	public static HashMap<Class, Class> getModelMapping() {
		HashMap<Class, Class> mapping = new HashMap<Class, Class>();
		Class YxxsAction_clz = com.yxxs.model.YxxsActionClp.class;
		mapping.put(com.yxxs.model.YxxsAction.class, YxxsAction_clz);

		Class YxxsActionDealLog_clz = com.yxxs.model.YxxsActionDealLogClp.class;
		mapping.put(com.yxxs.model.YxxsActionDealLog.class,
			YxxsActionDealLog_clz);

		Class YxxsActionUser_clz = com.yxxs.model.YxxsActionUserClp.class;
		mapping.put(com.yxxs.model.YxxsActionUser.class, YxxsActionUser_clz);

		Class YxxsWechatWall_clz = com.yxxs.model.YxxsWechatWallClp.class;
		mapping.put(com.yxxs.model.YxxsWechatWall.class, YxxsWechatWall_clz);

		return mapping;
	}

	public static SimpleModule addModelMapping(SimpleModule module) {
		HashMap<Class, Class> mapping = getModelMapping();

		HttpServiceUtil.addJsonModelMapping(mapping);

		for (Class c : mapping.keySet()) {
			module.addAbstractTypeMapping(c, mapping.get(c));
		}

		return module;
	}
}