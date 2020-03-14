package com.wwy.springcloud.dao;

import com.wwy.springcloud.entities.Payment;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PaymentDao {

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into payment(serial) values(#{serial})")
    public int create(Payment payment);

    @Select("select * from payment where id=#{id}")
    public Payment getPaymentById(@Param("id") Long id);
}
