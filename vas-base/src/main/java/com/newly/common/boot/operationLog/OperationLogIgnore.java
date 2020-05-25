package com.newly.common.boot.operationLog;

import java.lang.annotation.*;

/**
 * Created by bingo on 2020/4/23 下午5:28
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLogIgnore {
}
