package cn.caohangwei.dmlive.ucenter.common.base;

import cn.caohangwei.dmlive.common.base.BaseResult;

public class UcenterResult extends BaseResult {

    public UcenterResult(UcenterResultEnum ucenterResultEnum, Object data) {
        super(ucenterResultEnum.getCode(), ucenterResultEnum.getMsg(), data);
    }

}
