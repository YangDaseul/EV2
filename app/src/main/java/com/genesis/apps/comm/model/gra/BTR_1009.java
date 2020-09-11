package com.genesis.apps.comm.model.gra;

import com.genesis.apps.comm.model.BaseData;
import com.genesis.apps.comm.model.vo.BtrVO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hjpark
 * @file GRA_BTR_1009
 * @Brief Genesis + 블루핸즈 변경/신청  요청
 */
public class BTR_1009 extends BaseData {

    /**
     * @brief BTR_1009 요청 항목
     * @see #vin 차대번호
     * @see #asnCd 정비망코드
     */
    @EqualsAndHashCode(callSuper = true)
    public @Data
    static
    class Request extends BaseRequest{

        @Expose
        @SerializedName("vin")
        private String vin;
        @Expose
        @SerializedName("asnCd")
        private String asnCd;

        public Request(String menuId,String vin, String asnCd){
            this.vin = vin;
            this.asnCd = asnCd;
            setData(APIInfo.GRA_BTR_1009.getIfCd(),menuId);
        }
    }

    /**
     * @brief BTR_1009 응답 항목
     */
    @EqualsAndHashCode(callSuper = true)
    public @Data
    class Response extends BaseResponse{
        private BtrVO btrVO;
    }
}
