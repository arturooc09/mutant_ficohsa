package co.com.adn.adapter.error.builder;

import co.com.adn.adapter.error.ExceptionBuilder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BdExceptionBuilder extends ExceptionBuilder {

    public BdExceptionBuilder(String code, String message) {
        super(code, message);
    }

 }
