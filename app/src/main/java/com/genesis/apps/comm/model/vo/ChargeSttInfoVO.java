package com.genesis.apps.comm.model.vo;

import com.genesis.apps.comm.model.BaseData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ki-man Kim
 * @brief service + S-트래픽 충전소 상세조회
 * @see #sid 충전소ID
 * @see #chgName 충전소명
 * @see #daddr 도로명주소
 * @see #daddrDtl 도로명주소상세
 * @see #lat 충전소위치-위도
 * @see #lot 충전소위치-경도
 * @see #useStartTime 이용가능시작시간
 * HH:MM
 * @see #useEndTime 이용가능종료시간
 * HH:MM
 * @see #bname 운영기관명칭
 * @see #bcall 운영기관연락처
 * @see #reservYn 예약가능여부
 * Y:가능 N:불가
 * @see #carPayUseYn 카페이사용가능여부
 * Y:가능 N:불가
 * @see #chgPrice 충전소가격
 * @see #superSpeedCnt 초고속충전기수
 * @see #highSpeedCnt 급속충전기수
 * @see #slowSpeedCnt 완속충전기수
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public @Data
class ChargeSttInfoVO extends BaseData {
    @Expose
    @SerializedName("sid")
    private String sid;
    @Expose
    @SerializedName("chgName")
    private String chgName;
    @Expose
    @SerializedName("daddr")
    private String daddr;
    @Expose
    @SerializedName("daddrDtl")
    private String daddrDtl;
    @Expose
    @SerializedName("lat")
    private String lat;
    @Expose
    @SerializedName("lot")
    private String lot;
    @Expose
    @SerializedName("useStartTime")
    private String useStartTime;
    @Expose
    @SerializedName("useEndTime")
    private String useEndTime;
    @Expose
    @SerializedName("bname")
    private String bname;
    @Expose
    @SerializedName("bcall")
    private String bcall;
    @Expose
    @SerializedName("reservYn")
    private String reservYn;
    @Expose
    @SerializedName("carPayUseYn")
    private String carPayUseYn;
    @Expose
    @SerializedName("chgPrice")
    private String chgPrice;
    @Expose
    @SerializedName("superSpeedCnt")
    private String superSpeedCnt;
    @Expose
    @SerializedName("highSpeedCnt")
    private String highSpeedCnt;
    @Expose
    @SerializedName("slowSpeedCnt")
    private String slowSpeedCnt;
} // end of class ChargeSttInfoVO
