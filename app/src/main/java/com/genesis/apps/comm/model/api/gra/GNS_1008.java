package com.genesis.apps.comm.model.api.gra;

import com.genesis.apps.comm.model.BaseData;
import com.genesis.apps.comm.model.api.APIInfo;
import com.genesis.apps.comm.model.api.BaseRequest;
import com.genesis.apps.comm.model.api.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hjpark
 * @file GRA_GNS_1008
 * @Brief My차고 + 렌트/리스 계약서이미지 등록
 */
public class GNS_1008 extends BaseData {

    /**
     * @brief GNS_1008 요청 항목
     * @see #vin 차대번호
     * @see #imgFilNm 계약서 이미지 파일명
     *
     * imgFil 이미지파일 -> 멀티파트폼포맷으로 바이너리
     */
    @EqualsAndHashCode(callSuper = true)
    public @Data
    static
    class Request extends BaseRequest {

        @Expose
        @SerializedName("vin")
        private String vin;
        @Expose
        @SerializedName("imgFilNm")
        private String imgFilNm;
        @Expose
        @SerializedName("imgFilNm2")
        private String imgFilNm2;


        private File file;
        private String imageKeyName="imgFil";
        private File imgFil2;
        private String imageKeyName2="imgFil2";

        public Request(String menuId, String vin, String imgFilNm, File file, String imgFilNm2, File imgFil2){
            this.vin = vin;
            this.imgFilNm = imgFilNm;
            this.file = file;
            this.imgFilNm2 = imgFilNm2;
            this.imgFil2 = imgFil2;
            setData(APIInfo.GRA_GNS_1008.getIfCd(), menuId);
        }
    }

    /**
     * @brief GNS_1008 응답 항목
     */
    @EqualsAndHashCode(callSuper = true)
    public @Data
    class Response extends BaseResponse {
    }
}
