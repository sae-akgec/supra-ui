package in.saeakgec.supra.websocket;

import in.saeakgec.supra.model.GreenFlag;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;


public class MyStompSessionHandler extends StompSessionHandlerAdapter {


    public MyStompSessionHandler() {

    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        session.subscribe("/server/flags", this);
//        session.send("/client/flags", "Red Flag");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        exception.printStackTrace();
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return GreenFlag.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
//        log.info("Received: {}stompEndpointRegistry.addEndpoint("/BO/socket").setAllowedOrigins("*").withSockJS();", ((Greeting) payload).getContent());
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        exception.printStackTrace();
        super.handleTransportError(session, exception);
    }
}
