package com.genesis.apps.comm.model.vo;

import com.genesis.apps.comm.model.BaseData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @brief 블루 맴버스 포인트 정보
 * @see #stMbrYn (저장된)에스트래픽회원여부
 * Y:가입회원 N:미회원
 * @see #bmMbrYn 블루멤버스회원여부
 * Y:가입회원 N:미회원
 * @see #useBmp 사용가능한블루포인트
 * @see #bmpUseYn 블루멤버스포인트사용여부
 * Y / N ,  미설정인 경우 null
 * @see #bmpUseRatio 블루멤버스포인트사용비율
 * 100, 80, 50, 0,  미설정인 경우 null
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public @Data
class BlueMembersPointInfoVO extends BaseData {
    @Expose
    @SerializedName("stMbrYn")
    private String stMbrYn;
    @Expose
    @SerializedName("bmMbrYn")
    private String bmMbrYn;
    @Expose
    @SerializedName("useBmp")
    private String useBmp;
    @Expose
    @SerializedName("bmpUseYn")
    private String bmpUseYn;
    @Expose
    @SerializedName("bmpUseRatio")
    private String bmpUseRatio;
}
