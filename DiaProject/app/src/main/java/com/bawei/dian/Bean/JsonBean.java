package com.bawei.dian.Bean;

import java.util.List;

/**
 * Time:2019/3/19
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class JsonBean {

    /**
     * result : [{"commodityId":135,"commodityName":"青春时尚 潮流男鞋 韩版舒适简约百搭板鞋男士休闲鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/nbx/1/1.jpg","price":149,"saleNum":0},{"commodityId":143,"commodityName":"素雅净色男士套脚帆布鞋懒人鞋平底休闲鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/nfbx/2/1.jpg","price":44,"saleNum":0},{"commodityId":151,"commodityName":"明星同款西装商务皮鞋男韩版潮真皮头层牛皮系带英伦正装鞋布洛克雕花男鞋男士皮鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/px/3/1.jpg","price":498,"saleNum":0},{"commodityId":159,"commodityName":"富贵鸟 头层牛皮系带百搭商务休闲鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/swxxx/4/1.jpg","price":249,"saleNum":0},{"commodityId":167,"commodityName":"鸿星尔克 新款气垫跑鞋复古轻便休闲鞋男款革面针织运动鞋 男鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/ydx/5/1.jpg","price":138,"saleNum":0},{"commodityId":140,"commodityName":"小白鞋男时尚新款运动休闲男鞋韩版潮流厚底原宿风板鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/nbx/6/1.jpg","price":398,"saleNum":0},{"commodityId":148,"commodityName":"Vernonsabin维斯国货粤潮原创撞色潮流时尚青年高帮男士休闲鞋男帆布鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/nfbx/7/1.jpg","price":389,"saleNum":0},{"commodityId":156,"commodityName":"黑色加绒牛皮商务休闲皮鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/swxxx/1/1.jpg","price":99,"saleNum":0},{"commodityId":164,"commodityName":"耐克Nike KAISHI RUN 奥利奥轻便透气 男款运动休闲鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/ydx/2/1.jpg","price":258,"saleNum":0},{"commodityId":137,"commodityName":"唐狮冬季男士冬休闲鞋高帮男鞋男士板鞋休闲男鞋子高帮男鞋百搭休闲板鞋男","masterPic":"http://172.17.8.100/images/small/commodity/nx/nbx/3/1.jpg","price":79,"saleNum":0}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 135
         * commodityName : 青春时尚 潮流男鞋 韩版舒适简约百搭板鞋男士休闲鞋
         * masterPic : http://172.17.8.100/images/small/commodity/nx/nbx/1/1.jpg
         * price : 149
         * saleNum : 0
         */

        private int commodityId;
        private String commodityName;
        private String masterPic;
        private int price;
        private int saleNum;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getMasterPic() {
            return masterPic;
        }

        public void setMasterPic(String masterPic) {
            this.masterPic = masterPic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }
    }
}
