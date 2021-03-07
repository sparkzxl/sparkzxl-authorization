package com.github.sparkzxl.authorization.infrastructure.convert;

import com.github.sparkzxl.authorization.infrastructure.entity.AuthApplication;
import com.github.sparkzxl.authorization.interfaces.dto.application.AuthApplicationSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.application.AuthApplicationUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * description: AuthClientDetails 对象Convert
 *
 * @author zhouxinlei
 * @date 2020-06-05 21:28:06
 */
@Mapper
public interface AuthApplicationConvert {

    AuthApplicationConvert INSTANCE = Mappers.getMapper(AuthApplicationConvert.class);

    /**
     * OauthClientDetailsSaveDTO转换OauthClientDetails
     *
     * @param oauthClientDetailsSaveDTO 应用客户端保存DTO
     * @return AuthApplication
     */
    AuthApplication convertAuthApplication(AuthApplicationSaveDTO oauthClientDetailsSaveDTO);

    /**
     * OauthClientDetailsUpdateDTO转换OauthClientDetails
     *
     * @param oauthClientDetailsUpdateDTO 应用客户端更新DTO
     * @return AuthApplication
     */
    AuthApplication convertAuthApplication(AuthApplicationUpdateDTO oauthClientDetailsUpdateDTO);
}
