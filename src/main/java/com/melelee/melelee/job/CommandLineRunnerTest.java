package com.melelee.melelee.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

/**
 * Job测试
 *
 * @author mengll
 * @create 2019-06-06 11:32
 **/
@Slf4j
public class CommandLineRunnerTest implements CommandLineRunner{
    @Override
    public void run(String... strings) throws Exception {
        log.info("CommandLineRunnerTest start");
    }
}
