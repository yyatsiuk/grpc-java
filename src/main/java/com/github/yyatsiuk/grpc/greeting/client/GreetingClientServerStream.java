package com.github.yyatsiuk.grpc.greeting.client;

import com.proto.greet.GreetManyTimeRequest;
import com.proto.greet.GreetManyTimeResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

public class GreetingClientServerStream {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);
        GreetManyTimeRequest greetRequest = GreetManyTimeRequest.newBuilder()
                .setGreeting(Greeting.newBuilder()
                        .setFirstName("John")
                        .setLastName("Doe")
                        .build())
                .build();
        Iterator<GreetManyTimeResponse> greetManyTimeResponseIterator = greetClient.greetManyTimes(greetRequest);
        greetManyTimeResponseIterator.forEachRemaining(greetResponse -> System.out.println(greetResponse.getResult()));

        channel.shutdown();
    }
}
