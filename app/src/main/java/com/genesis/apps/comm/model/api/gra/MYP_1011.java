package com.genesis.apps.comm.model.api.gra;

import com.genesis.apps.comm.model.BaseData;
import com.genesis.apps.comm.model.api.APIInfo;
import com.genesis.apps.comm.model.api.BaseRequest;
import com.genesis.apps.comm.model.api.BaseResponse;
import com.genesis.apps.comm.model.vo.HSWCouponVO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hjpark
 * @file GRA_MYP_1011
 * @Brief MyG+ 정비 쿠폰 목록 (신규 쿠폰 여부)
 */
public class MYP_1011 extends BaseData {
    /**
     * @author hjpark
     * @brief MYP_1011 요청 항목
     */
    @EqualsAndHashCode(callSuper = true)
    public @Data
    static class Request extends BaseRequest {

        public Request(String menuId){
            setData(APIInfo.GRA_MYP_1011.getIfCd(), menuId);
        }
    }

    /**
     * @author hjpark
     * @brief MYP_1011 응답 항목
     * @see #newCpnYn 쿠폰 신규 여부
     * (다운로드 가능 쿠폰 여부, Y:신규, N:없음)
     * @see #cpnList 쿠폰 리스트
     */
    @EqualsAndHashCode(callSuper = true)
    public @Data
    class Response extends BaseResponse {
        @Expose
        @SerializedName("newCpnYn")
        private String newCpnYn;
        @Expose
        @SerializedName("cpnList")
        private List<HSWCouponVO> cpnList;
    }
}
