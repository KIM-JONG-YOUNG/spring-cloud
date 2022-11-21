package edu.jong.spring.redis.service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

	private final RedisTemplate<String, String> redisTemplate;
	private final ObjectMapper objectMapper = new ObjectMapper()
			.registerModule(new JavaTimeModule());
	
	@Override
	public void registMapperModule(SimpleModule module) {
		objectMapper.registerModule(module);
	}

	@Override
	public void caching(String key, String value, long expireSeconds) {
		redisTemplate.opsForValue().set(key, value, expireSeconds, TimeUnit.SECONDS);
	}

	@Override
	public void caching(String key, Object value, long expireSeconds) {
		
		try {
			String json = objectMapper.writeValueAsString(value);
			redisTemplate.opsForValue().set(key, json, expireSeconds, TimeUnit.SECONDS);
		} catch (Exception e) {
			log.warn("캐싱에 실패했습니다.");
		}
	}

	@Override
	public Optional<String> get(String key) {
		return Optional.ofNullable(redisTemplate.opsForValue().get(key));
	}

	@Override
	public <T> Optional<T> get(String key, TypeReference<T> type) {

		try {
			String json = redisTemplate.opsForValue().get(key);
			T value = objectMapper.readValue(json, type);
			return Optional.ofNullable(value);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	@Override
	public boolean has(String key) {
		return redisTemplate.hasKey(key);
	}

	@Override
	public boolean remove(String key) {
		return redisTemplate.delete(key);
	}

}
