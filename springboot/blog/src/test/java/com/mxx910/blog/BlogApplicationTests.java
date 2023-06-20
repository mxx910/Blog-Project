package com.mxx910.blog;

import com.mxx910.blog.model.DTO.LoginDTO;
import com.mxx910.blog.model.DTO.MailDTO;
import com.mxx910.blog.model.DTO.RegisterDTO;
import com.mxx910.blog.service.AlbumService;
import com.mxx910.blog.service.EmailService;
import com.mxx910.blog.service.LoginService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
class BlogApplicationTests {
	@Autowired
	private AlbumService albumService;
	@Test
	void testAlbumService(){
		System.out.println(albumService.listAlbumVO());
	}


	@Test
	void contextLoads() {
	}

}
