import com.devanshu.cache.Cache;
import com.devanshu.cache.factories.CacheFactorty;
import org.junit.Before;
//import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CacheTest {
    Cache<Integer, Integer> cache;

    @BeforeEach
    public void setup()
    {
        cache = new CacheFactorty<Integer, Integer>().defaultCache(3);
    }

    @Test
    public void checkgetandaddoperations()
    {
        cache.put(1,101);
        cache.put(2,102);
        assertEquals(101, cache.get(1));
        cache.put(3, 103);
        assertEquals(103,cache.get(3));

        cache.put(4,104);
        cache.get(2);
    }
}
