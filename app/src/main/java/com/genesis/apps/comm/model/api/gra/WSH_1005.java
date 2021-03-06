package com.genesis.apps.comm.model.api.gra;

import com.genesis.apps.comm.model.BaseData;
import com.genesis.apps.comm.model.api.APIInfo;
import com.genesis.apps.comm.model.api.BaseRequest;
import com.genesis.apps.comm.model.api.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @file GRA_WSH_1005
 * @Brief service + 소낙스 직원에게 확인받기
 * @author hjpark
 */
public class WSH_1005 extends BaseData {
    /**
     * @brief WSH_1005의 요청 항목
     * @author hjpark
     * @see #rsvtSeqNo 예약일련번호      1~16자리 숫자만
     * @see #brnhCd 지점코드            직원이 입력한 지점코드
     */
    @EqualsAndHashCode(callSuper = true)
    public @Data
    static class Request extends BaseRequest {
        @Expose
        @SerializedName("rsvtSeqNo")
        private String rsvtSeqNo;
        @Expose
        @SerializedName("brnhCd")
        private String brnhCd;
        public Request(String menuId, String rsvtSeqNo, String brnhCd){
            this.rsvtSeqNo = rsvtSeqNo;
            this.brnhCd = brnhCd;
            setData(APIInfo.GRA_WSH_1005.getIfCd(), menuId);
        }
    }

    /**
     * @brief WSH_1005의 응답 항목
     * @author hjpark
     */
    @EqualsAndHashCode(callSuper = true)
    public @Data
    class Response extends BaseResponse {
    }
}
