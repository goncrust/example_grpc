package pt.tecnico.grpc.client;

import java.util.ArrayList;
import java.util.List;

public class ResponseCollector {
    private final List<String> responses;

    public ResponseCollector() {
          responses = new ArrayList<>();
    }

    public synchronized void addResponse(String response) {
        responses.add(response);
        notifyAll();
    }

    public synchronized List<String> getResponses() {
        return new ArrayList<>(responses);
    }

    public synchronized void waitAllResponses(int n) {
        while (responses.size() < n) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
