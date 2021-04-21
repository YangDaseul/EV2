package com.genesis.apps.comm.model.api.gra;

import com.genesis.apps.comm.model.BaseData;
import com.genesis.apps.comm.model.api.APIInfo;
import com.genesis.apps.comm.model.api.BaseRequest;
import com.genesis.apps.comm.model.api.BaseResponse;
import com.genesis.apps.comm.model.vo.carlife.CarVO;
import com.genesis.apps.comm.model.vo.carlife.LotVO;
import com.genesis.apps.comm.model.vo.carlife.MembershipVO;
import com.genesis.apps.comm.model.vo.carlife.OrderVO;
import com.genesis.apps.comm.model.vo.carlife.PaymtCardVO;
import com.genesis.apps.comm.model.vo.carlife.ReqInfoVO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

public class CHB_1010 extends BaseData {
    /**
     * @brief CHB_1010 요청 항목
     * @see #txid   트랜잭션ID
     * @see #hpNo   휴대폰번호
     * @see #carInfo    차량정보
     * @see #requestInfo    기본 신청정보
     * @see #serviceLocationList    서비스 위치 정보 리스트
     * @see #orderInfo  주문정보
     * @see #membershipList 멤버십 적용 리스트(선택)
     * @see #paymentInfo    결제수단정보(선택)
     */
    @EqualsAndHashCode(callSuper = true)
    public @Data
    static
    class Request extends BaseRequest {
        @Expose
        @SerializedName("txid")
        private String txid;
        @Expose
        @SerializedName("hpNo")
        private String hpNo;
        @Expose
        @SerializedName("carInfo")
        private CarVO carInfo;
        @Expose
        @SerializedName("requestInfo")
        private ReqInfoVO requestInfo;
        @Expose
        @SerializedName("serviceLocationList")
        private List<LotVO> serviceLocationList;
        @Expose
        @SerializedName("orderInfo")
        private OrderVO orderInfo;
        @Expose
        @SerializedName("membershipList")
        private List<MembershipVO> membershipList;
        @Expose
        @SerializedName("paymentInfo")
        private PaymtCardVO paymentInfo;

        public Request(String menuId, String txid, String hpNo, CarVO carInfo, ReqInfoVO requestInfo, List<LotVO> serviceLocationList, OrderVO orderInfo, List<MembershipVO> membershipList, PaymtCardVO paymentInfo) {
            this.txid = txid;
            this.hpNo = hpNo;
            this.carInfo = carInfo;
            this.requestInfo = requestInfo;
            this.serviceLocationList = serviceLocationList;
            this.orderInfo = orderInfo;
            this.membershipList = membershipList;
            this.paymentInfo = paymentInfo;
            setData(APIInfo.GRA_CHB_1010.getIfCd(), menuId);
        }
    }

    /**
     * @brief CHB_1010 응답 항목
     *
     * TODO 응답문 필요한 데이터 실데이터 확인 후 처리 필요.
     */
    @EqualsAndHashCode(callSuper = true)
    public @Data
    class Response extends BaseResponse {
    }
}
