package com.nk.aoptodetermineruntime;

import static com.nk.aoptodetermineruntime.model.Product.*;
import com.nk.aoptodetermineruntime.model.Widget;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AopToDetermineRunTimeApplicationTests {
    //Product product= Product.builder().id(1001).productName("IPhone").amount(200).brand("Apple").build();

    // Widget testWidget = Widget.builder().name("foo").id(1).build();

    @Test
    public void contextLoads() {
    }

}
