package com.alexbezsh.effectivejava.cache;

import com.alexbezsh.effectivejava.cache.common.Cache;
import com.alexbezsh.effectivejava.cache.common.CachedObject;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import static com.alexbezsh.effectivejava.cache.common.CacheUtils.DEFAULT_CACHE_SIZE;
import static com.alexbezsh.effectivejava.cache.common.CacheUtils.DEFAULT_RETENTION_PERIOD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
public abstract class AbstractCacheTest {

    protected Cache<String, CachedObject> testedInstance;

    @Test
    void getShouldReturnValue() {
        String key = "key";
        CachedObject value = new CachedObject("value");

        testedInstance.put(key, value);

        assertEquals(value, testedInstance.get(key));
    }

    @Test
    void getShouldReturnNullIfValueExpired() throws Exception {
        String key = "key";

        testedInstance.put(key, new CachedObject("value"));

        Thread.sleep(DEFAULT_RETENTION_PERIOD.toMillis());

        assertNull(testedInstance.get(key));
    }

    @Test
    void putShouldEvictValueIfMaxSizeReached() {
        for (int i = 0; i <= DEFAULT_CACHE_SIZE; i++) {
            String key = String.valueOf(i);
            testedInstance.put(key, new CachedObject(key));
        }
        assertTrue(testedInstance.getEvictionsCount() > 0);
    }

    @Test
    void getAverageInsertionTimeTest() {
        testedInstance.put("key", new CachedObject("value"));

        assertTrue(testedInstance.getAverageInsertionTime() >= 0);
    }

}
