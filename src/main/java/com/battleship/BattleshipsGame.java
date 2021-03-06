package com.battleship;

import com.sun.jdi.Bootstrap;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.jline.PromptProvider;

@SpringBootApplication
public class BattleshipsGame extends Bootstrap {

    public static void main(String[] args){
        SpringApplication.run(BattleshipsGame.class, args);
    }

    @Bean
    public PromptProvider promptProvider() {
        return () -> new AttributedString("\n/:>\t", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }

}
