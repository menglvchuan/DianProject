package com.bawei.dian.Bean;

/**
 * @Auther: 12547
 * @Date: 2019/3/23 15:04:05
 * @Description:
 */
public class AddShoppBean {


    /**
     * message : 同步成功
     * status : 0000
     */

    private String message;
    private String status;

    @Override
    public String toString() {
        return "AddShoppBean{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AddShoppBean(String message, String status) {
        this.message = message;
        this.status = status;
    }
}
