package com.github.sparkzxl.authorization.infrastructure.convert;

import com.github.sparkzxl.authorization.infrastructure.entity.OauthClientDetails;
import com.github.sparkzxl.authorization.interfaces.dto.client.OauthClientDetailsSaveDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * description: AuthClientDetails 对象Convert
 *
 * @author zhouxinlei
 * @date 2020-06-05 21:28:06
 */
@Mapper
public interface OauthClientDetailsConvert {

    OauthClientDetailsConvert INSTANCE = Mappers.getMapper(OauthClientDetailsConvert.class);

    /**
     * OauthClientDetailsSaveDTO转换OauthClientDetails
     *
     * @param oauthClientDetailsSaveDTO 应用客户端保存DTO
     * @return OauthClientDetails
     */
    OauthClientDetails convertBaseClientDetails(OauthClientDetailsSaveDTO oauthClientDetailsSaveDTO);
}
