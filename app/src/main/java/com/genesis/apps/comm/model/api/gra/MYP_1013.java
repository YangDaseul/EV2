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
 * @file GRA_MYP_1013
 * @Brief MyG + 블루멤버스 정보
 */
public class MYP_1013 extends BaseData {
    /**
     * @author hjpark
     * @brief MYP_1013 요청 항목
     */
    @EqualsAndHashCode(callSuper = true)
    public @Data
    static class Request extends BaseRequest {

        public Request(String menuId){
            setData(APIInfo.GRA_MYP_1013.getIfCd(), menuId);
        }
    }

    /**
     * @author hjpark
     * @brief MYP_1013 응답 항목
     * @see #cpnList 쿠폰 리스트
     */
    @EqualsAndHashCode(callSuper = true)
    public @Data
    class Response extends BaseResponse {
        @Expose
        @SerializedName("cpnList")
        private List<HSWCouponVO> cpnList;
    }
}
