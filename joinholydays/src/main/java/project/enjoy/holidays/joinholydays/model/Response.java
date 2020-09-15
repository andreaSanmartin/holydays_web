package project.enjoy.holidays.joinholydays.model;

public class Response {
    private String message;
    private Boolean transaccion;
    private Object payload;

    public Response() {
    }

    public String getMessage() {
        return message;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }


    public Boolean getTransaccion() {
        return transaccion;
    }


    public Response setTransaccion(Boolean transaccion) {
        this.transaccion = transaccion;
        return this;
    }

    public Object getPayload() {
        return payload;
    }

    public Response setPayload(Object payload) {
        this.payload = payload;
        return this;
    }

    public Response build() {
        return this;
    }

    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                ", transaccion=" + transaccion +
                '}';
    }
}
