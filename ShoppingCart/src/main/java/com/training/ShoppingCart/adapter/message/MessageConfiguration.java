package com.training.ShoppingCart.adapter.message;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

import com.training.ShoppingCart.adapter.message.inbound.CartInputChannel;
import com.training.ShoppingCart.adapter.message.outbound.CartoutputChannel;

@Configuration
@EnableBinding({ CartInputChannel.class, CartoutputChannel.class })
public class MessageConfiguration {

}
