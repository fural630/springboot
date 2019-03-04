package com.xhz.redis;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisUtilsTest {
	
	@Autowired
	private RedisService redisUtils;
	
	private boolean flag;

//	@Test
	public void testExpire() {
		boolean flag = redisUtils.expire("user-2", 60);
		assertEquals(true, flag);
	}

//	@Test
	public void testGetExpire() {
		String key = "testGetExpire";
//		flag = redisUtils.set(key, key);
//		redisUtils.expire(key, 60);
		long time = redisUtils.getExpire(key);
		System.out.println(time);
		assertEquals(true, time > 0);
	}
//
//	@Test
	public void testHasKey() {
		String key = "testGetExpire";
		flag = redisUtils.hasKey(key);
		assertEquals(true, flag);
	}

//	@Test
	public void testDel() {
		String key1 = "testDel1";
		String key2 = "testDel2";
//		redisUtils.set(key1, key1);
//		redisUtils.set(key2, key2);
		redisUtils.del(key1, key2);
	}

//	@Test
	public void testGet() {
		String key = "testGetExpire";
		String value = redisUtils.get(key);
		assertEquals(key, value);
	}

//	@Test
	public void testSetStringString() {
		String key = "testSetStringString";
		flag = redisUtils.set(key, key);
		assertEquals(true, flag);
	}

//	@Test
	public void testSetStringStringLong() {
		String key = "testSetStringStringLong0";
		flag = redisUtils.set(key, key, 0);
		assertEquals(true, flag);
	}

//	@Test
	public void testIncr() {
		fail("Not yet implemented");
	}

//	@Test
	public void testDecr() {
		fail("Not yet implemented");
	}

//	@Test
	public void testHget() {
		fail("Not yet implemented");
	}

//	@Test
	public void testHmget() {
		fail("Not yet implemented");
	}

//	@Test
	public void testHmsetStringMapOfStringObject() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("zhang", "1");
		map.put("lisi", "name");
		redisUtils.hmset("user", map);
	}

//	@Test
	public void testHmsetStringMapOfStringObjectLong() {
		fail("Not yet implemented");
	}

//	@Test
	public void testHsetStringStringObject() {
		redisUtils.hset("user2", "zhang1", "zhang");
	}

//	@Test
	public void testHsetStringStringObjectLong() {
		fail("Not yet implemented");
	}

//	@Test
	public void testHdel() {
		fail("Not yet implemented");
	}

//	@Test
	public void testHHasKey() {
		fail("Not yet implemented");
	}

//	@Test
	public void testHincr() {
		fail("Not yet implemented");
	}

//	@Test
	public void testHdecr() {
		fail("Not yet implemented");
	}

//	@Test
	public void testSGet() {
		fail("Not yet implemented");
	}

//	@Test
	public void testSHasKey() {
		fail("Not yet implemented");
	}

//	@Test
	public void testSSet() {
		fail("Not yet implemented");
	}

//	@Test
	public void testSSetAndTime() {
		fail("Not yet implemented");
	}

//	@Test
	public void testSGetSetSize() {
		fail("Not yet implemented");
	}

//	@Test
	public void testSetRemove() {
		fail("Not yet implemented");
	}

//	@Test
	public void testLGet() {
		fail("Not yet implemented");
	}

//	@Test
	public void testLGetListSize() {
		fail("Not yet implemented");
	}

//	@Test
	public void testLGetIndex() {
		fail("Not yet implemented");
	}

//	@Test
	public void testLSetStringString() {
		fail("Not yet implemented");
	}

//	@Test
	public void testLSetStringStringLong() {
		fail("Not yet implemented");
	}

//	@Test
	public void testLSetStringListOfString() {
		fail("Not yet implemented");
	}

//	@Test
	public void testLSetStringListOfStringLong() {
		fail("Not yet implemented");
	}

//	@Test
	public void testLUpdateIndex() {
		fail("Not yet implemented");
	}

//	@Test
	public void testLRemove() {
		fail("Not yet implemented");
	}

}
