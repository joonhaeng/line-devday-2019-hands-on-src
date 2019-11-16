package com.linecorp.devday.handson.demo.service;

import com.linecorp.devday.handson.demo.proto.DemoGrpc;
import com.linecorp.devday.handson.demo.proto.DemoGrpcProto;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class DemoGrpcService extends DemoGrpc.DemoImplBase {

    private final DemoService demoService;

    @Override
    public void hello(
            DemoGrpcProto.DemoGrpcRequest request,
            StreamObserver<DemoGrpcProto.DemoGrpcResponse> responseObserver) {

        String message = request.getMessage();
        log.debug("MESSAGE FROM GRPC REQUEST : {}", message);

        demoService.getHello()
                .map(s -> DemoGrpcProto.DemoGrpcResponse.newBuilder().setMessage(s).build())
                .subscribe(
                        demoGrpcResponse    -> responseObserver.onNext(demoGrpcResponse),
                        cause               -> responseObserver.onError(cause),
                        ()                  -> responseObserver.onCompleted()
                );

    }

}
