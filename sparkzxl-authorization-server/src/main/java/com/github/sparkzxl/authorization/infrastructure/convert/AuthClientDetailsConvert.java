package com.github.sparkzxl.authorization.infrastructure.convert;

import com.github.sparkzxl.authorization.infrastructure.entity.AuthClientDetails;
import com.github.sparkzxl.oauth.entity.BaseClientDetails;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * description: AuthClientDetails 对象Convert
 *
 * @author zhouxinlei
 * @date 2020-06-05 21:28:06
 */
@Mapper
public interface AuthClientDetailsConvert {

    AuthClientDetailsConvert INSTANCE = Mappers.getMapper(AuthClientDetailsConvert.class);

    /**
     * AuthClientDetails转换BaseClientDetails
     *
     * @param authClientDetails 应用客户端
     * @return BaseClientDetails
     */
    BaseClientDetails convertBaseClientDetails(AuthClientDetails authClientDetails);
}
