package com.genesis.apps.comm.model.vo;

import com.genesis.apps.comm.model.BaseData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @brief 모빌리티케어 쿠폰 정보
 * @author hjpark
 * @see #cpnNo 쿠폰번호
 * 정비 쿠폰 번호, 예: 733268476275
 * @see #cpnNm 쿠폰명
 * 정비 쿠폰 명
 * @see #avlSttDtm 시작일자
 * 사용가능 시작일자, 예: 20210514
 * @see #avlEndDtm 종료일자
 * 사용가능 종료일자, 예: 20210615
 * @see #cpnStus 쿠폰상태
 * 사용상태 : A:사용가능, F:사용완료, E:기간만료
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public @Data
class HSWCouponVO extends BaseData {
    @Expose
    @SerializedName("cpnNo")
    private String cpnNo;
    @Expose
    @SerializedName("cpnNm")
    private String cpnNm;
    @Expose
    @SerializedName("avlSttDtm")
    private String avlSttDtm;
    @Expose
    @SerializedName("avlEndDtm")
    private String avlEndDtm;
    @Expose
    @SerializedName("cpnStus")
    private String cpnStus;
}
