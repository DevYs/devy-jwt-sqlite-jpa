package devy.jwt.sqlite.jpa.response;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * 공통 응답 클래스
 *
 * 유효성 검사 항목을 정의한다.
 *
 * 필드에러 : 요청 파라미터에서 발생하는 유효성 검사 항목의 에러
 * 전역에러 : 요청 파라미터에서 발생한 에러가 아닌 다른 이유에서 발생한 에러
 */
public class Response {

    /** 전역에러, 필드에러를 포함한 모든 유효성 검사 항목 */
    private List<ObjectError> allErrors;

    /** 필드 에러 발생 여부 */
    private boolean hasFieldErrors;

    /** 필드 에러 개수 */
    private int fieldErrorCount;

    /** 필드 에러 정보 목록 */
    private List<FieldError> fieldErrors;

    /** 필드 에러 정보 */
    private FieldError fieldError;

    /** 전역 에러의 발생 여부 */
    private boolean hasGlobalErrors;

    /** 전역에러 개수 */
    private int globalErrorCount;

    /** 전역에러 정보 목록 */
    private List<ObjectError> globalErrors;

    /** 전역 에러 정보 */
    private ObjectError globalError;

    /** 응답 데이터 */
    private Object data;

    /**
     * Default constructor
     */
    public Response() {
    }

    public Response(BindingResult bindingResult) {
        setBindingResult(bindingResult);
    }

    public Response(Object data, BindingResult bindingResult) {
        this.data = data;
        setBindingResult(bindingResult);
    }

    public Response(Object data) {
        this.data = data;
    }

    public void setBindingResult(BindingResult bindingResult) {
        this.allErrors = bindingResult.getAllErrors();
        this.hasGlobalErrors = bindingResult.hasGlobalErrors();
        this.globalErrorCount = bindingResult.getGlobalErrorCount();
        this.globalErrors = bindingResult.getGlobalErrors();
        this.globalError = bindingResult.getGlobalError();
        this.hasFieldErrors = bindingResult.hasFieldErrors();
        this.fieldErrorCount = bindingResult.getFieldErrorCount();
        this.fieldErrors = bindingResult.getFieldErrors();
        this.fieldError = bindingResult.getFieldError();
    }

    public List<ObjectError> getAllErrors() {
        return allErrors;
    }

    public void setAllErrors(List<ObjectError> allErrors) {
        this.allErrors = allErrors;
    }

    public boolean isHasGlobalErrors() {
        return hasGlobalErrors;
    }

    public void setHasGlobalErrors(boolean hasGlobalErrors) {
        this.hasGlobalErrors = hasGlobalErrors;
    }

    public int getGlobalErrorCount() {
        return globalErrorCount;
    }

    public void setGlobalErrorCount(int globalErrorCount) {
        this.globalErrorCount = globalErrorCount;
    }

    public List<ObjectError> getGlobalErrors() {
        return globalErrors;
    }

    public void setGlobalErrors(List<ObjectError> globalErrors) {
        this.globalErrors = globalErrors;
    }

    public ObjectError getGlobalError() {
        return globalError;
    }

    public void setGlobalError(ObjectError globalError) {
        this.globalError = globalError;
    }

    public boolean isHasFieldErrors() {
        return hasFieldErrors;
    }

    public void setHasFieldErrors(boolean hasFieldErrors) {
        this.hasFieldErrors = hasFieldErrors;
    }

    public int getFieldErrorCount() {
        return fieldErrorCount;
    }

    public void setFieldErrorCount(int fieldErrorCount) {
        this.fieldErrorCount = fieldErrorCount;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public FieldError getFieldError() {
        return fieldError;
    }

    public void setFieldError(FieldError fieldError) {
        this.fieldError = fieldError;
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
                "allErrors=" + allErrors +
                ", hasFieldErrors=" + hasFieldErrors +
                ", fieldErrorCount=" + fieldErrorCount +
                ", fieldErrors=" + fieldErrors +
                ", fieldError=" + fieldError +
                ", hasGlobalErrors=" + hasGlobalErrors +
                ", globalErrorCount=" + globalErrorCount +
                ", globalErrors=" + globalErrors +
                ", globalError=" + globalError +
                ", data=" + data +
                '}';
    }

}
