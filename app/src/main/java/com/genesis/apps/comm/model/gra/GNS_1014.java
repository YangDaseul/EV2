package com.genesis.apps.comm.model.gra;

import com.genesis.apps.comm.model.BaseData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hjpark
 * @file GRA_GNS_1014
 * @Brief My차고 + 렌트/리스 신청취소
 */
public class GNS_1014 extends BaseData {

    /**
     * @brief GNS_1014 요청 항목
     * @see #vin 차대번호
     * @see #seqNo 일련번호
     */
    @EqualsAndHashCode(callSuper = true)
    public @Data
    static
    class Request extends BaseRequest{

        @Expose
        @SerializedName("vin")
        private String vin;
        @Expose
        @SerializedName("seqNo")
        private String seqNo;

        public Request(String menuId, String vin, String seqNo){
            this.vin = vin;
            this.seqNo = seqNo;
            setData(APIInfo.GRA_GNS_1014.getIfCd(),menuId);
        }
    }

    /**
     * @brief GNS_1014 응답 항목
     *
     */
    @EqualsAndHashCode(callSuper = true)
    public @Data
    class Response extends BaseResponse{
    }
}
