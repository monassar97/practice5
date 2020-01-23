package com.training.payment.adapter.message;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

import com.training.payment.adapter.message.inbound.CardInputChannel;
import com.training.payment.adapter.message.outbound.CardOutputChannel;

@Configuration
@EnableBinding({ CardInputChannel.class, CardOutputChannel.class })
public class MessageConfiguration {

}
