package com.ayit.demo.springboot;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ayit.demo.springboot.ApplicationConfiguration;

@Component
public class StaticServer extends AbstractVerticle {

    @Autowired
    ApplicationConfiguration configuration;

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);

        router.route("/dev").handler(new Handler<RoutingContext>() {
            @Override
            public void handle(RoutingContext routingContext) {
                routingContext.response().end("dev");
            }
        });


        // Serve the static pages
        router.route().handler(StaticHandler.create());

        vertx.createHttpServer().requestHandler(router::accept).listen(configuration.httpPort());
    }
}