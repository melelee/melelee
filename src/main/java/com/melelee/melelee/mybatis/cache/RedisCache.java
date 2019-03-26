package com.melelee.melelee.mybatis.cache;

import com.melelee.melelee.utils.ApplicationContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * mybatis集成reids开启二级缓存
 *
 * @author mengll
 * @create 2019-03-26 15:33
 **/
@Slf4j
public class RedisCache implements Cache {

	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private final String id;
	private RedisTemplate redisTemplate;

	private static final long EXPIRE_TIME_IN_MINUTES = 30; // redis过期时间

	public RedisCache(String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void putObject(Object key, Object value) {
		RedisTemplate redisTemplate = getRedisTemplate();
		ValueOperations opsForValue = redisTemplate.opsForValue();
		opsForValue.set(key, value, EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES);
		log.debug("Put query result to redis");
	}

	@Override
	public Object getObject(Object key) {
		RedisTemplate redisTemplate = getRedisTemplate();
		ValueOperations opsForValue = redisTemplate.opsForValue();
		log.debug("Get cached query result from redis");
		return opsForValue.get(key);
	}

	@Override
	public Object removeObject(Object key) {
		RedisTemplate redisTemplate = getRedisTemplate();
		redisTemplate.delete(key);
		log.debug("Remove cached query result from redis");
		return null;
	}

	@Override
	public void clear() {
		RedisTemplate redisTemplate = getRedisTemplate();
		redisTemplate.execute((RedisCallback) connection -> {
			connection.flushDb();
			return null;
		});
		log.debug("Clear all the cached query result from redis");
	}

	@Override
	public int getSize() {
		return 0;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

	private RedisTemplate getRedisTemplate() {
		if (redisTemplate == null) {
			redisTemplate = ApplicationContextHolder.getBean(RedisTemplate.class);
		}
		return redisTemplate;
	}
}
