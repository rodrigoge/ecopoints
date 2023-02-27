package br.com.ecopoints.userservice.exceptions;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ExceptionTest {

    @Test
    void shouldGetErrorException() {
        var errorException = new ErrorException();
        errorException.setStatus(500);
        errorException.setError(ErrorTypeEnum.INVALID_REQUEST);
        errorException.setDate("2023-01-01T00:00:00Z");
        errorException.setMessage("Error invalid request.");
        Assertions.assertThat(errorException).isNotNull();
        Assertions.assertThat(errorException.getStatus()).isEqualTo(500);
        Assertions.assertThat(errorException.getError()).isEqualTo(ErrorTypeEnum.INVALID_REQUEST);
        Assertions.assertThat(errorException.getDate()).isEqualTo("2023-01-01T00:00:00Z");
        Assertions.assertThat(errorException.getMessage()).isEqualTo("Error invalid request.");
    }
}
