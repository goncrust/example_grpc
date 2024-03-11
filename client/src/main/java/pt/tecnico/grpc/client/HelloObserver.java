package pt.tecnico.grpc.client;

import io.grpc.stub.StreamObserver;
import pt.tecnico.grpc.HelloWorld;

public class HelloObserver implements StreamObserver<HelloWorld.HelloResponse> {
    ResponseCollector collector;

    public HelloObserver(ResponseCollector collector) {
        this.collector = collector;
    }

    @Override
    public void onNext(HelloWorld.HelloResponse r) {
        collector.addResponse(r.getGreeting());
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Received error: " + throwable);
    }

    @Override
    public void onCompleted() {
        System.out.println("Request completed");
    }
}
