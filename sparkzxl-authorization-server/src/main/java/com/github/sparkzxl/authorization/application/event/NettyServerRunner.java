package com.github.sparkzxl.authorization.application.event;

import com.github.sparkzxl.authorization.infrastructure.netty.NettyServer;
import com.github.sparkzxl.open.service.OauthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
@Slf4j
public class NettyServerRunner implements CommandLineRunner, Ordered {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private OauthService oauthService;

    @Override
    public void run(String... args) throws Exception {
        Environment env = applicationContext.getEnvironment();
        try {
            new NettyServer(12345, "/ws", oauthService).start();
            log.info("NettyServer 启动成功:{}:{}/{}",
                    InetAddress.getLocalHost().getHostAddress(),
                    env.getProperty("server.port"), "/ws");
        } catch (Exception e) {
            log.error("NettyServerError:{}", e.getMessage());
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
