package br.com.ecopoints.userservice.api;

import br.com.ecopoints.userservice.exceptions.ErrorTypeEnum;
import br.com.ecopoints.userservice.exceptions.FlowException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class GlobalControllerAdviceTest {

    @InjectMocks
    private GlobalControllerAdvice globalControllerAdvice;

    @Test
    void handleFlowException() {
        var flowException = new FlowException(
                HttpStatus.NOT_FOUND,
                ErrorTypeEnum.INVALID_REQUEST,
                "An internal invalid request error message sample."
        );
        var response = globalControllerAdvice.handleFlowException(flowException);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
    }
}
