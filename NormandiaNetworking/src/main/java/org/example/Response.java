package org.example;

import java.io.Serializable;

public class Response implements Serializable {
    private ResponseType type;
    private Object data;

    private Response(){}

    public static class Builder{
        private Response response;

        public Builder(){
            response = new Response();
        }

        public Builder type(ResponseType type){
            response.type = type;
            return this;
        }

        public Builder data(Object data){
            response.data = data;
            return this;
        }

        public Response build(){
            return response;
        }
    }

    public ResponseType getType() {
        return type;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "type=" + type +
                ", data=" + data +
                '}';
    }
}