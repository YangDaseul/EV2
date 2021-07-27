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
 * @file GRA_BMP_1001
 * @Brief 블루맴버스 포인트 결제 설정/변경
 */
public class BMP_1001 extends BaseData {

    /**
     * @brief BMP_1001 요청 항목
     * @see #bmpUseYn 블루멤버스포인트사용여부
     * Y / N ,  미설정인 경우 null
     * @see #bmpUseYn 블루멤버스포인트사용비율
     * 100, 80, 50, 0,  미설정인 경우 null
     * @see #bmpUseRatio 상태코드
     * "신규: NEW 변경:UPD,  해제: UUD (unused)
     * '- 블루멤버스포인트사용여부, 블루멤버스포인트사용비율이 값이 있고
     * 사용체크를 비활성화 할 경우에 설정해지로 한다"
     */
    @EqualsAndHashCode(callSuper = true)
    public @Data
    static
    class Request extends BaseRequest {
        @Expose
        @SerializedName("bmpUseYn")
        private String bmpUseYn;

        @Expose
        @SerializedName("bmpUseRatio")
        private String bmpUseRatio;

        @Expose
        @SerializedName("stusCd")
        private String stusCd;

        public Request(String menuId, String bmpUseYn, String bmpUseRatio, String stusCd) {
            setData(APIInfo.GRA_BTR_1010.getIfCd(),menuId);
            this.bmpUseYn = bmpUseYn;
            this.bmpUseRatio = bmpUseRatio;
            this.stusCd = stusCd;
        }
    }

    /**
     * @brief BMP_1001 응답항목
     */
    @EqualsAndHashCode(callSuper = true)
    public @Data
    class Response extends BaseResponse {

    }
}
