package com.genesis.apps.comm.model.gra;

import com.genesis.apps.comm.model.BaseData;
import com.genesis.apps.comm.model.vo.RepairVO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hjpark
 * @file GRA_REQ_1002
 * @Brief service + 서비스메인 - 네트워크 찾기
 */
public class REQ_1002 extends BaseData {
    /**
     * @brief REQ_1002 요청 항목
     * @see #vin 차대번호
     * @see #mdlCd 모델코드
     * @see #nx 단말 경도 좌표
     * @see #ny 단말 위도 좌표
     * @see #addr 주소(시)
     * 서울특별시
     * @see #addrDtl 주소(구)
     * 금천구
     * @see #fillerCd 필터코드
     * A: 제네시스전담, B: 일반블루핸즈, C:종합, D:전문
     * @see #rparTypCd 정비내용코드
     * 부품계통코드 --> 코드값 문의 필요
     * null 값이 아니면 부품계통 정비가 가능한 정비소 찾기
     */
    @EqualsAndHashCode(callSuper = true)
    public @Data
    static
    class Request extends BaseRequest{
        @Expose
        @SerializedName("vin")
        private String vin;
        @Expose
        @SerializedName("mdlCd")
        private String mdlCd;
        @Expose
        @SerializedName("nx")
        private String nx;
        @Expose
        @SerializedName("ny")
        private String ny;
        @Expose
        @SerializedName("addr")
        private String addr;
        @Expose
        @SerializedName("addrDtl")
        private String addrDtl;
        @Expose
        @SerializedName("fillerCd")
        private String fillerCd;
        @Expose
        @SerializedName("rparTypCd")
        private String rparTypCd;

        public Request(String menuId, String vin, String mdlCd, String nx, String ny, String addr, String fillerCd, String rparTypCd){
            this.vin = vin;

            this.mdlCd = mdlCd;
            this.nx = nx;
            this.ny = ny;
            this.addr = addr;
            this.fillerCd = fillerCd;
            this.rparTypCd = rparTypCd;
            setData(APIInfo.GRA_REQ_1002.getIfCd(), menuId);
        }
    }

    /**
     * @brief REQ_1002 응답 항목
     * @see #asnList 정비소 리스트
     */
    @EqualsAndHashCode(callSuper = true)
    public @Data
    class Response extends BaseResponse{
        @Expose
        @SerializedName("asnList")
        private List<RepairVO> asnList;
    }
}
