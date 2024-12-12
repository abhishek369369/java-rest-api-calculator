package com.coralogix.calculator.services;

import com.coralogix.calculator.model.Result;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddServiceTest {

    @Test
    public void addIntegers() {
        // given
        AddService service = new AddService();

        // when
        Result result = service.add(2, 3);

        // then
        assertEquals(result.getResult(), 5);
    }

    @Test
    public void addTest() {
        AddService service = new AddService();
        Result result = service.add(5, 5);
        assertEquals(result.getResult(), 10);
    }
}
