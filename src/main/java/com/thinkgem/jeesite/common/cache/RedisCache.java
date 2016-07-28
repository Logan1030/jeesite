package com.thinkgem.jeesite.common.cache;


import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.common.utils.JedisUtils;

/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title RedisCache.java
 * @package com.cxdai.common.cache
 * @author zhaowei
 * @version 0.1 2015年11月19日
 */
@Component("redisCache")
public class RedisCache extends KeyValueCache {

	 
	public RedisCache() {
	}

	public RedisCache(IKeyValueCache delegate) {
		super(delegate);
	}

	@Override
	public Long selfRemove(String key) {
		if (key != null && key.length() > 0) {
			return JedisUtils.del(key);
		}
		return 0L;
	}

	@Override
	protected void selfPut(String key, String value) {
		if (key != null && key.length() > 0) {
			JedisUtils.set(key, value,0);
		}
	}

	@Override
	protected void selfPut(String key, String value, long expiredTime) {
		if (key != null && key.length() > 0) {
			JedisUtils.set(key, value,new Long(expiredTime).intValue() / 1000);
		}
	}

	@Override
	protected boolean isSelfExists(String key) {
		return JedisUtils.exists(key);
	}

	@Override
	protected String selfGet(String key) {
		return JedisUtils.get(key);	
	}

}
