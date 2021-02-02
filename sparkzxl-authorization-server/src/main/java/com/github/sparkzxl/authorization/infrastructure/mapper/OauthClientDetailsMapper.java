package com.github.sparkzxl.authorization.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.sparkzxl.authorization.infrastructure.entity.OauthClientDetails;
import com.github.sparkzxl.database.base.mapper.SuperMapper;
import org.springframework.stereotype.Repository;

/**
 * description: 应用客户端 Mapper 接口
 *
 * @author: zhouxinlei
 * @date: 2021-02-02 11:34:50
 */
@Repository
public interface OauthClientDetailsMapper extends SuperMapper<OauthClientDetails> {

}
