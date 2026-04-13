package com.project.admin.exception.admin;

import com.project.admin.exception.base.BaseException;
import lombok.Getter;

@Getter
public class AdminException extends BaseException {


    public AdminException(AdminErrorCode errorCode) {
        super(errorCode);

    }
}
