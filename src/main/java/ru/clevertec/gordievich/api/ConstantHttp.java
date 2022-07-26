package ru.clevertec.gordievich.api;

public final class ConstantHttp {

    private ConstantHttp() {
        throw new UnsupportedOperationException();
    }

    public static class HttpMethod {

        private HttpMethod() {
            throw new UnsupportedOperationException();
        }

        public static final String GET = "GET";
    }

    public static class HttpResponseStatus {

        private HttpResponseStatus() {
            throw new UnsupportedOperationException();
        }

        public static final int STATUS_OK = 200;
        public static final int STATUS_NOT_FOUND = 404;

    }

    public static class UrlPath {

        private UrlPath() {
            throw new UnsupportedOperationException();
        }

        public static final String CHECK_PATH = "/check";
    }

}
