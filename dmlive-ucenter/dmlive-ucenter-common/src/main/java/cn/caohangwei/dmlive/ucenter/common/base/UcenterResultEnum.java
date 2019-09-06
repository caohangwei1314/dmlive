package cn.caohangwei.dmlive.ucenter.common.base;

/**
 * User returns result enum.
 * @author PinuoC
 */
public enum UcenterResultEnum {

    /**
     * failed
     */
    FAILED(0, "failed"),

    /**
     * success
     */
    SUCCESS(1, "success");

    private int code;

    private String msg;

    UcenterResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
