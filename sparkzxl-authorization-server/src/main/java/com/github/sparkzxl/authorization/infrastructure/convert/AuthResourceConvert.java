package com.github.sparkzxl.authorization.infrastructure.convert;

import com.github.sparkzxl.authorization.infrastructure.entity.AuthResource;
import com.github.sparkzxl.authorization.interfaces.dto.resource.AuthResourceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * description: AuthResource 对象Convert
 *
 * @author zhouxinlei
 * @date 2020-06-05 21:28:06
 */
@Mapper
public interface AuthResourceConvert {

    AuthResourceConvert INSTANCE = Mappers.getMapper(AuthResourceConvert.class);

    /**
     * AuthResource转化为 AuthResourceDTO
     *
     * @param authResource 资源
     * @return AuthResourceDTO
     */
    AuthResourceDTO convertAuthResourceDTO(AuthResource authResource);

}
