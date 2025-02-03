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
    public void addTest1() {
        AddService service = new AddService();
        Result result = service.add(5, 5);
        assertEquals(result.getResult(), 10);
    }

    @Test
    public void addTest2(){
        AddService service = new AddService();
        Result res = service.add(10,15);
        assertEquals(res.getResult(), 25);
    }

    @Test
    public void addTest3(){
        AddService service = new AddService();
        Result res = service.add(10,10);
        assertEquals(res.getResult(), 20);
    }
}
