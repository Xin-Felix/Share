package work.huangxin.share.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis的配置
 *
 * @author atrun
 */

@Configuration
public class RedisConfig {

    public class RedisListenerConfig {
        @Bean
        RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
            RedisMessageListenerContainer container = new RedisMessageListenerContainer();
            container.setConnectionFactory(connectionFactory);
            return container;
        }


        /**
         * redisTemplate 序列化使用的jdkSerializeable, 存储二进制字节码, 所以自定义序列化类
         *
         * @param redisConnectionFactory
         * @return
         */
        @Bean
        public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
            RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
            redisTemplate.setConnectionFactory(redisConnectionFactory);

            // 使用Jackson2JsonRedisSerialize 替换默认序列化
            Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

            jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

            // 设置value的序列化规则和 key的序列化规则
            redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.afterPropertiesSet();
            return redisTemplate;
        }
    }


}