package rocketseat.company.exception;

public class CourseException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private ErrorTypeEnum errorType;

    public CourseException(String msg, Throwable throwable, ErrorTypeEnum errorType) {
        super(msg, throwable);
        this.errorType = errorType;
    }

    public CourseException(String message) {
        super(message);
    }

    public ErrorTypeEnum getErrorType() {
        return errorType;
    }
}
