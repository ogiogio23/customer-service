package com.ogiogio.customerservice.exceptions;

import com.ogiogio.customerservice.model.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ControllerAdvice(annotations = RestController.class)
@ResponseBody
public class ApiAdvice {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleValidationException(MethodArgumentNotValidException e) {
        BaseResponse response = new BaseResponse();

        response.setResponseCode("400");
        String message = "Bad request";
        BindingResult result = e.getBindingResult();
        List<FieldError> errorList = result.getFieldErrors();
        if (!errorList.isEmpty()) {
            message += (". " + errorList.get(0).getDefaultMessage());
        }

        response.setResponseMessage(message);
        return response;
    }
}
