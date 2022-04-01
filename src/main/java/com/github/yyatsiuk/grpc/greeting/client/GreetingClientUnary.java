package com.github.yyatsiuk.grpc.greeting.client;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClientUnary {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);
        GreetRequest greetRequest = GreetRequest.newBuilder()
                .setGreeting(Greeting.newBuilder()
                        .setFirstName("John")
                        .setLastName("Doe")
                        .build())
                .build();
        GreetResponse greetResponse = greetClient.greet(greetRequest);

        String result = greetResponse.getResult();
        System.out.println(result);

        GreetRequest greetRequest2 = GreetRequest.newBuilder()
                .setGreeting(Greeting.newBuilder()
                        .setFirstName("Jane")
                        .setLastName("Doe")
                        .build())
                .build();
        GreetResponse greetResponse2 = greetClient.greet(greetRequest2);

        String result2 = greetResponse2.getResult();
        System.out.println(result2);

        channel.shutdown();
    }

}
